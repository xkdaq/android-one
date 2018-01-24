package com.xuke.andoirdone.view.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xuke.andoirdone.R;
import com.xuke.andoirdone.view.fragment.my.child.MyFragment;
import com.zyw.horrarndoo.sdk.base.fragment.BaseCompatFragment;

/**
 * Created by xuke on 2018/1/23.
 */

public class MyRootFragment extends BaseCompatFragment {

    public static MyRootFragment newInstance() {
        Bundle args = new Bundle();
        MyRootFragment fragment = new MyRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(MyFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MyFragment.newInstance());
        }
    }
}
