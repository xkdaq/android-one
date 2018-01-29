package com.xuke.andoirdone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xuke.andoirdone.utils.Constants;
import com.xuke.andoirdone.view.fragment.home.child.HomeContentFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mAdapterType;

    public ViewPagerAdapter(FragmentManager fm, int adapterType) {
        super(fm);
        mAdapterType = adapterType;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == Constants.MAX_PAGE_NUM - 1) {
            //return MoreContentFragment.getInstance(mAdapterType);
        }
        switch (mAdapterType) {
            case Constants.TYPE_HOME:
                return HomeContentFragment.getInstance(position);
        }
        return HomeContentFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return Constants.MAX_PAGE_NUM;
    }
}
