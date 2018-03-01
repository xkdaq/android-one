package com.xuke.androidone.presenter.movie;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.xuke.androidone.contract.movie.TopMovieContract;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.xuke.androidone.model.movie.TopMovieModel;
import com.xuke.androidone.view.activity.MovieDetailActivity;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public class TopMoviePresenter extends TopMovieContract.TopMoivePresenter {

    private int mStart;
    private int mCount = 30;
    private boolean isLoading;

    @NonNull
    public static TopMoviePresenter newInstance() {
        return new TopMoviePresenter();
    }

    @Override
    public void loadTopMovieList() {
        if (mIModel == null || mIView == null)
            return;
        mStart = 0;
        mRxManager.register(mIModel.getTopMovieList(mStart, mCount).subscribe(hotMovieBean -> {
            if (mIView == null)
                return;

            mStart += mCount;
            mIView.updateContentList(hotMovieBean.getSubjects());
        }, throwable -> {
            if (mIView == null) {
                return;
            }

            if (mIView.isVisiable()) {
                mIView.showToast("Network error.");
            }

            mIView.showNetworkError();
        }));
    }

    @Override
    public void loadMoreTopMovie() {
        if (!isLoading) {
            isLoading = true;
            mRxManager.register(mIModel.getTopMovieList(mStart, mCount).subscribe(hotMovieBean -> {
                isLoading = false;
                if (mIView == null)
                    return;

                if (hotMovieBean != null && hotMovieBean.getSubjects() != null &&
                        hotMovieBean.getSubjects().size() > 0) {
                    mStart += mCount;
                    mIView.updateContentList(hotMovieBean.getSubjects());
                } else {
                    mIView.showNoMoreData();
                }
            }, throwable -> {
                isLoading = false;
                if (mIView != null) {
                    mIView.showLoadMoreError();
                }
            }));
        }
    }

    @Override
    public void onItemClick(int position, SubjectsBean item, ImageView imageView) {
        MovieDetailActivity.start(mIView.getBindActivity(), item, imageView);
    }

    @Override
    public TopMovieModel getModel() {
        return TopMovieModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
