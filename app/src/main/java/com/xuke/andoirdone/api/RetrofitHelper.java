package com.xuke.andoirdone.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.xuke.andoirdone.model.bean.login.LoginBean;
import com.xuke.andoirdone.model.bean.login.RegisterBean;
import com.xuke.andoirdone.model.bean.login.ResultBean;
import com.zyw.horrarndoo.sdk.helper.okhttp.CacheInterceptor;
import com.zyw.horrarndoo.sdk.helper.okhttp.HttpCache;
import com.zyw.horrarndoo.sdk.helper.okhttp.TrustManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Horrarndoo on 2017/9/7.
 * <p>
 */

public class RetrofitHelper {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //SSL证书
            .sslSocketFactory(TrustManager.getUnsafeOkHttpClient())
            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            //打印日志
            .addInterceptor(interceptor)
            //设置Cache拦截器
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(HttpCache.getCache())
            //time out
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();
    private static RetrofitHelper mInstance;
    private static Gson gson;
    private final APIService mAPIService;

    private RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mAPIService = retrofit.create(APIService.class);
    }

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitHelper();
                    gson = new GsonBuilder().create();
                }
            }
        }
        return mInstance;
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @// TODO: 2018/1/25 登录
     */
    public Call<ResultBean<JsonObject>> login(String username, String password) {
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        String strEntity = gson.toJson(loginBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strEntity);
        return mAPIService.login(body);
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @// TODO: 2018/1/26 注册
     */
    public Call<ResultBean<JsonObject>> register(String username, String password) {
        RegisterBean registerBean = new RegisterBean();
        registerBean.setUsername(username);
        registerBean.setPassword(password);
        String strEntity = gson.toJson(registerBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strEntity);
        return mAPIService.register(body);
    }
}

