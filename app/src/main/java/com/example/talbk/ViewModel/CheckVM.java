package com.example.talbk.ViewModel;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiCheck;
import com.example.talbk.Api.ApiSignup;
import com.example.talbk.Model.Check;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckVM extends ViewModel {
    Context mContext;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ProgressDialog pDialog;

    public LiveData<String> getResult(Check check, Context mContext, ProgressDialog pDialog) {
        this.mContext = mContext;
        this.pDialog = pDialog;

        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<String>();
            //loadData(user);

        }
        loadData(check);
        return mClientsMutableLiveData;

    }

    private void loadData(Check check) {
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSignup.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiCheck api = retrofit.create(ApiCheck.class);

        compositeDisposable.add(api.createCheck(check)
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

                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));

    }
}

