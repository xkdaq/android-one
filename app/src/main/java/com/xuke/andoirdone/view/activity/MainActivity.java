package com.xuke.andoirdone.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.xuke.andoirdone.R;
import com.xuke.andoirdone.view.fragment.home.HomeRootFragment;
import com.xuke.andoirdone.view.fragment.movie.MovieRootFragment;
import com.xuke.andoirdone.view.fragment.my.MyRootFragment;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.helper.BottomNavigationViewHelper;
import com.zyw.horrarndoo.sdk.rxbus.RxBus;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xuke on 2018/1/23.
 *
 */

public class MainActivity extends BaseCompatActivity {

    @BindView(R.id.bviv_bar)
    BottomNavigationView bottomNavigationView;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private long TOUCH_TIME = 0;
    private static final long WAIT_TIME = 2000L;
    private SupportFragment[] mFragments = new SupportFragment[3];
    @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null){
            mFragments[FIRST] = HomeRootFragment.newInstance();
            mFragments[SECOND] = MovieRootFragment.newInstance();
            mFragments[THIRD] = MyRootFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        }else {
            mFragments[FIRST] = findFragment(HomeRootFragment.class);
            mFragments[SECOND] = findFragment(MovieRootFragment.class);
            mFragments[THIRD] = findFragment(MyRootFragment.class);
        }

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_home:
                    showHideFragment(mFragments[FIRST]);
                    break;
                case R.id.menu_item_knowledge:
                    showHideFragment(mFragments[SECOND]);
                    break;
                case R.id.menu_item_my:
                    showHideFragment(mFragments[THIRD]);
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                setIsTransAnim(false);
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                ToastUtils.showToast(R.string.press_again);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
