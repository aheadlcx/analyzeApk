package com.volokh.danylo.visibility_utils.a;

import android.view.View;
import com.budejie.www.util.aa;
import com.volokh.danylo.visibility_utils.b.b;
import com.volokh.danylo.visibility_utils.scroll_utils.ScrollDirectionDetector.ScrollDirection;
import java.util.List;

public class d extends a {
    private static final String a = d.class.getSimpleName();
    private final a<com.volokh.danylo.visibility_utils.b.a> b;
    private final List<? extends com.volokh.danylo.visibility_utils.b.a> c;
    private ScrollDirection d = ScrollDirection.UP;
    private final b e = new b();

    public interface a<T extends com.volokh.danylo.visibility_utils.b.a> {
        void a(T t, View view, int i);

        void a(T t, View view, int i, boolean z, boolean z2);
    }

    public d(a<com.volokh.danylo.visibility_utils.b.a> aVar, List<? extends com.volokh.danylo.visibility_utils.b.a> list) {
        this.b = aVar;
        this.c = list;
    }

    protected void b(com.volokh.danylo.visibility_utils.scroll_utils.a aVar) {
        a(aVar, this.e);
    }

    private void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, b bVar, b bVar2) {
        int a = bVar.a() + 1;
        if (a < this.c.size()) {
            int a2 = aVar.a(bVar.b());
            if (a2 >= 0) {
                View a3 = aVar.a(a2 + 1);
                if (a3 != null) {
                    ((com.volokh.danylo.visibility_utils.b.a) this.c.get(a)).getVisibilityPercents(a3);
                    bVar2.a(a, a3);
                }
            }
        }
    }

    private void b(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, b bVar, b bVar2) {
        int a = bVar.a() - 1;
        if (a >= 0) {
            int a2 = aVar.a(bVar.b());
            if (a2 > 0) {
                View a3 = aVar.a(a2 - 1);
                ((com.volokh.danylo.visibility_utils.b.a) this.c.get(a)).getVisibilityPercents(a3);
                bVar2.a(a, a3);
            }
        }
    }

    public void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, int i, int i2) {
        b(aVar, i, i2);
    }

    private void b(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, int i, int i2) {
        b c = c(aVar, i, i2);
        int a = c.a(this.c);
        switch (this.d) {
            case UP:
                b(aVar, a, c);
                break;
            case DOWN:
                a(aVar, a, c);
                break;
            default:
                throw new RuntimeException("not handled mScrollDirection " + this.d);
        }
        if (c.d()) {
            a(aVar);
            a(c, false);
            return;
        }
        a(c, false);
    }

    private void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, int i, b bVar) {
        aa.b("AutoPlayVideoListActivity", "topToBottomMostVisibleItem firstVisibleItem=" + aVar.c() + ",visibleItemCount=" + aVar.a());
        int c = aVar.c();
        int a = aVar.a(bVar.b());
        while (c <= aVar.b()) {
            com.volokh.danylo.visibility_utils.b.a aVar2 = (com.volokh.danylo.visibility_utils.b.a) this.c.get(c);
            View a2 = aVar.a(a);
            if (aVar2.getIsPlayArea(a2)) {
                bVar.a(c, a2);
            }
            c++;
            a++;
        }
        bVar.a(this.e.b() != bVar.b());
    }

    private void b(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, int i, b bVar) {
        aa.b("AutoPlayVideoListActivity", "bottomToTopMostVisibleItem firstVisibleItem=" + aVar.c() + ",visibleItemCount=" + aVar.a());
        int b = aVar.b();
        for (int a = aVar.a(bVar.b()); a >= 0; a--) {
            com.volokh.danylo.visibility_utils.b.a aVar2 = (com.volokh.danylo.visibility_utils.b.a) this.c.get(b);
            View a2 = aVar.a(a);
            if (aVar2.getIsPlayArea(a2)) {
                bVar.a(b, a2);
            }
            b--;
        }
        bVar.a(this.e.b() != bVar.b());
    }

    private b c(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, int i, int i2) {
        switch (this.d) {
            case UP:
                if (i2 >= 0) {
                    i = i2;
                }
                return new b().a(i, aVar.a(aVar.a() - 1));
            case DOWN:
                return new b().a(i, aVar.a(0));
            default:
                throw new RuntimeException("not handled mScrollDirection " + this.d);
        }
    }

    private void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar, b bVar) {
        bVar.a(this.c);
        boolean b = bVar.b(this.c);
        b bVar2 = new b();
        switch (this.d) {
            case UP:
                b(aVar, bVar, bVar2);
                break;
            case DOWN:
                a(aVar, bVar, bVar2);
                break;
        }
        if (!b && bVar2.c()) {
            a(aVar);
            a(bVar2, true);
        }
    }

    protected void a(com.volokh.danylo.visibility_utils.scroll_utils.a aVar) {
        b bVar = this.e;
        this.b.a((com.volokh.danylo.visibility_utils.b.a) this.c.get(bVar.a()), bVar.b(), bVar.a());
    }

    public void a(ScrollDirection scrollDirection) {
        this.d = scrollDirection;
    }

    private void a(b bVar, boolean z) {
        int a = bVar.a();
        View b = bVar.b();
        boolean d = bVar.d();
        this.e.a(a, b);
        this.b.a((com.volokh.danylo.visibility_utils.b.a) this.c.get(a), b, a, z, d);
    }
}
