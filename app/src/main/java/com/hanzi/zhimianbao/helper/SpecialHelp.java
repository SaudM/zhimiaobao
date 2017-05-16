package com.hanzi.zhimianbao.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hanzi.zhimianbao.R;
import com.hanzi.zhimianbao.adapter.CommonAdapter;
import com.hanzi.zhimianbao.adapter.ViewHolder;
import com.hanzi.zhimianbao.bean.SpecialBean;
import com.hanzi.zhimianbao.fragment.HomeFragment;

import java.util.ArrayList;

/**
 * Created by Saud on 15/12/1.
 */
public class SpecialHelp {


    private HomeFragment fragment;
    private ListView lv_speciallist;
    private ArrayList<SpecialBean> mDatas;
    private View headerView;


    public SpecialHelp(HomeFragment fragment) {
        this.fragment = fragment;
        initdata();
    }

    private void initdata() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            SpecialBean bean = new SpecialBean();
            bean.title = ("" + (char) i + "、2015年北京海淀基础部分考题,2015年北京海淀基础部分考题2015年北京海淀基础部分考题");
            mDatas.add(bean);
        }
    }

    public void init(View view) {
        initView(view);
        initRecyclerView();
    }


    private void initView(View view) {
        lv_speciallist = (ListView) view.findViewById(R.id.lv_speciallist);
        headerView = ((LayoutInflater) fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_special_heard, null);
    }

    private void initRecyclerView() {
        lv_speciallist.setAdapter(new CommonAdapter<SpecialBean>(fragment.getActivity(), mDatas, R.layout.item_special) {
            @Override
            public void convert(ViewHolder holder, SpecialBean specialBean) {

                holder.setText(R.id.tv_title, specialBean.title);
                if (holder.getPosition() % 2 == 1) {
//                    holder.setBackgroundColor(R.id.rl_item, Color.WHITE);



                }
            }
        });
        lv_speciallist.addHeaderView(headerView);

    }


}
