package com.wei.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wei.base.base.BaseActivity;
import com.wei.base.base.BaseAdapter;
import com.wei.base.base.BasePresenter;
import com.wei.base.base.BaseViewHolder;
import com.wei.news.App;
import com.wei.news.R;
import com.wei.news.dao.NewBeanDao;
import com.wei.news.model.BaseBean;
import com.wei.news.model.NewBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：310Lab
 * 时间：2019/4/2 17:03
 * 邮箱：1760567382@qq.com
 * 功能：新闻收藏页面
 */
public class CollectionActivity extends BaseActivity
{
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private BaseAdapter mAdapter;


    @Override
    protected BasePresenter createPresenter()
    {
        return null;
    }

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_collection;
    }

    @Override
    public void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mTvTitle.setText(getString(R.string.tv_collect_title));
        EventBus.getDefault().register(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseAdapter<NewBean>(this, R.layout.item_new)
        {
            @Override
            public void convert(BaseViewHolder holder, int position, final NewBean data)
            {
                holder.setText(data.getTitle(), R.id.item_title)
                        .setText(data.getAuthorName(), R.id.item_author)
                        .setText(data.getDate(), R.id.item_time)
                        .setImageResource(data.getThumbnailPicS(), R.id.item_img)
                        .itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent it = new Intent(CollectionActivity.this, WebViewActivity.class);
                        it.putExtra(WebViewActivity.class.getSimpleName(), data);
                        startActivity(it);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        updateCollection();
    }

    /**
     * 查询收藏的新闻信息
     */
    private void updateCollection()
    {
        NewBeanDao dao = App.mSession.getNewBeanDao();
        List<NewBean> list = dao.loadAll();
        mAdapter.setData(list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void obEventMain(BaseBean data)
    {
        if (200 == data.getCode())
        {
            updateCollection();
        }
    }


    @OnClick(R.id.img_finish)
    public void onViewClicked()
    {
        doFinish();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
