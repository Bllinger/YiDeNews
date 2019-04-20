package com.wei.news.db;

import android.content.Context;


import com.wei.news.dao.DaoMaster;
import com.wei.news.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * 作者：310Lab
 * 时间：2019/4/1 10:13
 * 邮箱：1760567382@qq.com
 * 功能：初始化数据库
 */

public class DbManager
{
    private static DbManager mManager;
    private DaoSession mSession;

    private DbManager()
    {

    }

    public static DbManager getManager()
    {
        if (mManager == null)
        {
            synchronized (DbManager.class)
            {
                if (mManager == null)
                {
                    mManager = new DbManager();
                }
            }
        }
        return mManager;
    }


    public DaoSession initialization(Context context)
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, context.getPackageName() + ".db", null);
        Database db = helper.getEncryptedWritableDb(context.getPackageName());
        mSession = new DaoMaster(db).newSession();
        mSession.clear();
        return mSession;
    }

}
