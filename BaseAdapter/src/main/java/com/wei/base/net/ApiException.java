package com.wei.base.net;

/**
 * 作者：赵若位
 * 时间：2018/5/1 22:15
 * 邮箱：1070138445@qq.com
 * 功能：服务器返回报错
 */

public class ApiException extends Throwable
{


    private int code;
    private String msg;


    public ApiException(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
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
}
