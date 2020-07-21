package com.example.talbk.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiOrders;
import com.example.talbk.Api.CategoryApi;
import com.example.talbk.Model.UserOrder;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderUsersVM extends ViewModel {

    Context mContext;
    private MutableLiveData<ArrayList<UserOrder>> mproductMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ProgressBar pb;
    AlertDialog.Builder builder;
    String id;

    public LiveData<ArrayList<UserOrder>> getResult(Context mContext, ProgressBar pb, AlertDialog.Builder builder, String id) {
        this.mContext = mContext;
        this.pb = pb;
        this.builder = builder;
        this.id = id;
        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<ArrayList<UserOrder>>();
            loadData();

        }
        return mproductMutableLiveData;

    }

    private void loadData() {
        pb.setVisibility(ProgressBar.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CategoryApi.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiOrders api = retrofit.create(ApiOrders.class);

        compositeDisposable.add(api.getOrders(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<UserOrder>>() {

                    @Override
                    public void accept(ArrayList<UserOrder> userOrders) throws Exception {
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        mproductMutableLiveData.setValue(userOrders);
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

