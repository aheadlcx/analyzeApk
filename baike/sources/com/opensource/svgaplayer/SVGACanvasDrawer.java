package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.widget.ImageView.ScaleType;
import com.opensource.svgaplayer.SGVADrawer.SVGADrawerSprite;
import com.opensource.svgaplayer.SVGAVideoShapeEntity.Styles;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u001c\u0010\"\u001a\u00020\u001d2\n\u0010#\u001a\u00060$R\u00020\u00012\u0006\u0010 \u001a\u00020!H\u0002J\u001c\u0010%\u001a\u00020\u001d2\n\u0010#\u001a\u00060$R\u00020\u00012\u0006\u0010 \u001a\u00020!H\u0002J\u001c\u0010&\u001a\u00020\u001d2\n\u0010#\u001a\u00060$R\u00020\u00012\u0006\u0010 \u001a\u00020!H\u0002J\u001c\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)2\n\u0010#\u001a\u00060$R\u00020\u0001H\u0002J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010+\u001a\u00020\u0010H\u0002J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020.H\u0002R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/opensource/svgaplayer/SVGACanvasDrawer;", "Lcom/opensource/svgaplayer/SGVADrawer;", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;", "(Lcom/opensource/svgaplayer/SVGAVideoEntity;Lcom/opensource/svgaplayer/SVGADynamicEntity;)V", "canvas", "Landroid/graphics/Canvas;", "getCanvas", "()Landroid/graphics/Canvas;", "setCanvas", "(Landroid/graphics/Canvas;)V", "getDynamicItem", "()Lcom/opensource/svgaplayer/SVGADynamicEntity;", "ratio", "", "ratioX", "", "sharedContentTransform", "Landroid/graphics/Matrix;", "sharedPaint", "Landroid/graphics/Paint;", "sharedPath", "Landroid/graphics/Path;", "sharedPath2", "tValues", "", "drawFrame", "", "frameIndex", "", "scaleType", "Landroid/widget/ImageView$ScaleType;", "drawImage", "sprite", "Lcom/opensource/svgaplayer/SGVADrawer$SVGADrawerSprite;", "drawShape", "drawSprite", "drawText", "drawingBitmap", "Landroid/graphics/Bitmap;", "performScaleType", "requestScale", "resetShapeStrokePaint", "shape", "Lcom/opensource/svgaplayer/SVGAVideoShapeEntity;", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGACanvasDrawer extends SGVADrawer {
    @Nullable
    private Canvas a;
    private float b = 1.0f;
    private boolean c;
    private final Paint d = new Paint();
    private final Path e = new Path();
    private final Path f = new Path();
    private final Matrix g = new Matrix();
    private final float[] h = new float[16];
    @NotNull
    private final SVGADynamicEntity i;

    @Metadata(bv = {1, 0, 1}, k = 3, mv = {1, 1, 6})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ScaleType.values().length];

        static {
            $EnumSwitchMapping$0[ScaleType.CENTER.ordinal()] = 1;
            $EnumSwitchMapping$0[ScaleType.CENTER_CROP.ordinal()] = 2;
            $EnumSwitchMapping$0[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            $EnumSwitchMapping$0[ScaleType.FIT_CENTER.ordinal()] = 4;
            $EnumSwitchMapping$0[ScaleType.FIT_START.ordinal()] = 5;
            $EnumSwitchMapping$0[ScaleType.FIT_END.ordinal()] = 6;
            $EnumSwitchMapping$0[ScaleType.FIT_XY.ordinal()] = 7;
        }
    }

    public SVGACanvasDrawer(@NotNull SVGAVideoEntity sVGAVideoEntity, @NotNull SVGADynamicEntity sVGADynamicEntity) {
        Intrinsics.checkParameterIsNotNull(sVGAVideoEntity, "videoItem");
        Intrinsics.checkParameterIsNotNull(sVGADynamicEntity, "dynamicItem");
        super(sVGAVideoEntity);
        this.i = sVGADynamicEntity;
    }

    @NotNull
    public final SVGADynamicEntity getDynamicItem() {
        return this.i;
    }

    @Nullable
    public final Canvas getCanvas() {
        return this.a;
    }

    public final void setCanvas(@Nullable Canvas canvas) {
        this.a = canvas;
    }

    public void drawFrame(int i, @NotNull ScaleType scaleType) {
        Canvas canvas;
        DrawFilter drawFilter = null;
        Intrinsics.checkParameterIsNotNull(scaleType, "scaleType");
        super.drawFrame(i, scaleType);
        DrawFilter drawFilter2 = null;
        if (getVideoItem().getAntiAlias()) {
            canvas = this.a;
            if (canvas != null) {
                drawFilter = canvas.getDrawFilter();
            }
            Canvas canvas2 = this.a;
            if (canvas2 != null) {
                canvas2.setDrawFilter(SVGACanvasDrawerKt.a);
            }
        } else {
            drawFilter = drawFilter2;
        }
        for (SVGADrawerSprite a : requestFrameSprites$library_release(i)) {
            a(a, scaleType);
        }
        if (getVideoItem().getAntiAlias()) {
            canvas = this.a;
            if (canvas != null) {
                canvas.setDrawFilter(drawFilter);
            }
        }
    }

    private final void a(ScaleType scaleType) {
        boolean z = true;
        Canvas canvas = this.a;
        if (canvas != null && canvas.getWidth() != 0 && canvas.getHeight() != 0 && getVideoItem().getVideoSize().getWidth() != 0.0d && getVideoItem().getVideoSize().getHeight() != 0.0d) {
            switch (WhenMappings.$EnumSwitchMapping$0[scaleType.ordinal()]) {
                case 1:
                    this.g.postTranslate((float) ((((double) canvas.getWidth()) - getVideoItem().getVideoSize().getWidth()) / 2.0d), (float) ((((double) canvas.getHeight()) - getVideoItem().getVideoSize().getHeight()) / 2.0d));
                    return;
                case 2:
                    if (getVideoItem().getVideoSize().getWidth() / getVideoItem().getVideoSize().getHeight() > ((double) (((float) canvas.getWidth()) / ((float) canvas.getHeight())))) {
                        this.b = (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight());
                        this.c = false;
                        this.g.postScale((float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                        this.g.postTranslate((float) ((((double) canvas.getWidth()) - ((((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()) * getVideoItem().getVideoSize().getWidth())) / 2.0d), 0.0f);
                        return;
                    }
                    this.b = (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth());
                    this.c = true;
                    this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()));
                    this.g.postTranslate(0.0f, (float) ((((double) canvas.getHeight()) - ((((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()) * getVideoItem().getVideoSize().getHeight())) / 2.0d));
                    return;
                case 3:
                    if (getVideoItem().getVideoSize().getWidth() < ((double) canvas.getWidth()) && getVideoItem().getVideoSize().getHeight() < ((double) canvas.getHeight())) {
                        this.g.postTranslate((float) ((((double) canvas.getWidth()) - getVideoItem().getVideoSize().getWidth()) / 2.0d), (float) ((((double) canvas.getHeight()) - getVideoItem().getVideoSize().getHeight()) / 2.0d));
                        return;
                    } else if (getVideoItem().getVideoSize().getWidth() / getVideoItem().getVideoSize().getHeight() > ((double) (((float) canvas.getWidth()) / ((float) canvas.getHeight())))) {
                        this.b = (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth());
                        this.c = true;
                        this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()));
                        this.g.postTranslate(0.0f, (float) ((((double) canvas.getHeight()) - ((((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()) * getVideoItem().getVideoSize().getHeight())) / 2.0d));
                        return;
                    } else {
                        this.b = (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight());
                        this.c = false;
                        this.g.postScale((float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                        this.g.postTranslate((float) ((((double) canvas.getWidth()) - ((((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()) * getVideoItem().getVideoSize().getWidth())) / 2.0d), 0.0f);
                        return;
                    }
                case 4:
                    if (getVideoItem().getVideoSize().getWidth() / getVideoItem().getVideoSize().getHeight() > ((double) (((float) canvas.getWidth()) / ((float) canvas.getHeight())))) {
                        this.b = (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth());
                        this.c = true;
                        this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()));
                        this.g.postTranslate(0.0f, (float) ((((double) canvas.getHeight()) - ((((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()) * getVideoItem().getVideoSize().getHeight())) / 2.0d));
                        return;
                    }
                    this.b = (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight());
                    this.c = false;
                    this.g.postScale((float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                    this.g.postTranslate((float) ((((double) canvas.getWidth()) - ((((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()) * getVideoItem().getVideoSize().getWidth())) / 2.0d), 0.0f);
                    return;
                case 5:
                    if (getVideoItem().getVideoSize().getWidth() / getVideoItem().getVideoSize().getHeight() > ((double) (((float) canvas.getWidth()) / ((float) canvas.getHeight())))) {
                        this.b = (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth());
                        this.c = true;
                        this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()));
                        return;
                    }
                    this.b = (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight());
                    this.c = false;
                    this.g.postScale((float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                    return;
                case 6:
                    if (getVideoItem().getVideoSize().getWidth() / getVideoItem().getVideoSize().getHeight() > ((double) (((float) canvas.getWidth()) / ((float) canvas.getHeight())))) {
                        this.b = (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth());
                        this.c = true;
                        this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()));
                        this.g.postTranslate(0.0f, (float) (((double) canvas.getHeight()) - ((((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()) * getVideoItem().getVideoSize().getHeight())));
                        return;
                    }
                    this.b = (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight());
                    this.c = false;
                    this.g.postScale((float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                    this.g.postTranslate((float) (((double) canvas.getWidth()) - ((((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()) * getVideoItem().getVideoSize().getWidth())), 0.0f);
                    return;
                case 7:
                    this.b = Math.max((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                    if (((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth())) <= ((float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()))) {
                        z = false;
                    }
                    this.c = z;
                    this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getHeight()) / getVideoItem().getVideoSize().getHeight()));
                    return;
                default:
                    this.b = (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth());
                    this.c = true;
                    this.g.postScale((float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()), (float) (((double) canvas.getWidth()) / getVideoItem().getVideoSize().getWidth()));
                    return;
            }
        }
    }

    private final void a(SVGADrawerSprite sVGADrawerSprite, ScaleType scaleType) {
        b(sVGADrawerSprite, scaleType);
        c(sVGADrawerSprite, scaleType);
    }

    private final void b(SVGADrawerSprite sVGADrawerSprite, ScaleType scaleType) {
        Canvas canvas = this.a;
        if (canvas != null) {
            Map dynamicImage = this.i.getDynamicImage();
            String imageKey = sVGADrawerSprite.getImageKey();
            if (dynamicImage == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
            }
            Bitmap bitmap = (Bitmap) dynamicImage.get(imageKey);
            if (bitmap == null) {
                dynamicImage = getVideoItem().getImages();
                imageKey = sVGADrawerSprite.getImageKey();
                if (dynamicImage == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
                }
                bitmap = (Bitmap) dynamicImage.get(imageKey);
            }
            if (bitmap != null) {
                this.d.reset();
                this.g.reset();
                this.d.setAntiAlias(getVideoItem().getAntiAlias());
                this.d.setAlpha((int) (sVGADrawerSprite.getFrameEntity().getAlpha() * ((double) 255)));
                a(scaleType);
                this.g.preConcat(sVGADrawerSprite.getFrameEntity().getTransform());
                if (sVGADrawerSprite.getFrameEntity().getMaskPath() != null) {
                    SVGAPath maskPath = sVGADrawerSprite.getFrameEntity().getMaskPath();
                    if (maskPath != null) {
                        canvas.save();
                        this.e.reset();
                        maskPath.buildPath(this.e);
                        this.e.transform(this.g);
                        canvas.clipPath(this.e);
                        this.g.preScale((float) (sVGADrawerSprite.getFrameEntity().getLayout().getWidth() / ((double) bitmap.getWidth())), (float) (sVGADrawerSprite.getFrameEntity().getLayout().getWidth() / ((double) bitmap.getWidth())));
                        canvas.drawBitmap(bitmap, this.g, this.d);
                        canvas.restore();
                    }
                }
                this.g.preScale((float) (sVGADrawerSprite.getFrameEntity().getLayout().getWidth() / ((double) bitmap.getWidth())), (float) (sVGADrawerSprite.getFrameEntity().getLayout().getWidth() / ((double) bitmap.getWidth())));
                canvas.drawBitmap(bitmap, this.g, this.d);
                a(bitmap, sVGADrawerSprite);
            }
        }
    }

    private final void a(Bitmap bitmap, SVGADrawerSprite sVGADrawerSprite) {
        Canvas canvas = this.a;
        if (canvas != null) {
            Map dynamicText = this.i.getDynamicText();
            String imageKey = sVGADrawerSprite.getImageKey();
            if (dynamicText == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
            }
            String str = (String) dynamicText.get(imageKey);
            if (str != null) {
                Map dynamicTextPaint = this.i.getDynamicTextPaint();
                String imageKey2 = sVGADrawerSprite.getImageKey();
                if (dynamicTextPaint == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
                }
                TextPaint textPaint = (TextPaint) dynamicTextPaint.get(imageKey2);
                if (textPaint != null) {
                    Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
                    Canvas canvas2 = new Canvas(createBitmap);
                    textPaint.setAntiAlias(true);
                    Rect rect = new Rect();
                    textPaint.getTextBounds(str, 0, str.length(), rect);
                    canvas2.drawText(str, (float) (((double) (bitmap.getWidth() - rect.width())) / 2.0d), ((((float) (bitmap.getHeight() + 0)) - textPaint.getFontMetrics().bottom) - textPaint.getFontMetrics().top) / ((float) 2), textPaint);
                    if (sVGADrawerSprite.getFrameEntity().getMaskPath() != null) {
                        SVGAPath maskPath = sVGADrawerSprite.getFrameEntity().getMaskPath();
                        if (maskPath != null) {
                            canvas.save();
                            canvas.concat(this.g);
                            canvas.clipRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                            this.d.setShader(new BitmapShader(createBitmap, TileMode.REPEAT, TileMode.REPEAT));
                            this.d.setAntiAlias(true);
                            this.e.reset();
                            maskPath.buildPath(this.e);
                            canvas.drawPath(this.e, this.d);
                            canvas.restore();
                        }
                    } else {
                        canvas.drawBitmap(createBitmap, this.g, this.d);
                    }
                }
            }
        }
    }

    private final void c(SVGADrawerSprite sVGADrawerSprite, ScaleType scaleType) {
        Canvas canvas = this.a;
        if (canvas != null) {
            this.g.reset();
            a(scaleType);
            this.g.preConcat(sVGADrawerSprite.getFrameEntity().getTransform());
            for (SVGAVideoShapeEntity sVGAVideoShapeEntity : sVGADrawerSprite.getFrameEntity().getShapes()) {
                this.e.reset();
                sVGAVideoShapeEntity.buildPath();
                Path shapePath = sVGAVideoShapeEntity.getShapePath();
                if (shapePath != null) {
                    this.e.addPath(shapePath);
                }
                if (!this.e.isEmpty()) {
                    Matrix matrix = new Matrix();
                    Matrix transform = sVGAVideoShapeEntity.getTransform();
                    if (transform != null) {
                        matrix.postConcat(transform);
                    }
                    matrix.postConcat(this.g);
                    this.e.transform(matrix);
                    Styles styles = sVGAVideoShapeEntity.getStyles();
                    if (styles != null) {
                        int fill = styles.getFill();
                        if (fill != 0) {
                            this.d.reset();
                            this.d.setColor(fill);
                            this.d.setAlpha((int) (sVGADrawerSprite.getFrameEntity().getAlpha() * ((double) 255)));
                            this.d.setAntiAlias(true);
                            if (sVGADrawerSprite.getFrameEntity().getMaskPath() != null) {
                                canvas.save();
                            }
                            SVGAPath maskPath = sVGADrawerSprite.getFrameEntity().getMaskPath();
                            if (maskPath != null) {
                                this.f.reset();
                                maskPath.buildPath(this.f);
                                this.f.transform(this.g);
                                canvas.clipPath(this.f);
                            }
                            canvas.drawPath(this.e, this.d);
                            if (sVGADrawerSprite.getFrameEntity().getMaskPath() != null) {
                                canvas.restore();
                            }
                        }
                    }
                    styles = sVGAVideoShapeEntity.getStyles();
                    if (styles != null) {
                        if (styles.getStrokeWidth() > ((float) 0)) {
                            this.d.reset();
                            this.d.setAlpha((int) (sVGADrawerSprite.getFrameEntity().getAlpha() * ((double) 255)));
                            a(sVGAVideoShapeEntity);
                            if (sVGADrawerSprite.getFrameEntity().getMaskPath() != null) {
                                canvas.save();
                            }
                            SVGAPath maskPath2 = sVGADrawerSprite.getFrameEntity().getMaskPath();
                            if (maskPath2 != null) {
                                this.f.reset();
                                maskPath2.buildPath(this.f);
                                this.f.transform(this.g);
                                canvas.clipPath(this.f);
                            }
                            canvas.drawPath(this.e, this.d);
                            if (sVGADrawerSprite.getFrameEntity().getMaskPath() != null) {
                                canvas.restore();
                            }
                        }
                    }
                }
            }
        }
    }

    private final float a() {
        this.g.getValues(this.h);
        if (this.h[0] == 0.0f) {
            return 0.0f;
        }
        double d = (double) this.h[0];
        double d2 = (double) this.h[3];
        double d3 = (double) this.h[1];
        double d4 = (double) this.h[4];
        if (d * d4 == d2 * d3) {
            return 0.0f;
        }
        double sqrt = Math.sqrt((d * d) + (d2 * d2));
        d /= sqrt;
        d2 /= sqrt;
        double d5 = (d * d3) + (d2 * d4);
        d3 -= d * d5;
        d4 -= d2 * d5;
        double sqrt2 = Math.sqrt((d3 * d3) + (d4 * d4));
        d5 /= sqrt2;
        if (d * (d4 / sqrt2) < d2 * (d3 / sqrt2)) {
            sqrt = -sqrt;
        }
        return this.c ? this.b / Math.abs((float) sqrt) : this.b / Math.abs((float) sqrt2);
    }

    private final void a(SVGAVideoShapeEntity sVGAVideoShapeEntity) {
        String lineCap;
        float f = 1.0f;
        this.d.reset();
        this.d.setAntiAlias(true);
        this.d.setStyle(Style.STROKE);
        Styles styles = sVGAVideoShapeEntity.getStyles();
        if (styles != null) {
            this.d.setColor(styles.getStroke());
        }
        styles = sVGAVideoShapeEntity.getStyles();
        if (styles != null) {
            this.d.setStrokeWidth(styles.getStrokeWidth() * a());
        }
        styles = sVGAVideoShapeEntity.getStyles();
        if (styles != null) {
            lineCap = styles.getLineCap();
            if (lineCap != null) {
                if (t.equals(lineCap, "butt", true)) {
                    this.d.setStrokeCap(Cap.BUTT);
                } else if (t.equals(lineCap, "round", true)) {
                    this.d.setStrokeCap(Cap.ROUND);
                } else if (t.equals(lineCap, "square", true)) {
                    this.d.setStrokeCap(Cap.SQUARE);
                }
            }
        }
        styles = sVGAVideoShapeEntity.getStyles();
        if (styles != null) {
            lineCap = styles.getLineJoin();
            if (lineCap != null) {
                if (t.equals(lineCap, "miter", true)) {
                    this.d.setStrokeJoin(Join.MITER);
                } else if (t.equals(lineCap, "round", true)) {
                    this.d.setStrokeJoin(Join.ROUND);
                } else if (t.equals(lineCap, "bevel", true)) {
                    this.d.setStrokeJoin(Join.BEVEL);
                }
            }
        }
        styles = sVGAVideoShapeEntity.getStyles();
        if (styles != null) {
            this.d.setStrokeMiter(((float) styles.getMiterLimit()) * a());
        }
        styles = sVGAVideoShapeEntity.getStyles();
        if (styles != null) {
            float[] lineDash = styles.getLineDash();
            if (lineDash != null) {
                if (lineDash.length == 3) {
                    Paint paint = this.d;
                    float[] fArr = new float[2];
                    if (lineDash[0] >= 1.0f) {
                        f = lineDash[0];
                    }
                    fArr[0] = f * a();
                    fArr[1] = (lineDash[1] < 0.1f ? 0.1f : lineDash[1]) * a();
                    paint.setPathEffect(new DashPathEffect(fArr, lineDash[2] * a()));
                }
            }
        }
    }
}
