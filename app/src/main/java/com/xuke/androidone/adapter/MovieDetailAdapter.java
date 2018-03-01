package com.xuke.androidone.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuke.androidone.R;
import com.xuke.androidone.model.bean.douban.child.PersonBean;

import java.util.List;


/**
 * Created by xuke on 2018/3/1.
 * 电影详情adapter
 */

public class MovieDetailAdapter extends BaseCompatAdapter<PersonBean, BaseViewHolder> {

    public MovieDetailAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public MovieDetailAdapter(@LayoutRes int layoutResId, @Nullable List<PersonBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonBean item) {
        helper.setText(R.id.tv_person_name, item.getName());
        helper.setText(R.id.tv_person_type, item.getType());
        Glide.with(mContext)
                .load(item.getAvatars().getLarge())
                .crossFade()
                .into((ImageView) helper.getView(R.id.iv_avatar_photo));
    }
}
