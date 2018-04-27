package com.xuke.androidone.presenter.account;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.xuke.androidone.contract.account.AccountContract;
import com.xuke.androidone.model.account.AccountModel;
import com.xuke.androidone.utils.Accounts;
import com.xuke.androidone.utils.XKLoggerUtils;
import com.zyw.horrarndoo.sdk.utils.FileUtils;
import com.zyw.horrarndoo.sdk.utils.MD5Utils;

import java.io.File;

/**
 * Created by xuke on 2018/4/27.
 * <P></P>
 */

public class AccountPresenter extends AccountContract.AccountPresenter {

    //请求相机
    private static final int REQUEST_CAMERA = 100;
    //请求相册
    private static final int REQUEST_PHOTO = 101;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private File tempFile;
    private Activity context;

    public AccountPresenter(Activity context) {
        this.context = context;
    }

    @NonNull
    public static AccountPresenter newInstance(Activity context) {
        return new AccountPresenter(context);
    }

    @Override
    public AccountContract.IAccountModel getModel() {
        return AccountModel.newInstance();
    }

    /**
     * 初始化
     */
    @Override
    public void onStart() {
        mIView.initPopupView();
    }

    /**
     * 头像点击
     */
    @Override
    public void btnHeadClicked() {
        mIView.showPopupView();
    }

    /**
     * 拍照
     */
    @Override
    public void btnCameraClicked() {
        //创建拍照存储的图片文件
        tempFile = new File(FileUtils.checkDirPath(Environment.getExternalStorageDirectory()
                .getPath() + "/image/"), MD5Utils.getMD5(Accounts.HEAD_IMAGE_NAME) + System
                .currentTimeMillis() + ".jpg");
        //权限判断
        if (ContextCompat.checkSelfPermission(context, Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(context, new String[]{Manifest
                    .permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到调用系统相机
            mIView.gotoSystemCamera(tempFile, REQUEST_CAMERA);
        }
        if (mIView.popupIsShowing()) {
            mIView.dismissPopupView();
        }
    }

    /**
     * 相册
     */
    @Override
    public void btnPhotoClicked() {
        //权限判断
        if (ContextCompat.checkSelfPermission(context, Manifest.permission
                .READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请READ_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(context, new String[]{Manifest
                            .permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到相册
            mIView.gotoSystemPhoto(REQUEST_PHOTO);
        }
        if (mIView.popupIsShowing())
            mIView.dismissPopupView();

    }

    /**
     * 取消
     */
    @Override
    public void btnCancelClicked() {
        if (mIView.popupIsShowing()) {
            mIView.dismissPopupView();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAMERA: //调用系统相机返回
                if (resultCode == Activity.RESULT_OK) {
                    mIView.gotoHeadSettingActivity(Uri.fromFile(tempFile));
                    XKLoggerUtils.e("xuke",Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PHOTO:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = intent.getData();
                    mIView.gotoHeadSettingActivity(uri);
                    XKLoggerUtils.e("xuke",uri);
                }
                break;
        }
    }


}
