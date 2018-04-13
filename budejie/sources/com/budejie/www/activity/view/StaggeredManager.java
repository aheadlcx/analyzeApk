package com.budejie.www.activity.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.budejie.www.util.aa;

public class StaggeredManager extends StaggeredGridLayoutManager {
    public StaggeredManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public StaggeredManager(int i, int i2) {
        super(i, i2);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("StaggeredManager", e.getMessage());
                Log.d("StaggeredManager", "onLayoutChildren: 崩溃信息--》" + e.getMessage());
            }
        }
    }
}
