package com.bawei.shilujie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.shilujie.base.BaseActivity;
import com.bawei.shilujie.base.BasePresenter;
import com.bawei.shilujie.contract.IHomeContract;
import com.bawei.shilujie.model.HomeModel;
import com.bawei.shilujie.model.bean.HomeBean;
import com.bawei.shilujie.presenter.HomePresenter;
import com.bawei.shilujie.util.NetUtil;
import com.bawei.shilujie.view.activity.Main2Activity;
import com.bawei.shilujie.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.rlv)
    RecyclerView rlv;

    @Override
    protected void initData() {
        boolean b = NetUtil.getInstance().hasNet(this);
        if (b){
            mPresenter.getHomeData();
        }else {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected HomePresenter providerPersenter() {
        return new HomePresenter();
    }


    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        List<HomeBean.RankingBean> ranking = homeBean.getRanking();
        MyAdapter myAdapter = new MyAdapter(ranking);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void getName(String name) {
                Toast.makeText(MainActivity.this, ""+name, Toast.LENGTH_SHORT).show();
            }
        });
        rlv.setAdapter(myAdapter);
    }

    @Override
    public void onFailer(Throwable throwable) {
        Log.e("TAG", "onFailer: 获取数据失败" );
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
}
