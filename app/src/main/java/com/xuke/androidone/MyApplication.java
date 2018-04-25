package com.xuke.androidone;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xuke.androidone.dao.GreenDaoManager;
import com.zyw.horrarndoo.sdk.global.GlobalApplication;

/**
 * Created by xuke on 2018/1/23.
 * <p></p>
 */

public class MyApplication extends GlobalApplication {
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    public static MyApplication app;

    {
        PlatformConfig.setWeixin("wxdcf8e495206e1429", "34023dd58da3e1c3a8b5e22812a4748c");
        PlatformConfig.setQQZone("101464794", "49385bf53e5c9743ca89b339e8c96a9a");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化屏幕宽高
        getScreenSize();
        //初始化短信mobsdk
        MobSDK.init(this);
        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), "b22ab6d8c5", true);
        //初始化友盟
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5a956c128f4a9d0e7b000336");
        //友盟分享
        UMShareAPI.get(this);
        //数据库
        GreenDaoManager.getInstance();
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
