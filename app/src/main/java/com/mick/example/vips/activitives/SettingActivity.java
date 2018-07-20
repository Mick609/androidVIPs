package com.mick.example.vips.activitives;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableDrawerLayout;
import android.support.wear.widget.drawer.WearableDrawerView;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.view.View;

import com.mick.example.vips.R;
import com.mick.example.vips.adapters.NavigationDrawerAdapter;
import com.mick.example.vips.utilities.AppManager;

public class SettingActivity extends WearableActivity {

    private AppManager mAppManager;

    private WearableDrawerLayout mWearableDrawerLayout;
    private WearableNavigationDrawerView mWearableNavigationDrawer;
    int DrawerCount = 2;

    Context mContext;
    private int nextPosition = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAppManager = new AppManager(this);
        mContext = this;

        mWearableDrawerLayout = findViewById(R.id.setting_drawer_layout);
        // Top navigation drawer
        mWearableNavigationDrawer = findViewById(R.id.setting_top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(new NavigationDrawerAdapter(this, DrawerCount));
        // Peeks navigation drawer on the top.
        mWearableNavigationDrawer.getController().peekDrawer();
        mWearableNavigationDrawer.setCurrentItem(1, true);
        mWearableNavigationDrawer.addOnItemSelectedListener(new WearableNavigationDrawerView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int pos) {
                nextPosition = pos;
                mAppManager.mLog("Drawer pos: " + pos);
            }
        });

        mWearableDrawerLayout.setDrawerStateCallback(new WearableDrawerLayout.DrawerStateCallback() {
            @Override
            public void onDrawerClosed(WearableDrawerLayout layout, WearableDrawerView drawerView) {
                super.onDrawerClosed(layout, drawerView);
                if (nextPosition != 1) {
                    Intent i = new Intent(mContext, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.setting_quit) {
            finish();
        }
    }
}
