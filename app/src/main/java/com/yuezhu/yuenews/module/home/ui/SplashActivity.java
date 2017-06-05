package com.yuezhu.yuenews.module.home.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.moudele.base.BaseActivity;
import com.yuezhu.yuenews.widget.SplashButton;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.sb_skip)
    SplashButton splashButton;
    private boolean mIsSkip = false;
    /**
     * @param:
     * @return:
     * @desc : 绑定布局文件
     * @author :yuezhusust
     * @time :2017/4/10  15:27
     */
    @Override
    protected int attachLayoutRes() {

        return R.layout.activity_splash;
    }

    /**
     * @param:
     * @return:
     * @desc : Dagger 注入
     * @author :yuezhusust
     * @time :2017/4/10  16:51
     */
    @Override
    protected void initInjector() {

    }

    /**
     * @param:
     * @return:
     * @desc : 初始化视图
     * @author :yuezhusust
     * @time :2017/4/10  17:00
     */
    @Override
    protected void initViews() {

    }

    /**
     * @param isRefresh
     * @param:
     * @return:
     * @desc : 更新视图控件
     * @author :yuezhusust
     * @time :2017/4/10  17:20
     */
    @Override
    protected void updateView(boolean isRefresh) {

    }
}
