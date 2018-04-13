package com.scwang.smartrefresh.layout.f;

import android.graphics.PointF;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

public class c {
    public static boolean a(View view, MotionEvent motionEvent) {
        if (a(view) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && motionEvent != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            PointF pointF = new PointF();
            for (int childCount = viewGroup.getChildCount(); childCount > 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (a(viewGroup, childAt, motionEvent.getX(), motionEvent.getY(), pointF)) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(pointF.x, pointF.y);
                    return a(childAt, obtain);
                }
            }
        }
        return true;
    }

    public static boolean b(View view, MotionEvent motionEvent) {
        if (!b(view) && a(view) && view.getVisibility() == 0) {
            return true;
        }
        if (!(view instanceof ViewGroup) || motionEvent == null) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        PointF pointF = new PointF();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (a(viewGroup, childAt, motionEvent.getX(), motionEvent.getY(), pointF)) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.offsetLocation(pointF.x, pointF.y);
                return b(childAt, obtain);
            }
        }
        return false;
    }

    public static boolean c(View view, MotionEvent motionEvent) {
        if (b(view) && view.getVisibility() == 0) {
            return true;
        }
        if (!(view instanceof ViewGroup) || motionEvent == null) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        PointF pointF = new PointF();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (a(viewGroup, childAt, motionEvent.getX(), motionEvent.getY(), pointF)) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.offsetLocation(pointF.x, pointF.y);
                return c(childAt, obtain);
            }
        }
        return false;
    }

    public static boolean a(View view) {
        if (VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(-1);
        }
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                return false;
            }
            return true;
        } else if (view.getScrollY() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean b(View view) {
        if (VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(1);
        }
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            if (absListView.getChildCount() <= 0 || (absListView.getLastVisiblePosition() >= absListView.getChildCount() - 1 && absListView.getChildAt(absListView.getChildCount() - 1).getBottom() <= absListView.getPaddingBottom())) {
                return false;
            }
            return true;
        } else if (view.getScrollY() >= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean a(ViewGroup viewGroup, View view, float f, float f2, PointF pointF) {
        if (view.getVisibility() != 0) {
            return false;
        }
        float[] fArr = new float[]{f, f2};
        a(viewGroup, view, fArr);
        boolean a = a(view, fArr[0], fArr[1], 0.0f);
        if (a && pointF != null) {
            pointF.set(fArr[0] - f, fArr[1] - f2);
        }
        return a;
    }

    public static boolean a(View view, float f, float f2, float f3) {
        return f >= (-f3) && f2 >= (-f3) && f < ((float) view.getWidth()) + f3 && f2 < ((float) view.getHeight()) + f3;
    }

    public static void a(ViewGroup viewGroup, View view, float[] fArr) {
        fArr[0] = fArr[0] + ((float) (viewGroup.getScrollX() - view.getLeft()));
        fArr[1] = fArr[1] + ((float) (viewGroup.getScrollY() - view.getTop()));
    }
}
