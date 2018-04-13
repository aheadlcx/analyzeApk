package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle.RGBAColor;
import com.squareup.wire.Message.Builder;

public final class ShapeEntity$ShapeStyle$Builder extends Builder<ShapeStyle, ShapeEntity$ShapeStyle$Builder> {
    public RGBAColor fill;
    public ShapeEntity$ShapeStyle$LineCap lineCap;
    public Float lineDashI;
    public Float lineDashII;
    public Float lineDashIII;
    public ShapeEntity$ShapeStyle$LineJoin lineJoin;
    public Float miterLimit;
    public RGBAColor stroke;
    public Float strokeWidth;

    public ShapeEntity$ShapeStyle$Builder fill(RGBAColor rGBAColor) {
        this.fill = rGBAColor;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder stroke(RGBAColor rGBAColor) {
        this.stroke = rGBAColor;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder strokeWidth(Float f) {
        this.strokeWidth = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder lineCap(ShapeEntity$ShapeStyle$LineCap shapeEntity$ShapeStyle$LineCap) {
        this.lineCap = shapeEntity$ShapeStyle$LineCap;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder lineJoin(ShapeEntity$ShapeStyle$LineJoin shapeEntity$ShapeStyle$LineJoin) {
        this.lineJoin = shapeEntity$ShapeStyle$LineJoin;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder miterLimit(Float f) {
        this.miterLimit = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder lineDashI(Float f) {
        this.lineDashI = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder lineDashII(Float f) {
        this.lineDashII = f;
        return this;
    }

    public ShapeEntity$ShapeStyle$Builder lineDashIII(Float f) {
        this.lineDashIII = f;
        return this;
    }

    public ShapeStyle build() {
        return new ShapeStyle(this.fill, this.stroke, this.strokeWidth, this.lineCap, this.lineJoin, this.miterLimit, this.lineDashI, this.lineDashII, this.lineDashIII, super.buildUnknownFields());
    }
}
