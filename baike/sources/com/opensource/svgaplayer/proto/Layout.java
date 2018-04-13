package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import okio.ByteString;

public final class Layout extends AndroidMessage<Layout, Layout$Builder> {
    public static final ProtoAdapter<Layout> ADAPTER = new Layout$a();
    public static final Creator<Layout> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Float DEFAULT_HEIGHT = Float.valueOf(0.0f);
    public static final Float DEFAULT_WIDTH = Float.valueOf(0.0f);
    public static final Float DEFAULT_X = Float.valueOf(0.0f);
    public static final Float DEFAULT_Y = Float.valueOf(0.0f);
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
    public final Float height;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
    public final Float width;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float x;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float y;

    public Layout(Float f, Float f2, Float f3, Float f4) {
        this(f, f2, f3, f4, ByteString.EMPTY);
    }

    public Layout(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
    }

    public Layout$Builder newBuilder() {
        Layout$Builder layout$Builder = new Layout$Builder();
        layout$Builder.x = this.x;
        layout$Builder.y = this.y;
        layout$Builder.width = this.width;
        layout$Builder.height = this.height;
        layout$Builder.addUnknownFields(unknownFields());
        return layout$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Layout)) {
            return false;
        }
        Layout layout = (Layout) obj;
        if (unknownFields().equals(layout.unknownFields()) && Internal.equals(this.x, layout.x) && Internal.equals(this.y, layout.y) && Internal.equals(this.width, layout.width) && Internal.equals(this.height, layout.height)) {
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
        int hashCode = ((this.x != null ? this.x.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
        if (this.y != null) {
            i2 = this.y.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.width != null) {
            i2 = this.width.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.height != null) {
            i = this.height.hashCode();
        }
        i2 += i;
        this.f = i2;
        return i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.x != null) {
            stringBuilder.append(", x=").append(this.x);
        }
        if (this.y != null) {
            stringBuilder.append(", y=").append(this.y);
        }
        if (this.width != null) {
            stringBuilder.append(", width=").append(this.width);
        }
        if (this.height != null) {
            stringBuilder.append(", height=").append(this.height);
        }
        return stringBuilder.replace(0, 2, "Layout{").append('}').toString();
    }
}
