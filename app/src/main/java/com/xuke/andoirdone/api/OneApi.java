package com.xuke.andoirdone.api;

import com.xuke.andoirdone.model.bean.one.OneBean;
import com.xuke.andoirdone.model.bean.one.OneIdBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xuke on 2018/2/26.
 * 首页的api
 */
public interface OneApi {
    String HOST = "http://v3.wufazhuce.com:8000/";

    /**
     * 获取首页id
     */
    @GET("api/hp/idlist/0")
    Call<OneIdBean> getHomeId();


    /**
     * 获取每条one的详情
     */
    @GET("api/hp/detail/{newsId}")
    Observable<OneBean> getHomeContent(@Path("newsId") String newsId);

}
