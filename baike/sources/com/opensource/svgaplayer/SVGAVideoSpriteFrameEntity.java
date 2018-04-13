package com.opensource.svgaplayer;

import android.graphics.Matrix;
import com.alipay.sdk.sys.a;
import com.opensource.svgaplayer.proto.FrameEntity;
import com.opensource.svgaplayer.proto.Layout;
import com.opensource.svgaplayer.proto.ShapeEntity;
import com.opensource.svgaplayer.proto.Transform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lcom/opensource/svgaplayer/SVGAVideoSpriteFrameEntity;", "", "obj", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "Lcom/opensource/svgaplayer/proto/FrameEntity;", "(Lcom/opensource/svgaplayer/proto/FrameEntity;)V", "alpha", "", "getAlpha", "()D", "setAlpha", "(D)V", "layout", "Lcom/opensource/svgaplayer/SVGARect;", "getLayout", "()Lcom/opensource/svgaplayer/SVGARect;", "setLayout", "(Lcom/opensource/svgaplayer/SVGARect;)V", "maskPath", "Lcom/opensource/svgaplayer/SVGAPath;", "getMaskPath", "()Lcom/opensource/svgaplayer/SVGAPath;", "setMaskPath", "(Lcom/opensource/svgaplayer/SVGAPath;)V", "shapes", "", "Lcom/opensource/svgaplayer/SVGAVideoShapeEntity;", "getShapes", "()Ljava/util/List;", "setShapes", "(Ljava/util/List;)V", "transform", "Landroid/graphics/Matrix;", "getTransform", "()Landroid/graphics/Matrix;", "setTransform", "(Landroid/graphics/Matrix;)V", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAVideoSpriteFrameEntity {
    private double a;
    @NotNull
    private SVGARect b = new SVGARect(0.0d, 0.0d, 0.0d, 0.0d);
    @NotNull
    private Matrix c = new Matrix();
    @Nullable
    private SVGAPath d;
    @NotNull
    private List<SVGAVideoShapeEntity> e = q.emptyList();

    public final double getAlpha() {
        return this.a;
    }

    public final void setAlpha(double d) {
        this.a = d;
    }

    @NotNull
    public final SVGARect getLayout() {
        return this.b;
    }

    public final void setLayout(@NotNull SVGARect sVGARect) {
        Intrinsics.checkParameterIsNotNull(sVGARect, "<set-?>");
        this.b = sVGARect;
    }

    @NotNull
    public final Matrix getTransform() {
        return this.c;
    }

    public final void setTransform(@NotNull Matrix matrix) {
        Intrinsics.checkParameterIsNotNull(matrix, "<set-?>");
        this.c = matrix;
    }

    @Nullable
    public final SVGAPath getMaskPath() {
        return this.d;
    }

    public final void setMaskPath(@Nullable SVGAPath sVGAPath) {
        this.d = sVGAPath;
    }

    @NotNull
    public final List<SVGAVideoShapeEntity> getShapes() {
        return this.e;
    }

    public final void setShapes(@NotNull List<SVGAVideoShapeEntity> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.e = list;
    }

    public SVGAVideoSpriteFrameEntity(@NotNull JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "obj");
        this.a = jSONObject.optDouble("alpha", 0.0d);
        JSONObject optJSONObject = jSONObject.optJSONObject("layout");
        if (optJSONObject != null) {
            this.b = new SVGARect(optJSONObject.optDouble("x", 0.0d), optJSONObject.optDouble("y", 0.0d), optJSONObject.optDouble(IndexEntry.COLUMN_NAME_WIDTH, 0.0d), optJSONObject.optDouble(IndexEntry.COLUMN_NAME_HEIGHT, 0.0d));
        }
        optJSONObject = jSONObject.optJSONObject("transform");
        if (optJSONObject != null) {
            r3 = new float[9];
            double optDouble = optJSONObject.optDouble("a", 1.0d);
            double optDouble2 = optJSONObject.optDouble(CustomButton.POSITION_BOTTOM, 0.0d);
            double optDouble3 = optJSONObject.optDouble("c", 0.0d);
            double optDouble4 = optJSONObject.optDouble("d", 1.0d);
            double optDouble5 = optJSONObject.optDouble("tx", 0.0d);
            double optDouble6 = optJSONObject.optDouble(a.g, 0.0d);
            r3[0] = (float) optDouble;
            r3[1] = (float) optDouble3;
            r3[2] = (float) optDouble5;
            r3[3] = (float) optDouble2;
            r3[4] = (float) optDouble4;
            r3[5] = (float) optDouble6;
            r3[6] = (float) 0.0d;
            r3[7] = (float) 0.0d;
            r3[8] = (float) 1.0d;
            this.c.setValues(r3);
        }
        String optString = jSONObject.optString("clipPath");
        if (optString != null) {
            if ((((CharSequence) optString).length() > 0 ? 1 : null) != null) {
                this.d = new SVGAPath(optString);
            }
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("shapes");
        if (optJSONArray != null) {
            List arrayList = new ArrayList();
            IntRange until = e.until(0, optJSONArray.length());
            int first = until.getFirst();
            int last = until.getLast();
            if (first <= last) {
                while (true) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(first);
                    if (optJSONObject2 != null) {
                        arrayList.add(new SVGAVideoShapeEntity(optJSONObject2));
                    }
                    if (first == last) {
                        break;
                    }
                    first++;
                }
            }
            this.e = v.toList(arrayList);
        }
    }

    public SVGAVideoSpriteFrameEntity(@NotNull FrameEntity frameEntity) {
        float floatValue;
        Intrinsics.checkParameterIsNotNull(frameEntity, "obj");
        Float f = frameEntity.alpha;
        this.a = (double) (f != null ? f.floatValue() : 0.0f);
        Layout layout = frameEntity.layout;
        if (layout != null) {
            f = layout.x;
            if (f != null) {
                floatValue = f.floatValue();
            } else {
                floatValue = 0.0f;
            }
            double d = (double) floatValue;
            f = layout.y;
            double floatValue2 = (double) (f != null ? f.floatValue() : 0.0f);
            f = layout.width;
            double floatValue3 = (double) (f != null ? f.floatValue() : 0.0f);
            f = layout.height;
            this.b = new SVGARect(d, floatValue2, floatValue3, (double) (f != null ? f.floatValue() : 0.0f));
        }
        Transform transform = frameEntity.transform;
        if (transform != null) {
            float floatValue4;
            float floatValue5;
            float floatValue6;
            float floatValue7;
            float floatValue8;
            float[] fArr = new float[9];
            f = transform.a;
            if (f != null) {
                floatValue = f.floatValue();
            } else {
                floatValue = 1.0f;
            }
            Float f2 = transform.b;
            if (f2 != null) {
                floatValue4 = f2.floatValue();
            } else {
                floatValue4 = 0.0f;
            }
            Float f3 = transform.c;
            if (f3 != null) {
                floatValue5 = f3.floatValue();
            } else {
                floatValue5 = 0.0f;
            }
            Float f4 = transform.d;
            if (f4 != null) {
                floatValue6 = f4.floatValue();
            } else {
                floatValue6 = 1.0f;
            }
            Float f5 = transform.tx;
            if (f5 != null) {
                floatValue7 = f5.floatValue();
            } else {
                floatValue7 = 0.0f;
            }
            Float f6 = transform.ty;
            if (f6 != null) {
                floatValue8 = f6.floatValue();
            } else {
                floatValue8 = 0.0f;
            }
            fArr[0] = floatValue;
            fArr[1] = floatValue5;
            fArr[2] = floatValue7;
            fArr[3] = floatValue4;
            fArr[4] = floatValue6;
            fArr[5] = floatValue8;
            fArr[6] = 0.0f;
            fArr[7] = 0.0f;
            fArr[8] = 1.0f;
            this.c.setValues(fArr);
        }
        String str = frameEntity.clipPath;
        if (str != null) {
            if ((((CharSequence) str).length() > 0 ? 1 : 0) == 0) {
                str = null;
            }
            if (str != null) {
                this.d = new SVGAPath(str);
            }
        }
        Iterable<ShapeEntity> iterable = frameEntity.shapes;
        Collection arrayList = new ArrayList(r.collectionSizeOrDefault(iterable, 10));
        for (ShapeEntity shapeEntity : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(shapeEntity, "it");
            arrayList.add(new SVGAVideoShapeEntity(shapeEntity));
        }
        this.e = (List) arrayList;
    }
}
