package com.xuke.androidone.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.xuke.androidone.R;
import com.xuke.androidone.utils.MyTextWatcher;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by xuke on 2018/4/28
 * 编辑界面
 */
public class EditActivity extends BaseCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    private static final int LIMIT = 30;
    private String title;
    private String data;
    private int limit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");
            data = getIntent().getStringExtra("data");
            limit = getIntent().getIntExtra("limit", LIMIT);
        }

        toolbar.setTitle(TextUtils.isEmpty(title) ? "编辑" : title);
        edittext.setText(TextUtils.isEmpty(data) ? "" : data);
        edittext.setSelection(edittext.length());
        edittext.addTextChangedListener(new MyTextWatcher(edittext, limit, mContext, tvNum));
    }

    @OnClick(R.id.tv_ok)
    public void onViewClicked() {
        String info = edittext.getText().toString().trim();
        if (TextUtils.isEmpty(info)) {
            ToastUtils.showToast("请填写信息");
            return;
        }
        Intent infoIntent = new Intent().putExtra("data", info);
        setResult(RESULT_OK, infoIntent);
        finish();
    }
}
