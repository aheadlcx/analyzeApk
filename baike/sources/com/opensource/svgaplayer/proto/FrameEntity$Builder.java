package com.opensource.svgaplayer.proto;

import com.squareup.wire.Message.Builder;
import com.squareup.wire.internal.Internal;
import java.util.List;

public final class FrameEntity$Builder extends Builder<FrameEntity, FrameEntity$Builder> {
    public Float alpha;
    public String clipPath;
    public Layout layout;
    public List<ShapeEntity> shapes = Internal.newMutableList();
    public Transform transform;

    public FrameEntity$Builder alpha(Float f) {
        this.alpha = f;
        return this;
    }

    public FrameEntity$Builder layout(Layout layout) {
        this.layout = layout;
        return this;
    }

    public FrameEntity$Builder transform(Transform transform) {
        this.transform = transform;
        return this;
    }

    public FrameEntity$Builder clipPath(String str) {
        this.clipPath = str;
        return this;
    }

    public FrameEntity$Builder shapes(List<ShapeEntity> list) {
        Internal.checkElementsNotNull((List) list);
        this.shapes = list;
        return this;
    }

    public FrameEntity build() {
        return new FrameEntity(this.alpha, this.layout, this.transform, this.clipPath, this.shapes, super.buildUnknownFields());
    }
}
