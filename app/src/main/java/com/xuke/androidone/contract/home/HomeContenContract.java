package com.xuke.androidone.contract.home;

import com.xuke.androidone.model.bean.one.OneBean;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.IBaseFragment;
import com.zyw.horrarndoo.sdk.base.IBaseModel;

import io.reactivex.Observable;

/**
 * Created by xuke on 2018/2/27.
 * <p></p>
 */
public interface HomeContenContract {

    /**
     * 首页的presenter
     * */
    abstract class HomeContentPresenter extends BasePresenter<IHomeContentModel, IHomeContentView> {

        /**
         * 加载内容
         * */
        public abstract void loadContent(String id);
    }

    /**
     * 首页的model
     * */
    interface IHomeContentModel extends IBaseModel {
        /**
         * 获取首页内容
         */
        Observable<OneBean> getHomeContent(String id);
    }

    /**
     * 首页的view
     * */
    interface IHomeContentView extends IBaseFragment {

        /**
         * 更新内容
         * */
        void updateContent(OneBean oneBean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }

}
