package com.xuke.andoirdone.view.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import com.xuke.andoirdone.R;
import com.xuke.andoirdone.api.RetrofitHelper;
import com.xuke.andoirdone.model.bean.login.ResultBean;
import com.xuke.andoirdone.view.widge.ProgressDlgUtil;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

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

    public void login(String username, String password) {
        ProgressDlgUtil.showProgressDlg(this);
        Call<ResultBean<JsonObject>> login = RetrofitHelper.getInstance().login(username, password);
        login.enqueue(new Callback<ResultBean<JsonObject>>() {
            @Override
            public void onResponse(Call<ResultBean<JsonObject>> call, Response<ResultBean<JsonObject>> response) {
                ProgressDlgUtil.stopProgressDlg();
                ResultBean<JsonObject> body = response.body();
                Logger.e("onResponse: response=" + body);
                if (body != null) {
                    ResultBean.MetaBean meta = body.getMeta();
                    if (meta.isSuccess()){

                        ToastUtils.showToast("登录成功");
                        finish();
                    }else {
                        ToastUtils.showToast(meta.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultBean<JsonObject>> call, Throwable t) {
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


















