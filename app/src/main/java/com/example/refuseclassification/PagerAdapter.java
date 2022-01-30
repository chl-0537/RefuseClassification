package com.example.refuseclassification;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.refuseclassification.mainfragment.GuideFragment;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    Context context;
    private List<Fragment> fragmentList;

    public PagerAdapter(@NonNull FragmentManager fragmentManager, Context context, List<Fragment> list) {
        super(fragmentManager);
        fragmentList = list;
        this.context = context;
    }

    public PagerAdapter(@NonNull FragmentManager fragmentManager, GuideFragment guideFragment, List<Fragment> list) {
        super(fragmentManager);
        fragmentList = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
