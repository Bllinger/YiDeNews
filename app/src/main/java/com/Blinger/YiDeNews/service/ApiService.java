package com.Blinger.YiDeNews.service;

import com.Blinger.YiDeNews.model.BaseBean;
import com.Blinger.YiDeNews.model.CommentBean;
import com.Blinger.YiDeNews.model.DataBean;
import com.Blinger.YiDeNews.model.NewBean;
import com.Blinger.YiDeNews.model.UserInfoBean;
import com.Blinger.YiDeNews.model.UserTailBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：310Lab
 * 时间：2019/4/2 10:56
 * 邮箱：1760567382@qq.com
 * 功能：接口文档
 */


public interface ApiService
{
//    @Headers({"url_name:news"})
//    @GET("/toutiao/index")
//    Observable<BaseBean<DataBean<List<NewBean>>>> getNewsList(@Query("type") String type, @Query("key") String key);
    @Headers({"url_name:other"})
    @GET("/news/news/content/other")
    Observable<BaseBean<DataBean<List<NewBean>>>> getNewsList(@Query("type") String type, @Query("userUniqueKey") String userId);

    @Headers({"url_name:other"})
    @GET("/news/news/content/top")
    Observable<BaseBean<DataBean<List<NewBean>>>> getMyNewsList(@Query("userUniqueKey") String userId);

    @Headers({"url_name:other"})
    @GET("/news/news/distaste")
    Observable<BaseBean<DataBean<List<UserInfoBean>>>> postEnjoy(@Query("newsUniqueKey") String newUid,@Query("userUniqueKey") String userId,@Query("feedbackContent") String feedbackContent);

    @Headers({"url_name:other"})
    @GET("/news/news/history")
    Observable<BaseBean<DataBean<List<UserTailBean>>>>  postHistory(@Query("userUniqueKey") String UID, @Query("newsUniqueKey") String newsID,@Query("newsTitle") String title,@Query("newsType") String type,@Query("newsUrl") String url);

    @Headers({"url_name:other"})
    @GET("/news/news/register")
    Observable<BaseBean<DataBean<List <UserInfoBean>>>> postNewUser(@Query("uniqueKey") String UID,@Query("userName") String name,@Query("imageType") int imageType,@Query("latitude") double latitude,@Query("longitude") double longitude);//

    @Headers({"url_name:other"})
    @POST("/news/news/comment/update")
    @FormUrlEncoded
    Observable<BaseBean<DataBean<List<UserTailBean>>>> postReview(@Field("newsUniqueKey") String newsId,@Field("commentUniqueKey") String reviewId,@Field("composeType") String reviewType,@Field("content") String reviewContent,@Field("userUniqueKey") String UID,@Field("commentTime") String commentTime);

    @Headers({"url_name:other"})
    @GET("/news/news/comment/get")
    Observable<BaseBean<DataBean<List<CommentBean>>>> getReviewList(@Query("newsUniqueKey") String newsUid,@Query("userUniqueKey") String userId);

    @Headers({"url_name:other"})
    @GET("/news/news/acclaim")
    Observable<BaseBean<DataBean<List<UserTailBean>>>> postAcclaim(@Query("typeId")String typeId,@Query("userUniqueKey") String userId,@Query("acclaimType") int type,@Query("value") int value);

    @Headers({"url_name:other"})
    @GET("/news/news/collect")
    Observable<BaseBean<DataBean<List<UserTailBean>>>> postCollect(@Query("newsUniqueKey") String newsId,@Query("userUniqueKey")String userId,@Query("value") int value);

}

