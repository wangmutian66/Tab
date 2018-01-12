package com.example.wangmutian.tab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangmutian.tab.listview.NewListview;

import java.util.ArrayList;

public class InitActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager;
    ArrayList<Basepage> list;
    public Activity mActivity;
    private LinearLayout ll;
    LinearLayout guidao;
    View viewun;
    int childcount;
    HorizontalScrollView horizontalScrollView;
    int scrolljuli=0;
    int pingmuwidth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity(); // 获取当前 fragment 所依赖的  Activity

    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity,R.layout.activity_init,null);
        viewPager = view.findViewById(R.id.vp_view);
        guidao=view.findViewById(R.id.guidao);
        ll=view.findViewById(R.id.ll);
        viewun=view.findViewById(R.id.undifind);
        horizontalScrollView=view.findViewById(R.id.horizontalScrollView);
        childcount=ll.getChildCount();
        for(int i = 0; i< childcount ; i++){
            ll.getChildAt(i).setOnClickListener(this);
        }

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        pingmuwidth = wm.getDefaultDisplay().getWidth();

        point(0,0);


        horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolljuli=scrollX;
            }
        });

        return view;
    }


    public void point(int position,float positionOffset){
        //guidao.removeAllViews();
        int mPointDis=ll.getChildAt(1).getLeft()-ll.getChildAt(0).getLeft();
//        View v=new View(mActivity);
        int dis= (int) (positionOffset*mPointDis) + (position*mPointDis);
//        LinearLayout.LayoutParams paramss=new LinearLayout.LayoutParams(120,22);
//        v.setBackgroundColor(Color.RED);
//        v.setLayoutParams(paramss);
//        paramss.leftMargin=dis;
//
//        guidao.addView(v);
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) viewun.getLayoutParams();
        params.leftMargin=dis;
        viewun.setLayoutParams(params);

    }


    @Override
    public void initData() {
        list = new ArrayList<Basepage>();
        for(int i=0;i<childcount;i++){
            list.add(new NewListview(mActivity));
        }



        viewPager.setAdapter(new myviewpager());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int getleft=ll.getChildAt(position).getLeft();

                System.out.println("---------->getleft:"+getleft);
                pointCoursor(position);
                point(position,positionOffset);
//                int scrolljuli=0;
//                int pingmuwidth;
                horizontalScrollView.getWidth();

                if(getleft-scrolljuli <= 68*2){
                    scrolljuli=scrolljuli-68;
                    horizontalScrollView.scrollTo(scrolljuli,0);
                }

                if(pingmuwidth-getleft < 68*2){
                    scrolljuli=scrolljuli+68;
                    horizontalScrollView.scrollTo(scrolljuli,0);
                }

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("---------->postion:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("---------->state:");
            }
        } );
    }


    private void pointCoursor(int postion){
        int count=ll.getChildCount();
        for(int i=0;i<count;i++){
            TextView text= (TextView) ll.getChildAt(i);
            if(i == postion){
                text.setTextAppearance(mActivity,R.style.Cursor);

            }else{
                text.setTextAppearance(mActivity,R.style.Default);

            }
        }
    }

    @Override
    public void onClick(View v) {
        int index=((LinearLayout)v.getParent()).indexOfChild(v);
        viewPager.setCurrentItem(index);
    }


    class myviewpager extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Basepage activity=list.get(position);
            View view=activity.initData();
            container.addView(view);
            //list.get(position).initData();
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }





    /*
    public static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }




        @Override
        public Fragment getItem(int arg0) {
            Fragment ft = null;

            switch (arg0) {
                case 0:
                    ft = new NewListview(mActivity);
                    break;

                default:
                    ft = new SecondFragment();

                    Bundle args = new Bundle();
                    args.putString(ARGUMENTS_NAME, tabTitle[arg0]);
                    ft.setArguments(args);

                    break;
            }


            return ft;
        }

        @Override
        public int getCount() {

            return tabTitle.length;
        }

    }
    */

}
