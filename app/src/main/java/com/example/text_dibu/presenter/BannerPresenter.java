package com.example.text_dibu.presenter;

import com.example.text_dibu.bean.HomeBean;
import com.example.text_dibu.contract.MainContract;
import com.example.text_dibu.fragment.HomeFragment;
import com.example.text_dibu.model.BannerModel;
import com.example.text_dibu.url.HttpCallBack;
import com.example.text_dibu.url.UrlUtil;

public class BannerPresenter implements MainContract.getBannerPresenter {
    private HomeFragment homeFragment;
    private final BannerModel bannerModel;

    public BannerPresenter(HomeFragment homeFragment) {
        bannerModel = new BannerModel();
        this.homeFragment = homeFragment;
    }

    @Override
    public void getBanner() {
        bannerModel.getBannerModel(UrlUtil.BANNER, new HttpCallBack<HomeBean>() {
            @Override
            public void OnNext(HomeBean homeBean) {
                homeFragment.onNext(homeBean);
            }

            @Override
            public void OnFail(String err) {
                homeFragment.onFail(err);
            }
        });
    }
}

