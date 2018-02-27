package com.xuke.androidone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuke on 2018/2/26.
 * 首页Viewpager的adapter
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int mAdapterType;
    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragList, int adapterType) {
        super(fm);
        this.fragments = fragList;
        this.mAdapterType = adapterType;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == Constants.MAX_PAGE_NUM - 1) {
//            return MoreContentFragment.getInstance();
//        }
//        switch (mAdapterType) {
//            case Constants.TYPE_HOME:
//                return HomeContentFragment.getInstance(position);
//        }
//        return HomeContentFragment.getInstance(position);
        return fragments.get(position);
    }

    public void refresh(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
