package com.opensource.svgaplayer;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView.ScaleType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u000fH\u0016J\u0010\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u000fH\u0016J\u0012\u0010(\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u000f@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006+"}, d2 = {"Lcom/opensource/svgaplayer/SVGADrawable;", "Landroid/graphics/drawable/Drawable;", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "(Lcom/opensource/svgaplayer/SVGAVideoEntity;)V", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;", "(Lcom/opensource/svgaplayer/SVGAVideoEntity;Lcom/opensource/svgaplayer/SVGADynamicEntity;)V", "value", "", "cleared", "getCleared", "()Z", "setCleared$library_release", "(Z)V", "", "currentFrame", "getCurrentFrame", "()I", "setCurrentFrame$library_release", "(I)V", "drawer", "Lcom/opensource/svgaplayer/SVGACanvasDrawer;", "getDynamicItem", "()Lcom/opensource/svgaplayer/SVGADynamicEntity;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "getScaleType", "()Landroid/widget/ImageView$ScaleType;", "setScaleType", "(Landroid/widget/ImageView$ScaleType;)V", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getOpacity", "setAlpha", "alpha", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGADrawable extends Drawable {
    private boolean a;
    private int b;
    @NotNull
    private ScaleType c;
    private final SVGACanvasDrawer d;
    @NotNull
    private final SVGAVideoEntity e;
    @NotNull
    private final SVGADynamicEntity f;

    public SVGADrawable(@NotNull SVGAVideoEntity sVGAVideoEntity, @NotNull SVGADynamicEntity sVGADynamicEntity) {
        Intrinsics.checkParameterIsNotNull(sVGAVideoEntity, "videoItem");
        Intrinsics.checkParameterIsNotNull(sVGADynamicEntity, "dynamicItem");
        this.e = sVGAVideoEntity;
        this.f = sVGADynamicEntity;
        this.a = true;
        this.c = ScaleType.MATRIX;
        this.d = new SVGACanvasDrawer(this.e, this.f);
    }

    @NotNull
    public final SVGADynamicEntity getDynamicItem() {
        return this.f;
    }

    @NotNull
    public final SVGAVideoEntity getVideoItem() {
        return this.e;
    }

    public SVGADrawable(@NotNull SVGAVideoEntity sVGAVideoEntity) {
        Intrinsics.checkParameterIsNotNull(sVGAVideoEntity, "videoItem");
        this(sVGAVideoEntity, new SVGADynamicEntity());
    }

    public final boolean getCleared() {
        return this.a;
    }

    public final void setCleared$library_release(boolean z) {
        if (this.a != z) {
            this.a = z;
            invalidateSelf();
        }
    }

    public final int getCurrentFrame() {
        return this.b;
    }

    public final void setCurrentFrame$library_release(int i) {
        if (this.b != i) {
            this.b = i;
            invalidateSelf();
        }
    }

    @NotNull
    public final ScaleType getScaleType() {
        return this.c;
    }

    public final void setScaleType(@NotNull ScaleType scaleType) {
        Intrinsics.checkParameterIsNotNull(scaleType, "<set-?>");
        this.c = scaleType;
    }

    public void draw(@Nullable Canvas canvas) {
        if (!this.a && canvas != null) {
            this.d.setCanvas(canvas);
            this.d.drawFrame(this.b, this.c);
        }
    }

    public void setAlpha(int i) {
    }

    public int getOpacity() {
        return -2;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
