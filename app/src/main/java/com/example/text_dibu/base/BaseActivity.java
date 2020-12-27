package com.example.text_dibu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    public P presenter;
    private P p1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        if (presenter == null){
            createPresenter();
        }
        initView(savedInstanceState);
        initData();

    }

    public void createPresenter() {
        Type[] genericInterfaces = getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        Class<P> p = (Class<P>) actualTypeArguments[0];
        try {
            p1 = p.newInstance();
            p1.attachView(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);


    protected abstract int getLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.detachView();
        }
    }
}

