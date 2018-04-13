package com.google.android.flexbox;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class c {
    int a = Integer.MAX_VALUE;
    int b = Integer.MAX_VALUE;
    int c = Integer.MIN_VALUE;
    int d = Integer.MIN_VALUE;
    int e;
    int f;
    int g;
    int h;
    int i;
    float j;
    float k;
    int l;
    int m;
    List<Integer> n = new ArrayList();
    int o;
    int p;

    c() {
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public int c() {
        return this.h - this.i;
    }

    void a(View view, int i, int i2, int i3, int i4) {
        b bVar = (b) view.getLayoutParams();
        this.a = Math.min(this.a, (view.getLeft() - bVar.m()) - i);
        this.b = Math.min(this.b, (view.getTop() - bVar.n()) - i2);
        this.c = Math.max(this.c, (view.getRight() + bVar.o()) + i3);
        this.d = Math.max(this.d, (bVar.p() + view.getBottom()) + i4);
    }
}
