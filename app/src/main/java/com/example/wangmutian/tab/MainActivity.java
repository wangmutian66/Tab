package com.example.wangmutian.tab;

import android.app.Activity;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final String TAG_CONTENT = "TAG_CONTENT";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }


    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();// 开始事务

        transaction.replace(R.id.mroot, new InitActivity(), TAG_CONTENT);
        transaction.commit();// 提交事务
        // Fragment fragment =
        // fm.findFragmentByTag(TAG_LEFT_MENU);//根据标记找到对应的fragment
    }

}
