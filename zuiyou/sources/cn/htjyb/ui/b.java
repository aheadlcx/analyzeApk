package cn.htjyb.ui;

import android.app.Activity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class b {
    public static void a(ViewGroup viewGroup) {
        int measuredWidth = viewGroup.getMeasuredWidth();
        int measuredHeight = viewGroup.getMeasuredHeight();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            int makeMeasureSpec;
            int makeMeasureSpec2;
            View childAt = viewGroup.getChildAt(i);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams.width > 0) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
            } else if (layoutParams.width == -1) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
            } else if (layoutParams.width == -2) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE);
            } else {
                makeMeasureSpec = 0;
            }
            if (layoutParams.height > 0) {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            } else if (layoutParams.height == -1) {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
            } else if (layoutParams.height == -2) {
                makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(measuredHeight, Integer.MIN_VALUE);
            } else {
                makeMeasureSpec2 = 0;
            }
            childAt.measure(makeMeasureSpec, makeMeasureSpec2);
        }
    }

    public static void a(View view) {
        try {
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-1, -2);
            }
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
            int i = layoutParams.height;
            if (i > 0) {
                i = MeasureSpec.makeMeasureSpec(i, 1073741824);
            } else {
                i = MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(childMeasureSpec, i);
        } catch (Exception e) {
        }
    }

    public static Activity a(Activity activity) {
        if (activity.getParent() != null) {
            return a(activity.getParent());
        }
        return activity;
    }
}
