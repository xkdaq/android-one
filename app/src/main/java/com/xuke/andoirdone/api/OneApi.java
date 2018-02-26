package com.xuke.andoirdone.api;

import com.xuke.andoirdone.model.bean.one.OneIdBean;

import retrofit2.Call;
import retrofit2.http.GET;

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

}
