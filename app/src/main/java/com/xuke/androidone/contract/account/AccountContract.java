package com.xuke.androidone.contract.account;

import android.content.Intent;
import android.net.Uri;

import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.IBaseActivity;
import com.zyw.horrarndoo.sdk.base.IBaseModel;

import java.io.File;

/**
 * Created by xuke on 2018/4/27.
 * <p></p>
 */

public interface AccountContract {

    abstract class AccountPresenter extends BasePresenter<IAccountModel, IAccountView> {
        /**
         * 头像点击
         */
        public abstract void btnHeadClicked();

        /**
         * 相机按钮点击
         */
        public abstract void btnCameraClicked();

        /**
         * 相册按钮点击
         */
        public abstract void btnPhotoClicked();

        /**
         * 取消按钮点击
         */
        public abstract void btnCancelClicked();

        /**
         * 回调
         * */
        public abstract void onActivityResult(int requestCode, int resultCode, Intent intent);
    }


    interface IAccountModel extends IBaseModel {

    }

    interface IAccountView extends IBaseActivity {
        /**
         * 初始化PopupView
         */
        void initPopupView();

        /**
         * 显示PopupView
         */
        void showPopupView();

        /**
         * 隐藏PopupView
         */
        void dismissPopupView();

        /**
         * 返回popupView是否显示
         */
        boolean popupIsShowing();

        /**
         * 前往系统相机界面
         *
         * @param tempFile    相片存储文件
         * @param requestCode requestCode
         */
        void gotoSystemCamera(File tempFile, int requestCode);

        /**
         * 前往系统图库界面
         *
         * @param requestCode requestCode
         */
        void gotoSystemPhoto(int requestCode);


        /**
         * 前往裁剪设置界面
         * */
        void gotoHeadSettingActivity(Uri uri);

    }
}
