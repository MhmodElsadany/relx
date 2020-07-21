package com.example.talbk.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiProduct;
import com.example.talbk.Api.ApiSearchSub;
import com.example.talbk.Model.Product;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchSubVM extends ViewModel {
    Context mContext;
    private MutableLiveData<ArrayList<Product>> mproductMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String id;
    private String subid;
    ProgressBar pb;
    AlertDialog.Builder builder;

    public LiveData<ArrayList<Product>> getResult(Context mContext, String id, String subid, ProgressBar pb, AlertDialog.Builder builder) {
        this.mContext = mContext;
        this.id = id;
        this.subid = subid;
        this.pb = pb;
        this.builder = builder;

        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<ArrayList<Product>>();

        }
        loadData();
        return mproductMutableLiveData;

    }

    private void loadData() {
        pb.setVisibility(ProgressBar.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiProduct.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiSearchSub api = retrofit.create(ApiSearchSub.class);
        Log.i("xttttt",subid+"k"+id);

        compositeDisposable.add(api.getProduct(subid,id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Product>>() {

                    @Override
                    public void accept(ArrayList<Product> products) throws Exception {
                        Log.i("xttttt",products.size()+"");
                        if (products.size() <= 0) {
                            Toast.makeText(mContext, "عفوا لا يوجد منتجات بهذا الاسم ", Toast.LENGTH_SHORT).show();
                        }
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        mproductMutableLiveData.setValue(products);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        builder.setPositiveButton("حاول مره اخرى  ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                loadData();
                            }

                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                        Toast.makeText(mContext, "من فضلك تاكد من وجود انترنت", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));

    }
}


