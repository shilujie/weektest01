package com.bawei.shilujie.view.adapter;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:9:18
 *@Description:${DESCRIPTION}
 **/


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shilujie.R;
import com.bawei.shilujie.model.bean.HomeBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoulder> {
    private List<HomeBean.RankingBean> ranking;

    public MyAdapter(List<HomeBean.RankingBean> ranking) {

        this.ranking = ranking;
    }

    @NonNull
    @Override
    public MyViewHoulder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.layout_item, null);
        return new MyViewHoulder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoulder holder, int position) {
        Glide.with(holder.img)
                .load(ranking.get(position).getAvatar())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.img);
        holder.name.setText(ranking.get(position).getName());
        holder.ids.setText(ranking.get(position).getRank());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.getName(ranking.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    class MyViewHoulder extends RecyclerView.ViewHolder {
        @BindView(R.id.ids)
        TextView ids;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.name)
        TextView name;

        public MyViewHoulder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void getName(String name);
    }
}
