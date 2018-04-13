package com.budejie.www.activity.posts;

import android.widget.LinearLayout;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.widget.XListView.b;
import com.budejie.www.widget.curtain.FloatVideoLayout;

class a$8 implements b {
    final /* synthetic */ a a;

    a$8(a aVar) {
        this.a = aVar;
    }

    public void a() {
        a.a(this.a, true);
        try {
            if (a.a(this.a).g().d.a().booleanValue()) {
                a.b(this.a).m.c();
                a.c(this.a);
                a.d(this.a).setVisibility(8);
            }
            if (a.a(this.a).g().f.a().booleanValue()) {
                HomeGroup.w.b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        a.a(this.a, false);
        try {
            if (a.b(this.a).m.getVisibility() == 8) {
                a.b(this.a).m.b();
            }
            if (HomeGroup.w.getVisibility() == 8) {
                HomeGroup.w.a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
        if (!FloatVideoLayout.a(a.b(this.a)).isShown()) {
            LinearLayout maskLayout = a.b(this.a).f.getMaskLayout();
            if (a.e(this.a) && a.b(this.a).e.a == 0) {
                if (maskLayout != null) {
                    maskLayout.setVisibility(0);
                }
                FloatVideoLayout.a(a.b(this.a), false);
                a.b(this.a).e.b();
            } else if (!a.e(this.a) && a.b(this.a).e.a == 8) {
                if (maskLayout != null) {
                    maskLayout.setVisibility(8);
                }
                FloatVideoLayout.a(a.b(this.a), true);
                a.b(this.a).e.a();
            }
        }
    }
}
