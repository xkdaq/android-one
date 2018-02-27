package com.xuke.andoirdone.presenter.home;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.xuke.andoirdone.contract.home.HomeContenContract;
import com.xuke.andoirdone.model.bean.one.OneBean;
import com.xuke.andoirdone.model.home.HomeContentModel;

/**
 * Created by xuke on 2018/2/27.
 * 首页的presenter
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
