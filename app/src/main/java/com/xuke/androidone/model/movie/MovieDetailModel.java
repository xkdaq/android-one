package com.xuke.androidone.model.movie;

import android.support.annotation.NonNull;

import com.xuke.androidone.api.DoubanApi;
import com.xuke.androidone.contract.movie.MovieDetailContract;
import com.xuke.androidone.model.bean.douban.MovieDetailBean;
import com.xuke.androidone.model.bean.douban.child.PersonBean;
import com.zyw.horrarndoo.sdk.base.BaseModel;
import com.zyw.horrarndoo.sdk.helper.RetrofitCreateHelper;
import com.zyw.horrarndoo.sdk.helper.RxHelper;

import io.reactivex.Observable;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public class MovieDetailModel extends BaseModel implements MovieDetailContract.IMovieDetailModel {
    @NonNull
    public static MovieDetailModel newInstance() {
        return new MovieDetailModel();
    }

    @Override
    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getMovieDetail(id)
                .compose(RxHelper.rxSchedulerHelper())
                .map(bean -> {
                    for (PersonBean bean1 : bean.getCasts()) {
                        bean1.setType("主演");
                    }
                    for (PersonBean bean2 : bean.getDirectors()) {
                        bean2.setType("导演");
                    }
                    return bean;
                });
    }
}
