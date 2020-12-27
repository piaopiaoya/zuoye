package com.example.text_dibu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.text_dibu.R;
import com.example.text_dibu.bean.HomeBean;

import java.util.ArrayList;

public class RvListAdapter extends RecyclerView.Adapter<RvListAdapter.ViewHolder> {
    private ArrayList<HomeBean.DataBean.TopicListBean> list;
    private Context context;


    public RvListAdapter(ArrayList<HomeBean.DataBean.TopicListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvlist_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.subtitle.setText(list.get(position).getSubtitle());
        holder.tvPrice.setText("￥"+list.get(position).getPrice_info()+"元起");
        Glide.with(context).load(list.get(position).getItem_pic_url()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView title;
        private TextView tvPrice;
        private TextView subtitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            title = (TextView) itemView.findViewById(R.id.title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }
}

