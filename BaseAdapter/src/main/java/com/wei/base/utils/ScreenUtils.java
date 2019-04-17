package com.wei.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 作者：赵若位
 * 时间：2017/11/30
 * 邮箱：1070138445@qq.com
 * 功能：手机一些常用功能
 */

public class ScreenUtils
{
    /**
     * 获取手机屏幕的宽高
     * @param mContext
     * @return
     */
    public static int[] getScreen(Context mContext)
    {
        WindowManager mManager = (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        mManager.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }
}
