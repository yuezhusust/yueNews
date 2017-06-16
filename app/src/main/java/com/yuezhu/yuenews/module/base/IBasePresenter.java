package com.yuezhu.yuenews.module.base;

/**
 * @name YueNews
 * @class name：${CLASS_NAME}
 * @class describe:
 * @author: yuezhusust
 * @time 2017/3/31 15:25
 */

public interface IBasePresenter {
    /**
     * @param isRefresh,Boolean型，判断是否刷新界面
     * @return
     * @desc :  从网络获取数据,更新界面
     * @author yuezhusust
     * @time 2017/3/31  15:30
     */

    void getData(Boolean isRefresh);

    /**
     * @param
     * @return
     * @desc : 获取更多数据
     * @author yuezhusust
     * @time 2017/3/31  15:31
     */

    void getMoreData();

}
