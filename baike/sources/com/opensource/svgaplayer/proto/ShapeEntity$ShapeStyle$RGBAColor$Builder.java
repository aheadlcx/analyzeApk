package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle.RGBAColor;
import com.squareup.wire.Message.Builder;

public final class ShapeEntity$ShapeStyle$RGBAColor$Builder extends Builder<RGBAColor, ShapeEntity$ShapeStyle$RGBAColor$Builder> {
    public Float a;
    public Float b;
    public Float g;
    public Float r;

    public ShapeEntity$ShapeStyle$RGBAColor$Builder r(Float f) {
        this.r = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$RGBAColor$Builder g(Float f) {
        this.g = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$RGBAColor$Builder b(Float f) {
        this.b = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$RGBAColor$Builder a(Float f) {
        this.a = f;
        return this;
    }

    public RGBAColor build() {
        return new RGBAColor(this.r, this.g, this.b, this.a, super.buildUnknownFields());
    }
}
