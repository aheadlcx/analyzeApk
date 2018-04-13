package cn.dreamtobe.kpswitch.b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;

public class c {
    private static int a = 0;
    private static int b = 0;
    private static int c = 0;
    private static int d = 0;

    private static class a implements OnGlobalLayoutListener {
        private int a = 0;
        private final ViewGroup b;
        private final cn.dreamtobe.kpswitch.b c;
        private final boolean d;
        private final boolean e;
        private final boolean f;
        private final int g;
        private boolean h;
        private final b i;
        private final int j;
        private boolean k = false;
        private int l;

        a(boolean z, boolean z2, boolean z3, ViewGroup viewGroup, cn.dreamtobe.kpswitch.b bVar, b bVar2, int i) {
            this.b = viewGroup;
            this.c = bVar;
            this.d = z;
            this.e = z2;
            this.f = z3;
            this.g = d.a(viewGroup.getContext());
            this.i = bVar2;
            this.j = i;
        }

        @TargetApi(13)
        public void onGlobalLayout() {
            int i;
            View childAt = this.b.getChildAt(0);
            View view = (View) this.b.getParent();
            Rect rect = new Rect();
            if (this.e) {
                view.getWindowVisibleDisplayFrame(rect);
                int i2 = rect.bottom - rect.top;
                if (!this.k) {
                    this.k = i2 == this.j;
                }
                if (this.k) {
                    i = i2;
                } else {
                    i = this.g + i2;
                }
            } else if (childAt != null) {
                childAt.getWindowVisibleDisplayFrame(rect);
                i = rect.bottom - rect.top;
            } else {
                Log.w("KeyBordUtil", "user root view not ready so ignore global layout changed!");
                i = -1;
            }
            if (i != -1) {
                a(i);
                b(i);
                this.a = i;
            }
        }

        private void a(int i) {
            if (this.a == 0) {
                this.a = i;
                this.c.a(c.b(a()));
                return;
            }
            int i2;
            if (a.a(this.d, this.e, this.f)) {
                int height = ((View) this.b.getParent()).getHeight() - i;
                Log.d("KeyboardStatusListener", String.format("action bar over layout %d display height: %d", new Object[]{Integer.valueOf(((View) this.b.getParent()).getHeight()), Integer.valueOf(i)}));
                i2 = height;
            } else {
                i2 = Math.abs(i - this.a);
            }
            if (i2 > c.c(a())) {
                Log.d("KeyboardStatusListener", String.format("pre display height: %d display height: %d keyboard: %d ", new Object[]{Integer.valueOf(this.a), Integer.valueOf(i), Integer.valueOf(i2)}));
                if (i2 == this.g) {
                    Log.w("KeyboardStatusListener", String.format("On global layout change get keyboard height just equal statusBar height %d", new Object[]{Integer.valueOf(i2)}));
                } else if (c.b(a(), i2)) {
                    i2 = c.b(a());
                    if (this.c.getHeight() != i2) {
                        this.c.a(i2);
                    }
                }
            }
        }

        private void b(int i) {
            boolean z;
            View view = (View) this.b.getParent();
            int height = view.getHeight() - view.getPaddingTop();
            if (a.a(this.d, this.e, this.f)) {
                z = (this.e || height - i != this.g) ? height > i : this.h;
            } else {
                int i2 = this.b.getResources().getDisplayMetrics().heightPixels;
                if (this.e || i2 != height) {
                    z = this.l == 0 ? this.h : i < this.l - c.c(a());
                    this.l = Math.max(this.l, height);
                } else {
                    Log.w("KeyboardStatusListener", String.format("skip the keyboard status calculate, the current activity is paused. and phone-display-height %d, root-height+actionbar-height %d", new Object[]{Integer.valueOf(i2), Integer.valueOf(height)}));
                    return;
                }
            }
            if (this.h != z) {
                Log.d("KeyboardStatusListener", String.format("displayHeight %d actionBarOverlayLayoutHeight %d keyboard status change: %B", new Object[]{Integer.valueOf(i), Integer.valueOf(height), Boolean.valueOf(z)}));
                this.c.a(z);
                if (this.i != null) {
                    this.i.a(z);
                }
            }
            this.h = z;
        }

        private Context a() {
            return this.b.getContext();
        }
    }

    public interface b {
        void a(boolean z);
    }

    public static void a(View view) {
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public static void b(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static boolean b(Context context, int i) {
        if (a == i || i < 0) {
            return false;
        }
        a = i;
        Log.d("KeyBordUtil", String.format("save keyboard: %d", new Object[]{Integer.valueOf(i)}));
        return b.a(context, i);
    }

    public static int a(Context context) {
        if (a == 0) {
            a = b.b(context, b(context.getResources()));
        }
        return a;
    }

    public static int b(Context context) {
        return Math.min(a(context.getResources()), Math.max(b(context.getResources()), a(context)));
    }

    public static int a(Resources resources) {
        if (b == 0) {
            b = resources.getDimensionPixelSize(cn.dreamtobe.kpswitch.c.a.max_panel_height);
        }
        return b;
    }

    public static int b(Resources resources) {
        if (c == 0) {
            c = resources.getDimensionPixelSize(cn.dreamtobe.kpswitch.c.a.min_panel_height);
        }
        return c;
    }

    public static int c(Context context) {
        if (d == 0) {
            d = context.getResources().getDimensionPixelSize(cn.dreamtobe.kpswitch.c.a.min_keyboard_height);
        }
        return d;
    }

    @TargetApi(13)
    public static OnGlobalLayoutListener a(Activity activity, cn.dreamtobe.kpswitch.b bVar, b bVar2) {
        int i;
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        boolean a = e.a(activity);
        boolean b = e.b(activity);
        boolean c = e.c(activity);
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT >= 13) {
            Point point = new Point();
            defaultDisplay.getSize(point);
            i = point.y;
        } else {
            i = defaultDisplay.getHeight();
        }
        OnGlobalLayoutListener aVar = new a(a, b, c, viewGroup, bVar, bVar2, i);
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
        return aVar;
    }
}
