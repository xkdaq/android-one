package com.xuke.andoirdone.api;

import com.google.gson.JsonObject;

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
    Call<JsonObject> login(@Body RequestBody body);

}