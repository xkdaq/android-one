package com.xuke.androidone.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuke.androidone.MyApplication;
import com.xuke.androidone.R;
import com.xuke.androidone.api.RetrofitHelper;
import com.xuke.androidone.model.bean.BaseResultBean;
import com.xuke.androidone.model.bean.login.UserBean;
import com.xuke.androidone.utils.XKLoggerUtils;
import com.xuke.androidone.view.widge.ProgressDlgUtil;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.pswTypeRl)
    RelativeLayout pswTypeRl;
    @BindView(R.id.pswTypeView)
    View pswTypeView;
    private boolean isShowPassWord = false;
    private Call<BaseResultBean<UserBean>> loginCall;


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.pswTypeRl, R.id.tv_login, R.id.tv_register, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pswTypeRl:
                isShowPassWord = !isShowPassWord;
                setEyeStatue();
                break;
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

    /**
     * 登录
     */
    public void login(String phone, String password) {
        ProgressDlgUtil.showProgressDlg(this);
        loginCall = RetrofitHelper.getInstance().login(phone, password);
        loginCall.enqueue(new Callback<BaseResultBean<UserBean>>() {
            @Override
            public void onResponse(Call<BaseResultBean<UserBean>> call, Response<BaseResultBean<UserBean>> response) {
                if (response != null) {
                    ProgressDlgUtil.stopProgressDlg();
                    BaseResultBean<UserBean> body = response.body();
                    if (body != null) {
                        UserBean user = body.getObj();
                        XKLoggerUtils.e("xuke", user.toString());
                        if (MyApplication.userBean == null) {
                            MyApplication.userBean = new UserBean();
                            MyApplication.userBean.setAccountNum(user.getAccountNum());
                            MyApplication.userBean.setName(user.getName());
                            MyApplication.userBean.setPicture_xd(user.getPicture_xd());
                            MyApplication.userBean.setSign(user.getSign());

                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResultBean<UserBean>> call, Throwable t) {
                ProgressDlgUtil.stopProgressDlg();
            }
        });
    }

    /**
     * 设置密码是否可见
     */
    private void setEyeStatue() {
        pswTypeView.setBackgroundResource(isShowPassWord ? R.drawable.mima_guangbiyanjing : R.drawable.mima_dakaiyanjing);
        psw.setInputType(isShowPassWord ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD));
    }
}


















