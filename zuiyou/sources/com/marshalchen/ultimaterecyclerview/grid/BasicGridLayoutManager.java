package com.marshalchen.ultimaterecyclerview.grid;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import com.marshalchen.ultimaterecyclerview.e;

public class BasicGridLayoutManager extends GridLayoutManager {
    protected int a = 2;
    protected SpanSizeLookup b = new SpanSizeLookup(this) {
        final /* synthetic */ BasicGridLayoutManager a;

        {
            this.a = r1;
        }

        public int getSpanSize(int i) {
            if (this.a.c.getItemViewType(i) == 2) {
                return this.a.getSpanCount();
            }
            if (this.a.c.getItemViewType(i) == 1) {
                return this.a.getSpanCount();
            }
            return this.a.a(i);
        }
    };
    private final e c;

    protected int a(int i) {
        return 1;
    }

    protected SpanSizeLookup a() {
        return this.b;
    }

    public BasicGridLayoutManager(Context context, int i, e eVar) {
        super(context, i);
        this.c = eVar;
        setSpanSizeLookup(a());
    }

    public BasicGridLayoutManager(Context context, int i, int i2, boolean z, e eVar) {
        super(context, i, i2, z);
        this.c = eVar;
        setSpanSizeLookup(a());
    }
}
