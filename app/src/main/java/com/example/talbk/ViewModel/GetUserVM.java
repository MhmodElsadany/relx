package com.example.talbk.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.talbk.Api.ApiGetUser;
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

public class GetUserVM extends ViewModel {
    String id;
    Context mContext;
    private MutableLiveData<User> mClientsMutableLiveData;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ProgressBar pb;
    AlertDialog.Builder builder;


    public LiveData<User> getResult(String id, Context mContext, ProgressBar pb, AlertDialog.Builder builder) {
        this.id = id;
        this.pb = pb;
        this.builder = builder;
        this.mContext = mContext;
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<User>();
        }
        loadClient();

        return mClientsMutableLiveData;

    }

    private void loadClient() {
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<User>();
        }
        pb.setVisibility(ProgressBar.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUser.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiGetUser api = retrofit.create(ApiGetUser.class);

        compositeDisposable.add(api.getUser(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {

                    @Override
                    public void accept(User s) throws Exception {
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        mClientsMutableLiveData.setValue(s);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        pb.setVisibility(ProgressBar.INVISIBLE);
                        builder.setPositiveButton("حاول مره اخرى  ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                loadClient();

                            }

                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                        Toast.makeText(mContext, "من فضلك تاكد من اتصالك بالانترنت ", Toast.LENGTH_SHORT).show();
                        mClientsMutableLiveData = null;

                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));


    }
}
