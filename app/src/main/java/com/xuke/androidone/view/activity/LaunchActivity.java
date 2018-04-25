package com.xuke.androidone.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xuke.androidone.R;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.helper.RxHelper;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by xuke on 2018/2/28.
 * 启动页
 */

public class LaunchActivity extends BaseCompatActivity {
    private int mTime = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        requestPermissions();
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        //分别请求权限
        rxPermission.requestEach(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        initCountDown();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        ToastUtils.showToast("App未能获取全部需要的相关权限，部分功能可能不能正常使用.");
                    }
                });


    }


    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .map(aLong -> mTime - aLong)
                .compose(RxHelper.<Long>rxSchedulerHelper())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}
