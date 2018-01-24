package com.xuke.andoirdone.view.fragment.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xuke.andoirdone.R;
import com.xuke.andoirdone.view.fragment.movie.child.MovieFragment;
import com.zyw.horrarndoo.sdk.base.fragment.BaseCompatFragment;

/**
 * Created by xuke on 2018/1/23.
 */
public class MovieRootFragment extends BaseCompatFragment {

    public static MovieRootFragment newInstance() {
        Bundle args = new Bundle();
        MovieRootFragment fragment = new MovieRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(MovieFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MovieFragment.newInstance());
        }
    }
}
