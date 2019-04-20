package com.Blinger.base.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.Blinger.base.BaseApplication;
import com.Blinger.base.R;
import com.Blinger.base.utils.AppManager;
import com.Blinger.base.utils.DialogLoading;
import com.Blinger.base.utils.DialogUtils;
import com.Blinger.base.utils.StatusBarUtils;

import butterknife.ButterKnife;

/**
 * 作者：310 Group
 * 时间：2019/4/1
 * 邮箱：1760567382@qq.com
 * 功能：
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity
{

    protected T mPresenter;
    protected Bundle savedInstanceState;
    protected Activity mActivity;

    /**
     * 初始化Presenter
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 获取xml文件资源ID
     * @return
     */
    protected abstract int getResourceId();

    @Override
    public final void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        //初始化的时候将其添加到集合中
        AppManager.getActivityManager().addActivity(this);
        //设置Activity不允许横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPresenter = createPresenter();
        setContentView(getResourceId());
        StatusBarUtils.setColorNoTranslucent(this, Color.WHITE);
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
    }


    public void initView(Bundle savedInstanceState)
    {
    }

    public void initData()
    {
        if (BaseApplication.isIsNetConnect())
        {
            netWork();
        }
    }


    protected void netWork()
    {

    }


    /**
     * Toast消息
     * @param msg 消息内容
     */
    public void alert(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void doFinish()
    {
        this.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        DialogLoading.dissDialog();
        DialogUtils.dissDialog();
        AppManager.getActivityManager().finishActivity(this);
        if (mPresenter != null)
        {
            mPresenter.cancleAll();
        }
    }


}
