package com.opensource.svgaplayer.proto;

import android.os.Parcelable.Creator;
import com.squareup.wire.AndroidMessage;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import okio.ByteString;

public final class ShapeEntity extends AndroidMessage<ShapeEntity, ShapeEntity$Builder> {
    public static final ProtoAdapter<ShapeEntity> ADAPTER = new ShapeEntity$a();
    public static final Creator<ShapeEntity> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final ShapeEntity$ShapeType DEFAULT_TYPE = ShapeEntity$ShapeType.SHAPE;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$EllipseArgs#ADAPTER", tag = 4)
    public final EllipseArgs ellipse;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$RectArgs#ADAPTER", tag = 3)
    public final RectArgs rect;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeArgs#ADAPTER", tag = 2)
    public final ShapeArgs shape;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle#ADAPTER", tag = 10)
    public final ShapeStyle styles;
    @WireField(adapter = "com.opensource.svgaplayer.proto.Transform#ADAPTER", tag = 11)
    public final Transform transform;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeType#ADAPTER", tag = 1)
    public final ShapeEntity$ShapeType type;

    public static final class EllipseArgs extends AndroidMessage<EllipseArgs, ShapeEntity$EllipseArgs$Builder> {
        public static final ProtoAdapter<EllipseArgs> ADAPTER = new ShapeEntity$EllipseArgs$a();
        public static final Creator<EllipseArgs> CREATOR = AndroidMessage.newCreator(ADAPTER);
        public static final Float DEFAULT_RADIUSX = Float.valueOf(0.0f);
        public static final Float DEFAULT_RADIUSY = Float.valueOf(0.0f);
        public static final Float DEFAULT_X = Float.valueOf(0.0f);
        public static final Float DEFAULT_Y = Float.valueOf(0.0f);
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
        public final Float radiusX;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
        public final Float radiusY;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
        public final Float x;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
        public final Float y;

        public EllipseArgs(Float f, Float f2, Float f3, Float f4) {
            this(f, f2, f3, f4, ByteString.EMPTY);
        }

        public EllipseArgs(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
            super(ADAPTER, byteString);
            this.x = f;
            this.y = f2;
            this.radiusX = f3;
            this.radiusY = f4;
        }

        public ShapeEntity$EllipseArgs$Builder newBuilder() {
            ShapeEntity$EllipseArgs$Builder shapeEntity$EllipseArgs$Builder = new ShapeEntity$EllipseArgs$Builder();
            shapeEntity$EllipseArgs$Builder.x = this.x;
            shapeEntity$EllipseArgs$Builder.y = this.y;
            shapeEntity$EllipseArgs$Builder.radiusX = this.radiusX;
            shapeEntity$EllipseArgs$Builder.radiusY = this.radiusY;
            shapeEntity$EllipseArgs$Builder.addUnknownFields(unknownFields());
            return shapeEntity$EllipseArgs$Builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EllipseArgs)) {
                return false;
            }
            EllipseArgs ellipseArgs = (EllipseArgs) obj;
            if (unknownFields().equals(ellipseArgs.unknownFields()) && Internal.equals(this.x, ellipseArgs.x) && Internal.equals(this.y, ellipseArgs.y) && Internal.equals(this.radiusX, ellipseArgs.radiusX) && Internal.equals(this.radiusY, ellipseArgs.radiusY)) {
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
            if (this.radiusX != null) {
                i2 = this.radiusX.hashCode();
            } else {
                i2 = 0;
            }
            i2 = (i2 + hashCode) * 37;
            if (this.radiusY != null) {
                i = this.radiusY.hashCode();
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
            if (this.radiusX != null) {
                stringBuilder.append(", radiusX=").append(this.radiusX);
            }
            if (this.radiusY != null) {
                stringBuilder.append(", radiusY=").append(this.radiusY);
            }
            return stringBuilder.replace(0, 2, "EllipseArgs{").append('}').toString();
        }
    }

    public static final class RectArgs extends AndroidMessage<RectArgs, ShapeEntity$RectArgs$Builder> {
        public static final ProtoAdapter<RectArgs> ADAPTER = new ShapeEntity$RectArgs$a();
        public static final Creator<RectArgs> CREATOR = AndroidMessage.newCreator(ADAPTER);
        public static final Float DEFAULT_CORNERRADIUS = Float.valueOf(0.0f);
        public static final Float DEFAULT_HEIGHT = Float.valueOf(0.0f);
        public static final Float DEFAULT_WIDTH = Float.valueOf(0.0f);
        public static final Float DEFAULT_X = Float.valueOf(0.0f);
        public static final Float DEFAULT_Y = Float.valueOf(0.0f);
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 5)
        public final Float cornerRadius;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
        public final Float height;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
        public final Float width;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
        public final Float x;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
        public final Float y;

