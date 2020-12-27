package com.example.text_dibu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.text_dibu.R;
import com.example.text_dibu.bean.HomeBean;

import java.util.ArrayList;

public class TopicAdapter extends DelegateAdapter.Adapter<TopicAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HomeBean.DataBean.TopicListBean> list;
    private SingleLayoutHelper singleLayoutHelper;


    public TopicAdapter(Context context, ArrayList<HomeBean.DataBean.TopicListBean> list, SingleLayoutHelper singleLayoutHelper) {
        this.context = context;
        this.list = list;
        this.singleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topic_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        holder.rv.setLayoutManager(linearLayoutManager);
        RvListAdapter rvListAdapter = new RvListAdapter(list, context);
        holder.rv.setAdapter(rvListAdapter);
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.rv);
        }
    }
}

