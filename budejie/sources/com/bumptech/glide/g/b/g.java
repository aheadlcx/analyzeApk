package com.bumptech.glide.g.b;

import com.bumptech.glide.i.h;

public abstract class g<Z> extends a<Z> {
    private final int height;
    private final int width;

    public g() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public g(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public final void getSize(h hVar) {
        if (h.a(this.width, this.height)) {
            hVar.a(this.width, this.height);
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.width + " and height: " + this.height + ", either provide dimensions in the constructor" + " or call override()");
    }
}
