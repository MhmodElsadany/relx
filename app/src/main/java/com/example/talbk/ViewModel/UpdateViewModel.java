package com.example.talbk.ViewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiSignup;
import com.example.talbk.Api.ApiUpdate;
import com.example.talbk.Model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateViewModel extends ViewModel {
    Context mContext;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String oldphone;
    ProgressDialog pDialog;

    public LiveData<String> getResult(User user, String oldphone, Context mContext, ProgressDialog pDialog) {
        this.mContext = mContext;
        this.pDialog = pDialog;
        this.oldphone = oldphone;
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<String>();

        }
        loadData(user);
        return mClientsMutableLiveData;

    }

    private void loadData(User user) {
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSignup.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiUpdate api = retrofit.create(ApiUpdate.class);

        compositeDisposable.add(api.updateUser(user)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        pDialog.dismiss();

                        if (s.equals("Changed Saved But Number No ...")) {
                            if (user.getPhone().equals(oldphone)) {
                                Toast.makeText(mContext, " تم التعديل بنجاح  ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, " خطأ! رقم الهاتف مستخدم بالفعل من قبل  ", Toast.LENGTH_SHORT).show();

                            }
                        } else if (s.equals("Done")) {
                            Toast.makeText(mContext, " تم التعديل بنجاح ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "oops sorry error happend", Toast.LENGTH_SHORT).show();

                        }
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

