package com.Blinger.base.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * 作者：310Lab
 * 时间：2019/4/1 10:07
 * 邮箱：1760567382@qq.com
 * 功能：
 */

public class LogUtils
{
    private static boolean isDebug = true;
    private static String TAG = "info";

    public static void v(String msg)
    {
        if (isDebug)
        {
            Logger.v(msg);
        }
    }

    public static void d(String msg)
    {
        if (isDebug)
        {
            Logger.d(msg);
        }
    }

    public static void i(String msg)
    {
        if (isDebug)
        {
            Logger.i( msg);
        }
    }

    public static void w(String msg)
    {
        if (isDebug)
        {
            Logger.w(msg);
        }
    }

    public static void e(String msg)
    {
        if (isDebug)
        {
            Logger.e( msg);
        }
    }

    public static void json(String msg)
    {
        if (isDebug)
        {
            Logger.json(msg);
        }
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
        {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
        {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg)
    {
        if (isDebug)
        {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg)
    {
        if (isDebug)
        {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
        {
            Log.e(tag, msg);
        }
    }
}
