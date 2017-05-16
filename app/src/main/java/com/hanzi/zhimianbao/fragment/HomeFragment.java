package com.hanzi.zhimianbao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanzi.zhimianbao.helper.CommendHelper;
import com.hanzi.zhimianbao.helper.FragmentViwHelper;
import com.hanzi.zhimianbao.helper.SpecialHelp;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;


/**
 * @Description: [一句话描述该类的功能]
 * @Author: [Saud]
 * @CreateDate: [15/11/24 18:10]
 * @UpDate: [15/11/24 18:10]
 * @Version: [v1.0]
 */
public class HomeFragment extends Fragment {

    private int position = 0;
    CommendHelper commendHelper;

    private ArrayList<String> mDatas;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (position) {
            case 0:
                commendHelper = new CommendHelper(this);
                commendHelper.init(view);
                commendHelper.pagerRunning();
                break;
            case 1:
                SpecialHelp specialHelp = new SpecialHelp(this);
                specialHelp.init(view);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            switch (position) {
                case 0:
                    System.out.println("推荐->显示##");
                    if (commendHelper != null) {

                        commendHelper.pagerRunning();
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }

        } else {
            //相当于Fragment的onPause
            switch (position) {
                case 0:
                    System.out.println("推荐->隐藏##");
                    if (commendHelper != null && commendHelper.handler != null) {

                        commendHelper.handler.removeMessages(0);
                    }
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        position = FragmentPagerItem.getPosition(getArguments());
        FragmentViwHelper fragmentViwHelper = new FragmentViwHelper(inflater, container);
        View view = fragmentViwHelper.getView(position);
        return view;
    }
}

