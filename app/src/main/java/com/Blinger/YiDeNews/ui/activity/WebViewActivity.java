package com.Blinger.YiDeNews.ui.activity;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Blinger.YiDeNews.App;
import com.Blinger.YiDeNews.R;
import com.Blinger.YiDeNews.config.Constant;
import com.Blinger.YiDeNews.dao.HistoryBeanDao;
import com.Blinger.YiDeNews.dao.NewBeanDao;
import com.Blinger.YiDeNews.entity.UserTail;
import com.Blinger.YiDeNews.model.BaseBean;
import com.Blinger.YiDeNews.model.CommentBean;
import com.Blinger.YiDeNews.model.HistoryBean;
import com.Blinger.YiDeNews.model.NewBean;
import com.Blinger.YiDeNews.model.UserTailBean;
import com.Blinger.YiDeNews.presenter.WebPresenter;
import com.Blinger.YiDeNews.ui.MyView.AdjustTypeDialog;
import com.Blinger.YiDeNews.ui.MyView.InputTextMsgDialog;
import com.Blinger.YiDeNews.ui.MyView.RecyclerViewAdapter;
import com.Blinger.YiDeNews.utils.Md5;
import com.Blinger.YiDeNews.utils.ObservableScrollView;
import com.Blinger.YiDeNews.utils.ToastUtil;
import com.Blinger.base.BaseApplication;
import com.Blinger.base.base.BaseActivity;
import com.Blinger.base.base.BaseView;
import com.Blinger.base.utils.DialogUtils;
import com.Blinger.base.utils.LogUtils;
import com.Blinger.base.utils.TimeUtils;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：310Lab
 * 时间：2019/4/3 12:16
 * 邮箱：1760567382@qq.com
 * 功能：
 */

public class WebViewActivity extends BaseActivity<WebPresenter> implements BaseView {
    @Bind(R.id.layout)
    RelativeLayout mLayout;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.img_collection)
    ImageView mImgCollection;
    @Bind(R.id.rv_review)
    RecyclerView rvReview;

    @Bind(R.id.rv_button)
    Button rvButton;
    @Bind(R.id.sv_web)
    ObservableScrollView svWeb;
    @Bind(R.id.rl_rv)
    RelativeLayout rlRv;
    @Bind(R.id.im_zan)
    ImageView imZan;
    @Bind(R.id.tv_zan_num)
    TextView tvZanNum;
    @Bind(R.id.empty1)
    View empty1;
    @Bind(R.id.bottom_view)
    TextView bottomView;
    @Bind(R.id.share_iv)
    ImageView shareIv;
    @Bind(R.id.empty)
    View empty;
    @Bind(R.id.adjust_type_iv)
    ImageView adjustTypeIv;


    private WebView mWebView;
    private NewBean mData;
    private UserTail mUserTail;
    private String uuid;
    private InputTextMsgDialog inputTextMsgDialog;
    private AdjustTypeDialog adjustTypeDialog;
    //private RvAdapter mAdapter;

    private List<String> userNameList;
    private List<String> timeList;
    private List<Integer> acclaimNumList;
    private List<String> reviewContentList;
    private List<Integer> imageTypeList;
    private List<Integer> statusList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private String reviewId;
    private String location;
    private int imageType;
    private int typeSize;
    private WebSettings webSettings;

    private Long commentCount = 0l;//新闻评论数
    private int articleAcclaimCount = 0;//新闻获赞个数
    private int isAcclaim = 0;//是否点赞


    @Override
    protected WebPresenter createPresenter() {
        return new WebPresenter(this);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_web;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mData = getIntent().getParcelableExtra(getClass().getSimpleName());

        addValue(mData);
        initData();

        svWeb.setScrollListener(new ObservableScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int oritention) {
                if (oritention == 0x01) {
                    //ScrollView正在向上滑动
                    ///LogUtils.d(Constant.debugName, "ScrollView正在向上滑动");
                    rlRv.setVisibility(View.GONE);

                } else if (oritention == 0x10) {
                    // ScrollView正在向下滑动
                    //LogUtils.d(Constant.debugName, "ScrollView正在向下滑动");
                    rlRv.setVisibility(View.VISIBLE);
                }
            }
        });

        //评论输入框
        inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
                //处理输入文字
