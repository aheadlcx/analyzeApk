package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.cafe.plugin.QbSignPlugin;

final class w extends LinearLayout {
    private View a;
    private List b = new ArrayList();
    private boolean c = true;
    private /* synthetic */ CatalogActivity d;

    public w(CatalogActivity catalogActivity, Context context) {
        this.d = catalogActivity;
        super(context);
        setOrientation(1);
        setGravity(48);
        this.a = new s(catalogActivity, context);
        this.a.setId(QbSignPlugin.REQUEST_BIND_PHONE);
        this.a.setOnClickListener(catalogActivity);
        a(a(context), this.c);
    }

    final List a(Context context) {
        Long l = null;
        List arrayList = new ArrayList();
        for (int i = 1; i <= 3; i++) {
            arrayList.add("Flurry_Canvas_Hook_" + i);
        }
        v vVar = this.d.f == null ? null : this.d.f.b;
        if (vVar != null) {
            l = Long.valueOf(vVar.a);
        }
        List<y> a = this.d.e.a(context, arrayList, l, 1, true);
        for (y onClickListener : a) {
            onClickListener.setOnClickListener(this.d);
        }
        return a;
    }

    final void a() {
        this.c = !this.c;
        a(null, this.c);
    }

    final void a(List list) {
        a(list, this.c);
    }

    private void a(List list, boolean z) {
        removeAllViews();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        addView(this.a, layoutParams);
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
        }
        if (z) {
            for (y yVar : this.b) {
                addView(yVar, layoutParams);
                yVar.a().a(new f((byte) 3, this.d.e.k()));
            }
        }
        refreshDrawableState();
    }

    final List b() {
        return this.b;
    }
}
