package com.example.text_dibu.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.text_dibu.R;
import com.example.text_dibu.adapter.BrandAdapter;
import com.example.text_dibu.adapter.NetGoodAdapter;
import com.example.text_dibu.adapter.NewAdapter;
import com.example.text_dibu.adapter.TuiAdapter;
import com.example.text_dibu.adapter.GridAdapter;
import com.example.text_dibu.adapter.RvAdapter;
import com.example.text_dibu.bean.HomeBean;
import com.example.text_dibu.contract.MainContract;
import com.example.text_dibu.presenter.BannerPresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MainContract.getBannerView {

    private Banner banner;
    private BannerPresenter bannerPresenter;
    private ArrayList<HomeBean.DataBean.BannerBean> bannerBeans;
    private RecyclerView rv;
    private RvAdapter rvAdapter;
    private GridAdapter gridAdapter;
    private ArrayList<HomeBean.DataBean.ChannelBean> channelBeans;
    private ArrayList<HomeBean.DataBean.BrandListBean> brandListBeans;
    private TuiAdapter tuiAdapter;
    private DelegateAdapter delegateAdapter;
    private VirtualLayoutManager virtualLayoutManager;
    private BrandAdapter brandAdapter;
    private NewAdapter newAdapter;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> newGoodsListBeans;
    private NetGoodAdapter netGoodAdapter;

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

    private void initData() {
        bannerPresenter.getBanner();
    }

    private void initVLayout(View view) {
        rv = view.findViewById(R.id.rv);
        virtualLayoutManager = new VirtualLayoutManager(getActivity());
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        rv.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0, 15);

        //第一行  banner
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        singleLayoutHelper.setBgColor(Color.WHITE);
        rvAdapter = new RvAdapter(bannerBeans, getActivity(), singleLayoutHelper);

        //第二行
        //设置Grid布局
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
// 在构造函数设置每行的网格个数
// 公共属性
        gridLayoutHelper.setItemCount(5);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(5);// 设置设置布局内每行布局的宽与高的比
// gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{20, 20, 20, 20, 20});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格
        gridAdapter = new GridAdapter(getActivity(), channelBeans, gridLayoutHelper);

        //第三行
        SingleLayoutHelper sing = new SingleLayoutHelper();
        // 公共属性
        sing.setItemCount(1);// 设置布局里Item个数
        sing.setPadding(10, 10, 10, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        sing.setMargin(10, 10, 10, 10);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        sing.setBgColor(Color.WHITE);// 设置背景颜色
        sing.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        tuiAdapter = new TuiAdapter(getActivity(), sing);

        //第四行
        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(2);
        gridLayoutHelper1.setItemCount(2);// 设置布局里Item个数gridLayoutHelper1.setBgColor(Color.WHITE);// 设置背景颜色
        gridLayoutHelper1.setAspectRatio(4);// 设置设置布局内每行布局的宽与高的比
        gridLayoutHelper1.setWeights(new float[]{50,50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper1.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper1.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper1.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper1.setSpanCount(2);// 设置每行多少个网格
        brandAdapter = new BrandAdapter(getActivity(), brandListBeans, gridLayoutHelper1);

        //第五行
        SingleLayoutHelper newGood = new SingleLayoutHelper();
        // 公共属性
        newGood.setItemCount(1);// 设置布局里Item个数
        newGood.setPadding(10, 10, 10, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        newGood.setMargin(10, 10, 10, 10);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        newGood.setBgColor(Color.WHITE);// 设置背景颜色
        newGood.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        newAdapter = new NewAdapter(getActivity(), newGood);


        //第六行
        GridLayoutHelper netGood = new GridLayoutHelper(2);
        netGood.setItemCount(2);// 设置布局里Item个数gridLayoutHelper1.setBgColor(Color.WHITE);// 设置背景颜色
        netGood.setAspectRatio(4);// 设置设置布局内每行布局的宽与高的比
        netGood.setWeights(new float[]{50,50});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        netGood.setVGap(20);// 控制子元素之间的垂直间距
        netGood.setHGap(20);// 控制子元素之间的水平间距
        netGood.setAutoExpand(false);//是否自动填充空白区域
        netGood.setSpanCount(2);// 设置每行多少个网格
        netGoodAdapter = new NetGoodAdapter(getActivity(), newGoodsListBeans, netGood);

        


        initAddAdapter();
    }

    private void initAddAdapter() {
        delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        delegateAdapter.addAdapter(rvAdapter);
        delegateAdapter.addAdapter(gridAdapter);
        delegateAdapter.addAdapter(tuiAdapter);
        delegateAdapter.addAdapter(brandAdapter);
        delegateAdapter.addAdapter(newAdapter);
        delegateAdapter.addAdapter(netGoodAdapter);
        rv.setLayoutManager(virtualLayoutManager);
        rv.setAdapter(delegateAdapter);
    }


    private void initView(View view) {
        banner = view.findViewById(R.id.banner);
        bannerBeans = new ArrayList<>();
        channelBeans = new ArrayList<>();
        brandListBeans = new ArrayList<>();
        newGoodsListBeans = new ArrayList<>();

    }

    @Override
    public void onNext(HomeBean homeBean) {
        bannerBeans.addAll(homeBean.getData().getBanner());
        rvAdapter.notifyDataSetChanged();
        List<HomeBean.DataBean.ChannelBean> channel = homeBean.getData().getChannel();
        channelBeans.addAll(channel);
        gridAdapter.notifyDataSetChanged();
        initAddAdapter();
        List<HomeBean.DataBean.BrandListBean> brandList = homeBean.getData().getBrandList();
        brandListBeans.addAll(brandList);
        brandAdapter.notifyDataSetChanged();
        newAdapter.notifyDataSetChanged();
        List<HomeBean.DataBean.NewGoodsListBean> newGoodsList = homeBean.getData().getNewGoodsList();
        newGoodsListBeans.addAll(newGoodsList);
        netGoodAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String err) {
        Log.d("TAG", "banner——v错误信息：" + err);
    }
}

