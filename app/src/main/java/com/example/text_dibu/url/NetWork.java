package com.example.text_dibu.url;

public interface NetWork {
    <T> void get(String url, HttpCallBack<T> callBack);
}

