package com.example.text_dibu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.text_dibu.R;
import com.example.text_dibu.bean.HomeBean;
import com.example.text_dibu.contract.MainContract;
import com.example.text_dibu.presenter.BannerPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements MainContract.getBannerView {

    private Banner banner;
    private BannerPresenter bannerPresenter;
    private ArrayList<HomeBean.DataBean.BannerBean> bannerBeans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        bannerPresenter = new BannerPresenter(this);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        bannerPresenter.getBanner();
    }

    private void initView(View view) {
        banner = view.findViewById(R.id.banner);
        bannerBeans = new ArrayList<>();
    }


    @Override
    public void onNext(HomeBean homeBean) {
        bannerBeans.addAll(homeBean.getData().getBanner());
        banner.setImages(homeBean.getData().getBanner());
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean db = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(db.getImage_url()).into(imageView);
            }
        }).start();
    }

    @Override
    public void onFail(String err) {
        Log.d("TAG","banner——v错误信息："+err);
    }
}

