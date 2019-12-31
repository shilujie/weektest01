package com.bawei.shilujie.base;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:8:57
 *@Description:${DESCRIPTION}
 **/


public abstract class BasePresenter<V> {
    public V view;

    public BasePresenter() {
        initModel();
    }

    public void attach(V view) {
        this.view = view;
    }
    public void detach() {
            view = null;
}

    protected abstract void initModel();
}
