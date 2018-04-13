package cn.dreamtobe.kpswitch.b;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class a {

    public interface a {
        void a(boolean z);
    }

    public static void a(final View view, View view2, final View view3, final a aVar) {
        Activity activity = (Activity) view.getContext();
        if (view2 != null) {
            view2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    boolean b = a.b(view, view3);
                    if (aVar != null) {
                        aVar.a(b);
                    }
                }
            });
        }
        if (a(activity)) {
            view3.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setVisibility(4);
                    }
                    return false;
                }
            });
        }
    }

    public static void a(View view) {
        Activity activity = (Activity) view.getContext();
        view.setVisibility(0);
        if (activity.getCurrentFocus() != null) {
            c.b(activity.getCurrentFocus());
        }
    }

    public static void a(View view, View view2) {
        Activity activity = (Activity) view.getContext();
        c.a(view2);
        if (a(activity)) {
            view.setVisibility(4);
        }
    }

    public static boolean b(View view, View view2) {
        boolean z = view.getVisibility() != 0;
        if (z) {
            a(view);
        } else {
            a(view, view2);
        }
        return z;
    }

    public static void b(View view) {
        Activity activity = (Activity) view.getContext();
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            c.b(activity.getCurrentFocus());
            currentFocus.clearFocus();
        }
        view.setVisibility(8);
    }

    public static boolean a(boolean z, boolean z2, boolean z3) {
        return z || (z2 && !z3);
    }

    static boolean a(Activity activity) {
        return a(e.a(activity), e.b(activity), e.c(activity));
    }
}
