package com.xuke.androidone.presenter.movie;

import android.support.annotation.NonNull;

import com.xuke.androidone.contract.movie.MovieDetailContract;
import com.xuke.androidone.model.bean.douban.child.PersonBean;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.xuke.androidone.model.movie.MovieDetailModel;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public class MovieDetailPresenter extends MovieDetailContract.MovieDetailPresenter {

    @NonNull
    public static MovieDetailPresenter newInstance() {
        return new MovieDetailPresenter();
    }

    @Override
    public void loadMovieDetail(String id) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.getMovieDetail(id).subscribe(bean -> {
            if (mIView == null)
                return;

            mIView.showMovieDetail(bean);
        }, throwable -> {
            if (mIView == null)
                return;
            mIView.showToast("Network error.");
            mIView.showNetworkError();
        }));
    }

    @Override
    public void onItemClick(int position, PersonBean item) {


    }

    @Override
    public void onHeaderClick(SubjectsBean bean) {
    }


    @Override
    public MovieDetailModel getModel() {
        return MovieDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
