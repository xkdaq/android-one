package com.xuke.androidone.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xuke.androidone.R;
import com.xuke.androidone.view.fragment.home.child.HomeFragment;
import com.zyw.horrarndoo.sdk.base.fragment.BaseCompatFragment;

/**
 * Created by xuke on 2018/1/23.
 */

public class HomeRootFragment extends BaseCompatFragment {

    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();
        HomeRootFragment fragment = new HomeRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }
}
