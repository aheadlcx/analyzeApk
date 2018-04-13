package cn.dreamtobe.kpswitch.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cn.dreamtobe.kpswitch.a;
import cn.dreamtobe.kpswitch.b.d;
import cn.dreamtobe.kpswitch.b.e;

public class c {
    private int a = -1;
    private final View b;
    private final int c;
    private final boolean d;
    private a e;

    public c(View view) {
        this.b = view;
        this.c = d.a(view.getContext());
        this.d = e.b((Activity) view.getContext());
    }

    @TargetApi(16)
    public void a(int i, int i2) {
        if (this.d && VERSION.SDK_INT >= 16 && this.b.getFitsSystemWindows()) {
            Rect rect = new Rect();
            this.b.getWindowVisibleDisplayFrame(rect);
            i2 = rect.bottom - rect.top;
        }
        Log.d("KPSRootLayoutHandler", "onMeasure, width: " + i + " height: " + i2);
        if (i2 >= 0) {
            if (this.a < 0) {
                this.a = i2;
                return;
            }
            int i3 = this.a - i2;
            if (i3 == 0) {
                Log.d("KPSRootLayoutHandler", "" + i3 + " == 0 break;");
            } else if (Math.abs(i3) == this.c) {
                Log.w("KPSRootLayoutHandler", String.format("offset just equal statusBar height %d", new Object[]{Integer.valueOf(i3)}));
            } else {
                this.a = i2;
                a a = a(this.b);
                if (a == null) {
                    Log.w("KPSRootLayoutHandler", "can't find the valid panel conflict layout, give up!");
                } else if (Math.abs(i3) < cn.dreamtobe.kpswitch.b.c.c(this.b.getContext())) {
                    Log.w("KPSRootLayoutHandler", "system bottom-menu-bar(such as HuaWei Mate7) causes layout changed");
                } else if (i3 > 0) {
                    a.c_();
                } else if (a.a() && a.b()) {
                    a.c();
                }
            }
        }
    }

    private a a(View view) {
        if (this.e != null) {
            return this.e;
        }
        if (view instanceof a) {
            this.e = (a) view;
            return this.e;
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                a a = a(((ViewGroup) view).getChildAt(i));
                if (a != null) {
                    this.e = a;
                    return this.e;
                }
            }
        }
        return null;
    }
}
