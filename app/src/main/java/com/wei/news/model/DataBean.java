package com.wei.news.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：赵若位
 * 时间：2018/8/1 23:48
 * 邮箱：1070138445@qq.com
 * 功能：二级实体类
 */
public class DataBean<T>
{
    @SerializedName("stat")
    private int code;
    @SerializedName("data")
    private T t;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public T getT()
    {
        return t;
    }

    public void setT(T t)
    {
        this.t = t;
    }
}