        public RectArgs(Float f, Float f2, Float f3, Float f4, Float f5) {
            this(f, f2, f3, f4, f5, ByteString.EMPTY);
        }

        public RectArgs(Float f, Float f2, Float f3, Float f4, Float f5, ByteString byteString) {
            super(ADAPTER, byteString);
            this.x = f;
            this.y = f2;
            this.width = f3;
            this.height = f4;
            this.cornerRadius = f5;
        }

        public ShapeEntity$RectArgs$Builder newBuilder() {
            ShapeEntity$RectArgs$Builder shapeEntity$RectArgs$Builder = new ShapeEntity$RectArgs$Builder();
            shapeEntity$RectArgs$Builder.x = this.x;
            shapeEntity$RectArgs$Builder.y = this.y;
            shapeEntity$RectArgs$Builder.width = this.width;
            shapeEntity$RectArgs$Builder.height = this.height;
            shapeEntity$RectArgs$Builder.cornerRadius = this.cornerRadius;
            shapeEntity$RectArgs$Builder.addUnknownFields(unknownFields());
            return shapeEntity$RectArgs$Builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RectArgs)) {
                return false;
            }
            RectArgs rectArgs = (RectArgs) obj;
            if (unknownFields().equals(rectArgs.unknownFields()) && Internal.equals(this.x, rectArgs.x) && Internal.equals(this.y, rectArgs.y) && Internal.equals(this.width, rectArgs.width) && Internal.equals(this.height, rectArgs.height) && Internal.equals(this.cornerRadius, rectArgs.cornerRadius)) {
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
            hashCode = (i2 + hashCode) * 37;
            if (this.height != null) {
                i2 = this.height.hashCode();
            } else {
                i2 = 0;
            }
            i2 = (i2 + hashCode) * 37;
            if (this.cornerRadius != null) {
                i = this.cornerRadius.hashCode();
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
            if (this.cornerRadius != null) {
                stringBuilder.append(", cornerRadius=").append(this.cornerRadius);
            }
            return stringBuilder.replace(0, 2, "RectArgs{").append('}').toString();
        }
    }

    public static final class ShapeArgs extends AndroidMessage<ShapeArgs, ShapeEntity$ShapeArgs$Builder> {
        public static final ProtoAdapter<ShapeArgs> ADAPTER = new ShapeEntity$ShapeArgs$a();
        public static final Creator<ShapeArgs> CREATOR = AndroidMessage.newCreator(ADAPTER);
        public static final String DEFAULT_D = "";
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
        public final String d;

        public ShapeArgs(String str) {
            this(str, ByteString.EMPTY);
        }

        public ShapeArgs(String str, ByteString byteString) {
            super(ADAPTER, byteString);
            this.d = str;
        }

        public ShapeEntity$ShapeArgs$Builder newBuilder() {
            ShapeEntity$ShapeArgs$Builder shapeEntity$ShapeArgs$Builder = new ShapeEntity$ShapeArgs$Builder();
            shapeEntity$ShapeArgs$Builder.d = this.d;
            shapeEntity$ShapeArgs$Builder.addUnknownFields(unknownFields());
            return shapeEntity$ShapeArgs$Builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ShapeArgs)) {
                return false;
            }
            ShapeArgs shapeArgs = (ShapeArgs) obj;
            if (unknownFields().equals(shapeArgs.unknownFields()) && Internal.equals(this.d, shapeArgs.d)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = this.f;
            if (i != 0) {
                return i;
            }
            i = (this.d != null ? this.d.hashCode() : 0) + (unknownFields().hashCode() * 37);
            this.f = i;
            return i;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.d != null) {
                stringBuilder.append(", d=").append(this.d);
            }
            return stringBuilder.replace(0, 2, "ShapeArgs{").append('}').toString();
        }
    }

    public static final class ShapeStyle extends AndroidMessage<ShapeStyle, ShapeEntity$ShapeStyle$Builder> {
        public static final ProtoAdapter<ShapeStyle> ADAPTER = new ShapeEntity$ShapeStyle$a();
        public static final Creator<ShapeStyle> CREATOR = AndroidMessage.newCreator(ADAPTER);
        public static final ShapeEntity$ShapeStyle$LineCap DEFAULT_LINECAP = ShapeEntity$ShapeStyle$LineCap.LineCap_BUTT;
        public static final Float DEFAULT_LINEDASHI = Float.valueOf(0.0f);
        public static final Float DEFAULT_LINEDASHII = Float.valueOf(0.0f);
        public static final Float DEFAULT_LINEDASHIII = Float.valueOf(0.0f);
        public static final ShapeEntity$ShapeStyle$LineJoin DEFAULT_LINEJOIN = ShapeEntity$ShapeStyle$LineJoin.LineJoin_MITER;
        public static final Float DEFAULT_MITERLIMIT = Float.valueOf(0.0f);
        public static final Float DEFAULT_STROKEWIDTH = Float.valueOf(0.0f);
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$RGBAColor#ADAPTER", tag = 1)
        public final RGBAColor fill;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$LineCap#ADAPTER", tag = 4)
        public final ShapeEntity$ShapeStyle$LineCap lineCap;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 7)
        public final Float lineDashI;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 8)
        public final Float lineDashII;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 9)
        public final Float lineDashIII;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$LineJoin#ADAPTER", tag = 5)
        public final ShapeEntity$ShapeStyle$LineJoin lineJoin;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 6)
        public final Float miterLimit;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$RGBAColor#ADAPTER", tag = 2)
        public final RGBAColor stroke;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
        public final Float strokeWidth;

        public static final class RGBAColor extends AndroidMessage<RGBAColor, ShapeEntity$ShapeStyle$RGBAColor$Builder> {
            public static final ProtoAdapter<RGBAColor> ADAPTER = new ShapeEntity$ShapeStyle$RGBAColor$a();
            public static final Creator<RGBAColor> CREATOR = AndroidMessage.newCreator(ADAPTER);
            public static final Float DEFAULT_A = Float.valueOf(0.0f);
            public static final Float DEFAULT_B = Float.valueOf(0.0f);
            public static final Float DEFAULT_G = Float.valueOf(0.0f);
            public static final Float DEFAULT_R = Float.valueOf(0.0f);
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
            public final Float a;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
            public final Float b;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
            public final Float g;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
            public final Float r;

            public RGBAColor(Float f, Float f2, Float f3, Float f4) {
                this(f, f2, f3, f4, ByteString.EMPTY);
            }

            public RGBAColor(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
                super(ADAPTER, byteString);
                this.r = f;
                this.g = f2;
                this.b = f3;
                this.a = f4;
            }

            public ShapeEntity$ShapeStyle$RGBAColor$Builder newBuilder() {
                ShapeEntity$ShapeStyle$RGBAColor$Builder shapeEntity$ShapeStyle$RGBAColor$Builder = new ShapeEntity$ShapeStyle$RGBAColor$Builder();
                shapeEntity$ShapeStyle$RGBAColor$Builder.r = this.r;
                shapeEntity$ShapeStyle$RGBAColor$Builder.g = this.g;
                shapeEntity$ShapeStyle$RGBAColor$Builder.b = this.b;
                shapeEntity$ShapeStyle$RGBAColor$Builder.a = this.a;
                shapeEntity$ShapeStyle$RGBAColor$Builder.addUnknownFields(unknownFields());
                return shapeEntity$ShapeStyle$RGBAColor$Builder;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof RGBAColor)) {
                    return false;
                }
                RGBAColor rGBAColor = (RGBAColor) obj;
                if (unknownFields().equals(rGBAColor.unknownFields()) && Internal.equals(this.r, rGBAColor.r) && Internal.equals(this.g, rGBAColor.g) && Internal.equals(this.b, rGBAColor.b) && Internal.equals(this.a, rGBAColor.a)) {
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
                int hashCode = ((this.r != null ? this.r.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
                if (this.g != null) {
                    i2 = this.g.hashCode();
                } else {
                    i2 = 0;
                }
                hashCode = (i2 + hashCode) * 37;
                if (this.b != null) {
                    i2 = this.b.hashCode();
                } else {
                    i2 = 0;
                }
                i2 = (i2 + hashCode) * 37;
                if (this.a != null) {
                    i = this.a.hashCode();
                }
                i2 += i;
                this.f = i2;
                return i2;
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                if (this.r != null) {
                    stringBuilder.append(", r=").append(this.r);
                }
                if (this.g != null) {
                    stringBuilder.append(", g=").append(this.g);
                }
                if (this.b != null) {
                    stringBuilder.append(", b=").append(this.b);
                }
                if (this.a != null) {
                    stringBuilder.append(", a=").append(this.a);
                }
                return stringBuilder.replace(0, 2, "RGBAColor{").append('}').toString();
            }
        }

        public ShapeStyle(RGBAColor rGBAColor, RGBAColor rGBAColor2, Float f, ShapeEntity$ShapeStyle$LineCap shapeEntity$ShapeStyle$LineCap, ShapeEntity$ShapeStyle$LineJoin shapeEntity$ShapeStyle$LineJoin, Float f2, Float f3, Float f4, Float f5) {
            this(rGBAColor, rGBAColor2, f, shapeEntity$ShapeStyle$LineCap, shapeEntity$ShapeStyle$LineJoin, f2, f3, f4, f5, ByteString.EMPTY);
        }

        public ShapeStyle(RGBAColor rGBAColor, RGBAColor rGBAColor2, Float f, ShapeEntity$ShapeStyle$LineCap shapeEntity$ShapeStyle$LineCap, ShapeEntity$ShapeStyle$LineJoin shapeEntity$ShapeStyle$LineJoin, Float f2, Float f3, Float f4, Float f5, ByteString byteString) {
            super(ADAPTER, byteString);
            this.fill = rGBAColor;
            this.stroke = rGBAColor2;
            this.strokeWidth = f;
            this.lineCap = shapeEntity$ShapeStyle$LineCap;
            this.lineJoin = shapeEntity$ShapeStyle$LineJoin;
            this.miterLimit = f2;
            this.lineDashI = f3;
            this.lineDashII = f4;
            this.lineDashIII = f5;
        }

        public ShapeEntity$ShapeStyle$Builder newBuilder() {
            ShapeEntity$ShapeStyle$Builder shapeEntity$ShapeStyle$Builder = new ShapeEntity$ShapeStyle$Builder();
            shapeEntity$ShapeStyle$Builder.fill = this.fill;
            shapeEntity$ShapeStyle$Builder.stroke = this.stroke;
            shapeEntity$ShapeStyle$Builder.strokeWidth = this.strokeWidth;
            shapeEntity$ShapeStyle$Builder.lineCap = this.lineCap;
            shapeEntity$ShapeStyle$Builder.lineJoin = this.lineJoin;
            shapeEntity$ShapeStyle$Builder.miterLimit = this.miterLimit;
            shapeEntity$ShapeStyle$Builder.lineDashI = this.lineDashI;
            shapeEntity$ShapeStyle$Builder.lineDashII = this.lineDashII;
            shapeEntity$ShapeStyle$Builder.lineDashIII = this.lineDashIII;
            shapeEntity$ShapeStyle$Builder.addUnknownFields(unknownFields());
            return shapeEntity$ShapeStyle$Builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ShapeStyle)) {
                return false;
            }
            ShapeStyle shapeStyle = (ShapeStyle) obj;
            if (unknownFields().equals(shapeStyle.unknownFields()) && Internal.equals(this.fill, shapeStyle.fill) && Internal.equals(this.stroke, shapeStyle.stroke) && Internal.equals(this.strokeWidth, shapeStyle.strokeWidth) && Internal.equals(this.lineCap, shapeStyle.lineCap) && Internal.equals(this.lineJoin, shapeStyle.lineJoin) && Internal.equals(this.miterLimit, shapeStyle.miterLimit) && Internal.equals(this.lineDashI, shapeStyle.lineDashI) && Internal.equals(this.lineDashII, shapeStyle.lineDashII) && Internal.equals(this.lineDashIII, shapeStyle.lineDashIII)) {
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
            int hashCode = ((this.fill != null ? this.fill.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
            if (this.stroke != null) {
                i2 = this.stroke.hashCode();
            } else {
                i2 = 0;
            }
            hashCode = (i2 + hashCode) * 37;
            if (this.strokeWidth != null) {
                i2 = this.strokeWidth.hashCode();
            } else {
                i2 = 0;
            }
            hashCode = (i2 + hashCode) * 37;
            if (this.lineCap != null) {
                i2 = this.lineCap.hashCode();
            } else {
                i2 = 0;
            }
            hashCode = (i2 + hashCode) * 37;
            if (this.lineJoin != null) {
                i2 = this.lineJoin.hashCode();
            } else {
                i2 = 0;
            }
            hashCode = (i2 + hashCode) * 37;
            if (this.miterLimit != null) {
                i2 = this.miterLimit.hashCode();
            } else {
                i2 = 0;
            }
            hashCode = (i2 + hashCode) * 37;
            if (this.lineDashI != null) {
                i2 = this.lineDashI.hashCode();
            } else {
                i2 = 0;
            }
            hashCode = (i2 + hashCode) * 37;
            if (this.lineDashII != null) {
                i2 = this.lineDashII.hashCode();
            } else {
                i2 = 0;
            }
            i2 = (i2 + hashCode) * 37;
            if (this.lineDashIII != null) {
                i = this.lineDashIII.hashCode();
            }
            i2 += i;
            this.f = i2;
            return i2;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.fill != null) {
                stringBuilder.append(", fill=").append(this.fill);
            }
            if (this.stroke != null) {
                stringBuilder.append(", stroke=").append(this.stroke);
            }
            if (this.strokeWidth != null) {
                stringBuilder.append(", strokeWidth=").append(this.strokeWidth);
            }
            if (this.lineCap != null) {
                stringBuilder.append(", lineCap=").append(this.lineCap);
            }
            if (this.lineJoin != null) {
                stringBuilder.append(", lineJoin=").append(this.lineJoin);
            }
            if (this.miterLimit != null) {
                stringBuilder.append(", miterLimit=").append(this.miterLimit);
            }
            if (this.lineDashI != null) {
                stringBuilder.append(", lineDashI=").append(this.lineDashI);
            }
            if (this.lineDashII != null) {
                stringBuilder.append(", lineDashII=").append(this.lineDashII);
            }
            if (this.lineDashIII != null) {
                stringBuilder.append(", lineDashIII=").append(this.lineDashIII);
            }
            return stringBuilder.replace(0, 2, "ShapeStyle{").append('}').toString();
        }
    }

    public ShapeEntity(ShapeEntity$ShapeType shapeEntity$ShapeType, ShapeStyle shapeStyle, Transform transform, ShapeArgs shapeArgs, RectArgs rectArgs, EllipseArgs ellipseArgs) {
        this(shapeEntity$ShapeType, shapeStyle, transform, shapeArgs, rectArgs, ellipseArgs, ByteString.EMPTY);
    }

    public ShapeEntity(ShapeEntity$ShapeType shapeEntity$ShapeType, ShapeStyle shapeStyle, Transform transform, ShapeArgs shapeArgs, RectArgs rectArgs, EllipseArgs ellipseArgs, ByteString byteString) {
        super(ADAPTER, byteString);
        if (Internal.countNonNull(shapeArgs, rectArgs, ellipseArgs) > 1) {
            throw new IllegalArgumentException("at most one of shape, rect, ellipse may be non-null");
        }
        this.type = shapeEntity$ShapeType;
        this.styles = shapeStyle;
        this.transform = transform;
        this.shape = shapeArgs;
        this.rect = rectArgs;
        this.ellipse = ellipseArgs;
    }

    public ShapeEntity$Builder newBuilder() {
        ShapeEntity$Builder shapeEntity$Builder = new ShapeEntity$Builder();
        shapeEntity$Builder.type = this.type;
        shapeEntity$Builder.styles = this.styles;
        shapeEntity$Builder.transform = this.transform;
        shapeEntity$Builder.shape = this.shape;
        shapeEntity$Builder.rect = this.rect;
        shapeEntity$Builder.ellipse = this.ellipse;
        shapeEntity$Builder.addUnknownFields(unknownFields());
        return shapeEntity$Builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShapeEntity)) {
            return false;
        }
        ShapeEntity shapeEntity = (ShapeEntity) obj;
        if (unknownFields().equals(shapeEntity.unknownFields()) && Internal.equals(this.type, shapeEntity.type) && Internal.equals(this.styles, shapeEntity.styles) && Internal.equals(this.transform, shapeEntity.transform) && Internal.equals(this.shape, shapeEntity.shape) && Internal.equals(this.rect, shapeEntity.rect) && Internal.equals(this.ellipse, shapeEntity.ellipse)) {
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
        int hashCode = ((this.type != null ? this.type.hashCode() : 0) + (unknownFields().hashCode() * 37)) * 37;
        if (this.styles != null) {
            i2 = this.styles.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.transform != null) {
            i2 = this.transform.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.shape != null) {
            i2 = this.shape.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.rect != null) {
            i2 = this.rect.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.ellipse != null) {
            i = this.ellipse.hashCode();
        }
        i2 += i;
        this.f = i2;
        return i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.type != null) {
            stringBuilder.append(", type=").append(this.type);
        }
        if (this.styles != null) {
            stringBuilder.append(", styles=").append(this.styles);
        }
        if (this.transform != null) {
            stringBuilder.append(", transform=").append(this.transform);
        }
        if (this.shape != null) {
            stringBuilder.append(", shape=").append(this.shape);
        }
        if (this.rect != null) {
            stringBuilder.append(", rect=").append(this.rect);
        }
        if (this.ellipse != null) {
            stringBuilder.append(", ellipse=").append(this.ellipse);
        }
        return stringBuilder.replace(0, 2, "ShapeEntity{").append('}').toString();
    }
}
