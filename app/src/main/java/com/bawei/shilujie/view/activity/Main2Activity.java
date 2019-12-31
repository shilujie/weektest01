package com.bawei.shilujie.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.shilujie.R;
import com.bawei.shilujie.base.BaseActivity;
import com.bawei.shilujie.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.wx)
    Button wx;
    @BindView(R.id.qq)
    Button qq;

    @Override
    protected void initData() {
        Bitmap bitmap = CodeUtils.createImage("史陆杰", 400, 400, null);
        img.setImageBitmap(bitmap);
    }

    @Override
    protected BasePresenter providerPersenter() {
        return null;
    }

    @Override
    protected void initView() {
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(img, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, ""+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "识别失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main2;
    }
    
    @OnClick({R.id.wx, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wx:
                EventBus.getDefault().post("微信");
                break;
            case R.id.qq:
                EventBus.getDefault().post("QQ");
                break;
        }
    }

    //土司
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GetZhi(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
