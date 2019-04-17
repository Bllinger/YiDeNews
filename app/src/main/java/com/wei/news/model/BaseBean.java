package com.wei.news.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：赵若位
 * 时间：2018/8/1 22:57
 * 邮箱：1070138445@qq.com
 * 功能：一级实体类
 */
public class BaseBean<T>
{
    @SerializedName("error_code")
    private int code;

    @SerializedName("reason")
    private String msg;

    @SerializedName("result")
    private T t;

    public BaseBean()
    {

    }


    public BaseBean(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
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
