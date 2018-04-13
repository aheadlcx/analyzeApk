package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.WireField.Label;
import com.squareup.wire.internal.Internal;
import java.util.List;
import okio.ByteString;

public final class FrameEntity extends AndroidMessage<FrameEntity, FrameEntity$Builder> {
    public static final ProtoAdapter<FrameEntity> ADAPTER = new FrameEntity$a();
    public static final Creator<FrameEntity> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Float DEFAULT_ALPHA = Float.valueOf(0.0f);
    public static final String DEFAULT_CLIPPATH = "";
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float alpha;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String clipPath;
    @WireField(adapter = "com.opensource.svgaplayer.proto.Layout#ADAPTER", tag = 2)
    public final Layout layout;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity#ADAPTER", label = Label.REPEATED, tag = 5)
    public final List<ShapeEntity> shapes;
    @WireField(adapter = "com.opensource.svgaplayer.proto.Transform#ADAPTER", tag = 3)
    public final Transform transform;

    public FrameEntity(Float f, Layout layout, Transform transform, String str, List<ShapeEntity> list) {
        this(f, layout, transform, str, list, ByteString.EMPTY);
    }

    public FrameEntity(Float f, Layout layout, Transform transform, String str, List<ShapeEntity> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.alpha = f;
        this.layout = layout;
        this.transform = transform;
        this.clipPath = str;
        this.shapes = Internal.immutableCopyOf("shapes", list);
    }

    public FrameEntity$Builder newBuilder() {
        FrameEntity$Builder frameEntity$Builder = new FrameEntity$Builder();
        frameEntity$Builder.alpha = this.alpha;
        frameEntity$Builder.layout = this.layout;
        frameEntity$Builder.transform = this.transform;
        frameEntity$Builder.clipPath = this.clipPath;
        frameEntity$Builder.shapes = Internal.copyOf("shapes", this.shapes);
        frameEntity$Builder.addUnknownFields(unknownFields());
        return frameEntity$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FrameEntity)) {
            return false;
        }
        FrameEntity frameEntity = (FrameEntity) obj;
        if (unknownFields().equals(frameEntity.unknownFields()) && Internal.equals(this.alpha, frameEntity.alpha) && Internal.equals(this.layout, frameEntity.layout) && Internal.equals(this.transform, frameEntity.transform) && Internal.equals(this.clipPath, frameEntity.clipPath) && this.shapes.equals(frameEntity.shapes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int i2 = this.f;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = ((this.alpha != null ? this.alpha.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
        if (this.layout != null) {
            i2 = this.layout.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.transform != null) {
            i2 = this.transform.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.clipPath != null) {
            i = this.clipPath.hashCode();
        }
        i2 = ((i2 + i) * 37) + this.shapes.hashCode();
        this.f = i2;
        return i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.alpha != null) {
            stringBuilder.append(", alpha=").append(this.alpha);
        }
        if (this.layout != null) {
            stringBuilder.append(", layout=").append(this.layout);
        }
        if (this.transform != null) {
            stringBuilder.append(", transform=").append(this.transform);
        }
        if (this.clipPath != null) {
            stringBuilder.append(", clipPath=").append(this.clipPath);
        }
        if (!this.shapes.isEmpty()) {
            stringBuilder.append(", shapes=").append(this.shapes);
        }
        return stringBuilder.replace(0, 2, "FrameEntity{").append('}').toString();
    }
}
