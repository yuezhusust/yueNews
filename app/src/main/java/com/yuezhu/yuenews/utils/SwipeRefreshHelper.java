package com.yuezhu.yuenews.utils;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.utils
 * @class describe: 下拉刷新帮助类
 * @author: yuezhusust
 * @time 2017/4/11 11:20
 */

/**
 * @author :yuezhusust
 * @param:
 * @return:
 * @desc : 初始化，关联AppBarLayout,解决滑动过程的冲突
 * @time :2017/4/11  17:07
 */
public class SwipeRefreshHelper {
    public static void init(final SwipeRefreshLayout swipeRefreshLayout, AppBarLayout appBar, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }
    /**
     *  @param:
     *  @return:
     *  @desc : 初始化
     *  @author :yuezhusust
     *  @time :2017/4/11  17:22
     */
    public static void init(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener listener) {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(listener);
    }

}
