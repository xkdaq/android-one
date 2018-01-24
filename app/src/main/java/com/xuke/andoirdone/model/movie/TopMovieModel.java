package com.xuke.andoirdone.model.movie;

import android.support.annotation.NonNull;

import com.xuke.andoirdone.api.DoubanApi;
import com.xuke.andoirdone.contract.movie.TopMovieContract;
import com.xuke.andoirdone.model.bean.douban.HotMovieBean;
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
