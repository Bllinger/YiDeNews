package com.Blinger.YiDeNews.utils;

/**
 * Created by 社会主义好 on 2019/4/16.
 */

import android.content.Context;

/**
 * Created by DongJr on 2017/2/21.
 */

public class ScreenUtils {

    private ScreenUtils() {
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}