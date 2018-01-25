package com.xuke.andoirdone.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xuke.andoirdone.R;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.RegexUtils;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by xuke on 2018/1/25.
 * 注册第二步 设置密码
 */

public class RegistTwoActivity extends BaseCompatActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_two_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
