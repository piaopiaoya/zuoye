package com.example.text_dibu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.text_dibu.R;
import com.example.text_dibu.bean.HomeBean;

import java.util.ArrayList;

public class BrandAdapter extends DelegateAdapter.Adapter<BrandAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HomeBean.DataBean.BrandListBean> list;
    private GridLayoutHelper gridLayoutHelper;


    public BrandAdapter(Context context, ArrayList<HomeBean.DataBean.BrandListBean> list, GridLayoutHelper gridLayoutHelper) {
        this.context = context;
        this.list = list;
        this.gridLayoutHelper = gridLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.brand_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getNew_pic_url()).into(holder.ivLe);
//        Glide.with(context).load(list.get(position).getApp_list_pic_url()).into(holder.ivRi);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivLe;
//        private ImageView ivRi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLe = (ImageView) itemView.findViewById(R.id.iv_le);
//            ivRi = (ImageView) itemView.findViewById(R.id.iv_ri);
        }
    }
}

