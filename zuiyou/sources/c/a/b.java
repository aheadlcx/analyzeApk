package c.a;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import c.a.i.a.a;
import c.a.i.w;

public class b {
    public static void a(TextView textView, @ColorRes int i) {
        if (textView != null) {
            if (textView instanceof a) {
                ((a) textView).setTextColorResource(i);
            }
            textView.setTextColor(c.a.d.a.a.a().c(i));
        }
    }

    public static void a(TextView textView, @DrawableRes int i, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4) {
        if (textView != null) {
            if (textView instanceof w) {
                textView.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
            } else if (VERSION.SDK_INT >= 17) {
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(c.a.d.a.a.a().d(i), c.a.d.a.a.a().d(i2), c.a.d.a.a.a().d(i3), c.a.d.a.a.a().d(i4));
            } else {
                Drawable b = c.a.d.a.a.a().b(i);
                if (b != null) {
                    b.setBounds(0, 0, b.getIntrinsicWidth(), b.getIntrinsicHeight());
                }
                Drawable b2 = c.a.d.a.a.a().b(i2);
                if (b2 != null) {
                    b2.setBounds(0, 0, b2.getIntrinsicWidth(), b2.getIntrinsicHeight());
                }
                Drawable b3 = c.a.d.a.a.a().b(i3);
                if (b3 != null) {
                    b3.setBounds(0, 0, b3.getIntrinsicWidth(), b3.getIntrinsicHeight());
                }
                Drawable b4 = c.a.d.a.a.a().b(i4);
                if (b4 != null) {
                    b4.setBounds(0, 0, b4.getIntrinsicWidth(), b4.getIntrinsicHeight());
                }
                textView.setCompoundDrawables(b, b2, b3, b4);
            }
        }
    }

    public static void a(Activity activity, @ColorRes int i) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (VERSION.SDK_INT >= 21) {
                window.setNavigationBarColor(activity.getResources().getColor(i));
            }
        }
    }

    public static int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[]{DisplayMetrics.class}).invoke(defaultDisplay, new Object[]{displayMetrics});
            return displayMetrics.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean a(Window window) {
        if (VERSION.SDK_INT < 21 || (window.getAttributes().flags & 134217728) == 0) {
            return false;
        }
        return true;
    }
}
