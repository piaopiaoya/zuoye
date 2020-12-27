package com.example.text_dibu.base;

public abstract class BasePresenter<V extends BaseView,M extends BaseModel> {
    private V v;
    private V iView;
    private M iModel;

    public void attachView(V v){
        iModel = getiModel();
        this.v = v;
    }

    protected abstract M getiModel();

    public void detachView(){
        iView = null;
        iModel = null;
    }

}

