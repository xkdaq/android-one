package com.xuke.androidone.view.fragment.movie.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuke.androidone.R;
import com.xuke.androidone.adapter.TopMovieAdapter;
import com.xuke.androidone.contract.movie.TopMovieContract;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.xuke.androidone.presenter.movie.TopMoviePresenter;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseRecycleFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public class TopMoiveFragment extends BaseRecycleFragment<TopMovieContract.TopMoivePresenter, TopMovieContract.ITopMovieModel> implements TopMovieContract.ITopMovieView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_top_movie)
    RecyclerView rvTopMovie;

    private TopMovieAdapter mTopMovieAdapter;

    public static TopMoiveFragment newInstance() {
        Bundle args = new Bundle();
        TopMoiveFragment fragment = new TopMoiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_top;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadTopMovieList();
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //返回键
        //toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        //toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mTopMovieAdapter = new TopMovieAdapter(R.layout.item_top_movie);
        rvTopMovie.setAdapter(mTopMovieAdapter);
        rvTopMovie.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void updateContentList(List<SubjectsBean> list) {
        if (mTopMovieAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mTopMovieAdapter.addData(list);
        }
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadTopMovieList();
    }

    @Override
    protected void showLoading() {
        mTopMovieAdapter.setEmptyView(loadingView);
    }

    @Override
    public void showNetworkError() {
        mTopMovieAdapter.setEmptyView(errorView);
    }

    @Override
    public void showNoMoreData() {
        mTopMovieAdapter.loadMoreEnd(true);
    }

    @Override
    public void showLoadMoreError() {
        mTopMovieAdapter.loadMoreFail();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return TopMoviePresenter.newInstance();
    }

    @Override
    public void onLoadMoreRequested() {
        //这里loadMoreComplete要放在前面，避免在Presenter.loadMoreNewsList处理中直接showNoMoreData，出现无限显示加载item
        mTopMovieAdapter.loadMoreComplete();
        mPresenter.loadMoreTopMovie();
    }

    private void initRecycleView(List<SubjectsBean> list) {
        mTopMovieAdapter = new TopMovieAdapter(R.layout.item_top_movie, list);
        mTopMovieAdapter.setOnLoadMoreListener(this, rvTopMovie);
        mTopMovieAdapter.setOnItemClickListener((adapter, view, position) -> mPresenter.onItemClick(position, (SubjectsBean) adapter.getItem(position), view.findViewById(R.id.iv_top_moive_photo)));
        rvTopMovie.setAdapter(mTopMovieAdapter);
        rvTopMovie.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
    }
}
