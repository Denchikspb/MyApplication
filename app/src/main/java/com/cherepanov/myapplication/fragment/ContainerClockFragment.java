package com.cherepanov.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.adapter.TabsFragmentAdapter;

/**
 * Created by Денис on 22.09.2017.
 */

public class ContainerClockFragment extends Fragment {

    private View view;
    private ViewPager viewPager;
    protected Context context;

    public static ContainerClockFragment getInstance(Context context) {
        Bundle args = new Bundle();
        ContainerClockFragment fragment = new ContainerClockFragment();
        fragment.setArguments(args);
        fragment.setContext(context);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.clock_container_fragment, container, false);
        initTabLayout();
        return view;
    }

    public void setContext(Context context){
        this.context = context;
    }

    private void initTabLayout() {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(((AppCompatActivity)context).getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
