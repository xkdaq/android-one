package com.xuke.androidone.contract.movie;

import android.widget.ImageView;

import com.xuke.androidone.model.bean.douban.HotMovieBean;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.IBaseFragment;
import com.zyw.horrarndoo.sdk.base.IBaseModel;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by xuke on 2018/1/24.
 * <p></p>
 */

public interface MovieMainContract {

    /**
     * 热门电影Presenter
     * */
    abstract class MovieMainPresenter extends BasePresenter<IMovieMainModel, IMovieMainView> {
        /**
         * 加载最新的最热电影
         */
        public abstract void loadHotMovieList();

        /**
         * item点击事件
         */
        public abstract void onItemClick(int position, SubjectsBean item, ImageView imageView);

        /**
         * Header被点击
         */
        public abstract void onHeaderClick();
    }

    /**
     * 热门电影Model
     * */
    interface IMovieMainModel extends IBaseModel {
        /**
         * 获取最热电影
         */
        Observable<HotMovieBean> getHotMovieList();
    }

    /**
     * 热门电影View
     * */
    interface IMovieMainView extends IBaseFragment {
        /**
         * 更新界面list
         */
        void updateContentList(List<SubjectsBean> list);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
