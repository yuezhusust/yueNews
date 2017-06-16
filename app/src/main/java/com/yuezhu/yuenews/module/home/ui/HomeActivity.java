package com.yuezhu.yuenews.module.home.ui;


import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.yuezhu.yuenews.R;
import com.yuezhu.yuenews.module.base.BaseActivity;

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
            switch (msg.what) {
                case R.id.nav_news:
                    //    replaceFragment(R.id.fl_container,new NewsMainFragment(),mSparseTags.get(R.id.nav_news));
                    break;
                case R.id.nav_photos:
                    break;
                case R.id.nav_videos:
                    break;
                case R.id.nav_setting:
                    //SettingActivity.launch(HomeActivity.class);
                    break;
            }
            mItemId = -1;
            return true;
        }
    });

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {


    }

    @Override
    protected void initViews() {
        initDrawerLayout(mDrawerLayout, mNavView);
        mSparseTags.put(R.id.nav_news, "News");
        mSparseTags.put(R.id.nav_photos, "Photos");
        mSparseTags.put(R.id.nav_videos, "Videos");
        getPermission();

    }


    @Override
    protected void updateView(boolean isRefresh) {
        mNavView.setCheckedItem(R.id.nav_news);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()) {
            return true;
        }
        mItemId = item.getItemId();
        return true;
    }

    @Override
    public void onBackPressed() {
        //获取堆栈里有几个
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount == 1) {
            //如果剩一个说明在home页面，提示按两次退出app
            exit();
        } else {
            //获取上一个堆栈中保存的是哪个页面，根据name来设置导航项的选中状态
            final String tagName = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount - 2).getName();
            mNavView.setCheckedItem(mSparseTags.keyAt(mSparseTags.indexOfValue(tagName)));
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @methodName: initDrawerLayout
     * @desc: 初始化DrawerLayout
     * @param: [drawerLayout, navView]
     * @return: void
     * @author: yuezhusust
     * @time: 2017/6/16  10:27
     */
    private void initDrawerLayout(DrawerLayout drawerLayout, NavigationView navView) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerClosed(View drawerView) {
                                               mHandler.sendEmptyMessage(mItemId);
                                           }
                                       }
        );
        navView.setNavigationItemSelectedListener(this);
    }

    /**
     * @methodName: getPermission()
     * @desc: 获取运行时权限
     * @param:
     * @return:
     * @author: yuezhusust
     * @time: 2017/6/16  10:45
     */
    private void getPermission() {
        // final File dir = new File(FileDownloader)
    }

    /**
     * @methodName: exit
     * @desc: 退出
     * @param: []
     * @return: void
     * @author: yuezhusust
     * @time: 2017/6/16  13:48
     */
    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
