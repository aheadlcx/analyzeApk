package com.opensource.svgaplayer;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.umeng.analytics.pro.b;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.DoubleRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001:\u0001<B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB+\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010+\u001a\u00020*H\u0014J\u0006\u0010,\u001a\u00020*J\b\u0010-\u001a\u00020*H\u0002J\u000e\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u000200J\u0016\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u00020*J\u0016\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\t2\u0006\u00106\u001a\u00020\u0016J\u0016\u00107\u001a\u00020*2\u0006\u00108\u001a\u0002092\u0006\u00106\u001a\u00020\u0016J\u0006\u0010:\u001a\u00020*J\u000e\u0010:\u001a\u00020*2\u0006\u0010;\u001a\u00020\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010\"\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0016@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001a\u0010$\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006="}, d2 = {"Lcom/opensource/svgaplayer/SVGAImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "animator", "Landroid/animation/ValueAnimator;", "callback", "Lcom/opensource/svgaplayer/SVGACallback;", "getCallback", "()Lcom/opensource/svgaplayer/SVGACallback;", "setCallback", "(Lcom/opensource/svgaplayer/SVGACallback;)V", "clearsAfterStop", "", "getClearsAfterStop", "()Z", "setClearsAfterStop", "(Z)V", "fillMode", "Lcom/opensource/svgaplayer/SVGAImageView$FillMode;", "getFillMode", "()Lcom/opensource/svgaplayer/SVGAImageView$FillMode;", "setFillMode", "(Lcom/opensource/svgaplayer/SVGAImageView$FillMode;)V", "<set-?>", "isAnimating", "setAnimating", "loops", "getLoops", "()I", "setLoops", "(I)V", "loadAttrs", "", "onDetachedFromWindow", "pauseAnimation", "setSoftwareLayerType", "setVideoItem", "videoItem", "Lcom/opensource/svgaplayer/SVGAVideoEntity;", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;", "startAnimation", "stepToFrame", "frame", "andPlay", "stepToPercentage", "percentage", "", "stopAnimation", "clear", "FillMode", "library_release"}, k = 1, mv = {1, 1, 6})
public class SVGAImageView extends ImageView {
    private boolean a;
    private int b;
    private boolean c = true;
    @NotNull
    private FillMode d = FillMode.Forward;
    @Nullable
    private SVGACallback e;
    private ValueAnimator f;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/opensource/svgaplayer/SVGAImageView$FillMode;", "", "(Ljava/lang/String;I)V", "Backward", "Forward", "library_release"}, k = 1, mv = {1, 1, 6})
    public enum FillMode {
    }

    private final void setAnimating(boolean z) {
        this.a = z;
    }

    public final boolean isAnimating() {
        return this.a;
    }

    public final int getLoops() {
        return this.b;
    }

    public final void setLoops(int i) {
        this.b = i;
    }

    public final boolean getClearsAfterStop() {
        return this.c;
    }

    public final void setClearsAfterStop(boolean z) {
        this.c = z;
    }

    @NotNull
    public final FillMode getFillMode() {
        return this.d;
    }

    public final void setFillMode(@NotNull FillMode fillMode) {
        Intrinsics.checkParameterIsNotNull(fillMode, "<set-?>");
        this.d = fillMode;
    }

    @Nullable
    public final SVGACallback getCallback() {
        return this.e;
    }

    public final void setCallback(@Nullable SVGACallback sVGACallback) {
        this.e = sVGACallback;
    }

    public SVGAImageView(@Nullable Context context) {
        super(context);
        a();
    }

