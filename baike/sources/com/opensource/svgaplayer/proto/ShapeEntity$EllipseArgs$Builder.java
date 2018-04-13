package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.EllipseArgs;
import com.squareup.wire.Message.Builder;

public final class ShapeEntity$EllipseArgs$Builder extends Builder<EllipseArgs, ShapeEntity$EllipseArgs$Builder> {
    public Float radiusX;
    public Float radiusY;
    public Float x;
    public Float y;

    public ShapeEntity$EllipseArgs$Builder x(Float f) {
        this.x = f;
        return this;
    }

    public ShapeEntity$EllipseArgs$Builder y(Float f) {
        this.y = f;
        return this;
    }

    public ShapeEntity$EllipseArgs$Builder radiusX(Float f) {
        this.radiusX = f;
        return this;
    }

    public ShapeEntity$EllipseArgs$Builder radiusY(Float f) {
        this.radiusY = f;
        return this;
    }

    public EllipseArgs build() {
        return new EllipseArgs(this.x, this.y, this.radiusX, this.radiusY, super.buildUnknownFields());
    }
}
