package com.xuke.andoirdone.view.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuke.andoirdone.R;
import com.xuke.andoirdone.contract.home.HomeContenContract;
import com.xuke.andoirdone.model.bean.one.OneBean;
import com.xuke.andoirdone.presenter.home.HomeMainPresenter;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseMVPCompatFragment;

import butterknife.BindView;

/**
 * Created by xuke on 2018/2/27.
 * 首页内容
 */
public class HomeContentFragment extends BaseMVPCompatFragment<HomeContenContract.HomeContentPresenter, HomeContenContract.IHomeContentModel> implements HomeContenContract.IHomeContentView {

    @BindView(R.id.img_hp_img_url)
    ImageView imgHpImgUrl;
    @BindView(R.id.tv_hp_author)
    TextView tvHpAuthor;
    @BindView(R.id.tv_hp_content)
    TextView tvHpContent;

    private static String INDEX = "index";

    public static HomeContentFragment getInstance(String index) {
        HomeContentFragment fragment = new HomeContentFragment();
        Bundle args = new Bundle();
        args.putString(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_content;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        String index = getArguments().getString(INDEX);
        mPresenter.loadContent(index);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return HomeMainPresenter.newInstance();
    }

    @Override
    public void updateContent(OneBean oneBean) {
        OneBean.DataBean data = oneBean.getData();

        Glide.with(this).load(data.getHp_img_url()).into(imgHpImgUrl);
        tvHpAuthor.setText(data.getHp_author());
        tvHpContent.setText(data.getHp_content());

    }

    @Override
    public void showNetworkError() {

    }
}