    public SVGAImageView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        if (attributeSet != null) {
            a(attributeSet);
        }
    }

    public SVGAImageView(@Nullable Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
        if (attributeSet != null) {
            a(attributeSet);
        }
    }

    public SVGAImageView(@Nullable Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
        if (attributeSet != null) {
            a(attributeSet);
        }
    }

    private final void a() {
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.f;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        valueAnimator = this.f;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
    }

    private final void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.SVGAImageView, 0, 0);
        this.b = obtainStyledAttributes.getInt(R.styleable.SVGAImageView_loopCount, 0);
        this.c = obtainStyledAttributes.getBoolean(R.styleable.SVGAImageView_clearsAfterStop, true);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SVGAImageView_antiAlias, true);
        String string = obtainStyledAttributes.getString(R.styleable.SVGAImageView_source);
        if (string != null) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, b.M);
            new Thread(new c(string, new SVGAParser(context), this, z, obtainStyledAttributes)).start();
        }
        String string2 = obtainStyledAttributes.getString(R.styleable.SVGAImageView_fillMode);
        if (string2 != null) {
            if (Intrinsics.areEqual(string2, "0")) {
                this.d = FillMode.Backward;
            } else if (Intrinsics.areEqual(string2, "1")) {
                this.d = FillMode.Forward;
            }
        }
    }

    public final void startAnimation() {
        Drawable drawable = getDrawable();
        if (!(drawable instanceof SVGADrawable)) {
            drawable = null;
        }
        SVGADrawable sVGADrawable = (SVGADrawable) drawable;
        if (sVGADrawable != null) {
            sVGADrawable.setCleared$library_release(false);
            ScaleType scaleType = getScaleType();
            Intrinsics.checkExpressionValueIsNotNull(scaleType, "scaleType");
            sVGADrawable.setScaleType(scaleType);
            SVGAVideoEntity videoItem = sVGADrawable.getVideoItem();
            if (videoItem != null) {
                DoubleRef doubleRef = new DoubleRef();
                doubleRef.element = 1.0d;
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, videoItem.getFrames() - 1});
                try {
                    Class cls = Class.forName("android.animation.ValueAnimator");
                    if (cls != null) {
                        Field declaredField = cls.getDeclaredField("sDurationScale");
                        if (declaredField != null) {
                            declaredField.setAccessible(true);
                            doubleRef.element = (double) declaredField.getFloat(Class.forName("android.animation.ValueAnimator"));
                        }
                    }
                } catch (Exception e) {
                }
                ofInt.setInterpolator(new LinearInterpolator());
                ofInt.setDuration((long) (((double) (videoItem.getFrames() * (1000 / videoItem.getFPS()))) / doubleRef.element));
                ofInt.setRepeatCount(this.b <= 0 ? 99999 : this.b - 1);
                ofInt.addUpdateListener(new f(ofInt, this, sVGADrawable));
                ofInt.addListener(new SVGAImageView$startAnimation$$inlined$let$lambda$2(this, sVGADrawable));
                ofInt.start();
                this.f = ofInt;
            }
        }
    }

    public final void pauseAnimation() {
        stopAnimation(false);
        SVGACallback sVGACallback = this.e;
        if (sVGACallback != null) {
            sVGACallback.onPause();
        }
    }

    public final void stopAnimation() {
        stopAnimation(this.c);
    }

    public final void stopAnimation(boolean z) {
        ValueAnimator valueAnimator = this.f;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        valueAnimator = this.f;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
        Drawable drawable = getDrawable();
        if (!(drawable instanceof SVGADrawable)) {
            drawable = null;
        }
        SVGADrawable sVGADrawable = (SVGADrawable) drawable;
        if (sVGADrawable != null) {
            sVGADrawable.setCleared$library_release(z);
        }
    }

    public final void setVideoItem(@NotNull SVGAVideoEntity sVGAVideoEntity) {
        Intrinsics.checkParameterIsNotNull(sVGAVideoEntity, "videoItem");
        setVideoItem(sVGAVideoEntity, new SVGADynamicEntity());
    }

    public final void setVideoItem(@NotNull SVGAVideoEntity sVGAVideoEntity, @NotNull SVGADynamicEntity sVGADynamicEntity) {
        Intrinsics.checkParameterIsNotNull(sVGAVideoEntity, "videoItem");
        Intrinsics.checkParameterIsNotNull(sVGADynamicEntity, "dynamicItem");
        SVGADrawable sVGADrawable = new SVGADrawable(sVGAVideoEntity, sVGADynamicEntity);
        sVGADrawable.setCleared$library_release(this.c);
        setImageDrawable(sVGADrawable);
    }

    public final void stepToFrame(int i, boolean z) {
        pauseAnimation();
        Drawable drawable = getDrawable();
        if (!(drawable instanceof SVGADrawable)) {
            drawable = null;
        }
        SVGADrawable sVGADrawable = (SVGADrawable) drawable;
        if (sVGADrawable != null) {
            sVGADrawable.setCurrentFrame$library_release(i);
            if (z) {
                startAnimation();
                ValueAnimator valueAnimator = this.f;
                if (valueAnimator != null) {
                    valueAnimator.setCurrentPlayTime((long) (Math.max(0.0f, Math.min(1.0f, ((float) i) / ((float) sVGADrawable.getVideoItem().getFrames()))) * ((float) valueAnimator.getDuration())));
                }
            }
        }
    }

    public final void stepToPercentage(double d, boolean z) {
        Drawable drawable = getDrawable();
        if (!(drawable instanceof SVGADrawable)) {
            drawable = null;
        }
        SVGADrawable sVGADrawable = (SVGADrawable) drawable;
        if (sVGADrawable != null) {
            int i;
            int frames = (int) (((double) sVGADrawable.getVideoItem().getFrames()) * d);
            if (frames < sVGADrawable.getVideoItem().getFrames() || frames <= 0) {
                i = frames;
            } else {
                i = sVGADrawable.getVideoItem().getFrames() - 1;
            }
            stepToFrame(i, z);
        }
    }
}
