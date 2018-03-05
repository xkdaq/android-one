package com.xuke.androidone.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xuke on 2018/3/2.
 * 关于我
 */

public class AboutMeActivity extends BaseCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_versionName)
    TextView tvVersionName;
    @BindView(R.id.tv_github)
    TextView tvGithub;

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutMeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvVersionName.setText(String.format(getResources().getString(R.string.versionNameV), AppUtils.getAppVersionName(mContext)));

    }

    @OnClick(R.id.tv_github)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("https://github.com/xkdaq"));
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }
}
