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

import com.xuke.androidone.api.RetrofitHelper;
import com.xuke.androidone.contract.account.AccountContract;
import com.xuke.androidone.dao.GreenDaoManager;
import com.xuke.androidone.model.account.AccountModel;
import com.xuke.androidone.model.bean.BaseResultBean;
import com.xuke.androidone.model.bean.SearchUserInfo;
import com.xuke.androidone.model.bean.login.ResultBean;
import com.xuke.androidone.model.bean.login.UserBean;
import com.xuke.androidone.model.bean.login.UserProfile;
import com.xuke.androidone.utils.Accounts;
import com.xuke.androidone.view.activity.EditActivity;
import com.xuke.androidone.view.widge.ProgressDlgUtil;
import com.zyw.horrarndoo.sdk.utils.FileUtils;
import com.zyw.horrarndoo.sdk.utils.MD5Utils;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 102;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 103;

    //修改昵称
    private static final int CODE_NAME = 105;
    //修改生日
    private static final int CODE_BIRTHDAY = 106;
    //修改个性签名
    private static final int CODE_SIGN = 107;


    private File tempFile;
    private Activity context;
    private Call<BaseResultBean<String>> uploadCall;
    private Call<BaseResultBean<SearchUserInfo>> searchCall;
    private Call<ResultBean> uploadUserCall;

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
        mIView.refresh();
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

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAMERA:
                    mIView.gotoHeadSettingActivity(Uri.fromFile(tempFile));
                    break;
                case REQUEST_PHOTO:
                    Uri uri = intent.getData();
                    mIView.gotoHeadSettingActivity(uri);
                    break;
                case CODE_NAME:
                    String name = intent.getStringExtra("data");
                    mIView.changeName(name);
                    break;
                case CODE_BIRTHDAY:
                    String birthday = intent.getStringExtra("data");
                    mIView.changeBirthday(birthday);
                    break;
                case CODE_SIGN:
                    String sign = intent.getStringExtra("data");
                    mIView.changeSign(sign);
                    break;
            }
        }
    }

    /**
     * 上传头像.
     */
    @Override
    public void uploadImage(String accountNum, String password, String uploadFileName, File takeImageFile) {
        uploadCall = RetrofitHelper.getInstance().uploadFile(accountNum, password, uploadFileName, takeImageFile);
        uploadCall.enqueue(new Callback<BaseResultBean<String>>() {
            @Override
            public void onResponse(Call<BaseResultBean<String>> call, Response<BaseResultBean<String>> response) {
                BaseResultBean<String> body = response.body();
                if (body != null) {
                    if (body.isSuccess()) {
                        ToastUtils.showToast(body.getMsg());
                        loadUserInfo(accountNum);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResultBean<String>> call, Throwable t) {
                ToastUtils.showToast("" + t.getMessage());
            }
        });
    }

    /**
     * 获取个人信息.
     */
    public void loadUserInfo(String accountNum) {
        searchCall = RetrofitHelper.getInstance().searchInfo(accountNum);
        searchCall.enqueue(new Callback<BaseResultBean<SearchUserInfo>>() {
            @Override
            public void onResponse(Call<BaseResultBean<SearchUserInfo>> call, Response<BaseResultBean<SearchUserInfo>> response) {
                BaseResultBean baseResultEntity = response.body();
                if (baseResultEntity != null) {
                    if (baseResultEntity.isSuccess()) {
                        SearchUserInfo searchUserInfo = (SearchUserInfo) baseResultEntity.getObj();
                        String picture_xd = searchUserInfo.getPicture_xd();
                        UserBean loginUser = GreenDaoManager.getInstance().getLoginUser(accountNum);
                        if (loginUser != null) {
                            loginUser.setPicture_xd(picture_xd);
                            GreenDaoManager.getInstance().saveLoginUser(loginUser);
                            mIView.refresh();
                            context.setResult(Activity.RESULT_OK);
                        }
                    } else {
                        ToastUtils.showToast("" + baseResultEntity.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResultBean<SearchUserInfo>> call, Throwable t) {
                ToastUtils.showToast("" + t.getMessage());
            }
        });
    }


    /**
     * 更新个人信息
     */
    @Override
    public void uploadUserProfile(String accountNum, String password,
                                  String name, String sex, String birthday, String phone, String sign) {
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountNum(accountNum);
        userProfile.setPassword(password);
        userProfile.setName(name);
        userProfile.setSex(sex);
        userProfile.setBirthday(birthday);
        userProfile.setPhoneNum(phone);
        userProfile.setSign(sign);
        ProgressDlgUtil.showProgressDlg(context);
        uploadUserCall = RetrofitHelper.getInstance().uploadUserProfile(userProfile);
        uploadUserCall.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {
                ResultBean responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.isSuccess()) {
                        ProgressDlgUtil.stopProgressDlg();
                        ToastUtils.showToast(responseBody.getMsg());
                        UserBean loginUser = GreenDaoManager.getInstance().getLoginUser(accountNum);
                        if (loginUser != null) {
                            loginUser.setName(name);
                            loginUser.setSex(sex);
                            loginUser.setBirthday(birthday);
                            loginUser.setPhoneNum(phone);
                            loginUser.setSign(sign);
                            GreenDaoManager.getInstance().saveLoginUser(loginUser);
                            mIView.refresh();
                            context.setResult(Activity.RESULT_OK);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {
                ProgressDlgUtil.stopProgressDlg();
                ToastUtils.showToast("" + t.getMessage());
            }
        });
    }


    @Override
    public void btnNameClicked(String name) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("title", "编辑昵称");
        intent.putExtra("data", name);
        intent.putExtra("limit", 30);
        context.startActivityForResult(intent, CODE_NAME);
    }

    @Override
    public void btnSexClicked() {
        ToastUtils.showToast("暂不支持修改性别");
    }

    @Override
    public void btnBirthdayClicked(String birthday) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("title", "编辑生日");
        intent.putExtra("data", birthday);
        intent.putExtra("limit", 30);
        context.startActivityForResult(intent, CODE_BIRTHDAY);
    }

    @Override
    public void btnPhoneNumClicked() {
        ToastUtils.showToast("暂不支持修改手机号");
    }

    @Override
    public void btnSignClicked(String sign) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("title", "编辑个性签名");
        intent.putExtra("data", sign);
        intent.putExtra("limit", 30);
        context.startActivityForResult(intent, CODE_SIGN);
    }


}
