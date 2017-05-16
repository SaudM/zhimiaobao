/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hanzi.zhimianbao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hanzi.zhimianbao.R;

/**
 * @Description: [banner的fragment]
 * @Author: [Saud]
 * @CreateDate: [15/11/25 11:18]
 * @UpDate: [15/11/25 11:18]
 * @Version: [v1.0]
 */
public class BannerFragment extends Fragment {

    private int bgRes;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getInt("data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) getView().findViewById(R.id.image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(bgRes);

        switch (bgRes) {
            case R.mipmap.bg1:
                System.out.println("banner-1-显示-"+bgRes);
                break;
            case R.mipmap.bg2:
                System.out.println("banner-2-显示-"+bgRes);
                break;
            case R.mipmap.bg3:
                System.out.println("banner-3-显示-"+bgRes);
                break;
            case R.mipmap.bg4:
                System.out.println("banner-4-显示-"+bgRes);
                break;

            default:
                break;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

//            switch (bgRes){
//                  case R.mipmap.bg1:
//                      System.out.println("banner-1-显示");
//                    break;
//                  case R.mipmap.bg2:
//                      System.out.println("banner-2-显示");
//                    break;
//                  case R.mipmap.bg3:
//                      System.out.println("banner-3-显示");
//                    break;
//                  case R.mipmap.bg4:
//                      System.out.println("banner-4-显示");
//                    break;
//
//                  default:
//                    break;
//                }
        } else {

        }
    }

    @Override
    public void onPause() {
//        System.out.println("banner->onPause");
        super.onPause();
    }
}
