package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import java.util.Timer;
import java.util.TimerTask;

public class e {
    private static Timer l;
    final Context a;
    int b = 81;
    int c;
    int d;
    float e;
    float f;
    View g;
    View h;
    private final LayoutParams i = new LayoutParams();
    private final WindowManager j;
    private final Handler k = new Handler();
    private a m;
    private final Runnable n = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b();
        }
    };
    private final Runnable o = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c();
        }
    };

    class a extends TimerTask {
        final /* synthetic */ e a;

        a(e eVar) {
            this.a = eVar;
        }

        public void run() {
            this.a.c();
        }
    }

    public e(Context context) {
        this.a = context;
        this.j = (WindowManager) context.getSystemService("window");
        this.d = context.getResources().getDimensionPixelSize(R.dimen.hint_y_offset);
        this.i.height = -2;
        this.i.width = -2;
        this.i.flags = 24;
        this.i.format = -3;
        this.i.windowAnimations = R.style.Animation.OnScreenHint;
        this.i.type = 1000;
        this.i.setTitle("OnScreenHint");
    }

    public void a(int i) {
        if (this.h == null) {
            throw new RuntimeException("setView must have been called");
        }
        this.k.post(this.n);
        b(i);
    }

    public void a() {
        this.k.post(this.o);
    }

    public static e a(Context context, CharSequence charSequence) {
        e eVar = new e(context);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.anytimetoast, null);
        ((TextView) inflate.findViewById(R.id.message)).setText(charSequence);
        l = new Timer(true);
        eVar.h = inflate;
        return eVar;
    }

    public void b(int i) {
        if (l != null) {
            if (this.m != null) {
                this.m.cancel();
            }
            this.m = new a(this);
            l.schedule(this.m, (long) (i * 1000));
        }
    }

    private synchronized void b() {
        if (this.g != this.h) {
            c();
            this.g = this.h;
            int i = this.b;
            this.i.gravity = i;
            if ((i & 7) == 7) {
                this.i.horizontalWeight = 1.0f;
            }
            if ((i & 112) == 112) {
                this.i.verticalWeight = 1.0f;
            }
            this.i.x = this.c;
            this.i.y = this.d;
            this.i.verticalMargin = this.f;
            this.i.horizontalMargin = this.e;
            if (this.g.getParent() != null) {
                this.j.removeView(this.g);
            }
            if (!((this.a instanceof Activity) && an.g((Activity) this.a))) {
                this.j.addView(this.g, this.i);
            }
        }
    }

    private synchronized void c() {
        if (this.g != null) {
            if (this.g.getParent() != null) {
                try {
                    this.j.removeView(this.g);
                } catch (Exception e) {
                }
            }
            this.g = null;
        }
    }
}
