package com.wei.news.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wei.base.base.BaseAdapter;
import com.wei.base.base.BaseFragment;
import com.wei.base.base.BaseView;
import com.wei.base.base.BaseViewHolder;
import com.wei.base.utils.CircleImageView;
import com.wei.base.utils.LogUtils;
import com.wei.news.App;
import com.wei.news.R;
import com.wei.news.config.Config;
import com.wei.news.config.Constant;
import com.wei.news.model.NewBean;
import com.wei.news.model.NewTypeBean;
import com.wei.news.model.UserInfoBean;
import com.wei.news.presenter.NewPresenter;
import com.wei.news.ui.activity.WebViewActivity;
import com.wei.news.utils.FitPopupUtil;
import com.wei.news.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * 作者：赵若位
 * 时间：2018/8/1 12:57
 * 邮箱：1070138445@qq.com
 * 功能：
 */
public class NewFragment extends BaseFragment<NewPresenter> implements BaseView {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @Bind(R.id.up_arrow_iv)
    CircleImageView upArrowIv;


    private BaseAdapter mAdapter;
    private String uuid;

    private NewTypeBean mData = null;
    private boolean isShow = true;
    private int disy = 0;
    private boolean isFirstRequest = false;

    public static NewFragment getFragment(NewTypeBean data) {
        NewFragment fragment = new NewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NewFragment.class.getSimpleName(), data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected NewPresenter createPresenter() {
        return new NewPresenter(this);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_new;
    }


    @Override
    public void initView(View rootView) {
        super.initView(rootView);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("init", MODE_PRIVATE);
        uuid = sharedPreferences.getString("uuid", "");
        isFirstRequest = true;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new BaseAdapter<NewBean>(mActivity, R.layout.item_new) {
            @Override
            public void convert(BaseViewHolder holder, int position, final NewBean data) {

                holder.setText(data.getTitle(), R.id.item_title)
                        .setText(data.getAuthorName(), R.id.item_author)
                        .setText(data.getDate(), R.id.item_time)
                        .setImageResource(data.getThumbnailPicS(), R.id.item_img)
                        .setOnClickListener(R.id.none_interest_iv, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FitPopupUtil fitPopupUtil = new FitPopupUtil(getActivity());
                                fitPopupUtil.setOnClickListener(new FitPopupUtil.OnCommitClickListener() {
                                    @Override
                                    public void onClick(String reason) {
                                        //Toast.makeText(App.getContext(),reason,Toast.LENGTH_SHORT).show();
                                        mPresenter.postEnjoy(data.getUniquekey(),uuid,reason);
                                    }
                                });
                                fitPopupUtil.showPopup(view);
                            }
                        })

                        .itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Intent it = new Intent(mActivity, WebViewActivity.class);
                        it.putExtra(WebViewActivity.class.getSimpleName(), data);
                        startActivity(it);
                    }
                });
            }
        };
        //LogUtils.d(Constant.debugName+"NewsFragment   ","add mAdapter");
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                if (firstVisibleItem == 0) {
                    if (!isShow) {
                        isShow = true;
                        upArrowIv.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (disy > 25 && isShow) {
                        isShow = false;
                        upArrowIv.setVisibility(View.GONE);
                        disy = 0;
                    }
                    if (disy < -25 && !isShow) {
                        isShow = true;
                        upArrowIv.setVisibility(View.VISIBLE);
                        disy = 0;
                    }
                }

                if ((isShow && dy > 0) || (!isShow && dy < 0)) {
                    disy += dy;
                }
            }
        });

        setRefreshListener();
        mData = getArguments().getParcelable(NewFragment.class.getSimpleName());
        isUIVisible = true;

    }


    @Override
    protected void netWork() {
        super.netWork();
        if (mData == null) {
            return;
        }
        isFirstRequest = false;
        mPresenter.getNewsList(mData.getDescript(),uuid);
    }

    /**
     * 刷新监听
     */
    private void setRefreshListener() {
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refresh) {
                //isRequest = true;
                initData();
                mRefresh.finishRefresh(Config.SMARTREFRESH_MAX_REFRESH_TIME);
            }
        });
        mRefresh.setEnableLoadmore(false);
    }

