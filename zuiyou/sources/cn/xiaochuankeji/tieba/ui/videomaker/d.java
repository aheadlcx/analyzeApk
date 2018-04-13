package cn.xiaochuankeji.tieba.ui.videomaker;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;

public final class d {
    private View a;
    private View b;
    private WindowManager c;
    private b d;
    private a e;
    private int f = -1;
    private int g = -1;
    private OnGlobalLayoutListener h = new OnGlobalLayoutListener(this) {
        final int[] a = new int[2];
        final /* synthetic */ d b;

        {
            this.b = r2;
        }

        public void onGlobalLayout() {
            if (this.b.b != null) {
                this.b.b.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.b.b != null) {
                            this.a.b.b.getLocationOnScreen(this.a.a);
                            int height = this.a.b.b.getHeight() + this.a.a[1];
                            if (this.a.b.g != height) {
                                this.a.b.a(Math.max(this.a.b.f - height, 0), this.a.a[1]);
                                this.a.b.g = height;
                            }
                        }
                    }
                }, 200);
            }
        }
    };

    public interface a {
        void a(boolean z, int i, int i2);
    }

    class b implements OnGlobalLayoutListener {
        final /* synthetic */ d a;
        private final Activity b;

        public b(d dVar, Activity activity) {
            this.a = dVar;
            this.b = activity;
        }

        public void onGlobalLayout() {
            if (VERSION.SDK_INT >= 16) {
                this.a.a.getViewTreeObserver().removeOnGlobalLayoutListener(this.a.d);
            } else {
                this.a.a.getViewTreeObserver().removeGlobalOnLayoutListener(this.a.d);
            }
            this.a.d = null;
            this.a.a(this.b, this.a.a.getWindowToken());
        }
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    private void a(int i, int i2) {
        boolean z = i > 0;
        if (this.e != null) {
            this.e.a(z, i, i2);
        }
    }

    public void a(Activity activity) {
        Activity parent;
        if (activity.getParent() != null) {
            parent = activity.getParent();
        } else {
            parent = activity;
        }
        this.a = parent.findViewById(16908290);
        if (this.a.getWindowToken() != null) {
            a(parent, this.a.getWindowToken());
        } else {
            this.d = new b(this, activity);
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(this.d);
        }
        this.f = activity.getResources().getDisplayMetrics().heightPixels;
        this.g = this.f;
    }

    private void a(Activity activity, IBinder iBinder) {
        b();
        this.c = (WindowManager) activity.getSystemService("window");
        this.b = new View(activity);
        LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 1000, 131096, -3);
        layoutParams.softInputMode = 16;
        layoutParams.token = iBinder;
        this.c.addView(this.b, layoutParams);
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(this.h);
    }

    public void a() {
        if (this.d != null) {
            if (VERSION.SDK_INT >= 16) {
                this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this.d);
            } else {
                this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this.d);
            }
        }
        this.a = null;
        b();
    }

    private void b() {
        if (this.b != null) {
            if (VERSION.SDK_INT >= 16) {
                this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this.d);
            } else {
                this.b.getViewTreeObserver().removeGlobalOnLayoutListener(this.d);
            }
            this.c.removeViewImmediate(this.b);
            this.b = null;
        }
    }
}
