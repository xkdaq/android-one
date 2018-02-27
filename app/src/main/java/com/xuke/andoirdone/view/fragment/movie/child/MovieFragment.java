package com.xuke.andoirdone.view.fragment.movie.child;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuke.andoirdone.R;
import com.xuke.andoirdone.adapter.HotMovieAdapter;
import com.xuke.andoirdone.contract.movie.MovieMainContract;
import com.xuke.andoirdone.model.bean.douban.child.SubjectsBean;
import com.xuke.andoirdone.presenter.movie.MovieMainPresenter;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseRecycleFragment;
import com.zyw.horrarndoo.sdk.utils.ResourcesUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xuke on 2018/1/23.
 * 电影
 */
public class MovieFragment extends BaseRecycleFragment<MovieMainContract.MovieMainPresenter,
        MovieMainContract.IMovieMainModel> implements MovieMainContract.IMovieMainView {

    @BindView(R.id.rv_hot_movie)
    RecyclerView rvHotMovie;

    private HotMovieAdapter mHotMovieAdapter;
    private View headView;

    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MovieMainPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mHotMovieAdapter = new HotMovieAdapter(R.layout.item_hot_movie);
        rvHotMovie.setAdapter(mHotMovieAdapter);
        rvHotMovie.setLayoutManager(new LinearLayoutManager(mActivity));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadHotMovieList();
    }

    @Override
    public void updateContentList(List<SubjectsBean> list) {
        if (mHotMovieAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mHotMovieAdapter.addData(list);
        }
    }

    private void initRecycleView(List<SubjectsBean> list) {
        mHotMovieAdapter = new HotMovieAdapter(R.layout.item_hot_movie, list);
        mHotMovieAdapter.setOnItemClickListener((adapter, view, position) -> mPresenter.onItemClick(position + 1, (SubjectsBean) adapter.getItem(position), (ImageView) view.findViewById(R.id.iv_moive_photo)));
        initHeadView();
        mHotMovieAdapter.addHeaderView(headView);
        rvHotMovie.setAdapter(mHotMovieAdapter);
    }

    private void initHeadView() {
        if (headView == null) {
            headView = ResourcesUtils.inflate(R.layout.sub_movie_top_header);
        }
        headView.findViewById(R.id.ll_movie_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onHeaderClick();
            }
        });
    }


    @Override
    public void showNetworkError() {
        mHotMovieAdapter.setEmptyView(errorView);
    }

    @Override
    protected void onErrorViewClick(View view) {
        mPresenter.loadHotMovieList();
    }

    @Override
    protected void showLoading() {
        mHotMovieAdapter.setEmptyView(loadingView);
    }
}
