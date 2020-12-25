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
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.text_dibu.R;
import com.example.text_dibu.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RvAdapter extends DelegateAdapter.Adapter<RvAdapter.ViewHolder> {
    private ArrayList<HomeBean.DataBean.BannerBean> list;
    private Context context;
    private SingleLayoutHelper singleLayoutHelper;

    public RvAdapter(ArrayList<HomeBean.DataBean.BannerBean> list, Context context, SingleLayoutHelper singleLayoutHelper) {
        this.list = list;
        this.context = context;
        this.singleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.column_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.banner.setImages(list);
        holder.banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean db = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(db.getImage_url()).into(imageView);
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }
}

