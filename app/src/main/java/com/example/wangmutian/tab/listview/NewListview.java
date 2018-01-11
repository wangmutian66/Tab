package com.example.wangmutian.tab.listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangmutian.tab.Basepage;
import com.example.wangmutian.tab.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wangmutian on 2018/1/11.
 */

public class NewListview extends Basepage {
    ListView listview;

    public NewListview(Activity activity) {
        super(activity);
    }


    @Override
    public View initData() {
        View view=View.inflate(mactivity,R.layout.active_list,null);
        listview=view.findViewById(R.id.listview);
        ArrayList<HashMap<String,String>> demo = new ArrayList<HashMap<String,String>>();
        for (int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("title",i+" 标题");
            demo.add(map);
        }
        MyAdapter adapter=new MyAdapter(mactivity,demo);
        listview.setAdapter(adapter);

        return view;

    }




    class MyAdapter extends BaseAdapter{

        private Activity activity;
        private ArrayList<HashMap<String,String>> demo;
        public MyAdapter(Activity activity,ArrayList<HashMap<String,String>> demo){
            this.activity=activity;
            this.demo=demo;
        }

        @Override
        public int getCount() {
            return demo.size();
        }

        @Override
        public Object getItem(int i) {
            return demo.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View views=View.inflate(activity,R.layout.item,null);
            TextView textview=views.findViewById(R.id.title);
            textview.setText(demo.get(i).get("title"));
            //System.out.println(demo.get(i).get("title"));
            return views;
        }
    }
}
