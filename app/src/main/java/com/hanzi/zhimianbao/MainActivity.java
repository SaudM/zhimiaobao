package com.hanzi.zhimianbao;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hanzi.zhimianbao.fragment.HomeFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
/**
 * @Description:  [app的主activity]
 * @Author:       [Saud]
 * @CreateDate:   [15/11/22 16:52]
 * @UpDate:       [15/11/25 16:52]
 * @Version:      [v1.0]
 *
 */

public class MainActivity extends AppCompatActivity {

    private static int[] tabName = new int[]{
            R.string.app_tab_1,
            R.string.app_tab_2,
            R.string.app_tab_3,
            R.string.app_tab_4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();

    }


    /**
     * 初始化app头部导航
     */
    private void initTab() {
        ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.view_distribute_evenly, tab, false));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        for (int titleResId : tabName) {
            pages.add(FragmentPagerItem.of(getString(titleResId), HomeFragment.class));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }





}
