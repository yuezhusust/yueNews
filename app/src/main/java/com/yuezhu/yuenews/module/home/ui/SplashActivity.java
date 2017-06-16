package com.yuezhu.yuenews.module.home.ui;

import android.content.Intent;

import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.module.base.BaseActivity;
import com.yuezhu.yuenews.utils.RxHelper;
import com.yuezhu.yuenews.widget.SplashButton;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

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
        RxHelper.countdown(5).compose(this.<Integer>bindToLife()).subscribe(
                new Subscriber() {
                    @Override
                    public void onCompleted() {
                        doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        doSkip();
                    }

                    @Override
                    public void onNext(Object o) {
                        splashButton.setText("跳过" + o);
                    }

                }
        );

    }

    private void doSkip() {
        if (!mIsSkip){
            mIsSkip=true;
            finish();
            startActivity(new Intent(SplashActivity.this,HomeActivity.class));
            overridePendingTransition(R.anim.hold,R.anim.zoom_in_exit);
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @OnClick(R.id.sb_skip)
    public void onClick() {
        doSkip();
    }
}
