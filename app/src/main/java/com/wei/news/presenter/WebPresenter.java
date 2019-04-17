package com.wei.news.presenter;

import com.wei.base.base.BasePresenter;
import com.wei.base.base.BaseView;
import com.wei.base.utils.LogUtils;
import com.wei.news.config.Constant;
import com.wei.news.model.CommentBean;
import com.wei.news.model.UserTailBean;
import com.wei.news.service.ApiService;
import com.wei.news.service.BaseObserver;
import com.wei.news.service.RetrofitManager;

import java.util.List;

import io.reactivex.disposables.Disposable;
import retrofit2.http.Query;


/**
 * Created by 社会主义好 on 2019/4/2.
 */

public class WebPresenter extends BasePresenter<BaseView>{
    public static int requireType;

    public WebPresenter(BaseView view) {
        super(view);
    }

    public static int getRequireType() {
        return requireType;
    }

    public void postHistory(String UID, String newsID, String title, final String type, String time, String url){
        subscribe(
                RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                        .postHistory(UID, newsID, title, type,url),
                new BaseObserver<List<UserTailBean>>() {
                    @Override
                    public void onSuccess(List<UserTailBean> userTailBean) {
                        LogUtils.d(Constant.debugName,"into success");
                        //LogUtils.d(Constant.debugName,userTailBean.get(0).getInfo()+"success");
                        mView.showData(userTailBean.get(0));
                    }

                    @Override
                    public void onFailed(String msg) {
                        LogUtils.d(Constant.debugName,msg+" failed");
                        requireType = 0;
                        mView.showError(msg);
                    }

                    @Override
                    public void onSubcribes(Disposable d) {
                        add(d);
                    }


                }
        );
    }

    public void postReview(String newsId,String reviewId,String reviewType,String reviewContent,String UID,String commentTime){
        subscribe(RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                        .postReview(newsId, reviewId, reviewType, reviewContent, UID,commentTime),
                new BaseObserver<List<UserTailBean>>() {
                    @Override
                    public void onSuccess(List<UserTailBean> userTailBeans) {
                        LogUtils.d(Constant.debugName+"postReview","into success");
                        mView.showData(userTailBeans);
                    }

                    @Override
                    public void onFailed(String msg) {
                        LogUtils.d(Constant.debugName+"postReview","into failue");
                        requireType = 1;
                        mView.showError(msg);
                    }

                    @Override
                    public void onSubcribes(Disposable d) {
                        add(d);
                    }

                }
        );
    }

    public void getReviewList(String newsId,String userId){
        subscribe(RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                        .getReviewList(newsId,userId),
                new BaseObserver<List<CommentBean>>() {
                    @Override
                    public void onSuccess(List<CommentBean> commentBeans) {
                        LogUtils.d(Constant.debugName+"getReviewList","into success");
                        mView.showData(commentBeans);
                    }

                    @Override
                    public void onFailed(String msg) {
                        LogUtils.d(Constant.debugName+"getReviewList","into failue");
                        requireType = 2;
                        mView.showError(msg);
                    }

                    @Override
                    public void onSubcribes(Disposable d) {
                        add(d);
                    }
                });
    }

    public void postAcclaim(String typeId,String userId,int type,int value){
        subscribe(RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                        .postAcclaim(typeId, userId,type,value),
                new BaseObserver<List<UserTailBean>>() {
                    @Override
                    public void onSuccess(List<UserTailBean> userTailBeans) {
                        mView.showData(userTailBeans);
                    }

                    @Override
                    public void onFailed(String msg) {
                        requireType = 3;
                        mView.showError(msg);
                    }

                    @Override
                    public void onSubcribes(Disposable d) {
                        add(d);
                    }
                });
    }

    public void postCollect(String newsId,String userId,int value){
        subscribe(RetrofitManager.getManager().getRetrofit().create(ApiService.class)
                    .postCollect(newsId,userId,value),
                new BaseObserver<List<UserTailBean>>(){
                    @Override
                    public void onSuccess(List<UserTailBean> userTailBeans) {
                        mView.showData(userTailBeans);
                    }

                    @Override
                    public void onFailed(String msg) {
                        requireType = 4;
                        mView.showError(msg);
                    }

                    @Override
                    public void onSubcribes(Disposable d) {
                        add(d);
                    }
                });
    }
}
