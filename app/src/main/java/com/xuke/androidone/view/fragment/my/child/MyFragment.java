package com.xuke.androidone.view.fragment.my.child;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.xuke.androidone.view.activity.LoginActivity;
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

    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        startActivity(new Intent(mContext, LoginActivity.class));
    }
}
