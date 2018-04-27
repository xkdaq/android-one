package com.xuke.androidone.view.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xuke.androidone.R;
import com.xuke.androidone.model.rxbus.RxEventHeadBean;
import com.xuke.androidone.utils.Accounts;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.helper.RxHelper;
import com.zyw.horrarndoo.sdk.rxbus.RxBus;
import com.zyw.horrarndoo.sdk.utils.ResourcesUtils;
import com.zyw.horrarndoo.sdk.widgets.headclip.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import static com.xuke.androidone.utils.RxBusCode.RXBUS_HEAD_IMAGE_URL_CODE;


/**
 * Created by xuke on 2018/4/27.
 * 设置头像
 */

public class HeadSettingActivity extends BaseCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cvl_rect)
    ClipViewLayout cvlRect;

    @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        toolbar.setTitle("选取头像");
        toolbar.setTitleTextColor(ResourcesUtils.getColor(R.color.md_white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(com.zyw.horrarndoo.sdk.R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(view -> onBackPressedSupport());

        cvlRect.setImageSrc(getIntent().getData());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_setting;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressedSupport();
                break;
            case R.id.tv_ok:
                Observable.create((ObservableOnSubscribe<Uri>) e -> {
                    e.onNext(generateUri());
                    e.onComplete();
                }).compose(RxHelper.<Uri>rxSchedulerHelper())
                        .subscribe(uri -> {
                            RxEventHeadBean rxEventHeadBean = new RxEventHeadBean(uri);
                            RxBus.get().send(RXBUS_HEAD_IMAGE_URL_CODE, rxEventHeadBean);
                            onBackPressedSupport();
                        });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    /**
     * 生成Uri
     */
    private Uri generateUri() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        zoomedCropBitmap = cvlRect.clip();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String format = dateFormat.format(new Date(System.currentTimeMillis()));
        Uri mSaveUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), Accounts.HEAD_IMAGE_NAME + format + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mSaveUri;
    }
}
