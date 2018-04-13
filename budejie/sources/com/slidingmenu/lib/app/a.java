package com.slidingmenu.lib.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import cn.v6.sixrooms.R;
import com.slidingmenu.lib.SlidingMenu;

public class a {
    private Activity a;
    private SlidingMenu b;
    private View c;
    private View d;
    private boolean e = false;
    private boolean f = false;
    private boolean g = true;

    public a(Activity activity) {
        this.a = activity;
    }

    public void a(Bundle bundle) {
        this.b = (SlidingMenu) LayoutInflater.from(this.a).inflate(R.layout.slidingmenumain, null);
    }

    public void b(Bundle bundle) {
        int i = 1;
        boolean z = false;
        if (this.d == null || this.c == null) {
            throw new IllegalStateException("Both setBehindContentView must be called in onCreate in addition to setContentView.");
        }
        boolean z2;
        this.f = true;
        SlidingMenu slidingMenu = this.b;
        Activity activity = this.a;
        if (this.g) {
            i = 0;
        }
        slidingMenu.a(activity, i);
        if (bundle != null) {
            z = bundle.getBoolean("SlidingActivityHelper.open");
            z2 = bundle.getBoolean("SlidingActivityHelper.secondary");
        } else {
            z2 = false;
        }
        new Handler().post(new b(this, z, z2));
    }

    public void a(boolean z) {
        if (this.f) {
            throw new IllegalStateException("enableSlidingActionBar must be called in onCreate.");
        }
        this.g = z;
    }

    public View a(int i) {
        if (this.b != null) {
            View findViewById = this.b.findViewById(i);
            if (findViewById != null) {
                return findViewById;
            }
        }
        return null;
    }

    public void c(Bundle bundle) {
        bundle.putBoolean("SlidingActivityHelper.open", this.b.e());
        bundle.putBoolean("SlidingActivityHelper.secondary", this.b.f());
    }

    public void a(View view, LayoutParams layoutParams) {
        if (!this.e) {
            this.c = view;
        }
    }

    public void b(View view, LayoutParams layoutParams) {
        this.d = view;
        this.b.setMenu(this.d);
    }

    public SlidingMenu a() {
        return this.b;
    }

    public void b() {
        this.b.d();
    }

    public void c() {
        this.b.c();
    }

    public void d() {
        this.b.a();
    }

    public void e() {
        this.b.b();
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.b.e()) {
            return false;
        }
        c();
        return true;
    }
}
