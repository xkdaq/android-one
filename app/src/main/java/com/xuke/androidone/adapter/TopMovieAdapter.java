package com.xuke.androidone.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuke.androidone.R;
import com.xuke.androidone.model.bean.douban.child.SubjectsBean;

import java.util.List;

/**
 * Created by xuke on 2018/3/1.
 * <p>
 */

public class TopMovieAdapter extends BaseCompatAdapter<SubjectsBean, BaseViewHolder> {

    public TopMovieAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public TopMovieAdapter(@LayoutRes int layoutResId, @Nullable List<SubjectsBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {
        helper.setText(R.id.tv_top_moive_name, item.getTitle());
        helper.setText(R.id.tv_top_moive_rate, String.valueOf(item.getRating().getAverage()));
        Glide.with(mContext)
                .load(item.getImages().getLarge())
                .placeholder(R.mipmap.img_default_movie)
                .into((ImageView) helper.getView(R.id.iv_top_moive_photo));
    }
}
