package com.yuezhu.yuenews.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.widget
 * @class describe: 首页按钮
 * @author: yuezhusust
 * @time 2017/4/17 17:33
 */

public class SplashButton extends View {
    //3种外形模式：圆角弧形、圆弧、直角弧形
    public final static int SHAPE_ROUND_RECT = 101;
    public final static int SHAPE_ARC = 102;
    public final static int SHAPE_RECT = 103;
    //类型模式：正常、可选中、图标消失选中、图标选中切换
    public final static int MODE_NORMAL = 201;
    public final static int MODE_CHECK = 202;
    public final static int MODE_ICON_CHECK_INVISIBLE = 203;
    public final static int MODE_ICON_CHECK_CHANGE = 204;

    //显示外形
    private int mTagShape = SHAPE_ROUND_RECT;
    //显示类型
    private int mTagMode = MODE_NORMAL;
    //画笔
    private Paint mPaint;
    //背景色
    private int mBgColor = Color.WHITE;
    //边框颜色
    private int mBorderColor = Color.parseColor("#ff33333");
    //原始标签颜色
    private int mTextColor = Color.parseColor("#ff666666");
    //选中状态背景色
    private int mBgColorChecked = Color.WHITE;
    //选中状态边框颜色
    private int mBorderColorChecked = Color.parseColor("#ff333333");
    //选中状态字体颜色
    private int mTextColorChecked = Color.parseColor("#ff666666");
    //遮罩颜色
    private int mScrimColor = Color.argb(0x66,0xc0,0xc0,0xc0);
    //字体大小
    private float mTextSize;
    //字体宽带和高度
    private int mFontLen;
    private int mFontH;
    private int mFontLenChecked;
    //极限偏移距离
    private float mBaseLineDistance;
    //边框大小
    private float mBorderWidth;
    //边框角半径
    private float mRadius;
    //内容
    private String mText;
    //选中时内容
    private String mTextChecked;
    //显示的文字
    private String mShowText;
    //字体水平空隙
    private int mHorizontalPadding;
    //字体垂直空隙
    private int mVerticalPadding;
    //边框矩形
    private RectF mRect;
    //装饰的icon
    private Drawable mDecorateIcon ;
    //变化模式下的icon
    private Drawable mDecorateIconChange;
    //设置图片的位置，只支持左右两边
    private int mIconGravity = Gravity.START;
    //icon和文字间距
    private int mIconPadding = 0;
    // icon大小
    private int mIconSize = 0;
    //是否选中
    private boolean mIsChecked = false;
    //是否自动切换状态
    private boolean mIsAutoToggleCheck = false;
    //是否被按住
    private boolean mIsPressed = false;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public SplashButton(Context context) {
       this(context,null);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p>
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(Context, AttributeSet, int)
     */
    public SplashButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
    }
}
