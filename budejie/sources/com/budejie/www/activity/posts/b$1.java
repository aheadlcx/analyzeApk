package com.budejie.www.activity.posts;

import android.text.TextUtils;
import android.widget.LinearLayout;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.util.aa;
import com.budejie.www.widget.curtain.FloatVideoLayout;
import com.budejie.www.widget.xrecyclerview.XRecyclerView$d;

class b$1 implements XRecyclerView$d {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public void a() {
        b.a(this.a, true);
        try {
            if (b.a(this.a).g().d.a().booleanValue()) {
                b.b(this.a).m.c();
                b.c(this.a).clearAnimation();
                b.c(this.a).setVisibility(8);
                b.d(this.a).setVisibility(8);
            }
            if (b.a(this.a).g().f.a().booleanValue()) {
                HomeGroup.w.b();
            }
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("StaggeredListFragment", e.getMessage());
            }
        }
    }

    public void b() {
        b.a(this.a, false);
        try {
            if (b.b(this.a).m.getVisibility() == 8) {
                b.b(this.a).m.b();
            }
            if (HomeGroup.w.getVisibility() == 8) {
                HomeGroup.w.a();
            }
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("StaggeredListFragment", e.getMessage());
            }
        }
    }

    public void c() {
        if (!FloatVideoLayout.a(b.b(this.a)).isShown()) {
            LinearLayout maskLayout = b.b(this.a).f.getMaskLayout();
            if (b.e(this.a) && b.b(this.a).e.a == 0) {
                if (maskLayout != null) {
                    maskLayout.setVisibility(0);
                }
                FloatVideoLayout.a(b.b(this.a), false);
                b.b(this.a).e.b();
            } else if (!b.e(this.a) && b.b(this.a).e.a == 8) {
                if (maskLayout != null) {
                    maskLayout.setVisibility(8);
                }
                FloatVideoLayout.a(b.b(this.a), true);
                b.b(this.a).e.a();
            }
        }
    }
}
