package com.xuke.andoirdone.view.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuke.andoirdone.R;


public class HomeContentFragment extends Fragment {

    public static HomeContentFragment getInstance(int index) {
        HomeContentFragment fragment = new HomeContentFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_itemview, container);
        return inflate;
    }
}
