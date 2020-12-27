package com.example.text_dibu.contract;

import com.example.text_dibu.bean.HomeBean;
import com.example.text_dibu.url.HttpCallBack;

public class MainContract {
    public interface getBannerView{
        void onNext(HomeBean homeBean);
        void onFail(String err);
    }

    public interface getBannerPresenter{
        void getBanner();
    }

    public interface getBannerModel{
        <T> void getBannerModel(String url, HttpCallBack<T> callBack);
    }

    public interface getChannilView{
        void onNext(HomeBean homeBean);
        void onFail(String err);
    }

    public interface getChannilPresenter{
        void getChannil();
    }

    public interface getChannilModel{
        <T> void getChannilModel(String url, HttpCallBack<T> callBack);
    }
}

