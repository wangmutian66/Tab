package com.example.wangmutian.tab;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by wangmutian on 2018/1/11.
 */

public abstract class Basepage{
    public Activity mactivity;
    public View mrootView;

    public Basepage(Activity activity){
        mactivity=activity;
        mrootView=initData();
    }

    public abstract View initData();

}
