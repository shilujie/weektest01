package com.bawei.shilujie.contract;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:9:04
 *@Description:${DESCRIPTION}
 **/


import com.bawei.shilujie.model.bean.HomeBean;

public interface IHomeContract {
    interface IView{
        void onSuccess(HomeBean homeBean);
        void onFailer(Throwable throwable);
    }

    interface IPresenter{
        void getHomeData();
    }

    interface IModel{

        void getHomeData(ModeCallBack modeCallBack);

        interface ModeCallBack{
            void onSuccess(HomeBean homeBean);
            void onFailer(Throwable throwable);
        }
    }
}
