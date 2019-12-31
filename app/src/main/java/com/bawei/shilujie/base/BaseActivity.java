package com.bawei.shilujie.base;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:8:58
 *@Description:${DESCRIPTION}
 **/


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        ButterKnife.bind(this);
        mPresenter = providerPersenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    protected abstract void initData();

    protected abstract P providerPersenter();

    protected abstract void initView();

    protected abstract int layoutid();
}
