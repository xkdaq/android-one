package com.xuke.androidone.api;

import com.google.gson.JsonObject;
import com.xuke.androidone.model.bean.BaseResultBean;
import com.xuke.androidone.model.bean.login.ResultBean;
import com.xuke.androidone.model.bean.login.UserBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    /**
     * 基础请求地址
     */
    String HOST = "http://www.wanandroid.cn";

    String URL = "/userProfile/userProfileAction!doNotNeedSessionAndSecurity_userProfileHandler.action";

    @FormUrlEncoded
    @POST(URL)
    Call<BaseResultBean<UserBean>> login(@Field("jsonStr") String result);

    @FormUrlEncoded
    @POST(URL)
    Call<ResultBean<JsonObject>> register(@Body RequestBody body);

}