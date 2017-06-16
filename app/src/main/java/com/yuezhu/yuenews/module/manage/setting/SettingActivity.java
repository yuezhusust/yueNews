package com.yuezhu.yuenews.module.manage.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.module.base.BaseActivity;
import com.yuezhu.yuenews.module.base.BaseSwipeBackActivity;

import butterknife.BindView;

public class SettingActivity extends BaseSwipeBackActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateView(boolean isRefresh) {

    }
    public static void launch(Context context){
        Intent intent = new Intent(context,SettingActivity.class);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.slide_right,R.anim.hold);

    }
}
