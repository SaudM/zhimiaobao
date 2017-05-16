package com.hanzi.zhimianbao.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hanzi.zhimianbao.R;

/**   
 * @Description:  [根据viewpager的不同，返回不同页面的view]
 * @Author:       [Saud]
 * @CreateDate:   [15/11/24 20:38]   
 * @UpDate:       [15/11/24 20:38]   
 * @Version:      [v1.0] 
 * 
 */
public class FragmentViwHelper {


    private final LayoutInflater mInflater;
    private final ViewGroup mContainer;

    public FragmentViwHelper(LayoutInflater inflater, ViewGroup container) {
        this.mInflater = inflater;
        this.mContainer = container;
    }

    public View getView(int position) {
        View view = null;
        switch (position) {
            case 0:
                view = mInflater.inflate(R.layout.fragment_recommend, mContainer, false);
                break;
            case 1:
                view = mInflater.inflate(R.layout.fragment_special, mContainer, false);
                break;
            case 2:
                view = mInflater.inflate(R.layout.fragment_test, mContainer, false);
                break;
            case 3:
                view = mInflater.inflate(R.layout.fragment_user, mContainer, false);
                break;
            default:
                break;
        }
        return view;
    }



}
