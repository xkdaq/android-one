package com.xuke.andoirdone.view.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuke.andoirdone.R;
import com.zyw.horrarndoo.sdk.base.fragment.BaseCompatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeContentFragment extends BaseCompatFragment {

    @BindView(R.id.text)
    TextView text;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_content;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        String index = getArguments().getString("index");
        text.setText(index);
    }

}
