package com.xuke.androidone.model.home;

import android.support.annotation.NonNull;

import com.xuke.androidone.api.OneApi;
import com.xuke.androidone.contract.home.HomeContenContract;
import com.xuke.androidone.model.bean.one.OneBean;
import com.zyw.horrarndoo.sdk.base.BaseModel;
import com.zyw.horrarndoo.sdk.helper.RetrofitCreateHelper;
import com.zyw.horrarndoo.sdk.helper.RxHelper;

import io.reactivex.Observable;

/**
 * Created by xuke on 2018/2/27.
 * 首页的Model
 */

public class HomeContentModel extends BaseModel implements HomeContenContract.IHomeContentModel {

    @NonNull
    public static HomeContentModel newInstance() {
        return new HomeContentModel();
    }

    @Override
    public Observable<OneBean> getHomeContent(String id) {
        return RetrofitCreateHelper
                .createApi(OneApi.class, OneApi.HOST)
                .getHomeContent(id)
                .compose(RxHelper.rxSchedulerHelper());
    }
}
