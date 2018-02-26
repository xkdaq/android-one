package com.xuke.andoirdone.view.fragment.home.child;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.xuke.andoirdone.R;
import com.xuke.andoirdone.adapter.ViewPagerAdapter;
import com.xuke.andoirdone.api.OneApi;
import com.xuke.andoirdone.model.bean.one.OneIdBean;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseMVPCompatFragment;
import com.zyw.horrarndoo.sdk.helper.RetrofitCreateHelper;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuke on 2018/2/26.
 * 首页
 */
public class HomeFragment extends BaseMVPCompatFragment {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private ArrayList<Fragment> fragmentList = null;
    private ArrayList<String> mDatas = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;

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
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), fragmentList, 1);
        viewpager.setAdapter(viewPagerAdapter);
        getData();

    }

    public void getData() {
        Call<OneIdBean> homeId = RetrofitCreateHelper.createApi(OneApi.class, OneApi.HOST).getHomeId();
        homeId.enqueue(new Callback<OneIdBean>() {
            @Override
            public void onResponse(Call<OneIdBean> call, Response<OneIdBean> response) {
                OneIdBean body = response.body();
                mDatas.clear();
                if (body != null) {
                    Logger.e("body=" + body.toString());
                    List<String> data = body.getData();
                    mDatas.addAll(data);
                    setData();
                }
            }

            @Override
            public void onFailure(Call<OneIdBean> call, Throwable t) {
                ToastUtils.showToast("首页获取数据失败");
            }
        });
    }

    private void setData() {
        fragmentList = new ArrayList<>();
        fragmentList.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            HomeContentFragment cardFragment = new HomeContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("index", mDatas.get(i));
            cardFragment.setArguments(bundle);
            fragmentList.add(cardFragment);
        }
        viewPagerAdapter.refresh(fragmentList);
    }


}
