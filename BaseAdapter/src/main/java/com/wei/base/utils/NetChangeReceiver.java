package com.wei.base.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.wei.base.utils.NetUtils;

/**
 * 作者：赵若位
 * 时间：2018/7/12 9:45
 * 邮箱：1070138445@qq.com
 * 功能：
 */
public class NetChangeReceiver extends BroadcastReceiver
{
    private OnNetchangeListener mListener;


    public void setListener(OnNetchangeListener listener)
    {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION))
        {
            int state = NetUtils.getNetState(context);
            // 当网络发生变化，判断当前网络状态，并通过NetEvent回调当前网络状态
            if (mListener != null)
            {
                mListener.onNetchangeListener(state);
            }
        }
    }


    public interface OnNetchangeListener
    {
        /**
         * Net status monitoring events
         * @param state
         */
        void onNetchangeListener(int state);
    }
}

