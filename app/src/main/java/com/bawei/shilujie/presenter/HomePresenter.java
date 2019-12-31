package com.bawei.shilujie.presenter;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:9:06
 *@Description:${DESCRIPTION}
 **/


import com.bawei.shilujie.base.BasePresenter;
import com.bawei.shilujie.contract.IHomeContract;
import com.bawei.shilujie.model.HomeModel;
import com.bawei.shilujie.model.bean.HomeBean;

public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {
    private HomeModel homeModel;
    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData() {
        homeModel.getHomeData(new IHomeContract.IModel.ModeCallBack() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                view.onSuccess(homeBean);
            }

            @Override
            public void onFailer(Throwable throwable) {
                view.onFailer(throwable);
            }
        });
    }
}
