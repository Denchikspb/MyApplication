package com.cherepanov.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.fragment.AlarmClockFragment;
import com.cherepanov.myapplication.fragment.CalculatorFragment;
import com.cherepanov.myapplication.fragment.ContainerClockFragment;
import com.cherepanov.myapplication.fragment.RemindFragment;
import com.cherepanov.myapplication.fragment.TopFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MyTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initNavigationView();
        initTopFragment();
    }

    private void initTopFragment() {
        Fragment fragment = TopFragment.getInstance();
        replaceFragmentToContainer(fragment);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                selectItem(item.getItemId());
                return true;
            }
        });
    }

    private void selectItem(int itemId) {
        Fragment fragment;
        switch (itemId) {
            case R.id.menu_navigation_clock:
                fragment = ContainerClockFragment.getInstance(this);
                break;
            case R.id.menu_navigation_calculate:
                fragment = CalculatorFragment.getInstance();
                break;
            case R.id.menu_navigation_remind:
                fragment = RemindFragment.getInstance(this);
                break;
            default:
                fragment = TopFragment.getInstance();
        }

        replaceFragmentToContainer(fragment);
    }

    private void replaceFragmentToContainer(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_fragment, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
