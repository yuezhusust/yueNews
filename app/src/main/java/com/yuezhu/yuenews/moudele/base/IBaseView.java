package com.yuezhu.yuenews.moudele.base;

import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.moudele.base
 * @class describe: 基础 BaseView 接口
 * @author: yuezhusust
 * @time 2017/3/30 14:05
 */

public interface IBaseView {
    /**
     * @param
     * @return
     * @desc : 隐藏loading界面接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:15
     */
    void hideLoading();

    /**
     * @param
     * @return
     * @desc : 展示loading界面接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:15
     */
    void showLoading();

    /**
     * @param
     * @return
     * @desc :完成刷新事件接口方法
     * @author yuezhusust
     * @time 2017/3/30  14:18
     */
    void finishRefresh();

    /**
     * @param <T>
     * @return <T>
     * @desc : 绑定生命周期接口
     * @author yuezhusust
     * @time 2017/3/30  14:20
     */
    <T> LifecycleTransformer<T> bindToLife();
    /**
     *  @param:
     *  @return:
     *  @desc :显示网络错误
     *  @author :yuezhusust
     *  @time :2017/4/1  14:57
     */
    void showNetWorkError();
}
