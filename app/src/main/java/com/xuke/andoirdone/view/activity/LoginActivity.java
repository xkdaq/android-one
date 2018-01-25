package com.xuke.andoirdone.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import com.xuke.andoirdone.R;
import com.xuke.andoirdone.api.APIService;
import com.xuke.andoirdone.api.RetrofitHelper;
import com.xuke.andoirdone.model.bean.login.LoginBean;
import com.xuke.andoirdone.view.widge.ProgressDlgUtil;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.helper.RetrofitCreateHelper;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuke on 2018/1/25.
 * 登录
 */

public class LoginActivity extends BaseCompatActivity {

    @BindView(R.id.acount)
    EditText acount;
    @BindView(R.id.psw)
    EditText psw;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget)
    TextView tvForget;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                login(acount.getText().toString().trim(), psw.getText().toString().trim());
                break;
            case R.id.tv_register:
                startActivity(RegistActivity.class);
                break;
            case R.id.tv_forget:
                break;
        }
    }

    public void login(String username, String password) {
        ProgressDlgUtil.showProgressDlg(this);
        Call<JsonObject> login = RetrofitHelper.getInstance().login(username, password);
        login.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ProgressDlgUtil.stopProgressDlg();
                Logger.e("onResponse: response="+response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ProgressDlgUtil.stopProgressDlg();

            }
        });
    }
}


















