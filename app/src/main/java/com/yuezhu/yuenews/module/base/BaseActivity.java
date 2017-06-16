package com.yuezhu.yuenews.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.utils.SwipeRefreshHelper;
import com.yuezhu.yuenews.widget.EmptyLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import injector.components.ApplicationComponent;
import injector.modules.ActivityModule;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        this.initInjector();
        this.initViews();
        initSwipeRefresh();
        this.updateView(false);
    }


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
        if (emptyLayout != null) {
            emptyLayout.hideView();
        }
    }

    /**
     * @return
     * @desc : 展示loading界面接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:15
     */
    @Override
    public void showLoading() {
        if (emptyLayout != null) {
            emptyLayout.setmCurrentState(EmptyLayout.STATE_LOADING);
        }

    }

    /**
     * @return
     * @desc :完成刷新事件接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:18
     */
    @Override
    public void finishRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    /**
     * @return <T>
     * @desc : 绑定生命周期接口
     * @author yuezhusust
     * @time 2017/3/30  14:20
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    /**
     * @param:
     * @return:
     * @desc :显示网络错误
     * @author :yuezhusust
     * @time :2017/4/1  14:57
     */
    @Override
    public void showNetWorkError(EmptyLayout.RetryListener retryListener) {
        if (emptyLayout != null) {
            emptyLayout.setmCurrentState(EmptyLayout.STATE_NO_NET);
            emptyLayout.setmRetyrListener(retryListener);

        }
    }

    /**
     * @param:
     * @return:
     * @desc :初始化下拉刷新
     * @author :yuezhusust
     * @time :2017/4/11  17:40
     */
    private void initSwipeRefresh() {
        if (mSwipeRefreshLayout != null) {
            SwipeRefreshHelper.init(mSwipeRefreshLayout, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateView(true);
                }
            });
        }

    }

    /**
     * @param:
     * @return: ApplicationComponent
     * @desc :
     * @author :yuezhusust
     * @time :2017/4/14  15:06
     */
    protected ApplicationComponent getAppComponent() {
        return null;
    }

    /**
     * @param:
     * @return: ActivityModule
     * @desc :
     * @author :yuezhusust
     * @time :2017/4/14  15:06
     */
    protected ActivityModule getActivityModule() {
        return null;
    }

    /**
     * @param:
     * @return:
     * @desc : 初始化ToolBar
     * @author :yuezhusust
     * @time :2017/4/17  9:50
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    /**
     * @param:
     * @return:
     * @desc :  添加fragment方法
     * @author :yuezhusust
     * @time :2017/4/17  10:38
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * @param:
     * @return:
     * @desc : 添加fragment
     * @author :yuezhusust
     * @time :2017/4/17  10:56
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * @param:
     * @return:
     * @desc : 替换fragment
     * @author :yuezhusust
     * @time :2017/4/17  11:11
     */
    protected void replaceFragment(int containViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    protected void replaceFragment(int containViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(containViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();

        } else {
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}