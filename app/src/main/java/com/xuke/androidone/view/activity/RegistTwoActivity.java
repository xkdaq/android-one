package com.xuke.androidone.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.xuke.androidone.R;
import com.xuke.androidone.api.RetrofitHelper;
import com.xuke.androidone.model.bean.login.ResultBean;
import com.xuke.androidone.view.widge.ProgressDlgUtil;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuke on 2018/1/25.
 * 注册第二步 设置密码
 */

public class RegistTwoActivity extends BaseCompatActivity {

    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_two_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        phone = getIntent().getStringExtra("phone");
    }


    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
        String password = etPassword.getText().toString().trim();
        String confirmPwd = etConfirmPwd.getText().toString().trim();
        if (!TextUtils.equals(password, confirmPwd)) {
            ToastUtils.showToast("两次输入的密码不一致");
        } else {
            regist();
        }
    }

    /**
     * 注册
     */
    private void regist() {


    }
}
