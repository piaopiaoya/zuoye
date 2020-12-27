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
            presenter = add();
            presenter.attachView(this);
        }
        initView(savedInstanceState);
        initData();

    }

    protected abstract P add();

    protected abstract void createPresenter();


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

