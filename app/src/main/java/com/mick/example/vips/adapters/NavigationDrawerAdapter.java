package com.mick.example.vips.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;

import com.mick.example.vips.R;

public class NavigationDrawerAdapter extends WearableNavigationDrawerView.WearableNavigationDrawerAdapter {


    Context mContext;
    private int count = 0;

    public NavigationDrawerAdapter(Context context, int count) {
        mContext = context;
        this.count = count;
    }

    @Override
    public CharSequence getItemText(int pos) {
        if (pos == 0) {
            return "Djembe";
        } else {
            return "Settings";
        }
    }

    @Override
    public Drawable getItemDrawable(int pos) {
        Resources res = mContext.getResources();
        if (pos == 0) {
            return res.getDrawable(R.drawable.djembe);
        } else {
            return res.getDrawable(R.drawable.settings);
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
