package com.example.text_dibu.model;

import com.example.text_dibu.contract.MainContract;
import com.example.text_dibu.url.HttpCallBack;
import com.example.text_dibu.url.RetrotUtil;

public class BannerModel implements MainContract.getBannerModel {
    @Override
    public <T> void getBannerModel(String url, HttpCallBack<T> callBack) {
        RetrotUtil.getInstance().get(url,callBack);
    }
}

