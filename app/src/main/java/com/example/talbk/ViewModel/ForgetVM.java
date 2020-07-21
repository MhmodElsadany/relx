package com.example.talbk.ViewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiForget;
import com.example.talbk.Api.ApiUser;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetVM extends ViewModel {
    String phone;
    Context mContext;
    AlertDialog.Builder builder;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ProgressDialog pDialog;


    public LiveData<String> getResult(String phone, Context mContext, ProgressDialog pDialog) {
        this.phone = phone;
        this.mContext = mContext;
        this.pDialog = pDialog;
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<String>();

        }
        loadClient();

        return mClientsMutableLiveData;

    }

    private void loadClient() {
        pDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUser.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiForget api = retrofit.create(ApiForget.class);

        compositeDisposable.add(api.getProduct(phone)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        pDialog.dismiss();
                        mClientsMutableLiveData.setValue(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        pDialog.dismiss();
                        Toast.makeText(mContext, "من فضلك تاجد من وجود انترنت", Toast.LENGTH_SHORT).show();
                        mClientsMutableLiveData = null;

                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));


    }
}

