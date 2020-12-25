package com.example.text_dibu.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.text_dibu.R;
import com.example.text_dibu.adapter.RvAdapter;
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
    private RecyclerView rv;
    private RvAdapter rvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        bannerPresenter = new BannerPresenter(this);
        initView(view);
        initData();
        initVLayout(view);
        return view;
    }

    private void initVLayout(View view) {
        rv = view.findViewById(R.id.rv);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getActivity());
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        rv.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0,10);

        //第一行
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setPadding(20,20,20,20);
        singleLayoutHelper.setMargin(20,20,20,20);
        singleLayoutHelper.setBgColor(Color.WHITE);
        singleLayoutHelper.setAspectRatio(6);
        rvAdapter = new RvAdapter(bannerBeans,getActivity(), singleLayoutHelper);

        //第二行  banner



        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        delegateAdapter.addAdapter(rvAdapter);
        rv.setLayoutManager(virtualLayoutManager);
        rv.setAdapter(delegateAdapter);
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
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String err) {
        Log.d("TAG","banner——v错误信息："+err);
    }
}

