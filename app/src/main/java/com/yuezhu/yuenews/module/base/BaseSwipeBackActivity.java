package com.yuezhu.yuenews.module.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yuezhu.yuenews.widget.SwipeBackLayout;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.moudele.base
 * @class describe:
 * @author: yuezhusust
 * @time 2017/6/6 15:26
 */
/**
 * Created by long on 2017/1/19.
 * 滑动退出Activity，参考：https://github.com/ikew0ng/SwipeBackLayout
 */
public abstract class BaseSwipeBackActivity<T extends IBasePresenter> extends BaseActivity<T> {
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mSwipeBackLayout = new SwipeBackLayout(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeBackLayout.attachToActivity(this, SwipeBackLayout.EDGE_LEFT);
        //触摸边缘变为屏幕宽的的1/2
        mSwipeBackLayout.setEdgeSize(getResources().getDisplayMetrics().widthPixels/2);
    }
    public SwipeBackLayout getSwipeBackLayout(){
        return mSwipeBackLayout;
    }
}