//                LogUtils.d(Constant.debugName + "评论", msg);
                newReview(msg);
                inputTextMsgDialog.dismiss();
            }
        });


        rvReview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //initRecyclerView();//初始化recyclerview


        //隐藏控件
        rvReview.setVisibility(View.GONE);
        imZan.setVisibility(View.INVISIBLE);
        tvZanNum.setVisibility(View.INVISIBLE);
        empty1.setVisibility(View.INVISIBLE);
        bottomView.setVisibility(View.INVISIBLE);


        mTvTitle.setText(mData != null ? mData.getCategory() : "");
        //是否被收藏
        mImgCollection.setImageResource(isCollection() ? R.drawable.icon_collection_2 : R.drawable.icon_collection_1);

        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(this.getApplicationContext());
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setLayoutParams(mParams);
        mLayout.addView(mWebView);
        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urls) {
                view.loadUrl(urls);
                return true;
            }
        });


        //声明WebSettings子类
        webSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(false);

        webSettings.setDomStorageEnabled(true);
        //设置自适应屏幕，两者合用（下面这两个方法合用）
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //缩放操作
        //支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //其他细节操作
        //webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //webSettings.setTextZoom(100);
        //webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
        setType(typeSize);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDomStorageEnabled(true);

        adjustTypeDialog = new AdjustTypeDialog(this, R.style.dialog_center, typeSize);
        adjustTypeDialog.setOnAdjustTypeListener(new AdjustTypeDialog.OnAdjustTypeListener() {
            @Override
            public void onAdjustType(int type) {
                setType(type);
                typeSize = type;
                editor.putInt("typeSize", type);
                editor.apply();
            }
        });

        mWebView.loadUrl(mData.getUrl());
        time();
    }

    private void setType(int type) {
        switch (type) {
            case 1:
                webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 2:
                webSettings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                webSettings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 4:
                webSettings.setTextSize(WebSettings.TextSize.LARGEST);
                break;
        }
    }

    private void initRecyclerView() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(BaseApplication.getContext(), userNameList, timeList, acclaimNumList, reviewContentList, statusList, imageTypeList);//需要修改
        rvReview.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.item_other_review_acclaim_icon_iv:
                        ImageView imageView = (ImageView) view;
                        if (imageView.getTag().equals("un_acclaim")) {
                            imageView.setTag("acclaim");
                            imageView.setImageResource(R.drawable.zan_red);
//                            LogUtils.d(Constant.debugName + "WebActivity position", position + "");
                            mRecyclerViewAdapter.addAcclaimNum(position);

                            mPresenter.postAcclaim(reviewId, uuid, 0, 1);//评论点赞+1
                        } else {
                            imageView.setTag("un_acclaim");
                            imageView.setImageResource(R.drawable.zan_grey);
                            mRecyclerViewAdapter.decideAcclaimNum(position);
                            // LogUtils.d(Constant.debugName+"position",position+"");
                            mPresenter.postAcclaim(reviewId, uuid, 0, -1);//评论点赞-1
                        }
                        break;
                }
            }
        });
    }

    private void newReview(String reviewContent) {
        mRecyclerViewAdapter.addData(userNameList.size(), location, TimeUtils.getTime() + "", 0, reviewContent, 0, imageType);
        reviewId = Md5.md5(reviewContent + TimeUtils.getTime() + uuid, "hello 310 lab");

        //Long newsId,Long reviewId,String reviewType,String reviewContent,String UID
        mPresenter.postReview(mData.getUniquekey(), reviewId, "1", reviewContent, uuid, TimeUtils.getTime());
    }

    //延迟启动recyclerview
    private void time() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //显示控件
                imZan.setVisibility(View.VISIBLE);
                rvReview.setVisibility(View.VISIBLE);
                tvZanNum.setVisibility(View.VISIBLE);
                empty1.setVisibility(View.VISIBLE);
                bottomView.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public void initData() {
        userNameList = new ArrayList<>();
        timeList = new ArrayList<>();
        acclaimNumList = new ArrayList<>();
        reviewContentList = new ArrayList<>();
        imageTypeList = new ArrayList<>();
        statusList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    //上传历史记录
    public void addValue(NewBean mData) {
        mUserTail = new UserTail();
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());

        sharedPreferences = getSharedPreferences("init", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        uuid = sharedPreferences.getString("uuid", "");
        location = sharedPreferences.getString("name", "");
        imageType = sharedPreferences.getInt("type", 0);
        typeSize = sharedPreferences.getInt("typeSize", 2);
        //LogUtils.d(Constant.debugName, "UUID:" + uuid);
        //LogUtils.d(Constant.debugName, "Location:" + location);
        //LogUtils.d(Constant.debugName, "imageType" + imageType);

        //取得信息处
        mUserTail.setScanTime(simpleDateFormat.format(date));
        mUserTail.setNewsId(mData.getUniquekey());
        mUserTail.setNewsTitle(mData.getTitle());
        mUserTail.setNewsType(mData.getCategory());
        mUserTail.setUserId(uuid);
        mUserTail.setNewsUrl(mData.getUrl());
        ///LogUtils.d(Constant.debugName+ " userTail   ",mUserTail.getUserId(), mUserTail.getNewsId(), mUserTail.getNewsTitle(), mUserTail.getNewsType(), mUserTail.getScanTime(), mUserTail.getNewsUrl());
        appendHistory(mData);
        mPresenter.postHistory(mUserTail.getUserId(), mUserTail.getNewsId(), mUserTail.getNewsTitle(), mUserTail.getNewsType(), mUserTail.getScanTime(), mUserTail.getNewsUrl());//需要修改
        mPresenter.getReviewList(mData.getUniquekey(), uuid);//获取评论
    }

    //将历史记录保存到数据库
    private void appendHistory(NewBean mData) {
        HistoryBeanDao dao = App.mSession.getHistoryBeanDao();
        HistoryBean historyBean = new HistoryBean();

        historyBean.setAuthorName(mData.getAuthorName());
        historyBean.setCategory(mData.getCategory());
        historyBean.setDate(mData.getDate());
        historyBean.setTitle(mData.getTitle());
        historyBean.setUniquekey(mData.getUniquekey());
        historyBean.setUrl(mData.getUrl());
        historyBean.setThumbnailPicS(mData.getThumbnailPicS());

        dao.insert(historyBean);
    }

    @OnClick({R.id.img_finish, R.id.img_collection, R.id.im_zan, R.id.rl_rv, R.id.rv_button, R.id.share_iv, R.id.adjust_type_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_finish:
                doFinish();
                break;
            case R.id.img_collection:
                collect();
                break;
            case R.id.im_zan:
                if (imZan.getTag().equals("un_zan")) {
                    imZan.setTag("zan");
                    imZan.setImageResource(R.drawable.zan_red);
                    ToastUtil.getInstance().showSuccess(getApplicationContext(), "点赞成功");
                    int i = ++articleAcclaimCount;
                    tvZanNum.setText(i + "赞");//设置赞数
                    mPresenter.postAcclaim(mData.getUniquekey(), uuid, 1, 1);//文章点赞+1
                } else {
                    imZan.setTag("un_zan");
                    imZan.setImageResource(R.drawable.zan_grey);
                    ToastUtil.showToast(getApplicationContext(), "取消点赞");
                    int i = --articleAcclaimCount;
                    tvZanNum.setText(i + "赞");//设置赞数
                    mPresenter.postAcclaim(mData.getUniquekey(), uuid, 1, -1);//文章点赞-1
                }
                break;
            case R.id.rv_button:
            case R.id.rl_rv:
                inputTextMsgDialog.show();
                break;
            case R.id.adjust_type_iv:
                adjustTypeDialog.show();
                break;
            case R.id.share_iv:
                //获取剪贴版
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", mData.getUrl());

                if (clip != null) {
                    clipboard.setPrimaryClip(clip);
                    ToastUtil.getInstance().showSuccess(App.getContext(), "复制分享链接成功");
                }


                break;

        }
    }

    /**
     * Determines whether the current entity class is stored
     *
     * @return
     */
    private boolean isCollection() {
        if (mData == null) {
            return false;
        }
        NewBeanDao dao = App.mSession.getNewBeanDao();
        NewBean data = dao.load(mData.getUniquekey());
        if (data != null) {
            return true;
        }
        return false;
    }


    /**
     * Remove or add a collection
     */
    private void collect() {
        Observable.just(isCollection())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(final Boolean aBoolean) throws Exception {
                        DialogUtils.getUtils().showDialog(aBoolean ? getString(R.string.dialog_cancel_collection) : getString(R.string.dialog_add_collection), new DialogUtils.OnItemClickListener() {
                            @Override
                            public void clickOk() {
                                NewBeanDao dao = App.mSession.getNewBeanDao();
                                if (aBoolean) {
                                    dao.delete(mData);

//                                    LogUtils.d(Constant.debugName + " collect", "取消收藏");
                                    mPresenter.postCollect(mData.getUniquekey(), uuid, -1);

                                } else {
                                    dao.insert(mData);
//                                    LogUtils.d(Constant.debugName + " collect", "收藏");
                                    mPresenter.postCollect(mData.getUniquekey(), uuid, 1);
                                }
                                //是否被收藏
                                mImgCollection.setImageResource(isCollection() ? R.drawable.icon_collection_2 : R.drawable.icon_collection_1);
                                EventBus.getDefault().post(new BaseBean(200));
                            }
                        });
                    }
                });
    }

    @Override
    public void showData(Object obj) {
        if (obj instanceof List) {
            if (((List) obj).get(0) instanceof CommentBean) {
                ///userNameList,timeList,acclaimNumList,reviewContentList
                List<CommentBean> list = (List<CommentBean>) obj;

                isAcclaim = list.get(0).getObject().getAcclaimStatus();//是否点赞

                articleAcclaimCount = list.get(0).getObject().getAcclaimCount();//文章点赞数
                commentCount = list.get(0).getObject().getCommentCount();//文章评论数

                for (int i = 1; i < list.size(); i++) {
                    userNameList.add(list.get(i).getObject().getName());
                    timeList.add(list.get(i).getObject().getCommentTime());
                    acclaimNumList.add(list.get(i).getObject().getAcclaimCount());//评论点赞数
                    reviewContentList.add(list.get(i).getObject().getContent());
                    imageTypeList.add(list.get(i).getObject().getImageType());
                    // LogUtils.d(Constant.debugName+"WebActivity   ",imageTypeList.get(0)+"");
                    statusList.add(list.get(i).getObject().getAcclaimStatus());
                }

                imZan.setImageResource((isAcclaim != 0) ? R.drawable.zan_red : R.drawable.zan_grey);
                if (isAcclaim != 0) {
                    imZan.setTag("zan");
                } else {
                    imZan.setTag("un_zan");
                }
                tvZanNum.setText(articleAcclaimCount + "赞");
                initRecyclerView();
            }
            if (((List) obj).get(0) instanceof UserTailBean) {
                List<UserTailBean> list = (List<UserTailBean>) obj;
//                LogUtils.d(Constant.debugName, list.get(0).getInfo());
            }
        }
    }

    @Override
    public void showError(String msg) {
        switch (WebPresenter.getRequireType()) {
            case 0:
                initRecyclerView();
//                LogUtils.e(Constant.debugName + "type = " + 0, msg);
                break;
            default:
//                LogUtils.e(Constant.debugName + "type = other", msg);
                break;
        }
    }

}
