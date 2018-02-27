package com.xuke.androidone.model.movie;

import android.support.annotation.NonNull;

import com.xuke.androidone.api.DoubanApi;
import com.xuke.androidone.contract.movie.TopMovieContract;
import com.xuke.androidone.model.bean.douban.HotMovieBean;
import com.zyw.horrarndoo.sdk.base.BaseModel;
import com.zyw.horrarndoo.sdk.helper.RetrofitCreateHelper;
import com.zyw.horrarndoo.sdk.helper.RxHelper;

import io.reactivex.Observable;

/**
 * Created by xuke on 2018/1/24.
 * 获取top250电影列表
 */

public class TopMovieModel extends BaseModel implements TopMovieContract.ITopMovieModel {
    @NonNull
    public static TopMovieModel newInstance() {
        return new TopMovieModel();
    }

    @Override
    public Observable<HotMovieBean> getTopMovieList(int start, int count) {
        return RetrofitCreateHelper
                .createApi(DoubanApi.class, DoubanApi.HOST)
                .getMovieTop250(start, count)
                .compose(RxHelper.<HotMovieBean>rxSchedulerHelper());
    }
}
