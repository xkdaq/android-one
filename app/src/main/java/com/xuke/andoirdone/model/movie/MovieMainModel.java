package com.xuke.andoirdone.model.movie;

import android.support.annotation.NonNull;

import com.xuke.andoirdone.api.DoubanApi;
import com.xuke.andoirdone.contract.movie.MovieMainContract;
import com.xuke.andoirdone.model.bean.douban.HotMovieBean;
import com.zyw.horrarndoo.sdk.base.BaseModel;
import com.zyw.horrarndoo.sdk.helper.RetrofitCreateHelper;
import com.zyw.horrarndoo.sdk.helper.RxHelper;

import io.reactivex.Observable;

/**
 * Created by xuke on 2018/1/24.
 * 获取主页热映列表
 */

public class MovieMainModel extends BaseModel implements MovieMainContract.IMovieMainModel {

    @NonNull
    public static MovieMainModel newInstance() {
        return new MovieMainModel();
    }

    @Override
    public Observable<HotMovieBean> getHotMovieList() {
        return RetrofitCreateHelper
                .createApi(DoubanApi.class, DoubanApi.HOST)
                .getHotMovie()
                .compose(RxHelper.<HotMovieBean>rxSchedulerHelper());
    }
}
