package com.xuke.androidone.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xuke.androidone.R;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.NetworkConnectionUtils;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;

import static com.xuke.androidone.utils.Accounts.ARG_KEY_WEB_VIEW_LOAD_TITLE;
import static com.xuke.androidone.utils.Accounts.ARG_KEY_WEB_VIEW_LOAD_URL;

/**
 * Created by xuke on 2018/1/23.
 * 加载webView
 */

public class WebViewActivity extends BaseCompatActivity {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.my_profile_tracker)
    ProgressBar mProgressBar;

    private String mTitle, mUrl;

    public static void start(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(ARG_KEY_WEB_VIEW_LOAD_TITLE, title);
        intent.putExtra(ARG_KEY_WEB_VIEW_LOAD_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(ARG_KEY_WEB_VIEW_LOAD_URL);
            mTitle = bundle.getString(ARG_KEY_WEB_VIEW_LOAD_TITLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        toolbar.setTitle(mTitle);
        initWebSetting(webView.getSettings());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        if (!TextUtils.isEmpty(mUrl)) {
            webView.loadUrl(mUrl);
        } else {
            ToastUtils.showToast("获取URL地址失败");
        }
    }

    /**
     * 设置webview
     */
    protected void initWebSetting(WebSettings settings) {
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setSupportZoom(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (NetworkConnectionUtils.isConnected(mContext)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
    }

}
