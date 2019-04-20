package com.wei.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wei.base.BaseApplication;
import com.wei.base.R;

import butterknife.ButterKnife;

/**
 * 作者：310 Group
 * 时间：2019/4/1
 * 邮箱：1760567382@qq.com
 * 功能：
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment
{
    protected T mPresenter;
    private View rootView;
    protected Activity mActivity;
    /**View布局是否可见**/
    protected boolean isUIVisible;
    /**UI是否已被初始化完成**/
    protected boolean isUICreated;


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
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            isUIVisible = true;
            initData();
        } else
        {
            isUIVisible = false;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        isUICreated = true;
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(getResourceId(), container, false);
        ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    public void initData()
    {
        if (isUIVisible && isUICreated)
        {
            if (BaseApplication.isIsNetConnect())
            {
                netWork();
            }else
            {
                alert(getString(R.string.alert_no_net_repeat));
            }
//            isUIVisible = false;
//            isUICreated = false;
        }
    }

    protected void netWork()
    {

    }


    public void alert(String msg)
    {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * StateView的根布局，默认是整个界面，如果需要变换可以重写此方法
     */
    public View getStateViewRoot()
    {
        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    public void initView(View rootView)
    {
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        isUICreated = false;
        isUIVisible = false;
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mPresenter != null)
        {
            mPresenter.cancleAll();
        }
        rootView = null;
    }


}
