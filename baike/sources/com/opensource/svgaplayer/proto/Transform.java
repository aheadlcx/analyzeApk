package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import okio.ByteString;

public final class Transform extends AndroidMessage<Transform, Transform$Builder> {
    public static final ProtoAdapter<Transform> ADAPTER = new Transform$a();
    public static final Creator<Transform> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Float DEFAULT_A = Float.valueOf(0.0f);
    public static final Float DEFAULT_B = Float.valueOf(0.0f);
    public static final Float DEFAULT_C = Float.valueOf(0.0f);
    public static final Float DEFAULT_D = Float.valueOf(0.0f);
    public static final Float DEFAULT_TX = Float.valueOf(0.0f);
    public static final Float DEFAULT_TY = Float.valueOf(0.0f);
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float a;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float b;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
    public final Float c;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
    public final Float d;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 5)
    public final Float tx;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 6)
    public final Float ty;

    public Transform(Float f, Float f2, Float f3, Float f4, Float f5, Float f6) {
        this(f, f2, f3, f4, f5, f6, ByteString.EMPTY);
    }

    public Transform(Float f, Float f2, Float f3, Float f4, Float f5, Float f6, ByteString byteString) {
        super(ADAPTER, byteString);
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.tx = f5;
        this.ty = f6;
    }

    public Transform$Builder newBuilder() {
        Transform$Builder transform$Builder = new Transform$Builder();
        transform$Builder.a = this.a;
        transform$Builder.b = this.b;
        transform$Builder.c = this.c;
        transform$Builder.d = this.d;
        transform$Builder.tx = this.tx;
        transform$Builder.ty = this.ty;
        transform$Builder.addUnknownFields(unknownFields());
        return transform$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Transform)) {
            return false;
        }
        Transform transform = (Transform) obj;
        if (unknownFields().equals(transform.unknownFields()) && Internal.equals(this.a, transform.a) && Internal.equals(this.b, transform.b) && Internal.equals(this.c, transform.c) && Internal.equals(this.d, transform.d) && Internal.equals(this.tx, transform.tx) && Internal.equals(this.ty, transform.ty)) {
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
        int hashCode = ((this.a != null ? this.a.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
        if (this.b != null) {
            i2 = this.b.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.c != null) {
            i2 = this.c.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.d != null) {
            i2 = this.d.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.tx != null) {
            i2 = this.tx.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.ty != null) {
            i = this.ty.hashCode();
        }
        i2 += i;
        this.f = i2;
        return i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a != null) {
            stringBuilder.append(", a=").append(this.a);
        }
        if (this.b != null) {
            stringBuilder.append(", b=").append(this.b);
        }
        if (this.c != null) {
            stringBuilder.append(", c=").append(this.c);
        }
        if (this.d != null) {
            stringBuilder.append(", d=").append(this.d);
        }
        if (this.tx != null) {
            stringBuilder.append(", tx=").append(this.tx);
        }
        if (this.ty != null) {
            stringBuilder.append(", ty=").append(this.ty);
        }
        return stringBuilder.replace(0, 2, "Transform{").append('}').toString();
    }
}
