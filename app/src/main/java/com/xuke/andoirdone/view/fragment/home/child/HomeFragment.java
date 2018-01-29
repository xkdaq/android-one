package com.xuke.andoirdone.view.fragment.home.child;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xuke.andoirdone.R;
import com.xuke.andoirdone.adapter.ViewPagerAdapter;
import com.xuke.andoirdone.utils.Constants;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseMVPCompatFragment;

import butterknife.BindView;

public class HomeFragment extends BaseMVPCompatFragment {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), Constants.TYPE_HOME);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(viewPagerAdapter);
    }
}
