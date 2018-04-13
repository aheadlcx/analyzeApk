package com.opensource.svgaplayer.proto;

import com.opensource.svgaplayer.proto.ShapeEntity.EllipseArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.RectArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle;
import com.squareup.wire.Message.Builder;

public final class ShapeEntity$Builder extends Builder<ShapeEntity, ShapeEntity$Builder> {
    public EllipseArgs ellipse;
    public RectArgs rect;
    public ShapeArgs shape;
    public ShapeStyle styles;
    public Transform transform;
    public ShapeEntity$ShapeType type;

    public ShapeEntity$Builder type(ShapeEntity$ShapeType shapeEntity$ShapeType) {
        this.type = shapeEntity$ShapeType;
        return this;
    }

    public ShapeEntity$Builder styles(ShapeStyle shapeStyle) {
        this.styles = shapeStyle;
        return this;
    }

    public ShapeEntity$Builder transform(Transform transform) {
        this.transform = transform;
        return this;
    }

    public ShapeEntity$Builder shape(ShapeArgs shapeArgs) {
        this.shape = shapeArgs;
        this.rect = null;
        this.ellipse = null;
        return this;
    }

    public ShapeEntity$Builder rect(RectArgs rectArgs) {
        this.rect = rectArgs;
        this.shape = null;
        this.ellipse = null;
        return this;
    }

    public ShapeEntity$Builder ellipse(EllipseArgs ellipseArgs) {
        this.ellipse = ellipseArgs;
        this.shape = null;
        this.rect = null;
        return this;
    }

    public ShapeEntity build() {
        return new ShapeEntity(this.type, this.styles, this.transform, this.shape, this.rect, this.ellipse, super.buildUnknownFields());
    }
}
