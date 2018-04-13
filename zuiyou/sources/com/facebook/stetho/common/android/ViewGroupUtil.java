package com.facebook.stetho.common.android;

import android.view.View;
import android.view.ViewGroup;

public final class ViewGroupUtil {
    private ViewGroupUtil() {
    }

    public static int findChildIndex(ViewGroup viewGroup, View view) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i) == view) {
                return i;
            }
        }
        return -1;
    }
}
