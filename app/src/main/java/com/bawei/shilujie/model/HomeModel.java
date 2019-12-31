package com.bawei.shilujie.model;
/*
 *@auther:史陆杰
 *@Date: 2019/12/31
 *@Time:9:03
 *@Description:${DESCRIPTION}
 **/


import com.bawei.shilujie.contract.IHomeContract;
import com.bawei.shilujie.model.bean.HomeBean;
import com.bawei.shilujie.util.NetUtil;
import com.google.gson.Gson;

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(ModeCallBack modeCallBack) {
        NetUtil.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/news/ranking.json", new NetUtil.MyCallBack() {
            @Override
            public void getSuccess(String json) {
                HomeBean homeBean = new Gson().fromJson(json, HomeBean.class);
                modeCallBack.onSuccess(homeBean);
            }

            @Override
            public void getError(Throwable throwable) {
                modeCallBack.onFailer(throwable);
            }
        });
    }
}
