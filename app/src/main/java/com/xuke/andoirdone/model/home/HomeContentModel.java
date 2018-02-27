package com.xuke.andoirdone.model.home;

import android.support.annotation.NonNull;

import com.xuke.andoirdone.api.OneApi;
import com.xuke.andoirdone.contract.home.HomeContenContract;
import com.xuke.andoirdone.model.bean.one.OneBean;
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
