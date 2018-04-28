package com.xuke.androidone.api;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuke.androidone.MyApplication;
import com.xuke.androidone.model.bean.BaseEntity;
import com.xuke.androidone.model.bean.BaseResultBean;
import com.xuke.androidone.model.bean.SearchUserInfo;
import com.xuke.androidone.model.bean.UserEntity;
import com.xuke.androidone.model.bean.login.RegisterBean;
import com.xuke.androidone.model.bean.login.ResultBean;
import com.xuke.androidone.model.bean.login.TokenUserEntity;
import com.xuke.androidone.model.bean.login.UserBean;
import com.xuke.androidone.model.bean.login.UserProfile;
import com.xuke.androidone.utils.Accounts;
import com.xuke.androidone.utils.PhoneInfoUtils;
import com.xuke.androidone.utils.XKLoggerUtils;
import com.zyw.horrarndoo.sdk.helper.okhttp.CacheInterceptor;
import com.zyw.horrarndoo.sdk.helper.okhttp.HttpCache;
import com.zyw.horrarndoo.sdk.helper.okhttp.TrustManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuke on 2018/1/7.
 * <P></P>
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 50;
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    //private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            //SSL证书
            .sslSocketFactory(TrustManager.getUnsafeOkHttpClient())
            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            //打印日志
            .addInterceptor(new TokenInterceptor())
            //设置Cache拦截器
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(HttpCache.getCache())
            //time out
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();
    private static RetrofitHelper mInstance;
    private static Gson gson;
    private final APIService mAPIService;
    private final SharedPreferences prefs;

    private RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mAPIService = retrofit.create(APIService.class);
        prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getBaseContext());
    }

    public class TokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request tokenRequest;
            //对 token 进行判空，如果为空，则不进行修改
            if (TextUtils.isEmpty(prefs.getString(Accounts.apptoken, ""))) {
                return chain.proceed(originalRequest);
            }
            //往请求头中添加 token 字段
            tokenRequest = originalRequest.newBuilder()
                    .header("authorization", prefs.getString(Accounts.apptoken, ""))
                    .build();

            XKLoggerUtils.e("xuke", "authorization=" + prefs.getString(Accounts.apptoken, ""));

            // try the request
            Response originalResponse = chain.proceed(tokenRequest);
            return originalResponse;
        }
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
     * @param phoneStr 手机号
     * @param password 密码
     * @// TODO: 2018/1/25 登录
     */
    public Call<BaseResultBean<UserBean>> login(String phoneStr, String password) {
        PhoneInfoUtils phoneInfoUtils = new PhoneInfoUtils(MyApplication.getInstance().getBaseContext());
        TokenUserEntity tokenUserEntity = new TokenUserEntity();
        tokenUserEntity.setAccountNum(phoneStr);
        tokenUserEntity.setPassword(password);
        tokenUserEntity.setMacid(phoneInfoUtils.getMacAddress());
        tokenUserEntity.setMeid(phoneInfoUtils.getANDROID_ID());
        tokenUserEntity.setSysid("android");
        tokenUserEntity.setFromid("0");
        BaseEntity<TokenUserEntity> baseEntity = new BaseEntity<>();
        baseEntity.setCommand(Commands.GET_USER_PROFILE);
        baseEntity.setContent(tokenUserEntity);
        String result = gson.toJson(baseEntity);
        XKLoggerUtils.e("xuke", result);
        return mAPIService.login(result);
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @// TODO: 2018/1/26 注册
     */
    public Call<ResultBean> register(String username, String password) {
        RegisterBean registerBean = new RegisterBean();
        registerBean.setUsername(username);
        registerBean.setPassword(password);
        String strEntity = gson.toJson(registerBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), strEntity);
        return mAPIService.register(body);
    }

    /**
     * 上传头像 6
     *
     * @param accountNum 用户id
     * @param pwd        用户密码
     * @param filename   文件名
     * @param file       文件
     */
    public Call<BaseResultBean<String>> uploadFile(String accountNum, String pwd, String filename, File file) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountNum(accountNum);
        userEntity.setPassword(pwd);
        BaseEntity<UserEntity> baseEntity = new BaseEntity<>();
        baseEntity.setCommand(Commands.UPLOAD_IMAGE_FILE);
        baseEntity.setContent(userEntity);
        String result = gson.toJson(baseEntity);
        XKLoggerUtils.e("xuke", result);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("jsonStr", result);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("upload", filename, imageBody);
        List<MultipartBody.Part> parts = builder.build().parts();
        return mAPIService.upload(parts);
    }

    /**
     * 个人详情接口 27
     *
     * @param accountNum 用户id
     */
    public Call<BaseResultBean<SearchUserInfo>> searchInfo(String accountNum) {
        UserEntity accountNumEntity = new UserEntity();
        accountNumEntity.setAccountNum(accountNum);
        BaseEntity<UserEntity> baseEntity = new BaseEntity<>();
        baseEntity.setCommand(Commands.SEARCH_FOR_CODE);
        baseEntity.setContent(accountNumEntity);
        String result = gson.toJson(baseEntity);
        return mAPIService.getSearchInfo(result);
    }

    /**
     * 更新个人信息接口
     */
    public Call<ResultBean> uploadUserProfile(UserProfile userProfile) {
        BaseEntity<UserProfile> baseEntity = new BaseEntity<>();
        baseEntity.setCommand(Commands.UPDATE_USER_INFO);
        baseEntity.setContent(userProfile);
        String result = gson.toJson(baseEntity);
        return mAPIService.result(result);
    }

}

