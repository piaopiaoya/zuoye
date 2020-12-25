package com.example.text_dibu.url;

public interface HttpCallBack<T> {
    void OnNext(T t);
    void OnFail(String err);
}

