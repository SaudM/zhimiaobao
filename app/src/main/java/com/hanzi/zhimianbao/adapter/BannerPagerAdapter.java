package com.hanzi.zhimianbao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

public class BannerPagerAdapter extends ModelPagerAdapter {


    public BannerPagerAdapter(FragmentManager fm, PagerModelManager pagerModelManager) {
        super(fm, pagerModelManager);
    }


    @Override
    public Fragment getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return super.getPageTitle(position);
    }

}
