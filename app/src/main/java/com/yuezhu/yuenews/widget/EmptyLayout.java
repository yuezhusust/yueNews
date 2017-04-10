package com.yuezhu.yuenews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.yuezhu.yuenews.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @name YueNews
 * @class name：com.yuezhu.yuenews.widget
 * @class describe: 要加载的空视图
 * @author: yuezhusust
 * @time 2017/3/30 14:31
 */

public class EmptyLayout extends FrameLayout {
    public static final int STATE_HIDE = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_NO_NET = 3;
    public static final int STATE_NO_DATE = 4;


    private int mCurrentState = STATE_LOADING;

    private RetryListener mRetyrListener;
    private Context mContext;
    private int mBgColor;
    @BindView(R.id.real_empty_container)
    FrameLayout emptyContainer;
    @BindView(R.id.text_net_error)
    TextView textNetError;
    @BindView(R.id.empty_loading)
    SpinKitView emptyLoading;
    @BindView(R.id.empty_layout)
    private FrameLayout emptyLayout;

    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    /**
     * @param
     * @return
     * @desc : 初始化视图
     * @author yuezhusust
     * @time 2017/3/30  16:56
     */
    private void init(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        try {
            mBgColor = a.getColor(R.styleable.EmptyLayout_backgroundColor, Color.WHITE);
        } finally {
            a.recycle();
        }
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        ButterKnife.bind(this);
        emptyLayout.setBackgroundColor(mBgColor);
        switchEmptyView();

    }

    /**
     * @param
     * @return
     * @desc :
     * @author yuezhusust
     * @time 2017/3/30  17:26
     */
    public void hideView() {
        mCurrentState = STATE_HIDE;
        switchEmptyView();
    }

    /**
     * @param
     * @return
     * @desc : 获取状态
     * @author yuezhusust
     * @time 2017/3/30  17:25
     */
    public int getmCurrentState() {
        return mCurrentState;
    }

    /**
     * @param
     * @return
     * @desc : 设置状态
     * @author yuezhusust
     * @time 2017/3/30  17:24
     */
    public void setmCurrentState(@EmptyStatus int mCurrentState) {
        this.mCurrentState = mCurrentState;
        switchEmptyView();
    }

    /**
     * @param
     * @return
     * @desc : 设置textView显示的消息
     * @author yuezhusust
     * @time 2017/3/30  17:33
     */
    public void setEmptyMessage(String msg) {
        textNetError.setText(msg);
    }

    /**
     * @param
     * @return
     * @desc : 隐藏网络错误图标
     * @author yuezhusust
     * @time 2017/3/30  17:34
     */
    public void hideErrorIcon() {
        textNetError.setCompoundDrawables(null, null, null, null);
    }

    /**
     * @param
     * @return
     * @desc : 设置动画图片效果
     * @author yuezhusust
     * @time 2017/3/30  18:15
     */
    public void setLoadingIcon(Sprite d) {
        emptyLoading.setIndeterminateDrawable(d);
    }

    /**
     * @param
     * @return
     * @desc : 切换视图
     * @author yuezhusust
     * @time 2017/3/30  17:18
     */
    private void switchEmptyView() {
        switch (mCurrentState) {
            case STATE_HIDE:
                setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                setVisibility(View.VISIBLE);
                emptyContainer.setVisibility(View.GONE);
                emptyLoading.setVisibility(View.VISIBLE);
                break;
            case STATE_NO_NET:
                setVisibility(View.VISIBLE);
                emptyLoading.setVisibility(View.GONE);
                emptyContainer.setVisibility(View.VISIBLE);
                break;
            case STATE_NO_DATE:
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.text_net_error)
    public void onClick() {
        if (mRetyrListener != null) {
            mRetyrListener.onRetry();
        }
    }

    /**
     * @param
     * @author yuezhusust
     * @return
     * @desc : 点击重试接口
     * @time 2017/3/30  18:26
     */
    public interface RetryListener {
        void onRetry();
    }

    /**
     * @param
     * @author yuezhusust
     * @return
     * @desc : 定义枚举类
     * @time 2017/3/30  18:37
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATE_LOADING, STATE_NO_NET, STATE_NO_DATE})
    public @interface EmptyStatus {
    }

}
