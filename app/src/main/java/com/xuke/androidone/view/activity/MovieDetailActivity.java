package com.xuke.androidone.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuke.androidone.R;
import com.xuke.androidone.adapter.MovieDetailAdapter;
import com.xuke.androidone.contract.movie.MovieDetailContract;
import com.xuke.androidone.model.bean.douban.MovieDetailBean;
import com.xuke.androidone.model.bean.douban.child.PersonBean;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.xuke.androidone.presenter.movie.MovieDetailPresenter;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.activity.BaseMVPCompatActivity;
import com.zyw.horrarndoo.sdk.utils.DisplayUtils;
import com.zyw.horrarndoo.sdk.utils.ResourcesUtils;
import com.zyw.horrarndoo.sdk.widgets.CompatNestedScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xuke.androidone.utils.Accounts.INTENT_KEY_MOVIE_SUBJECTBEAN;
import static com.zyw.horrarndoo.sdk.utils.StatusBarUtils.getStatusBarHeight;

/**
 * Created by xuke on 2018/2/28.
 * 电影详情
 */

public class MovieDetailActivity extends BaseMVPCompatActivity<MovieDetailContract.MovieDetailPresenter, MovieDetailContract.IMovieDetailModel> implements MovieDetailContract.IMovieDetailView {

    @BindView(R.id.iv_header_bg)
    ImageView ivHeaderBg;
    @BindView(R.id.iv_movie_photo)
    ImageView ivMoviePhoto;
    @BindView(R.id.tv_movie_rating_rate)
    TextView tvMovieRatingRate;
    @BindView(R.id.tv_movie_rating_number)
    TextView tvMovieRatingNumber;
    @BindView(R.id.ll_rating)
    LinearLayout llRating;
    @BindView(R.id.tv_collect_count)
    TextView tvCollectCount;
    @BindView(R.id.tv_movie_directors)
    TextView tvMovieDirectors;
    @BindView(R.id.tv_movie_casts)
    TextView tvMovieCasts;
    @BindView(R.id.tv_movie_genres)
    TextView tvMovieGenres;
    @BindView(R.id.tv_movie_date)
    TextView tvMovieDate;
    @BindView(R.id.tv_movie_city)
    TextView tvMovieCity;
    @BindView(R.id.tv_movie_sub_title)
    TextView tvMovieSubTitle;
    @BindView(R.id.tv_moive_summary)
    TextView tvMoiveSummary;
    @BindView(R.id.rv_movie_detail)
    RecyclerView rvMovieDetail;
    @BindView(R.id.nsv_scrollview)
    CompatNestedScrollView nsvScrollview;
    @BindView(R.id.iv_toolbar_bg)
    ImageView ivToolbarBg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SubjectsBean mSubjectBean;
    private MovieDetailAdapter mMovieDetailAdapter;
    private View errorView;

    public static void start(Activity context, SubjectsBean subjectsBean, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(INTENT_KEY_MOVIE_SUBJECTBEAN, subjectsBean);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView, ResourcesUtils.getString(R.string.transition_movie_img));
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MovieDetailPresenter.newInstance();
    }

    @Override
    protected void initData() {
        super.initData();
        if (getIntent() != null) {
            mSubjectBean = (SubjectsBean) getIntent().getSerializableExtra(INTENT_KEY_MOVIE_SUBJECTBEAN);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolbar, mSubjectBean.getTitle());
        initHeaderView(mSubjectBean);

        mMovieDetailAdapter = new MovieDetailAdapter(R.layout.item_movie_detail_person);
        rvMovieDetail.setAdapter(mMovieDetailAdapter);
        rvMovieDetail.setLayoutManager(new LinearLayoutManager(this));
        rvMovieDetail.setNestedScrollingEnabled(false);
        nsvScrollview.bindAlphaView(ivToolbarBg);
        mPresenter.loadMovieDetail(mSubjectBean.getId());

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvMovieDetail, false);
        errorView.setOnClickListener(v -> mPresenter.loadMovieDetail(mSubjectBean.getId()));
    }

    /**
     * 加载头部view
     * */
    private void initHeaderView(SubjectsBean subjectsBean) {
        tvMovieRatingNumber.setText(String.valueOf(subjectsBean.getRating().getAverage()));
        tvCollectCount.setText(String.valueOf(subjectsBean.getCollect_count()));
        tvMovieDirectors.setText(subjectsBean.getDirectorsString());
        tvMovieCasts.setText(subjectsBean.getActorsString());
        tvMovieGenres.setText(subjectsBean.getGenresString());
        tvMovieDate.setText(subjectsBean.getYear());
        Glide.with(this)
                .load(subjectsBean.getImages().getLarge())
                .asBitmap()
                .into(ivMoviePhoto);

        //显示网络虚化图片
        DisplayUtils.displayBlurImg(this, subjectsBean.getImages().getLarge(), ivHeaderBg);
        DisplayUtils.displayBlurImg(this, subjectsBean.getImages().getLarge(), ivToolbarBg);

        int headerBgHeight = toolbar.getLayoutParams().height + getStatusBarHeight(this);
        // 使背景图向上移动到图片的最低端，保留（toolbar+状态栏）的高度
        // 实际上此时ivToolbarBg高度还是330dp，只是除了toolbar外，剩下部分是透明状态
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) ivToolbarBg.getLayoutParams();
        int marginTop = ivToolbarBg.getLayoutParams().height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
    }


    @Override
    public void showMovieDetail(MovieDetailBean bean) {
        if (mMovieDetailAdapter.getData().size() == 0) {
            tvMovieCity.setText("制片国家/地区： " + bean.getCountriesString());
            tvMovieSubTitle.setText(bean.getAkaString());
            tvMoiveSummary.setText(bean.getSummary());
            initRecycleView(bean);
        } else {
            mMovieDetailAdapter.addData(bean.getCasts());
        }
    }
    /**
     * 导演和主演recyclerview
     * */
    private void initRecycleView(MovieDetailBean bean) {
        List<PersonBean> list = bean.getDirectors();
        list.addAll(bean.getCasts());
        mMovieDetailAdapter = new MovieDetailAdapter(R.layout.item_movie_detail_person, list);
        mMovieDetailAdapter.setOnItemClickListener((adapter, view, position) ->
                mPresenter.onItemClick(position, (PersonBean) adapter.getItem(position)));
        rvMovieDetail.setAdapter(mMovieDetailAdapter);
    }

    @Override
    public void showNetworkError() {
        mMovieDetailAdapter.setEmptyView(errorView);
    }


    @OnClick(R.id.ll_movie_header)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_movie_header:
                mPresenter.onHeaderClick(mSubjectBean);
                break;
        }
    }

}
