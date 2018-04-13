package com.lnyp.recyclerview;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.Adapter;

public class b extends SpanSizeLookup {
    private a a;
    private Adapter b;
    private int c = 1;

    public b(Adapter adapter, int i) {
        this.b = adapter;
        this.c = i;
    }

    public int getSpanSize(int i) {
        int i2;
        if (this.b instanceof a) {
            this.a = (a) this.b;
            i2 = (this.a.a(i) || this.a.b(i)) ? 1 : 0;
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            return this.c;
        }
        return 1;
    }
}
