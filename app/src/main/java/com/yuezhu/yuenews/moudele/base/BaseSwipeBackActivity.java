package com.yuezhu.yuenews.moudele.base;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.moudele.base
 * @class describe:
 * @author: yuezhusust
 * @time 2017/6/6 15:26
 */

public abstract class BaseSwipeBackActivity <T extends  IBasePresenter>extends BaseActivity <T> {
  private SwipeBackLayout mSwipeBackLayout;
}
