package com.xuke.andoirdone.view.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuke.andoirdone.R;

/**
 * Created by xuke on 2018/2/27.
 * 首页的更多页
 */
public class MoreContentFragment extends Fragment {

    public static MoreContentFragment getInstance() {
        MoreContentFragment fragment = new MoreContentFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home_more, container, false);
        return inflate;
    }
}
