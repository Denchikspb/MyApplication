package com.cherepanov.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cherepanov.myapplication.fragment.AlarmClockFragment;
import com.cherepanov.myapplication.fragment.StopwatchFragment;

/**
 * Created by Денис on 18.09.2017.
 */

public class TabsFragmentAdapter extends FragmentStatePagerAdapter {

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
        switch (position) {
            case 0:
                return AlarmClockFragment.getInstance();
            case 1:
                return StopwatchFragment.getInstance();
            default:
                return AlarmClockFragment.getInstance();
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
