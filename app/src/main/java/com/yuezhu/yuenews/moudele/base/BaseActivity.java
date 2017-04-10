package com.yuezhu.yuenews.moudele.base;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.widget.EmptyLayout;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.moudele.base
 * @class describe:
 * @author: yuezhusust
 * @time 2017/3/31 16:43
 */

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView {
    /**
     * 把emptylayout放在基类统一处理
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout emptyLayout;
    /**
     * Inject 注入
     */
    @Inject
    protected T mPresenter;
    @Nullable
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * @param:
     * @return:
     * @desc : 绑定布局文件
     * @author :yuezhusust
     * @time :2017/4/10  15:27
     */
    protected abstract int attachLayoutRes();

    /**
     * @param:
     * @return:
     * @desc : Dagger 注入
     * @author :yuezhusust
     * @time :2017/4/10  16:51
     */
    protected abstract void initInjector();

    /**
     * @param:
     * @return:
     * @desc : 初始化视图
     * @author :yuezhusust
     * @time :2017/4/10  17:00
     */
    protected abstract void initViews();

    /**
     * @param:
     * @return:
     * @desc : 更新视图控件
     * @author :yuezhusust
     * @time :2017/4/10  17:20
     */
    protected abstract void updateView(boolean isRefresh);

    /**
     * @return
     * @desc : 隐藏loading界面接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:15
     */
    @Override
    public void hideLoading() {

    }

    /**
     * @return
     * @desc : 展示loading界面接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:15
     */
    @Override
    public void showLoading() {

    }

    /**
     * @return
     * @desc :完成刷新事件接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:18
     */
    @Override
    public void finishRefresh() {

    }

    /**
     * @return <T>
     * @desc : 绑定生命周期接口
     * @author yuezhusust
     * @time 2017/3/30  14:20
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

    /**
     * @param:
     * @return:
     * @desc :显示网络错误
     * @author :yuezhusust
     * @time :2017/4/1  14:57
     */
    @Override
    public void showNetWorkError() {

    }
}
