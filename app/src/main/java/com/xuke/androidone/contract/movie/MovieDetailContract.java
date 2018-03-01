package com.xuke.androidone.contract.movie;

import com.xuke.androidone.model.bean.douban.MovieDetailBean;
import com.xuke.androidone.model.bean.douban.child.PersonBean;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.IBaseActivity;
import com.zyw.horrarndoo.sdk.base.IBaseModel;

import io.reactivex.Observable;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public interface MovieDetailContract {


    abstract class MovieDetailPresenter extends BasePresenter<IMovieDetailModel,
            IMovieDetailView> {
        /**
         * 加载电影详情
         */
        public abstract void loadMovieDetail(String id);

        /**
         * item点击事件
         */
        public abstract void onItemClick(int position, PersonBean item);

        /**
         * header点击事件
         */
        public abstract void onHeaderClick(SubjectsBean bean);
    }


    interface IMovieDetailModel extends IBaseModel {
        /**
         * 获取电影详情
         */
        Observable<MovieDetailBean> getMovieDetail(String id);
    }


    interface IMovieDetailView extends IBaseActivity {
        /**
         * 显示电影详情
         *
         */
        void showMovieDetail(MovieDetailBean bean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
