package com.example.talbk.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiProduct;
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

public class ProductVM extends ViewModel {

    Context mContext;
    private MutableLiveData<ArrayList<Product>> mproductMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String id;
    ProgressBar pb;
    AlertDialog.Builder builder;
    String currentpage;
    View viewprogress;

    public LiveData<ArrayList<Product>> getResult
            (Context mContext, String id, ProgressBar pb, AlertDialog.Builder builder, View viewprogress, String currentpage) {
        this.mContext = mContext;
        this.id = id;
        this.pb = pb;
        this.builder = builder;
        this.viewprogress = viewprogress;
        this.currentpage = currentpage;

        mproductMutableLiveData = new MutableLiveData<ArrayList<Product>>();
        loadData();


        return mproductMutableLiveData;

    }

    private void loadData() {
        pb.setVisibility(ProgressBar.VISIBLE);
        viewprogress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiProduct.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiProduct api = retrofit.create(ApiProduct.class);

        compositeDisposable.add(api.getProduct(id, currentpage, "10")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Product>>() {

                    @Override
                    public void accept(ArrayList<Product> products) throws Exception {
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        viewprogress.setVisibility(View.INVISIBLE);
                        mproductMutableLiveData.setValue(products);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        viewprogress.setVisibility(View.INVISIBLE);
                        builder.setPositiveButton("حاول مره اخرى  ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                loadData();
                            }

                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                        Toast.makeText(mContext, "من فضلك تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));

    }
}

