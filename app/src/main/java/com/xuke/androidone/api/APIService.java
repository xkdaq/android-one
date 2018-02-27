package com.xuke.androidone.api;

import com.google.gson.JsonObject;
import com.xuke.androidone.model.bean.login.ResultBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    /**
     * 基础请求地址
     */
    String HOST = "http://120.78.147.1/";

    @POST("ssm/user/login")
    Call<ResultBean<JsonObject>> login(@Body RequestBody body);

    @POST("ssm/user/register")
    Call<ResultBean<JsonObject>> register(@Body RequestBody body);

}