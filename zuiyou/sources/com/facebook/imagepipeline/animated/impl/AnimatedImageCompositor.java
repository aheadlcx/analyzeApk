package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.BlendOperation;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.d;

public class AnimatedImageCompositor {
    private final d a;
    private final a b;
    private final Paint c = new Paint();

    public interface a {
        com.facebook.common.references.a<Bitmap> a(int i);

        void a(int i, Bitmap bitmap);
    }

    private enum FrameNeededResult {
        REQUIRED,
        NOT_REQUIRED,
        SKIP,
        ABORT
    }

    public AnimatedImageCompositor(d dVar, a aVar) {
        this.a = dVar;
        this.b = aVar;
        this.c.setColor(0);
        this.c.setStyle(Style.FILL);
        this.c.setXfermode(new PorterDuffXfermode(Mode.SRC));
    }

    public void a(int i, Bitmap bitmap) {
        int i2;
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0, Mode.SRC);
        if (b(i)) {
            i2 = i;
        } else {
            i2 = a(i - 1, canvas);
        }
        while (i2 < i) {
            AnimatedDrawableFrameInfo a = this.a.a(i2);
            DisposalMethod disposalMethod = a.g;
            if (disposalMethod != DisposalMethod.DISPOSE_TO_PREVIOUS) {
                if (a.f == BlendOperation.NO_BLEND) {
                    a(canvas, a);
                }
                this.a.a(i2, canvas);
                this.b.a(i2, bitmap);
                if (disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
                    a(canvas, a);
                }
            }
            i2++;
        }
        AnimatedDrawableFrameInfo a2 = this.a.a(i);
        if (a2.f == BlendOperation.NO_BLEND) {
            a(canvas, a2);
        }
        this.a.a(i, canvas);
    }

    private int a(int i, Canvas canvas) {
        for (int i2 = i; i2 >= 0; i2--) {
            switch (a(i2)) {
                case REQUIRED:
                    AnimatedDrawableFrameInfo a = this.a.a(i2);
                    com.facebook.common.references.a a2 = this.b.a(i2);
                    if (a2 == null) {
                        if (!b(i2)) {
                            break;
                        }
                        return i2;
                    }
                    try {
                        canvas.drawBitmap((Bitmap) a2.a(), 0.0f, 0.0f, null);
                        if (a.g == DisposalMethod.DISPOSE_TO_BACKGROUND) {
                            a(canvas, a);
                        }
                        int i3 = i2 + 1;
                        a2.close();
                        return i3;
                    } catch (Throwable th) {
                        a2.close();
                    }
                case NOT_REQUIRED:
                    return i2 + 1;
                case ABORT:
                    return i2;
                default:
                    break;
            }
        }
        return 0;
    }

    private void a(Canvas canvas, AnimatedDrawableFrameInfo animatedDrawableFrameInfo) {
        canvas.drawRect((float) animatedDrawableFrameInfo.b, (float) animatedDrawableFrameInfo.c, (float) (animatedDrawableFrameInfo.b + animatedDrawableFrameInfo.d), (float) (animatedDrawableFrameInfo.c + animatedDrawableFrameInfo.e), this.c);
    }

    private FrameNeededResult a(int i) {
        AnimatedDrawableFrameInfo a = this.a.a(i);
        DisposalMethod disposalMethod = a.g;
        if (disposalMethod == DisposalMethod.DISPOSE_DO_NOT) {
            return FrameNeededResult.REQUIRED;
        }
        if (disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
            if (a(a)) {
                return FrameNeededResult.NOT_REQUIRED;
            }
            return FrameNeededResult.REQUIRED;
        } else if (disposalMethod == DisposalMethod.DISPOSE_TO_PREVIOUS) {
            return FrameNeededResult.SKIP;
        } else {
            return FrameNeededResult.ABORT;
        }
    }

    private boolean b(int i) {
        if (i == 0) {
            return true;
        }
        AnimatedDrawableFrameInfo a = this.a.a(i);
        AnimatedDrawableFrameInfo a2 = this.a.a(i - 1);
        if (a.f == BlendOperation.NO_BLEND && a(a)) {
            return true;
        }
        if (a2.g == DisposalMethod.DISPOSE_TO_BACKGROUND && a(a2)) {
            return true;
        }
        return false;
    }

    private boolean a(AnimatedDrawableFrameInfo animatedDrawableFrameInfo) {
        return animatedDrawableFrameInfo.b == 0 && animatedDrawableFrameInfo.c == 0 && animatedDrawableFrameInfo.d == this.a.g() && animatedDrawableFrameInfo.e == this.a.h();
    }
}
