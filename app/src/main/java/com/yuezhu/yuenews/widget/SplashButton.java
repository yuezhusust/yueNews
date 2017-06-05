package com.yuezhu.yuenews.widget;

import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.utils.MeasureUtils;

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
    private int mBorderColor = Color.parseColor("#ff333333");
    //原始标签颜色
    private int mTextColor = Color.parseColor("#ff666666");
    //选中状态背景色
    private int mBgColorChecked = Color.WHITE;
    //选中状态边框颜色
    private int mBorderColorChecked = Color.parseColor("#ff333333");
    //选中状态字体颜色
    private int mTextColorChecked = Color.parseColor("#ff666666");
    //遮罩颜色
    private int mScrimColor = Color.argb(0x66, 0xc0, 0xc0, 0xc0);
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
    private Drawable mDecorateIcon;
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
        this(context, null);
    }


    public SplashButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mBorderWidth = MeasureUtils.dp2px(context, 0.5f);
        mRadius = MeasureUtils.dp2px(context, 5f);
        mHorizontalPadding = (int) MeasureUtils.dp2px(context, 5f);
        mVerticalPadding = (int) MeasureUtils.dp2px(context, 5f);
        mIconPadding = (int) MeasureUtils.dp2px(context, 3f);
        mTextSize = MeasureUtils.sp2px(context, 14f);
        if (attrs != null) {
            final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SplashButton);
            try {
                mTagShape = typedArray.getInteger(R.styleable.SplashButton_spb_mode, MODE_NORMAL);
                if (mTagMode == MODE_CHECK || mTagMode == MODE_ICON_CHECK_INVISIBLE || mTagMode == MODE_ICON_CHECK_CHANGE) {
                    mIsAutoToggleCheck = true;
                    mIsChecked = typedArray.getBoolean(R.styleable.SplashButton_spb_checked, false);
                    mDecorateIconChange = typedArray.getDrawable(R.styleable.SplashButton_spb_icon_change);
                }
                mIsAutoToggleCheck = typedArray.getBoolean(R.styleable.SplashButton_spb_auto_check, mIsAutoToggleCheck);

                mText = typedArray.getString(R.styleable.SplashButton_spb_text);
                mTextChecked = typedArray.getString(R.styleable.SplashButton_spb_text_check);
                mTextSize = typedArray.getDimension(R.styleable.SplashButton_spb_text_size, mTextSize);
                mBgColor = typedArray.getColor(R.styleable.SplashButton_spb_bg_color, Color.WHITE);
                mBorderColor = typedArray.getColor(R.styleable.SplashButton_spb_border_color, Color.parseColor("#ff333333"));
                mTextColor = typedArray.getColor(R.styleable.SplashButton_spb_text_color, Color.parseColor("#ff666666"));
                mBgColorChecked = typedArray.getColor(R.styleable.SplashButton_spb_bg_color_check, mBgColor);
                mBorderColorChecked = typedArray.getColor(R.styleable.SplashButton_spg_border_color_check, mBorderColor);
                mTextColorChecked = typedArray.getColor(R.styleable.SplashButton_spb_text_color_check, mTextColor);
                mBorderWidth = typedArray.getDimension(R.styleable.SplashButton_spb_border_width, mBorderWidth);
                mRadius = typedArray.getDimension(R.styleable.SplashButton_spb_border_radius, mRadius);
                mHorizontalPadding = (int) typedArray.getDimension(R.styleable.SplashButton_spb_horizontal_padding, mHorizontalPadding);
                mVerticalPadding = (int) typedArray.getDimension(R.styleable.SplashButton_spb_vertical_padding, mVerticalPadding);
                mIconPadding = (int) typedArray.getDimension(R.styleable.SplashButton_spb_icon_padding, mIconPadding);
                mDecorateIcon = typedArray.getDrawable(R.styleable.SplashButton_spb_icon);
                mIconGravity = typedArray.getInteger(R.styleable.SplashButton_spb_gravity, Gravity.LEFT);
            } finally {
                typedArray.recycle();
            }
            if (mTagMode == MODE_ICON_CHECK_CHANGE && mDecorateIconChange == null) {
                throw new RuntimeException("You must set the drawable by 'tag_icon_change' property in" +
                        "MODE_ICON_CHECK_CHANGE mode ");
            }
            if (mDecorateIcon == null && mDecorateIconChange == null) {
                mIconPadding = 0;
            }
            mRect = new RectF();
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            setClickable(true);

        }
    }

    /**
     * @param
     * @return
     * @desc 文字大小适配
     * @author yuezhusust
     * @time 2017/4/28  15:42
     */
    private int adjustText(int maxWidth) {
        if (mPaint.getTextSize() != mTextSize) {
            mPaint.setTextSize(mTextSize);
            final Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            //获取文字高度
            mFontH = (int) (fontMetrics.descent - fontMetrics.ascent);
            //设置基线的偏移量
            mBaseLineDistance = (float) Math.ceil((fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
        }
        //计算文字宽度
        if (TextUtils.isEmpty(mText)) {
            mText = "";
        }
        mFontLen = (int) mPaint.measureText(mText);
        if (TextUtils.isEmpty(mTextChecked)) {
            mFontLenChecked = mFontLen;
        } else {
            mFontLenChecked = (int) mPaint.measureText(mTextChecked);
        }
        //计算图标大小
        if ((mDecorateIcon != null || mDecorateIconChange != null) && mIconSize != mFontH) {
            mIconSize = mFontH;
        }
        //计算出了文字外所需要占用的宽度
        int allPadding;
        if (mTagMode == MODE_ICON_CHECK_INVISIBLE && mIsChecked) {
            allPadding = mHorizontalPadding * 2;
        } else if (mDecorateIcon == null && !mIsChecked) {
            allPadding = mHorizontalPadding * 2;
        } else {
            allPadding = mIconPadding + mIconSize + mHorizontalPadding * 2;
        }
        //设置显示的文字
        if (mIsChecked && !TextUtils.isEmpty(mTextChecked)) {
            if (mFontLenChecked + allPadding > maxWidth) {
                float pointWidth = mPaint.measureText(".");
                //计算能显示的字体长度
                float maxTextWidth = maxWidth - allPadding - pointWidth * 3;
                mShowText = clipShowText(mTextChecked, mPaint, maxTextWidth);
                mFontLen = (int) mPaint.measureText(mShowText);
            } else {
                mShowText = mText;
            }

        } else if (mFontLen + allPadding > maxWidth) {
            float pointWidth = mPaint.measureText(".");
            //计算能显示的字体长度
            float maxTextWidth = maxWidth - allPadding - pointWidth * 3;
            mShowText = clipShowText(mText, mPaint, maxTextWidth);
            mFontLen = (int) mPaint.measureText(mShowText);
        } else {
            mShowText = mText;
        }
        return allPadding;
    }


    /**
     * @methodName: clipShowText
     * @desc:
     * @param: [oriText, mPaint, maxTextWidth]
     * @return: java.lang.String
     * @author: yuezhusust
     * @time: 2017/4/28  16:30
     */
    private String clipShowText(String oriText, Paint mPaint, float maxTextWidth) {

        float tmpWidth = 0;
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < oriText.length(); i++) {
            char c = oriText.charAt(i);
            float cWidth = mPaint.measureText(String.valueOf(c));
            //计算每个字符的宽度之和，如果超过能显示的长度则退出
            if ((tmpWidth + cWidth) > maxTextWidth) {
                break;
            }
            strBuilder.append(c);
            tmpWidth += cWidth;
        }
        //末尾添加3个...，并设置为显示字符
        strBuilder.append("...");
        return strBuilder.toString();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int allPadding = adjustText(getMeasuredWidth());
        int fontLen = mIsChecked ? mFontLenChecked : mFontLen;
        //如果未精确测量MeasureSpec.EXACTLY,则直接使用测量的大小，否则让控件实现自适应
        //如果用了精确测量则mHorizontalPadding 和 mVerticalPadding 会对最终大小判定无效
        int width = (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) ?
                MeasureSpec.getSize(widthMeasureSpec) : allPadding + fontLen;
        int height = (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) ?
                MeasureSpec.getSize(heightMeasureSpec) : mVerticalPadding * 2 + mFontH;
        setMeasuredDimension(width, height);
        //计算图标放置位置
        if (mDecorateIcon != null || mDecorateIconChange != null) {
            int top = (height - mIconSize) / 2;
            int left;
            if (mIconGravity == Gravity.RIGHT) {
                int padding = (width - mIconSize - fontLen - mIconPadding) / 2;
                left = width - padding - mIconSize;
            } else {
                left = (width - mIconSize - fontLen - mIconPadding) / 2;
            }
            if (mTagMode == MODE_ICON_CHECK_CHANGE && mIsChecked && mDecorateIconChange != null) {
                mDecorateIconChange.setBounds(left, top, mIconSize + left, mIconSize + top);
            } else if (mDecorateIcon != null) {
                mDecorateIcon.setBounds(left, top, mIconSize + left, mIconSize + top);
            }
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //设置矩形边框
        mRect.set(mBorderWidth, mBorderWidth, w - mBorderWidth, h - mBorderWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //  super.onDraw(canvas);
        //圆角
        float radius = mRadius;
        if (mTagShape == SHAPE_ARC) {
            radius = mRect.height() / 2;
        } else if (mTagShape == SHAPE_RECT) {
            radius = 0;
        }
        //绘制背景
        mPaint.setStyle(Paint.Style.FILL);
        if (mIsChecked) {
            mPaint.setColor(mBgColorChecked);
        } else {
            mPaint.setColor(mBgColor);
        }
        canvas.drawRoundRect(mRect, radius, radius, mPaint);
        //绘制边框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        if (mIsChecked) {
            mPaint.setColor(mBorderColorChecked);
        } else {
            mPaint.setColor(mBorderColor);
        }
        canvas.drawRoundRect(mRect, radius, radius, mPaint);
        //绘制文字
        mPaint.setStyle(Paint.Style.FILL);
        if (mIsChecked) {
            mPaint.setColor(mTextColorChecked);
            int padding = mTagMode == MODE_ICON_CHECK_INVISIBLE ? 0 : mIconSize + mIconPadding;
            canvas.drawText(mShowText, mIconGravity == Gravity.RIGHT ? (getWidth() - mFontLen - padding) / 2 : (getWidth() - mFontLenChecked -
                    padding) / 2 + padding, getHeight() / 2 + mBaseLineDistance, mPaint);
        } else {
            mPaint.setColor(mTextColor);
            int padding = mDecorateIcon == null ? 0 : mIconSize + mIconPadding;
            canvas.drawText(mShowText,
                    mIconGravity == Gravity.RIGHT ? (getWidth() - mFontLen - padding) / 2 :
                            (getWidth() - mFontLen - padding) / 2 + padding,
                    getHeight() / 2 + mBaseLineDistance, mPaint);
        }
        //绘制icon
        if (mTagMode == MODE_ICON_CHECK_CHANGE && mIsChecked && mDecorateIconChange != null) {
            mDecorateIconChange.setColorFilter(mPaint.getColor(), PorterDuff.Mode.SRC_IN);
            mDecorateIconChange.draw(canvas);
        } else if (mTagMode == MODE_ICON_CHECK_INVISIBLE && mIsChecked) {
            // don't need to draw
        } else if (mDecorateIcon != null) {
            mDecorateIcon.setColorFilter(mPaint.getColor(), PorterDuff.Mode.SRC_IN);
            mDecorateIcon.draw(canvas);
        }
        //绘制半透明遮罩
        if (mIsPressed) {
            mPaint.setColor(mScrimColor);
            canvas.drawRoundRect(mRect, radius, radius, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                mIsPressed = true;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsPressed && !isVIewUnder(event.getX(), event.getY())) {
                    mIsPressed = false;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isVIewUnder(event.getX(), event.getY())) {
                    toggleTagCheckStatus();
                }
            case MotionEvent.ACTION_CANCEL:
                if (mIsPressed) {
                    mIsPressed = false;
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * @methodName: toggleTagCheckStatus
     * @desc: 切换tag选中状态
     * @param: []
     * @return: void
     * @author: yuezhusust
     * @time: 2017/6/2  9:53
     */
    private void toggleTagCheckStatus() {
        if (mIsAutoToggleCheck) {
            setChecked(!mIsChecked);
        }
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    /**
     * @methodName: setChecked
     * @desc: 设置选中状态
     * @param: [b]
     * @return: void
     * @author: yuezhusust
     * @time: 2017/6/2  10:00
     */
    private void setChecked(boolean checked) {
        if (mIsChecked == checked) {
            return;
        }
        mIsChecked = checked;
        requestLayout();
        invalidate();
        if (mCheckListener != null) {
            mCheckListener.onCheckedChanged(mIsChecked);
        }

    }

    /**
     * @methodName: isVIewUnder
     * @desc: 判定是否在Tag控件内
     * @param: [x, y]
     * @return: boolean
     * @author: yuezhusust
     * @time: 2017/5/2  15:50
     */
    private boolean isVIewUnder(float x, float y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    /**
     * 接口
     */
    public int getTagShape() {
        return mTagShape;
    }

    public void setTagShape(int tagShape) {
        mTagShape = tagShape;
        update();
    }

    public int getTagMode() {
        return mTagMode;
    }

    public void setTagMode(int mTagMode) {
        this.mTagMode = mTagMode;
        update();
    }

    public int getBgColor() {
        return mBgColor;
    }

    public void setBgColor(int mBgColor) {
        this.mBgColor = mBgColor;
        invalidate();
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int mBorderColor) {
        this.mBorderColor = mBorderColor;
        invalidate();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        invalidate();
    }

    public int getBgColorChecked() {
        return mBgColorChecked;
    }

    public void setBgColorChecked(int mBgColorChecked) {
        this.mBgColorChecked = mBgColorChecked;
        invalidate();
    }

    public int getBorderColorChecked() {
        return mBorderColorChecked;
    }

    public void setBorderColorChecked(int mBorderColorChecked) {
        this.mBorderColorChecked = mBorderColorChecked;
        invalidate();
    }

    public int getTextColorChecked() {
        return mTextColorChecked;
    }

    public void setTextColorChecked(int mTextColorChecked) {
        this.mTextColorChecked = mTextColorChecked;
        invalidate();
    }

    public int getScrimColor() {
        return mScrimColor;
    }

    public void setScrimColor(int mScrimColor) {
        this.mScrimColor = mScrimColor;
        invalidate();
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }


    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
        update();
    }

    public String getTextChecked() {
        return mTextChecked;
    }

    public void setTextChecked(String mTextChecked) {
        this.mTextChecked = mTextChecked;
        update();
    }

    public String getShowText() {
        return mShowText;
    }

    public void setShowText(String mShowText) {
        this.mShowText = mShowText;
        update();
    }


    /**
     * @methodName: update
     * @desc:
     * @param: []
     * @return: void
     * @author: yuezhusust
     * @time: 2017/6/3  13:49
     */
    private void update() {
        requestLayout();
        invalidate();

    }


    /**
     * 点击监听
     */
    private OnCheckedChangeListener mCheckListener;

    public void setCheckListener(OnCheckedChangeListener onCheckedChangeListener) {
        mCheckListener = onCheckedChangeListener;
    }

    interface OnCheckedChangeListener {
        void onCheckedChanged(boolean isChecked);
    }
}
