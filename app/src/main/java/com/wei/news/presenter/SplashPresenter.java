package com.wei.news.presenter;

import com.wei.base.base.BasePresenter;
import com.wei.base.base.BaseView;
import com.wei.news.model.UserInfoBean;
import com.wei.news.service.ApiService;
import com.wei.news.service.BaseObserver;
import com.wei.news.service.RetrofitManager;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by 社会主义好 on 2019/4/9.
 */

public class SplashPresenter extends BasePresenter<BaseView>{
    public SplashPresenter(BaseView view) {
        super(view);
    }

    public void postNewUser(String UID,String name,int imageType,double latitude,double longitude){
        subscribe(
                RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                        .postNewUser(UID,name,imageType,latitude,longitude),
                new BaseObserver <List <UserInfoBean>>() {
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
