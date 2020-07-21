package com.example.talbk.ViewModel;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiUser;
import com.example.talbk.Model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserViewModel extends ViewModel {
    String username, password;
    Context mContext;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ProgressDialog pDialog;

    public LiveData<String> getResult(String username, String password, Context mContext, ProgressDialog pDialog) {
        this.username = username;
        this.password = password;
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


        ApiUser api = retrofit.create(ApiUser.class);

        compositeDisposable.add(api.checkUser(new User(username, password))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        pDialog.dismiss();

                        if (s.equals("Failed")) {
                            Toast.makeText(mContext, "خطا فى رقم الهاتف او كلمة المرور", Toast.LENGTH_SHORT).show();
                            mClientsMutableLiveData.setValue(s);
                        } else {

                            Log.i("غغغععتتى", s);
                            Toast.makeText(mContext, "تم تسجيلك بنجاح", Toast.LENGTH_SHORT).show();
                            mClientsMutableLiveData.setValue(s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        pDialog.dismiss();
                        Toast.makeText(mContext, "من فضلك تاكد با اتصالك بالانترنت ", Toast.LENGTH_SHORT).show();
                        mClientsMutableLiveData = null;

                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));


    }
}
