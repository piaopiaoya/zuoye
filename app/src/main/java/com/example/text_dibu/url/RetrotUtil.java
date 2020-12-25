package com.example.text_dibu.url;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrotUtil implements NetWork{
    private static volatile RetrotUtil resultUtil;
    private final ApiService apiService;

    public RetrotUtil() {

        Retrofit build = new Retrofit.Builder()
                .baseUrl(UrlUtil.BASEUTIL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = build.create(ApiService.class);
    }

    public static RetrotUtil getInstance() {
        if (resultUtil==null){
            synchronized (RetrotUtil.class){
                if (resultUtil==null){
                    resultUtil = new RetrotUtil();
                }
            }
        }
        return resultUtil;
    }

    @Override
    public <T> void get(String url, HttpCallBack<T> callBack) {
        apiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type t = actualTypeArguments[0];

                            callBack.OnNext(new Gson().fromJson(string,t));


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TAG","banner错误信息："+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

