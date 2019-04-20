package com.wei.news.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wei.base.base.BaseActivity;
import com.wei.base.base.BasePresenter;
import com.wei.base.utils.LogUtils;
import com.wei.base.utils.SpUtils;
import com.wei.base.utils.StatusBarUtils;
import com.wei.news.R;
import com.wei.news.config.Constant;
import com.wei.news.model.NewTypeBean;
import com.wei.news.ui.fragment.AboutFragment;
import com.wei.news.ui.fragment.NewFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * 作者：310Lab
 * 时间：2019/4/1 21:16
 * 邮箱：1760567382@qq.com
 * 功能：
 */
public class MainActivity extends BaseActivity
{
    @Bind(R.id.toolBar)
    Toolbar mToolBar;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.navigationView)
    NavigationView mNavigationView;
    @Bind(R.id.drawer)
    DrawerLayout mDrawer;


    private HashMap<String, NewFragment> fragments = new HashMap<>();


    @Override
    protected BasePresenter createPresenter()
    {
        return null;
    }

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        StatusBarUtils.setColorNoTranslucentForDrawerLayout(this, mDrawer, Color.parseColor("#f54343"));
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationIcon(R.mipmap.icon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        hideNavigationViewScrollBars();


        //ToolBar点击事件
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menu_opean:
                        mDrawer.openDrawer(Gravity.END);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        setNavigationItemSelectedListener();
        showFragment((String) SpUtils.getUtils(this).get("title", getString(R.string.menu_toutiao)));

//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    b = true;
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }else {
//                    b = false;
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }
//
//            }
//        });
    }

    /**
     * 隐藏NavigationView的滚动条
     */
    private void hideNavigationViewScrollBars()
    {
        if (mNavigationView != null)
        {
            NavigationMenuView navigationMenuView = (NavigationMenuView) mNavigationView.getChildAt(0);
            if (navigationMenuView != null)
            {
                navigationMenuView.setVerticalScrollBarEnabled(false);
                navigationMenuView.setOverScrollMode(View.OVER_SCROLL_NEVER);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//
//        getMenuInflater().inflate(R.menu.menu_navigation,menu);
//
//        final MenuItem menuItem = menu.findItem(R.id.menu_theme);
//        SwitchCompat switchCompat = (SwitchCompat) menuItem.getActionView();
//
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }else {
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }
//            }
//        });
//
//        return true;
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//    /**
//     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
//     * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
//     * if it is present.
//     */
//        getMenuInflater().inflate(R.menu.menu_navigation,menu);
//
//        final MenuItem menuItem = menu.findItem(R.id.menu_theme);
//        SwitchCompat switchCompat = (SwitchCompat) menuItem.getActionView();
//
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }else {
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }
//            }
//        });
//
//        return true;
//    }

    /**
     * NavagationView菜单点击事件
     */
    private void setNavigationItemSelectedListener()
    {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.menu_toutiao:
                        showFragment(getString(R.string.menu_toutiao));
                        break;
                    case R.id.menu_shehui:
                        showFragment(getString(R.string.menu_shehui));
                        break;
                    case R.id.menu_guonei:
                        showFragment(getString(R.string.menu_guonei));
                        break;
                    case R.id.menu_yule:
                        showFragment(getString(R.string.menu_yule));
                        break;
                    case R.id.menu_tiyu:
                        showFragment(getString(R.string.menu_tiyu));
                        break;
                    case R.id.menu_junshi:
                        showFragment(getString(R.string.menu_junshi));
                        break;
                    case R.id.menu_keji:
                        showFragment(getString(R.string.menu_keji));
                        break;
                    case R.id.menu_caijing:
                        showFragment(getString(R.string.menu_caijing));
                        break;
                    case R.id.menu_shishang:
                        showFragment(getString(R.string.menu_shishang));
                        break;
                    //收藏:
                    case R.id.menu_collection:
                        startActivity(new Intent(MainActivity.this, CollectionActivity.class));
                        break;
                    //关于
                    case R.id.menu_about:
                        AboutFragment about = new AboutFragment();
                        about.show(getSupportFragmentManager(), AboutFragment.class.getSimpleName());
                        break;
                    default:
                        break;
                }
                mDrawer.closeDrawers();
                return true;
            }
        });
    }


    private void showFragment(String s)
    {

        if (s.equals(mTvTitle.getText().toString()))
        {
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        NewFragment fragment = fragments.get(s);
        hideFragment(ft);
        if (fragment == null)
        {
            NewTypeBean data = NewTypeBean.getNewTypeBean(s);
            fragment = NewFragment.getFragment(data,s);
            fragments.put(s, fragment);
            ft.add(R.id.frame, fragment);
        } else
        {
            ft.show(fragment);
        }
        ft.commit();
        SpUtils.getUtils(this).put("title", s);
        mTvTitle.setText(s);
    }

    /**
     * 隐藏添加过的Fragment，避免重复添加
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft)
    {
        for (Map.Entry<String, NewFragment> map : fragments.entrySet())
        {
            NewFragment value = map.getValue();
            ft.hide(value);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //        getMenuInflater().inflate(R.menu.menu_navigation,menu);

//        final MenuItem menuItem = menu.findItem(R.id.menu_theme);
//        FrameLayout frameLayout = (FrameLayout) menuItem.getActionView();
//
//        SwitchCompat switchCompat = (SwitchCompat) frameLayout.findViewById(R.id.view_switch);
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }else {
//                    LogUtils.d(Constant.debugName+"MainActivity","mySwitch");
//                }
//            }
//        });

//        return true;
        return true;
    }
}
