package com.hanzi.zhimianbao.helper;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.hanzi.zhimianbao.R;
import com.hanzi.zhimianbao.adapter.BannerPagerAdapter;
import com.hanzi.zhimianbao.adapter.CommonAdapter;
import com.hanzi.zhimianbao.adapter.ViewHolder;
import com.hanzi.zhimianbao.bean.RecommendBean;
import com.hanzi.zhimianbao.fragment.BannerFragment;
import com.hanzi.zhimianbao.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.TabClickListener;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

/**
 * @Description: [commend页面的数据等逻辑]
 * @Author: [Saud]
 * @CreateDate: [15/11/30 16:39]
 * @UpDate: [15/11/30 16:39]
 * @Version: [v1.0]
 */
public class CommendHelper {

    private final HomeFragment fragment;
    private ScrollerViewPager viewPager;
    private BannerPagerAdapter adapter;
    private ArrayList<RecommendBean> mDatas;
    private int BANNER_PAGER_SCOLER_TIME = 5000;
    private ListView lv_recommedlist;
    private View headerView;

    public CommendHelper(HomeFragment fragment) {
        this.fragment = fragment;
        initData();
    }

    public void init(View view) {
        viewInit(view);
        heardInit();
        recommendListInit();
    }


    private void viewInit(View view) {

        lv_recommedlist = (ListView) view.findViewById(R.id.lv_recommedlist);
        headerView = ((LayoutInflater)fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_commend_heard, null);
    }

    private void heardInit() {
        viewPager = (ScrollerViewPager) headerView.findViewById(R.id.view_pager);
        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(BannerFragment.class, getBgRes(), getTitles());
        adapter = new BannerPagerAdapter(fragment.getChildFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed(1200);
        SpringIndicator springIndicator = (SpringIndicator) headerView.findViewById(R.id.indicator);
        springIndicator.setViewPager(viewPager);
        springIndicator.setOnTabClickListener(new TabClickListener() {
            @Override
            public boolean onTabClick(int position) {
                handler.removeMessages(0);
                pagerRunning();
                return true;
            }
        });
    }

    private void recommendListInit() {

        lv_recommedlist.setAdapter(new CommonAdapter<RecommendBean>(fragment.getContext(), mDatas,
                R.layout.item_recommend) {
            @Override
            public void convert(ViewHolder holder, RecommendBean recommendBean) {
                holder.setText(R.id.tv_title, recommendBean.title);
                holder.setText(R.id.tv_desc, recommendBean.desc);
            }
        });

        lv_recommedlist.addHeaderView(headerView);
    }


    public void pagerRunning() {

        handler.sendEmptyMessageDelayed(0, BANNER_PAGER_SCOLER_TIME);

    }

    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % getBgRes().size());
            pagerRunning();
        }
    };



    private List<Integer> getBgRes() {
        return Lists.newArrayList(R.mipmap.bg1, R.mipmap.bg2, R.mipmap.bg3, R.mipmap.bg4);
    }

    /**
     * 设置bannertilte 的参数
     *
     * @return
     */
    private List<String> getTitles() {

        ArrayList<String> list = Lists.newArrayList();
        for (int i = 0; i < getBgRes().size(); i++) {
            list.add(String.valueOf(i + 1));
        }
        return list;
    }


    /*
    初始化假数据
     */
    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            RecommendBean bean = new RecommendBean();
            bean.title = ("" + (char) i + "、高分语文十个不为人知的秘密");
            bean.desc = ("" + (char) i + "、历史语文考题分析，高校名师为你精准定位");
            mDatas.add(bean);
        }
    }

}


