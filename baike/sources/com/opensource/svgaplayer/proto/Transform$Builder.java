package com.opensource.svgaplayer.proto;

import com.squareup.wire.Message.Builder;

public final class Transform$Builder extends Builder<Transform, Transform$Builder> {
    public Float a;
    public Float b;
    public Float c;
    public Float d;
    public Float tx;
    public Float ty;

    public Transform$Builder a(Float f) {
        this.a = f;
        return this;
    }

    public Transform$Builder b(Float f) {
        this.b = f;
        return this;
    }

    public Transform$Builder c(Float f) {
        this.c = f;
        return this;
    }

    public Transform$Builder d(Float f) {
        this.d = f;
        return this;
    }

    public Transform$Builder tx(Float f) {
        this.tx = f;
        return this;
    }

    public Transform$Builder ty(Float f) {
        this.ty = f;
        return this;
    }

    public Transform build() {
        return new Transform(this.a, this.b, this.c, this.d, this.tx, this.ty, super.buildUnknownFields());
    }
}
