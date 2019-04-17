package com.wei.base.base;

import io.reactivex.disposables.Disposable;

/**
 * 作者：310 Group
 * 时间：2018/4/30 21:13
 * 邮箱：1070138445@qq.com
 * 功能：
 */

public interface BaseSubscriptionHelper
{
    /**
     * 将请求添加进队列
     * @param disposable
     */
    void add(Disposable disposable);

    /**
     * 取消单个请求
     * @param disposable
     */
    void cancle(Disposable disposable);

    /**
     * 取消所有的请求
     */
    void cancleAll();
}