//    private List<NewBean> getRecentNewsList(){
//        RecentNewsBeanDao dao = App.mSession.getRecentNewsBeanDao();
//        List<RecentNewsBean> recentNewsBeans = dao.loadAll();
//        List<NewBean> newBeans = new ArrayList<>();
//
//        for (int i = 0; i < recentNewsBeans.size(); i++){
//            NewBean newBean = new NewBean();
//
//            newBean.setUniquekey(recentNewsBeans.get(i).getUniquekey());
//            newBean.setTitle(recentNewsBeans.get(i).getTitle());
//            newBean.setDate(recentNewsBeans.get(i).getDate());
//            newBean.setCategory(recentNewsBeans.get(i).getCategory());
//            newBean.setAuthorName(recentNewsBeans.get(i).getAuthorName());
//            newBean.setUrl(recentNewsBeans.get(i).getUrl());
//            newBean.setThumbnailPicS(recentNewsBeans.get(i).getThumbnailPicS());
//
//            newBeans.add(newBean);
//        }
//
//        return newBeans;
//    }
//
//    private void setRecentNewsList(List<NewBean> list){
//        RecentNewsBeanDao dao = App.mSession.getRecentNewsBeanDao();
//        LogUtils.d(Constant.debugName+"setRecentNewsList   ",(list.size())+"条");
//        for (int i = 0; i < list.size(); i++){
//            RecentNewsBean recentNewsBean = new RecentNewsBean();
//
//            recentNewsBean.setUniquekey(list.get(i).getUniquekey());
//            recentNewsBean.setTitle(list.get(i).getTitle());
//            recentNewsBean.setDate(list.get(i).getDate());
//            recentNewsBean.setCategory(list.get(i).getCategory());
//            recentNewsBean.setAuthorName(list.get(i).getAuthorName());
//            recentNewsBean.setUrl(list.get(i).getUrl());
//            recentNewsBean.setThumbnailPicS(list.get(i).getThumbnailPicS());
//
//            dao.insert(recentNewsBean);
//        }
//
//        LogUtils.d(Constant.debugName+"setRecentNewsList   ","保存成功");
//    }

    @Override
    public void showData(Object obj) {
        if (obj instanceof List) {
            if (((List) obj).size() < 1){
                if (isFirstRequest){
                    //mAdapter.setData(getRecentNewsList());
                }else {
                    if (mRefresh.isRefreshing()) {
                        mRefresh.finishRefresh();
                    }
                    alert("~休息一下吧~");
                }
            }else {
                if (((List) obj).get(0) instanceof NewBean){
                    List<NewBean> list = (List<NewBean>) obj;
                    LogUtils.d(Constant.debugName+"NewFragment   ",list.size()+"");
                    mAdapter.setData(list);


                    if (mRefresh.isRefreshing()) {
                        alert("为你成功推荐"+list.size()+"条");
                        //ToastUtil.getInstance().showSuccess(App.getContext(), "数据刷新成功");
                        //isRequest = false;
                        mRefresh.finishRefresh();
                    }
                }else if (((List) obj).get(0) instanceof UserInfoBean){
                    List<UserInfoBean> list = (List<UserInfoBean>) obj;
                    LogUtils.d(Constant.debugName+"postEnjoy",list.get(0).getInfo());
                    ToastUtil.getInstance().showSuccess(App.getContext(), "反馈成功");
                }
            }
        }
    }

    @Override
    public void showError(String msg) {
        if (mRefresh.isRefreshing()) {
            mRefresh.finishRefresh();
        }
        alert(msg);
    }

    @Override
    public void onDestroyView() {
        //setRecentNewsList(mAdapter.getRecentNews());
        //mAdapter.getRecentNews();
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.up_arrow_iv)
    public void onViewClicked() {
        mRecyclerView.smoothScrollToPosition(0);
        mRefresh.autoRefresh();
    }

}
