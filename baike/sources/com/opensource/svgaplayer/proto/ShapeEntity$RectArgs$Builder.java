package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.RectArgs;
import com.squareup.wire.Message.Builder;

public final class ShapeEntity$RectArgs$Builder extends Builder<RectArgs, ShapeEntity$RectArgs$Builder> {
    public Float cornerRadius;
    public Float height;
    public Float width;
    public Float x;
    public Float y;

    public ShapeEntity$RectArgs$Builder x(Float f) {
        this.x = f;
        return this;
    }

    public ShapeEntity$RectArgs$Builder y(Float f) {
        this.y = f;
        return this;
    }

    public ShapeEntity$RectArgs$Builder width(Float f) {
        this.width = f;
        return this;
    }

    public ShapeEntity$RectArgs$Builder height(Float f) {
        this.height = f;
        return this;
    }

    public ShapeEntity$RectArgs$Builder cornerRadius(Float f) {
        this.cornerRadius = f;
        return this;
    }

    public RectArgs build() {
        return new RectArgs(this.x, this.y, this.width, this.height, this.cornerRadius, super.buildUnknownFields());
    }
}
