package com.opensource.svgaplayer;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import com.alipay.sdk.sys.a;
import com.opensource.svgaplayer.proto.ShapeEntity;
import com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$LineCap;
import com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$LineJoin;
import com.opensource.svgaplayer.proto.ShapeEntity$ShapeType;
import com.opensource.svgaplayer.proto.ShapeEntity.EllipseArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.RectArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeArgs;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle;
import com.opensource.svgaplayer.proto.ShapeEntity.ShapeStyle.RGBAColor;
import com.opensource.svgaplayer.proto.Transform;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u000201B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010*\u001a\u00020+J\u0010\u0010,\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0005H\u0002J\u0010\u0010,\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010-\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0005H\u0002J\u0010\u0010-\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010.\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0005H\u0002J\u0010\u0010.\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010/\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0005H\u0002J\u0010\u0010/\u001a\u00020+2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R@\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b2\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0007\u001a\u0004\u0018\u00010\u0018@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR(\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u001e@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010%\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020$@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u00062"}, d2 = {"Lcom/opensource/svgaplayer/SVGAVideoShapeEntity;", "", "obj", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "Lcom/opensource/svgaplayer/proto/ShapeEntity;", "(Lcom/opensource/svgaplayer/proto/ShapeEntity;)V", "<set-?>", "", "", "args", "getArgs", "()Ljava/util/Map;", "setArgs", "(Ljava/util/Map;)V", "isKeep", "", "()Z", "shapePath", "Landroid/graphics/Path;", "getShapePath", "()Landroid/graphics/Path;", "setShapePath", "(Landroid/graphics/Path;)V", "Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Styles;", "styles", "getStyles", "()Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Styles;", "setStyles", "(Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Styles;)V", "Landroid/graphics/Matrix;", "transform", "getTransform", "()Landroid/graphics/Matrix;", "setTransform", "(Landroid/graphics/Matrix;)V", "Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Type;", "type", "getType", "()Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Type;", "setType", "(Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Type;)V", "buildPath", "", "parseArgs", "parseStyles", "parseTransform", "parseType", "Styles", "Type", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAVideoShapeEntity {
    @NotNull
    private Type a = Type.shape;
    @Nullable
    private Map<String, ? extends Object> b;
    @Nullable
    private Styles c;
    @Nullable
    private Matrix d;
    @Nullable
    private Path e;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR$\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR$\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR$\u0010 \u001a\u00020\u001f2\u0006\u0010\u0003\u001a\u00020\u001f@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Styles;", "", "()V", "<set-?>", "", "fill", "getFill", "()I", "setFill$library_release", "(I)V", "", "lineCap", "getLineCap", "()Ljava/lang/String;", "setLineCap$library_release", "(Ljava/lang/String;)V", "", "lineDash", "getLineDash", "()[F", "setLineDash$library_release", "([F)V", "lineJoin", "getLineJoin", "setLineJoin$library_release", "miterLimit", "getMiterLimit", "setMiterLimit$library_release", "stroke", "getStroke", "setStroke$library_release", "", "strokeWidth", "getStrokeWidth", "()F", "setStrokeWidth$library_release", "(F)V", "library_release"}, k = 1, mv = {1, 1, 6})
    public static final class Styles {
        private int a;
        private int b;
        private float c;
        @NotNull
        private String d = "butt";
        @NotNull
        private String e = "miter";
        private int f;
        @NotNull
        private float[] g = new float[0];

        public final int getFill() {
            return this.a;
        }

        public final void setFill$library_release(int i) {
            this.a = i;
        }

        public final int getStroke() {
            return this.b;
        }

        public final void setStroke$library_release(int i) {
            this.b = i;
        }

        public final float getStrokeWidth() {
            return this.c;
        }

        public final void setStrokeWidth$library_release(float f) {
            this.c = f;
        }

        @NotNull
        public final String getLineCap() {
            return this.d;
        }

        public final void setLineCap$library_release(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.d = str;
        }

        @NotNull
        public final String getLineJoin() {
            return this.e;
        }

        public final void setLineJoin$library_release(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.e = str;
        }

        public final int getMiterLimit() {
            return this.f;
        }

        public final void setMiterLimit$library_release(int i) {
            this.f = i;
        }

        @NotNull
        public final float[] getLineDash() {
            return this.g;
        }

        public final void setLineDash$library_release(@NotNull float[] fArr) {
            Intrinsics.checkParameterIsNotNull(fArr, "<set-?>");
            this.g = fArr;
        }
    }

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/opensource/svgaplayer/SVGAVideoShapeEntity$Type;", "", "(Ljava/lang/String;I)V", "shape", "rect", "ellipse", "keep", "library_release"}, k = 1, mv = {1, 1, 6})
    public enum Type {
    }

    @Metadata(bv = {1, 0, 1}, k = 3, mv = {1, 1, 6})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ShapeEntity$ShapeType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[ShapeEntity$ShapeStyle$LineCap.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[ShapeEntity$ShapeStyle$LineJoin.values().length];

        static {
            $EnumSwitchMapping$0[ShapeEntity$ShapeType.SHAPE.ordinal()] = 1;
            $EnumSwitchMapping$0[ShapeEntity$ShapeType.RECT.ordinal()] = 2;
            $EnumSwitchMapping$0[ShapeEntity$ShapeType.ELLIPSE.ordinal()] = 3;
            $EnumSwitchMapping$0[ShapeEntity$ShapeType.KEEP.ordinal()] = 4;
            $EnumSwitchMapping$1[ShapeEntity$ShapeStyle$LineCap.LineCap_BUTT.ordinal()] = 1;
            $EnumSwitchMapping$1[ShapeEntity$ShapeStyle$LineCap.LineCap_ROUND.ordinal()] = 2;
            $EnumSwitchMapping$1[ShapeEntity$ShapeStyle$LineCap.LineCap_SQUARE.ordinal()] = 3;
            $EnumSwitchMapping$2[ShapeEntity$ShapeStyle$LineJoin.LineJoin_BEVEL.ordinal()] = 1;
            $EnumSwitchMapping$2[ShapeEntity$ShapeStyle$LineJoin.LineJoin_MITER.ordinal()] = 2;
            $EnumSwitchMapping$2[ShapeEntity$ShapeStyle$LineJoin.LineJoin_ROUND.ordinal()] = 3;
        }
    }

    @NotNull
    public final Type getType() {
        return this.a;
    }

    @Nullable
    public final Map<String, Object> getArgs() {
        return this.b;
    }

    @Nullable
    public final Styles getStyles() {
        return this.c;
    }

    @Nullable
    public final Matrix getTransform() {
        return this.d;
    }

    public SVGAVideoShapeEntity(@NotNull JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "obj");
        a(jSONObject);
        b(jSONObject);
        c(jSONObject);
        d(jSONObject);
        buildPath();
    }

    public SVGAVideoShapeEntity(@NotNull ShapeEntity shapeEntity) {
        Intrinsics.checkParameterIsNotNull(shapeEntity, "obj");
        a(shapeEntity);
        b(shapeEntity);
        c(shapeEntity);
        d(shapeEntity);
    }

    public final boolean isKeep() {
        return Intrinsics.areEqual(this.a, Type.keep);
    }

    @Nullable
    public final Path getShapePath() {
        return this.e;
    }

    public final void setShapePath(@Nullable Path path) {
        this.e = path;
    }

    private final void a(JSONObject jSONObject) {
        String optString = jSONObject.optString("type");
        if (optString != null) {
            if (t.equals(optString, "shape", true)) {
                this.a = Type.shape;
            } else if (t.equals(optString, "rect", true)) {
                this.a = Type.rect;
            } else if (t.equals(optString, "ellipse", true)) {
                this.a = Type.ellipse;
            } else if (t.equals(optString, "keep", true)) {
                this.a = Type.keep;
            }
        }
    }

    private final void a(ShapeEntity shapeEntity) {
        ShapeEntity$ShapeType shapeEntity$ShapeType = shapeEntity.type;
        if (shapeEntity$ShapeType != null) {
            Type type;
            switch (WhenMappings.$EnumSwitchMapping$0[shapeEntity$ShapeType.ordinal()]) {
                case 1:
                    type = Type.shape;
                    break;
                case 2:
                    type = Type.rect;
                    break;
                case 3:
                    type = Type.ellipse;
                    break;
                case 4:
                    type = Type.keep;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            this.a = type;
        }
    }

    private final void b(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        JSONObject optJSONObject = jSONObject.optJSONObject("args");
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                Object obj = optJSONObject.get(str);
                if (obj != null) {
                    hashMap.put(str, obj);
                }
            }
            this.b = hashMap;
        }
    }

    private final void b(ShapeEntity shapeEntity) {
        Object obj;
        HashMap hashMap = new HashMap();
        ShapeArgs shapeArgs = shapeEntity.shape;
        if (shapeArgs != null) {
            String str = shapeArgs.d;
            if (str != null) {
                hashMap.put("d", str);
            }
        }
        EllipseArgs ellipseArgs = shapeEntity.ellipse;
        if (ellipseArgs != null) {
            String str2 = "x";
            obj = ellipseArgs.x;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = "y";
            obj = ellipseArgs.y;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = "radiusX";
            obj = ellipseArgs.radiusX;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = "radiusY";
            obj = ellipseArgs.radiusY;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
        }
        RectArgs rectArgs = shapeEntity.rect;
        if (rectArgs != null) {
            str2 = "x";
            obj = rectArgs.x;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = "y";
            obj = rectArgs.y;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = IndexEntry.COLUMN_NAME_WIDTH;
            obj = rectArgs.width;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = IndexEntry.COLUMN_NAME_HEIGHT;
            obj = rectArgs.height;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
            str2 = "cornerRadius";
            obj = rectArgs.cornerRadius;
            if (obj == null) {
                obj = Float.valueOf(0.0f);
            }
            hashMap.put(str2, obj);
        }
        this.b = hashMap;
    }

    private final void c(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("styles");
        if (optJSONObject != null) {
            Styles styles = new Styles();
            JSONArray optJSONArray = optJSONObject.optJSONArray("fill");
            if (optJSONArray != null) {
                if (optJSONArray.length() == 4) {
                    styles.setFill$library_release(Color.argb((int) (optJSONArray.optDouble(3) * ((double) 255)), (int) (optJSONArray.optDouble(0) * ((double) 255)), (int) (optJSONArray.optDouble(1) * ((double) 255)), (int) (optJSONArray.optDouble(2) * ((double) 255))));
                }
            }
            optJSONArray = optJSONObject.optJSONArray("stroke");
            if (optJSONArray != null) {
                if (optJSONArray.length() == 4) {
                    styles.setStroke$library_release(Color.argb((int) (optJSONArray.optDouble(3) * ((double) 255)), (int) (optJSONArray.optDouble(0) * ((double) 255)), (int) (optJSONArray.optDouble(1) * ((double) 255)), (int) (optJSONArray.optDouble(2) * ((double) 255))));
                }
            }
            styles.setStrokeWidth$library_release((float) optJSONObject.optDouble("strokeWidth", 0.0d));
            String optString = optJSONObject.optString("lineCap", "butt");
            Intrinsics.checkExpressionValueIsNotNull(optString, "it.optString(\"lineCap\", \"butt\")");
            styles.setLineCap$library_release(optString);
            optString = optJSONObject.optString("lineJoin", "miter");
            Intrinsics.checkExpressionValueIsNotNull(optString, "it.optString(\"lineJoin\", \"miter\")");
            styles.setLineJoin$library_release(optString);
            styles.setMiterLimit$library_release(optJSONObject.optInt("miterLimit", 0));
            optJSONArray = optJSONObject.optJSONArray("lineDash");
            if (optJSONArray != null) {
                styles.setLineDash$library_release(new float[optJSONArray.length()]);
                IntRange until = e.until(0, optJSONArray.length());
                int first = until.getFirst();
                int last = until.getLast();
                if (first <= last) {
                    while (true) {
                        styles.getLineDash()[first] = (float) optJSONArray.optDouble(first, 0.0d);
                        if (first == last) {
                            break;
                        }
                        first++;
                    }
                }
            }
            this.c = styles;
        }
    }

    private final void c(ShapeEntity shapeEntity) {
        float f = 0.0f;
        ShapeStyle shapeStyle = shapeEntity.styles;
        if (shapeStyle != null) {
            Float f2;
            int floatValue;
            int floatValue2;
            int floatValue3;
            float floatValue4;
            Styles styles = new Styles();
            RGBAColor rGBAColor = shapeStyle.fill;
            if (rGBAColor != null) {
                f2 = rGBAColor.a;
                floatValue = (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255));
                f2 = rGBAColor.r;
                floatValue2 = (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255));
                f2 = rGBAColor.g;
                floatValue3 = (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255));
                f2 = rGBAColor.b;
                styles.setFill$library_release(Color.argb(floatValue, floatValue2, floatValue3, (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255))));
            }
            rGBAColor = shapeStyle.stroke;
            if (rGBAColor != null) {
                f2 = rGBAColor.a;
                if (f2 != null) {
                    floatValue4 = f2.floatValue();
                } else {
                    floatValue4 = 0.0f;
                }
                floatValue = (int) (floatValue4 * ((float) 255));
                f2 = rGBAColor.r;
                floatValue2 = (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255));
                f2 = rGBAColor.g;
                floatValue3 = (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255));
                f2 = rGBAColor.b;
                styles.setStroke$library_release(Color.argb(floatValue, floatValue2, floatValue3, (int) ((f2 != null ? f2.floatValue() : 0.0f) * ((float) 255))));
            }
            f2 = shapeStyle.strokeWidth;
            if (f2 != null) {
                floatValue4 = f2.floatValue();
            } else {
                floatValue4 = 0.0f;
            }
            styles.setStrokeWidth$library_release(floatValue4);
            ShapeEntity$ShapeStyle$LineCap shapeEntity$ShapeStyle$LineCap = shapeStyle.lineCap;
            if (shapeEntity$ShapeStyle$LineCap != null) {
                switch (WhenMappings.$EnumSwitchMapping$1[shapeEntity$ShapeStyle$LineCap.ordinal()]) {
                    case 1:
                        styles.setLineCap$library_release("butt");
                        break;
                    case 2:
                        styles.setLineCap$library_release("round");
                        break;
                    case 3:
                        styles.setLineCap$library_release("square");
                        break;
                }
            }
            ShapeEntity$ShapeStyle$LineJoin shapeEntity$ShapeStyle$LineJoin = shapeStyle.lineJoin;
            if (shapeEntity$ShapeStyle$LineJoin != null) {
                switch (WhenMappings.$EnumSwitchMapping$2[shapeEntity$ShapeStyle$LineJoin.ordinal()]) {
                    case 1:
                        styles.setLineJoin$library_release("bevel");
                        break;
                    case 2:
                        styles.setLineJoin$library_release("miter");
                        break;
                    case 3:
                        styles.setLineJoin$library_release("round");
                        break;
                }
            }
            f2 = shapeStyle.miterLimit;
            if (f2 != null) {
                f = f2.floatValue();
            }
            styles.setMiterLimit$library_release((int) f);
            styles.setLineDash$library_release(new float[3]);
            f2 = shapeStyle.lineDashI;
            if (f2 != null) {
                styles.getLineDash()[0] = f2.floatValue();
            }
            f2 = shapeStyle.lineDashII;
            if (f2 != null) {
                styles.getLineDash()[1] = f2.floatValue();
            }
            f2 = shapeStyle.lineDashIII;
            if (f2 != null) {
                styles.getLineDash()[2] = f2.floatValue();
            }
            this.c = styles;
        }
    }

    private final void d(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("transform");
        if (optJSONObject != null) {
            Matrix matrix = new Matrix();
            r4 = new float[9];
            double optDouble = optJSONObject.optDouble("a", 1.0d);
            double optDouble2 = optJSONObject.optDouble(CustomButton.POSITION_BOTTOM, 0.0d);
            double optDouble3 = optJSONObject.optDouble("c", 0.0d);
            double optDouble4 = optJSONObject.optDouble("d", 1.0d);
            double optDouble5 = optJSONObject.optDouble("tx", 0.0d);
            double optDouble6 = optJSONObject.optDouble(a.g, 0.0d);
            r4[0] = (float) optDouble;
            r4[1] = (float) optDouble3;
            r4[2] = (float) optDouble5;
            r4[3] = (float) optDouble2;
            r4[4] = (float) optDouble4;
            r4[5] = (float) optDouble6;
            r4[6] = (float) 0.0d;
            r4[7] = (float) 0.0d;
            r4[8] = (float) 1.0d;
            matrix.setValues(r4);
            this.d = matrix;
        }
    }

    private final void d(ShapeEntity shapeEntity) {
        Transform transform = shapeEntity.transform;
        if (transform != null) {
            float floatValue;
            float floatValue2;
            float floatValue3;
            float floatValue4;
            float floatValue5;
            Matrix matrix = new Matrix();
            float[] fArr = new float[9];
            Float f = transform.a;
            float floatValue6 = f != null ? f.floatValue() : 1.0f;
            Float f2 = transform.b;
            if (f2 != null) {
                floatValue = f2.floatValue();
            } else {
                floatValue = 0.0f;
            }
            Float f3 = transform.c;
            if (f3 != null) {
                floatValue2 = f3.floatValue();
            } else {
                floatValue2 = 0.0f;
            }
            Float f4 = transform.d;
            if (f4 != null) {
                floatValue3 = f4.floatValue();
            } else {
                floatValue3 = 1.0f;
            }
            Float f5 = transform.tx;
            if (f5 != null) {
                floatValue4 = f5.floatValue();
            } else {
                floatValue4 = 0.0f;
            }
            Float f6 = transform.ty;
            if (f6 != null) {
                floatValue5 = f6.floatValue();
            } else {
                floatValue5 = 0.0f;
            }
            fArr[0] = floatValue6;
            fArr[1] = floatValue2;
            fArr[2] = floatValue4;
            fArr[3] = floatValue;
            fArr[4] = floatValue3;
            fArr[5] = floatValue5;
            fArr[6] = 0.0f;
            fArr[7] = 0.0f;
            fArr[8] = 1.0f;
            matrix.setValues(fArr);
            this.d = matrix;
        }
    }

    public final void buildPath() {
        Object obj = null;
        if (this.e == null) {
            SVGAVideoShapeEntityKt.getSharedPath().reset();
            Map map;
            Object obj2;
            if (Intrinsics.areEqual(this.a, Type.shape)) {
                map = this.b;
                obj2 = map != null ? map.get("d") : null;
                if (!(obj2 instanceof String)) {
                    obj2 = null;
                }
                String str = (String) obj2;
                if (str != null) {
                    new SVGAPath(str).buildPath(SVGAVideoShapeEntityKt.getSharedPath());
                }
            } else if (Intrinsics.areEqual(this.a, Type.ellipse)) {
                map = this.b;
                obj2 = map != null ? map.get("x") : null;
                if (!(obj2 instanceof Number)) {
                    obj2 = null;
                }
                r0 = (Number) obj2;
                if (r0 != null) {
                    r1 = this.b;
                    if (r1 != null) {
                        r1 = r1.get("y");
                    } else {
                        r1 = null;
                    }
                    if (!(r1 instanceof Number)) {
                        r1 = null;
                    }
                    r1 = (Number) r1;
                    if (r1 != null) {
                        r2 = this.b;
                        if (r2 != null) {
                            r2 = r2.get("radiusX");
                        } else {
                            r2 = null;
                        }
                        if (!(r2 instanceof Number)) {
                            r2 = null;
                        }
                        r2 = (Number) r2;
                        if (r2 != null) {
                            r3 = this.b;
                            if (r3 != null) {
                                r3 = r3.get("radiusY");
                            } else {
                                r3 = null;
                            }
                            if (!(r3 instanceof Number)) {
                                r3 = null;
                            }
                            r3 = (Number) r3;
                            if (r3 != null) {
                                r0 = r0.floatValue();
                                r1 = r1.floatValue();
                                r2 = r2.floatValue();
                                r3 = r3.floatValue();
                                SVGAVideoShapeEntityKt.getSharedPath().addOval(new RectF(r0 - r2, r1 - r3, r0 + r2, r1 + r3), Direction.CW);
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                return;
            } else if (Intrinsics.areEqual(this.a, Type.rect)) {
                map = this.b;
                obj2 = map != null ? map.get("x") : null;
                if (!(obj2 instanceof Number)) {
                    obj2 = null;
                }
                r0 = (Number) obj2;
                if (r0 != null) {
                    r1 = this.b;
                    if (r1 != null) {
                        r1 = r1.get("y");
                    } else {
                        r1 = null;
                    }
                    if (!(r1 instanceof Number)) {
                        r1 = null;
                    }
                    r1 = (Number) r1;
                    if (r1 != null) {
                        r2 = this.b;
                        if (r2 != null) {
                            r2 = r2.get(IndexEntry.COLUMN_NAME_WIDTH);
                        } else {
                            r2 = null;
                        }
                        if (!(r2 instanceof Number)) {
                            r2 = null;
                        }
                        r2 = (Number) r2;
                        if (r2 != null) {
                            r3 = this.b;
                            if (r3 != null) {
                                r3 = r3.get(IndexEntry.COLUMN_NAME_HEIGHT);
                            } else {
                                r3 = null;
                            }
                            if (!(r3 instanceof Number)) {
                                r3 = null;
                            }
                            r3 = (Number) r3;
                            if (r3 != null) {
                                Object obj3;
                                Map map2 = this.b;
                                if (map2 != null) {
                                    obj3 = map2.get("cornerRadius");
                                } else {
                                    obj3 = null;
                                }
                                if (obj3 instanceof Number) {
                                    obj = obj3;
                                }
                                Number number = (Number) obj;
                                if (number != null) {
                                    r0 = r0.floatValue();
                                    r1 = r1.floatValue();
                                    r2 = r2.floatValue();
                                    r3 = r3.floatValue();
                                    float floatValue = number.floatValue();
                                    SVGAVideoShapeEntityKt.getSharedPath().addRoundRect(new RectF(r0, r1, r2 + r0, r3 + r1), floatValue, floatValue, Direction.CW);
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            this.e = new Path();
            Path path = this.e;
            if (path != null) {
                path.addPath(SVGAVideoShapeEntityKt.getSharedPath());
            }
        }
    }
}
