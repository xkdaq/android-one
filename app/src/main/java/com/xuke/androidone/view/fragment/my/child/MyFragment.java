package com.xuke.androidone.view.fragment.my.child;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuke.androidone.MyApplication;
import com.xuke.androidone.R;
import com.xuke.androidone.utils.GlideManager;
import com.xuke.androidone.utils.RelativePath;
import com.xuke.androidone.view.activity.AboutMeActivity;
import com.xuke.androidone.view.activity.LoginActivity;
import com.xuke.androidone.view.activity.WebViewActivity;
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

    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
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
        refresh();
    }

    private void refresh() {
        if (MyApplication.userBean == null) {
            tvName.setText("登录/注册");
            tvSimple.setVisibility(View.INVISIBLE);
        } else {
            tvName.setText(MyApplication.userBean.getName());
            tvSimple.setVisibility(View.VISIBLE);
            tvSimple.setText(MyApplication.userBean.getSign());
            GlideManager.getInstance().loadImage(mContext, ivPhoto, RelativePath.toAbs(MyApplication.userBean.getPicture_xd()));
        }
    }

    @OnClick({R.id.rl_login, R.id.item_like, R.id.item_image, R.id.item_feedback, R.id.item_about_me, R.id.item_setting, R.id.tv_login})
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
                break;
            case R.id.tv_login:
                //登录
                LoginActivity.start(mContext);
                break;
            case R.id.rl_login:
                //登录
                if (MyApplication.userBean == null) {
                    startActivityForResult(new Intent(mContext, LoginActivity.class), 100);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            refresh();
        }
    }
}
