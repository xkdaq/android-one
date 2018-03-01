package com.xuke.androidone.presenter.movie;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.xuke.androidone.cache.Cache;
import com.xuke.androidone.contract.movie.MovieMainContract;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.xuke.androidone.model.movie.MovieMainModel;
import com.xuke.androidone.view.activity.MovieDetailActivity;


/**
 * Created by xuke on 2018/1/24.
 * 热门电影的Presenter
 */

public class MovieMainPresenter extends MovieMainContract.MovieMainPresenter {

    @NonNull
    public static MovieMainPresenter newInstance() {
        return new MovieMainPresenter();
    }

    @Override
    public MovieMainContract.IMovieMainModel getModel() {
        return MovieMainModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadHotMovieList() {
        if (mIView == null || mIModel == null) {
            return;
        }
        mRxManager.register(mIModel.getHotMovieList().subscribe(hotMovieBean -> {
            //Logger.e("hotMovieBean:" + hotMovieBean.toString());
            if (mIView == null) {
                return;
            }
            mIView.updateContentList(hotMovieBean.getSubjects());
            Cache.saveHotMovieCache(hotMovieBean.getSubjects());
        }, throwable -> {
            if (mIView == null) {
                if (mIView.isVisiable()) {
                    mIView.showToast("请检查网络连接");
                }
                if (Cache.getHotMovieCache().size() == 0) {
                    mIView.showNetworkError();
                } else {
                    mIView.updateContentList(Cache.getHotMovieCache());
                }
            }
        }));
    }

    @Override
    public void onItemClick(int position, SubjectsBean item, ImageView imageView) {
        Logger.e("item is clicked");
        MovieDetailActivity.start(mIView.getBindActivity(), item, imageView);
    }

    @Override
    public void onHeaderClick() {
        Logger.e("header is clicked");
    }
}
