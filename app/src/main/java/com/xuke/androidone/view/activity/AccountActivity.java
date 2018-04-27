package com.xuke.androidone.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.xuke.androidone.dao.GreenDaoManager;
import com.xuke.androidone.model.bean.login.UserBean;
import com.xuke.androidone.utils.Accounts;
import com.xuke.androidone.utils.GlideManager;
import com.xuke.androidone.utils.RelativePath;
import com.xuke.androidone.view.widge.CircleImageView;
import com.xuke.androidone.view.widge.ItemView;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;

import butterknife.BindView;

/**
 * Created by xuke on 2018/4/26.
 * 个人
 */

public class AccountActivity extends BaseCompatActivity {


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

    public static void start(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        accountNum = prefs.getString(Accounts.accountNum, "");
        UserBean loginUser = GreenDaoManager.getInstance().getLoginUser(accountNum);
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
}


















