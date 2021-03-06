package com.example.text_dibu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.text_dibu.R;
import com.example.text_dibu.bean.HomeBean;

import java.util.ArrayList;

public class HotGoodAdapter extends DelegateAdapter.Adapter<HotGoodAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> list;
    private GridLayoutHelper gridLayoutHelper;

    public HotGoodAdapter(Context context, ArrayList<HomeBean.DataBean.HotGoodsListBean> list, GridLayoutHelper gridLayoutHelper) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.hotgood_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvBrief.setText(list.get(position).getGoods_brief());
        holder.tvPrice.setText("￥"+list.get(position).getRetail_price());
        Glide.with(context).load(list.get(position).getList_pic_url()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvName;
        private TextView tvBrief;
        private TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvBrief = (TextView) itemView.findViewById(R.id.tv_brief);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}

