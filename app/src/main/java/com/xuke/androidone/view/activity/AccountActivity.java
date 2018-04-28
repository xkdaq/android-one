package com.xuke.androidone.view.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.xuke.androidone.contract.account.AccountContract;
import com.xuke.androidone.dao.GreenDaoManager;
import com.xuke.androidone.model.bean.login.UserBean;
import com.xuke.androidone.model.rxbus.RxEventHeadBean;
import com.xuke.androidone.presenter.account.AccountPresenter;
import com.xuke.androidone.utils.Accounts;
import com.xuke.androidone.utils.GlideManager;
import com.xuke.androidone.utils.RelativePath;
import com.xuke.androidone.utils.compresshelper.CompressHelper;
import com.xuke.androidone.view.widge.CircleImageView;
import com.xuke.androidone.view.widge.ItemView;
import com.xuke.androidone.view.widge.PersonalPopupWindow;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.activity.BaseMVPCompatActivity;
import com.zyw.horrarndoo.sdk.rxbus.RxBus;
import com.zyw.horrarndoo.sdk.rxbus.Subscribe;
import com.zyw.horrarndoo.sdk.utils.AppUtils;
import com.zyw.horrarndoo.sdk.utils.FileUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xuke.androidone.utils.RxBusCode.RXBUS_HEAD_IMAGE_URL_CODE;

/**
 * Created by xuke on 2018/4/26.
 * 个人
 */

public class AccountActivity extends BaseMVPCompatActivity<AccountContract.AccountPresenter,
        AccountContract.IAccountModel> implements AccountContract.IAccountView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_user_avatar)
    CircleImageView ivUserAvatar;
    @BindView(R.id.rl_photo)
    RelativeLayout rlPhoto;
    @BindView(R.id.item_user_name)
    ItemView itemUserName;
    @BindView(R.id.item_user_sex)
    ItemView itemUserSex;
    @BindView(R.id.item_user_birth)
    ItemView itemUserBirth;
    @BindView(R.id.item_user_phone)
    ItemView itemUserPhone;
    @BindView(R.id.item_user_sign)
    ItemView itemUserSign;
    @BindView(R.id.tv_sign)
    TextView tvSign;

    private SharedPreferences prefs;
    private String accountNum;
    private PersonalPopupWindow popupWindow;
    private String password;
    private File file;
    private UserBean loginUser;

    public static void start(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return AccountPresenter.newInstance(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        accountNum = prefs.getString(Accounts.accountNum, "");
        password = prefs.getString(Accounts.password, "");
        loginUser = GreenDaoManager.getInstance().getLoginUser(accountNum);
        RxBus.get().register(this);
    }


    @Override
    public void initPopupView() {
        popupWindow = new PersonalPopupWindow(this);
        popupWindow.setOnItemClickListener(new PersonalPopupWindow.OnItemClickListener() {
            @Override
            public void onCaremaClicked() {
                mPresenter.btnCameraClicked();
            }

            @Override
            public void onPhotoClicked() {
                mPresenter.btnPhotoClicked();
            }

            @Override
            public void onCancelClicked() {
                mPresenter.btnCancelClicked();
            }
        });
    }

    @Override
    public void showPopupView() {
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_account, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    @Override
    public void dismissPopupView() {
        popupWindow.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return popupWindow.isShowing();
    }

    @Override
    public void gotoSystemCamera(File tempFile, int requestCode) {
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0  fileProvider
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void gotoSystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void gotoHeadSettingActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(mContext, HeadSettingActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void refresh() {
        if (loginUser != null) {
            //头像
            GlideManager.getInstance().loadImage(this, ivUserAvatar, RelativePath.toAbs(loginUser.getPicture_xd()), R.drawable.img_defout_man);
            //昵称
            itemUserName.setRightText(loginUser.getName());
            //性别
            itemUserSex.setRightText("1".equals(loginUser.getSex()) ? "女" : "0".equals(loginUser.getSex()) ? "男" : "未知");
            //生日
            itemUserBirth.setRightText(loginUser.getBirthday());
            //手机号
            itemUserPhone.setRightText(loginUser.getPhoneNum());
            //签名
            tvSign.setText(loginUser.getSign());
        }
    }

    @OnClick({R.id.rl_photo, R.id.item_user_name, R.id.item_user_sex, R.id.item_user_birth, R.id.item_user_phone, R.id.item_user_sign, R.id.tv_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_photo:
                //修改头像
                mPresenter.btnHeadClicked();
                break;
            case R.id.item_user_name:
                //修改昵称
                break;
            case R.id.item_user_sex:
                //修改性别
                break;
            case R.id.item_user_birth:
                //修改生日
                break;
            case R.id.item_user_phone:
                //修改手机号
                break;
            case R.id.item_user_sign:
            case R.id.tv_sign:
                //修改个性签名
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(code = RXBUS_HEAD_IMAGE_URL_CODE)
    public void rxBusEvent(RxEventHeadBean bean) {
        Uri uri = bean.getUri();
        if (uri == null) {
            return;
        }
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), uri);
        file = CompressHelper.getDefault(mContext).compressToFile(new File(cropImagePath));
        mPresenter.uploadImage(accountNum, password, "avatar.jpg", file);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}


















