package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.ShapeArgs;
import com.squareup.wire.Message.Builder;

public final class ShapeEntity$ShapeArgs$Builder extends Builder<ShapeArgs, ShapeEntity$ShapeArgs$Builder> {
    public String d;

    public ShapeEntity$ShapeArgs$Builder d(String str) {
        this.d = str;
        return this;
    }

    public ShapeArgs build() {
        return new ShapeArgs(this.d, super.buildUnknownFields());
    }
}
