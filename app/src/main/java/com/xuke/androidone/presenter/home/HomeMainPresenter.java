package com.xuke.androidone.presenter.home;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.xuke.androidone.contract.home.HomeContenContract;
import com.xuke.androidone.model.bean.one.OneBean;
import com.xuke.androidone.model.home.HomeContentModel;

/**
 * Created by xuke on 2018/2/27.
 * 首页的Presenter
 */

public class HomeMainPresenter extends HomeContenContract.HomeContentPresenter {

    @NonNull
    public static HomeMainPresenter newInstance() {
        return new HomeMainPresenter();
    }

    @Override
    public HomeContenContract.IHomeContentModel getModel() {
        return HomeContentModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadContent(String id) {
        if (mIView == null || mIModel == null) {
            return;
        }
        mRxManager.register(mIModel.getHomeContent(id).subscribe((OneBean oneBean) -> {
            Logger.e("oneBean="+oneBean.toString());
            if (mIView == null) {
                return;
            }
            mIView.updateContent(oneBean);
        }, throwable -> {
            if (mIView == null) {
                if (mIView.isVisiable()) {
                    mIView.showToast("请检查网络连接");
                }
            }
        }));
    }
}
