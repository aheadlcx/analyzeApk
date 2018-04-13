package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.WireField.Label;
import com.squareup.wire.internal.Internal;
import java.util.List;
import okio.ByteString;

public final class SpriteEntity extends AndroidMessage<SpriteEntity, SpriteEntity$Builder> {
    public static final ProtoAdapter<SpriteEntity> ADAPTER = new SpriteEntity$a();
    public static final Creator<SpriteEntity> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_IMAGEKEY = "";
    @WireField(adapter = "com.opensource.svgaplayer.proto.FrameEntity#ADAPTER", label = Label.REPEATED, tag = 2)
    public final List<FrameEntity> frames;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String imageKey;

    public SpriteEntity(String str, List<FrameEntity> list) {
        this(str, list, ByteString.EMPTY);
    }

    public SpriteEntity(String str, List<FrameEntity> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.imageKey = str;
        this.frames = Internal.immutableCopyOf("frames", list);
    }

    public SpriteEntity$Builder newBuilder() {
        SpriteEntity$Builder spriteEntity$Builder = new SpriteEntity$Builder();
        spriteEntity$Builder.imageKey = this.imageKey;
        spriteEntity$Builder.frames = Internal.copyOf("frames", this.frames);
        spriteEntity$Builder.addUnknownFields(unknownFields());
        return spriteEntity$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpriteEntity)) {
            return false;
        }
        SpriteEntity spriteEntity = (SpriteEntity) obj;
        if (unknownFields().equals(spriteEntity.unknownFields()) && Internal.equals(this.imageKey, spriteEntity.imageKey) && this.frames.equals(spriteEntity.frames)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.f;
        if (i != 0) {
            return i;
        }
        i = (((this.imageKey != null ? this.imageKey.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37) + this.frames.hashCode();
        this.f = i;
        return i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.imageKey != null) {
            stringBuilder.append(", imageKey=").append(this.imageKey);
        }
        if (!this.frames.isEmpty()) {
            stringBuilder.append(", frames=").append(this.frames);
        }
        return stringBuilder.replace(0, 2, "SpriteEntity{").append('}').toString();
    }
}
