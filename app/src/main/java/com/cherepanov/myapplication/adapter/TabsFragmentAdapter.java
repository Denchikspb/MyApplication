package com.cherepanov.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cherepanov.myapplication.fragment.AlarmClockFragment;
import com.cherepanov.myapplication.fragment.StopwatchFragment;

/**
 * Created by Денис on 18.09.2017.
 */

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsFragmentAdapter(FragmentManager fm) {
        super(fm);

        tabs = new String[]{
                "Будильник",
                "Секундомер"
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = AlarmClockFragment.getInstance();
                break;
            case 1:
                fragment = StopwatchFragment.getInstance();
                break;
            default:
                fragment = AlarmClockFragment.getInstance();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
