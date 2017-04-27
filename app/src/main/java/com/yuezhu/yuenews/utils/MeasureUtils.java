package com.yuezhu.yuenews.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.utils
 * @class describe: 测量工具类
 * @author: yuezhusust
 * @time 2017/4/19 18:19
 */

public class MeasureUtils extends Object {
    private MeasureUtils() {
        throw new AssertionError();
    }

    /**
     * @param:
     * @return:
     * @desc : dp转px
     * @author :yuezhusust
     * @time :2017/4/19  18:32
     */
    public static float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * @param:
     * @return:
     * @desc : sp转px
     * @author :yuezhusust
     * @time :2017/4/19  18:36
     */

    public static float sp2px(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * @param i
     * @return
     * @desc 获取测量的宽间距
     * @author yuezhusust
     * @time 2017/4/19  18:59
     */
    public static int getMeasuredWidthWithMargins(View child) {
        final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
    }

    /**
     * @param i
     * @return
     * @desc 获取显示的信息
     * @author yuezhusust
     * @time 2017/4/27  11:11
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }
    /**
     *  @param
     *  @return 
     *  @desc 获取view的位置
     *  @author yuezhusust
     *  @time 2017/4/27  11:15
     */
    public static int[] getViewLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }


}
