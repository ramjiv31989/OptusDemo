package com.optus.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.optus.demo.ui.FirstFragment;
import com.optus.demo.ui.FourthFragment;
import com.optus.demo.ui.SecondFragment;
import com.optus.demo.ui.ThirdFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final int totalPage = 4;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = FirstFragment.newInstance();
                break;
            case 1:
                fragment = SecondFragment.newInstance();
                break;
            case 2:
                fragment = ThirdFragment.newInstance();
                break;
            case 3:
                fragment = FourthFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return totalPage;
    }

}
