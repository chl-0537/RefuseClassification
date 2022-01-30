package com.example.refuseclassification.mainfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.refuseclassification.PagerAdapter;
import com.example.refuseclassification.R;
import com.example.refuseclassification.guidefragment.DryFragment;
import com.example.refuseclassification.guidefragment.HarmfulFragment;
import com.example.refuseclassification.guidefragment.RecyclableFragment;
import com.example.refuseclassification.guidefragment.WetFragment;
import com.example.refuseclassification.setTitleCenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideFragment extends Fragment {

    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_guide, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.guide_toolbar);
        toolbar.setTitle("指南");
        new setTitleCenter().setTitleCenter(toolbar);
        // 初始化tabLayout和viewPager并绑定滑动和点击事件
        initMenuTabs(view);
        initViewPager(view);
        bindTabAndPager(view);
        return view;
    }

    public void initMenuTabs(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.guide_tab);
        tabLayout.setSelectedTabIndicatorColor(0);
        tabLayout.addTab(tabLayout.newTab().setText("可回收物"));
        tabLayout.addTab(tabLayout.newTab().setText("有害垃圾"));
        tabLayout.addTab(tabLayout.newTab().setText("湿垃圾"));
        tabLayout.addTab(tabLayout.newTab().setText("干垃圾"));
    }

    public void initViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerGuide);
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecyclableFragment());
        fragmentList.add(new HarmfulFragment());
        fragmentList.add(new WetFragment());
        fragmentList.add(new DryFragment());
        adapter = new PagerAdapter(getFragmentManager(), this, fragmentList);
        viewPager.setAdapter(adapter);
    }

    public void bindTabAndPager(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.guide_tab);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerGuide);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(3); // 设置缓存页面为3
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
