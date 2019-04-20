package com.wei.news.presenter;

import com.wei.base.base.BasePresenter;
import com.wei.base.base.BaseView;
import com.wei.base.utils.LogUtils;
import com.wei.news.config.Config;
import com.wei.news.config.Constant;
import com.wei.news.model.NewBean;
import com.wei.news.model.UserInfoBean;
import com.wei.news.service.ApiService;
import com.wei.news.service.BaseObserver;
import com.wei.news.service.RetrofitManager;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 作者：310Lab
 * 时间：2019/4/1 23:08
 * 邮箱：1760567382@qq.com
 * 功能：
 */
public class NewPresenter extends BasePresenter<BaseView>
{
    public NewPresenter(BaseView view)
    {
        super(view);
    }


    public void getNewsList(String type,String userId)
    {
        //LogUtils.d(Constant.debugName+"NewPresenter   ",type);
        if ("top".equals(type)){
            subscribe(
                    RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                            .getMyNewsList(userId),
                    new BaseObserver<List<NewBean>>()
                    {
                        @Override
                        public void onSuccess(List<NewBean> list)
                        {
                            mView.showData(list);
                        }

                        @Override
                        public void onFailed(String msg)
                        {
                            mView.showError(msg);
                        }

                        @Override
                        public void onSubcribes(Disposable d)
                        {
                            add(d);
                        }
                    }
            );
        }else {
            subscribe
                    (
                            RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                                    .getNewsList("toutiao".equals(type) ? "top" : type, userId),
                            new BaseObserver<List<NewBean>>()
                            {
                                @Override
                                public void onSuccess(List<NewBean> list)
                                {
                                    mView.showData(list);
                                }

                                @Override
                                public void onFailed(String msg)
                                {
                                    mView.showError(msg);
                                }

                                @Override
                                public void onSubcribes(Disposable d)
                                {
                                    add(d);
                                }
                            }
                    );
        }

    }




    //String newUid,@Query("userUniqueKey") String userId,@Query("feedbackContent") String feedbackContent
    public void postEnjoy(String newUid,String userId,String feedbackContent){
        subscribe
                (
                        RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                                .postEnjoy(newUid, userId, feedbackContent),
                        new BaseObserver<List<UserInfoBean>>() {
                            @Override
                            public void onSuccess(List<UserInfoBean> userInfoBeans) {
                                mView.showData(userInfoBeans);
                            }

                            @Override
                            public void onFailed(String msg) {
                                mView.showError(msg);
                            }

                            @Override
                            public void onSubcribes(Disposable d) {
                                add(d);
                            }
                        }
                );
    }


}
