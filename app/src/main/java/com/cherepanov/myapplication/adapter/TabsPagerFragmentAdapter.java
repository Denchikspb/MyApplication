package com.cherepanov.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cherepanov.myapplication.fragment.ClockFragment;

/**
 * Created by Денис on 18.09.2017.
 */

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);

        tabs = new String[]{
                "Будильник",
                "Секундомер",
                "Tab 3"
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ClockFragment.getInstance();
            case 1:
                ClockFragment.getInstance();
            case 2:
                ClockFragment.getInstance();
        }

        return ClockFragment.getInstance();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
