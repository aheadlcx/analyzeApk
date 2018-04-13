package com.opensource.svgaplayer.proto;

import com.squareup.wire.Message.Builder;

public final class Layout$Builder extends Builder<Layout, Layout$Builder> {
    public Float height;
    public Float width;
    public Float x;
    public Float y;

    public Layout$Builder x(Float f) {
        this.x = f;
        return this;
    }

    public Layout$Builder y(Float f) {
        this.y = f;
        return this;
    }

    public Layout$Builder width(Float f) {
        this.width = f;
        return this;
    }

    public Layout$Builder height(Float f) {
        this.height = f;
        return this;
    }

    public Layout build() {
        return new Layout(this.x, this.y, this.width, this.height, super.buildUnknownFields());
    }
}
