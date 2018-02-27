package com.xuke.andoirdone.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.xuke.andoirdone.R;
import com.xuke.andoirdone.model.bean.douban.child.SubjectsBean;

import java.util.List;


/**
 * Created by xuke on 2018/1/24.
 * 热门电影列表的adapter
 */

public class HotMovieAdapter extends BaseQuickAdapter<SubjectsBean, BaseViewHolder> {

    public HotMovieAdapter(int layoutResId, @Nullable List<SubjectsBean> data) {
        super(layoutResId, data);
        initView();
    }

    public HotMovieAdapter(@Nullable List<SubjectsBean> data) {
        super(data);
        initView();
    }

    public HotMovieAdapter(int layoutResId) {
        super(layoutResId);
        initView();
    }

    private void initView() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {
        helper.setText(R.id.tv_movie_title, item.getTitle());
        helper.setText(R.id.tv_movie_directors, item.getDirectorsString());
        helper.setText(R.id.tv_movie_actors, item.getActorsString());
        helper.setText(R.id.tv_movie_genres, item.getGenresString());
        helper.setText(R.id.tv_movie_rating_rate, String.valueOf(item.getRating().getAverage()));
        Glide.with(mContext).load(item.getImages().getLarge()).crossFade(300).placeholder(R.mipmap.img_default_movie).into((ImageView) helper.getView(R.id.iv_moive_photo));
    }


}
