package com.volokh.danylo.visibility_utils.b;

import android.app.LauncherActivity.ListItem;
import android.view.View;
import java.util.List;

public class b {
    private static final String a = ListItem.class.getSimpleName();
    private Integer b;
    private View c;
    private boolean d;

    public int a() {
        return this.b == null ? 0 : this.b.intValue();
    }

    public View b() {
        return this.c;
    }

    public b a(int i, View view) {
        this.b = Integer.valueOf(i);
        this.c = view;
        return this;
    }

    public boolean c() {
        return (this.b == null || this.c == null) ? false : true;
    }

    public int a(List<? extends a> list) {
        return ((a) list.get(a())).getVisibilityPercents(b());
    }

    public boolean b(List<? extends a> list) {
        return ((a) list.get(a())).getIsPlayArea(b());
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean d() {
        return this.d;
    }

    public String toString() {
        return "ListItemData{mIndexInAdapter=" + this.b + ", mView=" + this.c + ", mIsMostVisibleItemChanged=" + this.d + '}';
    }
}
