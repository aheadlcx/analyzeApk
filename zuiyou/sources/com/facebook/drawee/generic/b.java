package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.g;
import com.facebook.drawee.drawable.n$b;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

public class b {
    public static final n$b a = n$b.f;
    public static final n$b b = n$b.g;
    private Resources c;
    private int d;
    private float e;
    private Drawable f;
    @Nullable
    private n$b g;
    private Drawable h;
    private n$b i;
    private Drawable j;
    private n$b k;
    private Drawable l;
    private n$b m;
    private n$b n;
    private Matrix o;
    private PointF p;
    private ColorFilter q;
    private Drawable r;
    private List<Drawable> s;
    private Drawable t;
    private RoundingParams u;

    public b(Resources resources) {
        this.c = resources;
        u();
    }

    public static b a(Resources resources) {
        return new b(resources);
    }

    private void u() {
        this.d = 300;
        this.e = 0.0f;
        this.f = null;
        this.g = a;
        this.h = null;
        this.i = a;
        this.j = null;
        this.k = a;
        this.l = null;
        this.m = a;
        this.n = b;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
    }

    public Resources a() {
        return this.c;
    }

    public b a(int i) {
        this.d = i;
        return this;
    }

    public int b() {
        return this.d;
    }

    public b a(float f) {
        this.e = f;
        return this;
    }

    public float c() {
        return this.e;
    }

    public b a(@Nullable Drawable drawable) {
        this.f = drawable;
        return this;
    }

    public b b(int i) {
        this.f = this.c.getDrawable(i);
        return this;
    }

    @Nullable
    public Drawable d() {
        return this.f;
    }

    public b a(@Nullable n$b n_b) {
        this.g = n_b;
        return this;
    }

    @Nullable
    public n$b e() {
        return this.g;
    }

    public b a(Drawable drawable, @Nullable n$b n_b) {
        this.f = drawable;
        this.g = n_b;
        return this;
    }

    public b b(@Nullable Drawable drawable) {
        this.h = drawable;
        return this;
    }

    @Nullable
    public Drawable f() {
        return this.h;
    }

    public b b(@Nullable n$b n_b) {
        this.i = n_b;
        return this;
    }

    @Nullable
    public n$b g() {
        return this.i;
    }

    public b c(@Nullable Drawable drawable) {
        this.j = drawable;
        return this;
    }

    @Nullable
    public Drawable h() {
        return this.j;
    }

    public b c(@Nullable n$b n_b) {
        this.k = n_b;
        return this;
    }

    @Nullable
    public n$b i() {
        return this.k;
    }

    public b b(Drawable drawable, @Nullable n$b n_b) {
        this.j = drawable;
        this.k = n_b;
        return this;
    }

    public b d(@Nullable Drawable drawable) {
        this.l = drawable;
        return this;
    }

    @Nullable
    public Drawable j() {
        return this.l;
    }

    public b d(@Nullable n$b n_b) {
        this.m = n_b;
        return this;
    }

    @Nullable
    public n$b k() {
        return this.m;
    }

    public b c(Drawable drawable, @Nullable n$b n_b) {
        this.l = drawable;
        this.m = n_b;
        return this;
    }

    public b e(@Nullable n$b n_b) {
        this.n = n_b;
        this.o = null;
        return this;
    }

    @Nullable
    public n$b l() {
        return this.n;
    }

    @Nullable
    public Matrix m() {
        return this.o;
    }

    public b a(@Nullable PointF pointF) {
        this.p = pointF;
        return this;
    }

    @Nullable
    public PointF n() {
        return this.p;
    }

    @Nullable
    public ColorFilter o() {
        return this.q;
    }

    public b e(@Nullable Drawable drawable) {
        this.r = drawable;
        return this;
    }

    @Nullable
    public Drawable p() {
        return this.r;
    }

    public b f(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.s = null;
        } else {
            this.s = Arrays.asList(new Drawable[]{drawable});
        }
        return this;
    }

    @Nullable
    public List<Drawable> q() {
        return this.s;
    }

    public b g(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.t = null;
        } else {
            Drawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, drawable);
            this.t = stateListDrawable;
        }
        return this;
    }

    @Nullable
    public Drawable r() {
        return this.t;
    }

    public b a(@Nullable RoundingParams roundingParams) {
        this.u = roundingParams;
        return this;
    }

    @Nullable
    public RoundingParams s() {
        return this.u;
    }

    private void v() {
        if (this.s != null) {
            for (Drawable a : this.s) {
                g.a(a);
            }
        }
    }

    public a t() {
        v();
        return new a(this);
    }
}
