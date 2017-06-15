package com.yuezhu.yuenews.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.utils
 * @class describe: RX java帮助类
 * @author: yuezhusust
 * @time 2017/6/5 16:02
 */

public class RxHelper {
    private RxHelper() {
        throw new AssertionError();
    }

    /**
     * @methodName: countdown
     * @desc: 倒计时
     * @param: [time]
     * @return: rx.Observable<java.lang.Integer>
     * @author: yuezhusust
     * @time: 2017/6/5  16:27
     */
    public static Observable<Integer> countdown(int time) {

        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS).map(new Func1<Long, Integer>() {
            @Override
            public Integer call(Long aLong) {
                return countTime - aLong.intValue();
            }
        }).take(countTime + 1).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
    }

}
