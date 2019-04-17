package com.wei.news;

import android.content.Context;
import android.text.TextUtils;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

import com.wei.base.BaseApplication;
import com.wei.news.dao.DaoSession;
import com.wei.news.db.DbManager;
import com.wei.news.utils.GainLocation;
import com.wei.news.utils.InitializeManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 作者：赵若位
 * 时间：2018/7/31 21:00
 * 邮箱：1070138445@qq.com
 * 功能：
 */
public class App extends BaseApplication
{
    public static DaoSession mSession;
    private static Context mContext;



    static
    {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater()
        {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout)
            {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorBG, R.color.colorEtHint);
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setTimeFormat(new SimpleDateFormat());
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater()
        {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout)
            {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = getApplicationContext();
        mSession = DbManager.getManager().initialization(this);
        InitializeManager.init(this);
    }



    public static Context getContent()
    {
        return mContext;
    }
}
