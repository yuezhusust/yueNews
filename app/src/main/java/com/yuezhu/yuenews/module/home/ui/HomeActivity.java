package com.yuezhu.yuenews.module.home.ui;


import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;

import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.moudele.base.BaseActivity;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private SparseArray<String> mSparseTags = new SparseArray<>();
    private long mExitTime = 0;
    private int mItemId = -1;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case R.id.nav_news:
                    replaceFragment(R.id.fl_container,new NewsMainFragment(),mSparseTags.get(R.id.nav_news));
                    break;
                case R.id.nav_photos:
                    break;
                case R.id.nav_videos:
                    break;
                case R.id.nav_setting:
                    SettingActivity.launch(HomeActivity.class);
                    break;
            }
            mItemId = -1;
            return true;
        }
    });
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
