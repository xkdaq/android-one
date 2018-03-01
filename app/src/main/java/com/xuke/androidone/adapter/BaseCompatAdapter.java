package com.xuke.androidone.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuke.androidone.view.widge.RvLoadMoreView;

import java.util.List;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public abstract class BaseCompatAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,
        K> {

    public BaseCompatAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    public BaseCompatAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        init();
    }

    private void init(){
        setLoadMoreView(new RvLoadMoreView());
        setEnableLoadMore(true);
        openLoadAnimation();
    }
}
