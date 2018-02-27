package com.xuke.androidone.view.widge;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuke.androidone.R;


/**
 * Create by xuke on 2017/12/28
 * 正在加载的progressBar
 */
public class ProgressDlgUtil {

    private static Dialog loadingDialog;
    private static Activity context;

    public static void showProgressDlg(Activity context) {
        showProgressDlg("正在加载", context);
    }

    public static void showProgressDlg(String msg, Activity context) {
        if (null == loadingDialog || ProgressDlgUtil.context != context) {
            if (!context.isDestroyed() && !context.isFinishing()) {
                init(context, msg);
            }
        } else {
            if (loadingDialog.isShowing()) {
            } else if (!context.isDestroyed() && !context.isFinishing()) {
                loadingDialog.show();
            }
        }
    }

    private static void init(final Activity context, String msg) {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (context == null) {
            return;
        }
        ProgressDlgUtil.context = context;
        View v = View.inflate(context, R.layout.dialog_loading, null);
        TextView tvMsg = v.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);
        loadingDialog = new Dialog(context, R.style.dialog_loading);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(v, new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        loadingDialog.setOnCancelListener(dialog -> stopProgressDlg());

        if (!context.isDestroyed() && !context.isFinishing()) {
            loadingDialog.show();
        }
    }

    public static void stopProgressDlg() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        loadingDialog = null;
        context = null;

    }
}
