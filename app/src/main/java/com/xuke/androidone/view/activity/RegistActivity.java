package com.xuke.androidone.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.xuke.androidone.view.widge.ProgressDlgUtil;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.RegexUtils;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by xuke on 2018/1/25.
 * 注册
 */

public class RegistActivity extends BaseCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.btn_check_code)
    Button btnCheckCode;
    private boolean timeEnable = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        btnCheckCode.setEnabled(false);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (timeEnable) {
                    boolean enable = !TextUtils.isEmpty(s) && RegexUtils.isMobile(s.toString());
                    btnCheckCode.setEnabled(enable);
                    btnCheckCode.setTextColor(getResources().getColor(enable ? R.color.black : R.color.c_c7c7c7));
                }
            }
        });

    }

    private boolean isPrepared() {
        if (!RegexUtils.checkMobileNumber(etPhone.getText().toString().trim())) {
            ToastUtils.showToast("请输入正确的手机号");
            etPhone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
            etCode.requestFocus();
            ToastUtils.showToast("请输入验证码");
            return false;
        }
        return true;
    }


    @OnClick({R.id.btn_check_code, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_check_code:
                ProgressDlgUtil.showProgressDlg("正在获取", this);
                String phone = etPhone.getText().toString().trim();
                if (RegexUtils.checkMobileNumber(phone)) {
                    timeEnable = false;
                    btnCheckCode.setEnabled(false);
                    CountDownTimer mCountDownTimer = new CountDownTimer(
                            60 * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            long s = millisUntilFinished / 1000;
                            String text = "重新获取(" + s + "s)";
                            btnCheckCode.setTextColor(getResources().getColor(R.color.c_c7c7c7));
                            btnCheckCode.setText(text);
                        }

                        @Override
                        public void onFinish() {
                            timeEnable = true;
                            btnCheckCode.setText("获取验证码");
                            btnCheckCode.setTextColor(getResources().getColor(R.color.black));
                            boolean enable = !TextUtils.isEmpty(etPhone.getText().toString())
                                    && RegexUtils.isMobile(etPhone.getText().toString());
                            btnCheckCode.setEnabled(enable);
                            this.cancel();
                        }
                    };
                    mCountDownTimer.start();
                    sendCode("86", phone);
                } else {
                    ToastUtils.showToast("请检查您输入的手机号");
                    etPhone.requestFocus();
                }
                break;
            case R.id.tv_next:
                ProgressDlgUtil.showProgressDlg("请稍后...", this);
                String phone2 = etPhone.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                if (isPrepared()) {
                    submitCode("86", phone2, code);
                } else {
                    ProgressDlgUtil.stopProgressDlg();
                }
                break;
        }
    }

    /**
     * 获取验证码
     *
     * @param country 国家代码,如"86"
     * @param phone   手机号,如"13800138000"
     */
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                ProgressDlgUtil.stopProgressDlg();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        ToastUtils.showToast("验证码发送成功");
                    }
                }

            }
        });
        SMSSDK.getVerificationCode(country, phone);
    }

    /**
     * 验证填写的验证码是否正确
     *
     * @param country 国家代码,如"86"
     * @param phone   手机号,如"13800138000"
     * @param code    输入的验证
     */
    public void submitCode(String country, String phone, String code) {
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                ProgressDlgUtil.stopProgressDlg();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Intent intent = new Intent();
                        intent.putExtra("phone", etPhone.getText().toString().trim());
                        startActivity(RegistTwoActivity.class, intent);
                        finish();
                    }
                }

            }
        });
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
