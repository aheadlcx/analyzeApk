package com.opensource.svgaplayer.proto;

import com.squareup.wire.Message.Builder;
import com.squareup.wire.internal.Internal;
import java.util.List;

public final class SpriteEntity$Builder extends Builder<SpriteEntity, SpriteEntity$Builder> {
    public List<FrameEntity> frames = Internal.newMutableList();
    public String imageKey;

    public SpriteEntity$Builder imageKey(String str) {
        this.imageKey = str;
        return this;
    }

    public SpriteEntity$Builder frames(List<FrameEntity> list) {
        Internal.checkElementsNotNull((List) list);
        this.frames = list;
        return this;
    }

    public SpriteEntity build() {
        return new SpriteEntity(this.imageKey, this.frames, super.buildUnknownFields());
    }
}
