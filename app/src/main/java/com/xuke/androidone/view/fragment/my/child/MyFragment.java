package com.xuke.androidone.view.fragment.my.child;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.xuke.androidone.dao.GreenDaoManager;
import com.xuke.androidone.model.bean.login.UserBean;
import com.xuke.androidone.utils.Accounts;
import com.xuke.androidone.utils.GlideManager;
import com.xuke.androidone.utils.RelativePath;
import com.xuke.androidone.view.activity.AboutMeActivity;
import com.xuke.androidone.view.activity.AccountActivity;
import com.xuke.androidone.view.activity.LoginActivity;
import com.xuke.androidone.view.activity.WebViewActivity;
import com.xuke.androidone.view.widge.CircleImageView;
import com.xuke.androidone.view.widge.ItemView;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseMVPCompatFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuke on 2018/1/23.
 * 我的
 */

public class MyFragment extends BaseMVPCompatFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_photo)
    CircleImageView ivPhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_simple)
    TextView tvSimple;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.iv_enter)
    ImageView ivEnter;
    @BindView(R.id.item_like)
    ItemView itemLike;
    @BindView(R.id.item_image)
    ItemView itemImage;
    @BindView(R.id.item_about_me)
    ItemView itemAboutMe;
    @BindView(R.id.item_setting)
    ItemView itemSetting;
    @BindView(R.id.message_container)
    LinearLayout messageContainer;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.item_feedback)
    ItemView itemFeedback;


    private SharedPreferences prefs;
    private String accountNum;
    private UserBean loginUser;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        refresh();
    }

    private void refresh() {
        accountNum = prefs.getString(Accounts.accountNum, "");
        loginUser = GreenDaoManager.getInstance().getLoginUser(accountNum);
        if (loginUser == null) {
            tvName.setText("登录/注册");
            tvSimple.setVisibility(View.GONE);
            ivSex.setVisibility(View.GONE);
            ivPhoto.setImageResource(R.drawable.img_defout_man);
        } else {
            tvName.setText(loginUser.getName());
            tvSimple.setVisibility(View.VISIBLE);
            tvSimple.setText(loginUser.getSign());
            GlideManager.getInstance().loadImage(mContext, ivPhoto, RelativePath.toAbs(loginUser.getPicture_xd()), R.drawable.img_defout_man);
            ivSex.setVisibility(View.VISIBLE);
            if ("1".equals(loginUser.getSex())) {
                ivSex.setImageResource(R.drawable.icon_female);
            } else if ("0".equals(loginUser.getSex())) {
                ivSex.setImageResource(R.drawable.icon_male);
            }
        }
    }

    @OnClick({R.id.rl_login, R.id.item_like, R.id.item_image, R.id.item_feedback, R.id.item_about_me, R.id.item_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_like:
                //我的收藏
                break;
            case R.id.item_image:
                //我的图文
                break;
            case R.id.item_feedback:
                //意见反馈
                WebViewActivity.start(mContext, "意见反馈", "https://github.com/xkdaq/android-one/issues");
                break;
            case R.id.item_about_me:
                //关于作者
                AboutMeActivity.start(mContext);
                break;
            case R.id.item_setting:
                //设置
                if (loginUser != null) {
                    //退出登录
                    GreenDaoManager.getInstance().deleteLoginUser(prefs.getString(Accounts.accountNum, ""));
                    cleanPreToken();
                    refresh();
                }
                break;
            case R.id.rl_login:
                //登录
                if (loginUser == null) {
                    startActivityForResult(new Intent(mContext, LoginActivity.class), 100);
                } else {
                    startActivityForResult(new Intent(mContext, AccountActivity.class), 200);
                }
                break;
        }
    }


    private void cleanPreToken() {
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .remove(Accounts.accountNum)
                .apply();
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .remove(Accounts.phone)
                .apply();
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .remove(Accounts.password)
                .apply();
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .remove(Accounts.apptoken)
                .apply();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            refresh();
        }
    }
}
