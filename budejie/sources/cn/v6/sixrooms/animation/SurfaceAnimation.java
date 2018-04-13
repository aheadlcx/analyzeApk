package cn.v6.sixrooms.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import cn.v6.sixrooms.animation.GiftAnimationManager.CallBackGiftBitmap;
import cn.v6.sixrooms.bean.BeanAnimation;
import cn.v6.sixrooms.bitmap.AnimationBitmapManager;
import java.util.ArrayList;
import java.util.Iterator;

public class SurfaceAnimation {
    CallBackGiftBitmap a;
    private int b;
    private int c = 0;
    private int d = 0;
    private ArrayList<BeanAnimation> e = null;
    private boolean f = false;

    public SurfaceAnimation(CallBackGiftBitmap callBackGiftBitmap, ArrayList<BeanAnimation> arrayList, int i, int i2) {
        this.a = callBackGiftBitmap;
        this.b = i;
        this.d = i2;
        this.e = arrayList;
    }

    public int getDuration() {
        return this.c;
    }

    public void setDuration(int i) {
        this.c = i;
    }

    public int getTime() {
        return this.b;
    }

    public void setTime(int i) {
        this.b = i;
    }

    public int getModeId() {
        return this.d;
    }

    public CallBackGiftBitmap getCallBack() {
        return this.a;
    }

    public ArrayList<BeanAnimation> getAnimation() {
        return this.e;
    }

    public boolean isFinishAnimation() {
        return this.f;
    }

    public void updateTranslateInfo(int i, int i2) {
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                BeanAnimation beanAnimation = (BeanAnimation) it.next();
                beanAnimation.translateX += i;
                beanAnimation.translateY += i2;
            }
        }
    }

    private static float a(BeanAnimation beanAnimation, int i, int i2) {
        float f = (i2 != 2 || beanAnimation.landScaleW < 0.0f) ? beanAnimation.scaleW : beanAnimation.landScaleW;
        if (beanAnimation.scaleWInfo == null) {
            return f;
        }
        int i3;
        if (beanAnimation.distanceShowInfo > 0) {
            i3 = i / beanAnimation.distanceShowInfo;
        } else {
            i3 = 0;
        }
        int[][] iArr = beanAnimation.scaleWTime;
        int i4 = 0;
        while (i4 < iArr.length) {
            int[] iArr2 = iArr[i4];
            int i5 = iArr2[0] + (beanAnimation.distanceShowInfo * i3);
            int i6 = iArr2[1] + (beanAnimation.distanceShowInfo * i3);
            if (i5 > i || i >= i6) {
                i4++;
            } else {
                f += (((float) (i - i5)) * beanAnimation.scaleWInfo[i4]) / ((float) (iArr2[1] - iArr2[0]));
                if (beanAnimation.scaleWOffset != null) {
                    return f + beanAnimation.scaleWOffset[i4];
                }
                return f;
            }
        }
        return f;
    }

    private static float b(BeanAnimation beanAnimation, int i, int i2) {
        float f = (i2 != 2 || beanAnimation.landScaleH < 0.0f) ? beanAnimation.scaleH : beanAnimation.landScaleH;
        if (beanAnimation.scaleHInfo == null) {
            return f;
        }
        int i3;
        if (beanAnimation.distanceShowInfo > 0) {
            i3 = i / beanAnimation.distanceShowInfo;
        } else {
            i3 = 0;
        }
        int[][] iArr = beanAnimation.scaleHTime;
        int i4 = 0;
        while (i4 < iArr.length) {
            int[] iArr2 = iArr[i4];
            int i5 = iArr2[0] + (beanAnimation.distanceShowInfo * i3);
            int i6 = iArr2[1] + (beanAnimation.distanceShowInfo * i3);
            if (i5 > i || i >= i6) {
                i4++;
            } else {
                f += (((float) (i - i5)) * beanAnimation.scaleHInfo[i4]) / ((float) (iArr2[1] - iArr2[0]));
                if (beanAnimation.scaleHOffset != null) {
                    return f + beanAnimation.scaleHOffset[i4];
                }
                return f;
            }
        }
        return f;
    }

    private static float[] a(BeanAnimation beanAnimation, int i) {
        float[] fArr = new float[2];
        if (beanAnimation.scaleOffsetTime != null) {
            int i2;
            if (beanAnimation.distanceShowInfo > 0) {
                i2 = (i / beanAnimation.distanceShowInfo) * beanAnimation.distanceShowInfo;
            } else {
                i2 = 0;
            }
            int i3 = 0;
            while (i3 < beanAnimation.scaleOffsetTime.length) {
                if (i < beanAnimation.scaleOffsetTime[i3][0] + i2 || i >= beanAnimation.scaleOffsetTime[i3][1] + i2) {
                    i3++;
                } else {
                    if (beanAnimation.scaleDx != null) {
                        fArr[0] = (beanAnimation.scaleDx[i3] * ((float) ((i - i2) - beanAnimation.scaleOffsetTime[i3][0]))) / ((float) (beanAnimation.scaleOffsetTime[i3][1] - beanAnimation.scaleOffsetTime[i3][0]));
                    }
                    if (beanAnimation.scaleDy != null) {
                        fArr[1] = (((float) ((i - i2) - beanAnimation.scaleOffsetTime[i3][0])) * beanAnimation.scaleDy[i3]) / ((float) (beanAnimation.scaleOffsetTime[i3][1] - beanAnimation.scaleOffsetTime[i3][0]));
                    }
                }
            }
        }
        return fArr;
    }

    private void a(Context context, Canvas canvas, BeanAnimation beanAnimation, int i) {
        if (beanAnimation.isShowInLand || i != 2) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int[][] iArr;
            int[] iArr2;
            int i9;
            int[][] iArr3;
            int[] iArr4;
            int i10;
            Paint paint;
            float f;
            Bitmap bitmapById;
            float giftWidth;
            float giftHeight;
            float[] a;
            Matrix matrix;
            String str;
            int i11 = this.c;
            int[][] iArr5 = beanAnimation.drawableTime;
            if (iArr5 != null) {
                i2 = 0;
                if (beanAnimation.distanceShowInfo > 0) {
                    i2 = i11 / beanAnimation.distanceShowInfo;
                }
                for (i3 = 0; i3 < iArr5.length; i3++) {
                    int[] iArr6 = iArr5[i3];
                    i4 = iArr6[0] + (beanAnimation.distanceShowInfo * i2);
                    i5 = iArr6[1] + (beanAnimation.distanceShowInfo * i2);
                    if (i4 <= i11 && i11 < i5) {
                        i2 = beanAnimation.drawableArray[i3][((((i11 - i4) * beanAnimation.drawableArray[i3].length) * beanAnimation.drawableFps) / (iArr6[1] - iArr6[0])) % beanAnimation.drawableArray[i3].length];
                        break;
                    }
                }
                i2 = -1;
            } else {
                i2 = beanAnimation.drawableId;
            }
            int i12 = this.c;
            i11 = beanAnimation.translateX;
            if (beanAnimation.translateXTime != null) {
                i3 = 0;
                if (beanAnimation.distanceShowInfo > 0) {
                    i3 = i12 / beanAnimation.distanceShowInfo;
                }
                int[][] iArr7 = beanAnimation.translateXTime;
                i6 = 0;
                while (i6 < iArr7.length) {
                    int[] iArr8 = iArr7[i6];
                    i5 = iArr8[0] + (beanAnimation.distanceShowInfo * i3);
                    i7 = iArr8[1] + (beanAnimation.distanceShowInfo * i3);
                    if (i5 > i12 || i12 >= i7) {
                        i6++;
                    } else {
                        i3 = (((i12 - i5) * beanAnimation.translateXInfo[i6]) / (iArr8[1] - iArr8[0])) + i11;
                        if (beanAnimation.translateXOffset != null) {
                            i3 += beanAnimation.translateXOffset[i6];
                        }
                        if (i == 2) {
                            i3 += beanAnimation.offsetLandX;
                        }
                        i8 = this.c;
                        i12 = beanAnimation.translateY;
                        if (beanAnimation.translateYTime != null) {
                            i6 = 0;
                            if (beanAnimation.distanceShowInfo > 0) {
                                i6 = i8 / beanAnimation.distanceShowInfo;
                            }
                            iArr = beanAnimation.translateYTime;
                            i11 = 0;
                            while (i11 < iArr.length) {
                                iArr2 = iArr[i11];
                                i7 = iArr2[0] + (beanAnimation.distanceShowInfo * i6);
                                i9 = iArr2[1] + (beanAnimation.distanceShowInfo * i6);
                                if (i7 <= i8 || i8 >= i9) {
                                    i11++;
                                } else {
                                    i6 = (((i8 - i7) * beanAnimation.translateYInfo[i11]) / (iArr2[1] - iArr2[0])) + i12;
                                    if (beanAnimation.translateYOffset != null) {
                                        i6 += beanAnimation.translateYOffset[i11];
                                    }
                                    if (i == 2) {
                                        i12 = i6 + beanAnimation.offsetLandY;
                                    } else {
                                        i12 = i6;
                                    }
                                    i4 = this.c;
                                    i8 = beanAnimation.alpha;
                                    if (beanAnimation.alphaTime != null) {
                                        i6 = 0;
                                        if (beanAnimation.distanceShowInfo > 0) {
                                            i6 = i4 / beanAnimation.distanceShowInfo;
                                        }
                                        iArr3 = beanAnimation.alphaTime;
                                        i11 = 0;
                                        while (i11 < iArr3.length) {
                                            iArr4 = iArr3[i11];
                                            i9 = iArr4[0] + (beanAnimation.distanceShowInfo * i6);
                                            i10 = iArr4[1] + (beanAnimation.distanceShowInfo * i6);
                                            if (i9 > i4 || i4 >= i10) {
                                                i11++;
                                            } else if (i8 != 0 || beanAnimation.alphaArray[i11] >= 0) {
                                                i6 = (((i4 - i9) * beanAnimation.alphaArray[i11]) / (iArr4[1] - iArr4[0])) + i8;
                                                i11 = beanAnimation.alphaOffset != null ? i6 + beanAnimation.alphaOffset[i11] : i6;
                                                paint = new Paint();
                                                paint.setAlpha(i11);
                                                paint.setFlags(1);
                                                paint.setAntiAlias(true);
                                                if (i2 > 0) {
                                                    f = beanAnimation.degrees;
                                                    if (TextUtils.isEmpty(beanAnimation.drawableUrl)) {
                                                        bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                                    } else {
                                                        bitmapById = AnimationBitmapManager.getBitmapByUrl(context, this.a, this.d, beanAnimation.drawableUrl);
                                                        if (bitmapById == null) {
                                                            bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                                        }
                                                    }
                                                    if (beanAnimation.mInterfaceAnimationDraw != null) {
                                                        i12 = 0;
                                                        i8 = 0;
                                                        if (i == 2) {
                                                            i12 = beanAnimation.offsetLandX;
                                                            i8 = beanAnimation.offsetLandY;
                                                        }
                                                        beanAnimation.mInterfaceAnimationDraw.draw(canvas, paint, bitmapById, i12, i8, this.c, i);
                                                        return;
                                                    }
                                                    if (beanAnimation.isGift) {
                                                        giftWidth = ((float) AnimationGiftValues.getGiftWidth()) / ((float) bitmapById.getWidth());
                                                        giftHeight = ((float) AnimationGiftValues.getGiftHeight()) / ((float) bitmapById.getHeight());
                                                    } else {
                                                        giftWidth = a(beanAnimation, this.c, i);
                                                        giftHeight = b(beanAnimation, this.c, i);
                                                    }
                                                    if (beanAnimation.scaleInCenter) {
                                                        if (beanAnimation.scaleWInfo != null) {
                                                            i3 = (int) (((float) i3) + ((((float) bitmapById.getWidth()) * (beanAnimation.scaleW - giftWidth)) / 2.0f));
                                                        }
                                                        if (beanAnimation.scaleHInfo != null) {
                                                            i12 = (int) (((float) i12) + ((((float) bitmapById.getHeight()) * (beanAnimation.scaleH - giftHeight)) / 2.0f));
                                                        }
                                                    }
                                                    a = a(beanAnimation, this.c);
                                                    i3 = (int) (((float) i3) + (a[0] * ((float) bitmapById.getWidth())));
                                                    i12 = (int) (((float) i12) + (a[1] * ((float) bitmapById.getHeight())));
                                                    matrix = new Matrix();
                                                    matrix.postScale(giftWidth, giftHeight);
                                                    matrix.postRotate(f, (((float) bitmapById.getWidth()) * giftWidth) / 2.0f, (giftWidth * ((float) bitmapById.getHeight())) / 2.0f);
                                                    matrix.postTranslate((float) i3, (float) i12);
                                                    canvas.drawBitmap(bitmapById, matrix, paint);
                                                } else if (beanAnimation.text != null) {
                                                    f = 0.0f;
                                                    for (i8 = 0; i8 < beanAnimation.text.length; i8++) {
                                                        if (beanAnimation.bold) {
                                                            paint.setTypeface(Typeface.DEFAULT_BOLD);
                                                        }
                                                        paint.setTextSize((float) beanAnimation.fontSize[i8]);
                                                        str = beanAnimation.color[i8];
                                                        if (str.length() > 7) {
                                                            i2 = (Color.parseColor(str) >> 24) + i11;
                                                        } else {
                                                            i2 = i11;
                                                        }
                                                        paint.setColor((i2 << 24) | (Color.parseColor(str) & ViewCompat.MEASURED_SIZE_MASK));
                                                        canvas.drawText(beanAnimation.text[i8], ((float) i3) + f, (float) i12, paint);
                                                        f += paint.measureText(beanAnimation.text[i8]);
                                                    }
                                                }
                                            } else {
                                                i11 = 255;
                                                paint = new Paint();
                                                paint.setAlpha(i11);
                                                paint.setFlags(1);
                                                paint.setAntiAlias(true);
                                                if (i2 > 0) {
                                                    f = beanAnimation.degrees;
                                                    if (TextUtils.isEmpty(beanAnimation.drawableUrl)) {
                                                        bitmapById = AnimationBitmapManager.getBitmapByUrl(context, this.a, this.d, beanAnimation.drawableUrl);
                                                        if (bitmapById == null) {
                                                            bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                                        }
                                                    } else {
                                                        bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                                    }
                                                    if (beanAnimation.mInterfaceAnimationDraw != null) {
                                                        if (beanAnimation.isGift) {
                                                            giftWidth = a(beanAnimation, this.c, i);
                                                            giftHeight = b(beanAnimation, this.c, i);
                                                        } else {
                                                            giftWidth = ((float) AnimationGiftValues.getGiftWidth()) / ((float) bitmapById.getWidth());
                                                            giftHeight = ((float) AnimationGiftValues.getGiftHeight()) / ((float) bitmapById.getHeight());
                                                        }
                                                        if (beanAnimation.scaleInCenter) {
                                                            if (beanAnimation.scaleWInfo != null) {
                                                                i3 = (int) (((float) i3) + ((((float) bitmapById.getWidth()) * (beanAnimation.scaleW - giftWidth)) / 2.0f));
                                                            }
                                                            if (beanAnimation.scaleHInfo != null) {
                                                                i12 = (int) (((float) i12) + ((((float) bitmapById.getHeight()) * (beanAnimation.scaleH - giftHeight)) / 2.0f));
                                                            }
                                                        }
                                                        a = a(beanAnimation, this.c);
                                                        i3 = (int) (((float) i3) + (a[0] * ((float) bitmapById.getWidth())));
                                                        i12 = (int) (((float) i12) + (a[1] * ((float) bitmapById.getHeight())));
                                                        matrix = new Matrix();
                                                        matrix.postScale(giftWidth, giftHeight);
                                                        matrix.postRotate(f, (((float) bitmapById.getWidth()) * giftWidth) / 2.0f, (giftWidth * ((float) bitmapById.getHeight())) / 2.0f);
                                                        matrix.postTranslate((float) i3, (float) i12);
                                                        canvas.drawBitmap(bitmapById, matrix, paint);
                                                    }
                                                    i12 = 0;
                                                    i8 = 0;
                                                    if (i == 2) {
                                                        i12 = beanAnimation.offsetLandX;
                                                        i8 = beanAnimation.offsetLandY;
                                                    }
                                                    beanAnimation.mInterfaceAnimationDraw.draw(canvas, paint, bitmapById, i12, i8, this.c, i);
                                                    return;
                                                } else if (beanAnimation.text != null) {
                                                    f = 0.0f;
                                                    for (i8 = 0; i8 < beanAnimation.text.length; i8++) {
                                                        if (beanAnimation.bold) {
                                                            paint.setTypeface(Typeface.DEFAULT_BOLD);
                                                        }
                                                        paint.setTextSize((float) beanAnimation.fontSize[i8]);
                                                        str = beanAnimation.color[i8];
                                                        if (str.length() > 7) {
                                                            i2 = i11;
                                                        } else {
                                                            i2 = (Color.parseColor(str) >> 24) + i11;
                                                        }
                                                        paint.setColor((i2 << 24) | (Color.parseColor(str) & ViewCompat.MEASURED_SIZE_MASK));
                                                        canvas.drawText(beanAnimation.text[i8], ((float) i3) + f, (float) i12, paint);
                                                        f += paint.measureText(beanAnimation.text[i8]);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i11 = i8;
                                    paint = new Paint();
                                    paint.setAlpha(i11);
                                    paint.setFlags(1);
                                    paint.setAntiAlias(true);
                                    if (i2 > 0) {
                                        f = beanAnimation.degrees;
                                        if (TextUtils.isEmpty(beanAnimation.drawableUrl)) {
                                            bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                        } else {
                                            bitmapById = AnimationBitmapManager.getBitmapByUrl(context, this.a, this.d, beanAnimation.drawableUrl);
                                            if (bitmapById == null) {
                                                bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                            }
                                        }
                                        if (beanAnimation.mInterfaceAnimationDraw != null) {
                                            i12 = 0;
                                            i8 = 0;
                                            if (i == 2) {
                                                i12 = beanAnimation.offsetLandX;
                                                i8 = beanAnimation.offsetLandY;
                                            }
                                            beanAnimation.mInterfaceAnimationDraw.draw(canvas, paint, bitmapById, i12, i8, this.c, i);
                                            return;
                                        }
                                        if (beanAnimation.isGift) {
                                            giftWidth = ((float) AnimationGiftValues.getGiftWidth()) / ((float) bitmapById.getWidth());
                                            giftHeight = ((float) AnimationGiftValues.getGiftHeight()) / ((float) bitmapById.getHeight());
                                        } else {
                                            giftWidth = a(beanAnimation, this.c, i);
                                            giftHeight = b(beanAnimation, this.c, i);
                                        }
                                        if (beanAnimation.scaleInCenter) {
                                            if (beanAnimation.scaleWInfo != null) {
                                                i3 = (int) (((float) i3) + ((((float) bitmapById.getWidth()) * (beanAnimation.scaleW - giftWidth)) / 2.0f));
                                            }
                                            if (beanAnimation.scaleHInfo != null) {
                                                i12 = (int) (((float) i12) + ((((float) bitmapById.getHeight()) * (beanAnimation.scaleH - giftHeight)) / 2.0f));
                                            }
                                        }
                                        a = a(beanAnimation, this.c);
                                        i3 = (int) (((float) i3) + (a[0] * ((float) bitmapById.getWidth())));
                                        i12 = (int) (((float) i12) + (a[1] * ((float) bitmapById.getHeight())));
                                        matrix = new Matrix();
                                        matrix.postScale(giftWidth, giftHeight);
                                        matrix.postRotate(f, (((float) bitmapById.getWidth()) * giftWidth) / 2.0f, (giftWidth * ((float) bitmapById.getHeight())) / 2.0f);
                                        matrix.postTranslate((float) i3, (float) i12);
                                        canvas.drawBitmap(bitmapById, matrix, paint);
                                    } else if (beanAnimation.text != null) {
                                        f = 0.0f;
                                        for (i8 = 0; i8 < beanAnimation.text.length; i8++) {
                                            if (beanAnimation.bold) {
                                                paint.setTypeface(Typeface.DEFAULT_BOLD);
                                            }
                                            paint.setTextSize((float) beanAnimation.fontSize[i8]);
                                            str = beanAnimation.color[i8];
                                            if (str.length() > 7) {
                                                i2 = (Color.parseColor(str) >> 24) + i11;
                                            } else {
                                                i2 = i11;
                                            }
                                            paint.setColor((i2 << 24) | (Color.parseColor(str) & ViewCompat.MEASURED_SIZE_MASK));
                                            canvas.drawText(beanAnimation.text[i8], ((float) i3) + f, (float) i12, paint);
                                            f += paint.measureText(beanAnimation.text[i8]);
                                        }
                                    }
                                }
                            }
                        }
                        i6 = i12;
                        if (i == 2) {
                            i12 = i6;
                        } else {
                            i12 = i6 + beanAnimation.offsetLandY;
                        }
                        i4 = this.c;
                        i8 = beanAnimation.alpha;
                        if (beanAnimation.alphaTime != null) {
                            i6 = 0;
                            if (beanAnimation.distanceShowInfo > 0) {
                                i6 = i4 / beanAnimation.distanceShowInfo;
                            }
                            iArr3 = beanAnimation.alphaTime;
                            i11 = 0;
                            while (i11 < iArr3.length) {
                                iArr4 = iArr3[i11];
                                i9 = iArr4[0] + (beanAnimation.distanceShowInfo * i6);
                                i10 = iArr4[1] + (beanAnimation.distanceShowInfo * i6);
                                if (i9 > i4) {
                                }
                                i11++;
                            }
                        }
                        i11 = i8;
                        paint = new Paint();
                        paint.setAlpha(i11);
                        paint.setFlags(1);
                        paint.setAntiAlias(true);
                        if (i2 > 0) {
                            f = beanAnimation.degrees;
                            if (TextUtils.isEmpty(beanAnimation.drawableUrl)) {
                                bitmapById = AnimationBitmapManager.getBitmapByUrl(context, this.a, this.d, beanAnimation.drawableUrl);
                                if (bitmapById == null) {
                                    bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                                }
                            } else {
                                bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                            }
                            if (beanAnimation.mInterfaceAnimationDraw != null) {
                                if (beanAnimation.isGift) {
                                    giftWidth = a(beanAnimation, this.c, i);
                                    giftHeight = b(beanAnimation, this.c, i);
                                } else {
                                    giftWidth = ((float) AnimationGiftValues.getGiftWidth()) / ((float) bitmapById.getWidth());
                                    giftHeight = ((float) AnimationGiftValues.getGiftHeight()) / ((float) bitmapById.getHeight());
                                }
                                if (beanAnimation.scaleInCenter) {
                                    if (beanAnimation.scaleWInfo != null) {
                                        i3 = (int) (((float) i3) + ((((float) bitmapById.getWidth()) * (beanAnimation.scaleW - giftWidth)) / 2.0f));
                                    }
                                    if (beanAnimation.scaleHInfo != null) {
                                        i12 = (int) (((float) i12) + ((((float) bitmapById.getHeight()) * (beanAnimation.scaleH - giftHeight)) / 2.0f));
                                    }
                                }
                                a = a(beanAnimation, this.c);
                                i3 = (int) (((float) i3) + (a[0] * ((float) bitmapById.getWidth())));
                                i12 = (int) (((float) i12) + (a[1] * ((float) bitmapById.getHeight())));
                                matrix = new Matrix();
                                matrix.postScale(giftWidth, giftHeight);
                                matrix.postRotate(f, (((float) bitmapById.getWidth()) * giftWidth) / 2.0f, (giftWidth * ((float) bitmapById.getHeight())) / 2.0f);
                                matrix.postTranslate((float) i3, (float) i12);
                                canvas.drawBitmap(bitmapById, matrix, paint);
                            }
                            i12 = 0;
                            i8 = 0;
                            if (i == 2) {
                                i12 = beanAnimation.offsetLandX;
                                i8 = beanAnimation.offsetLandY;
                            }
                            beanAnimation.mInterfaceAnimationDraw.draw(canvas, paint, bitmapById, i12, i8, this.c, i);
                            return;
                        } else if (beanAnimation.text != null) {
                            f = 0.0f;
                            for (i8 = 0; i8 < beanAnimation.text.length; i8++) {
                                if (beanAnimation.bold) {
                                    paint.setTypeface(Typeface.DEFAULT_BOLD);
                                }
                                paint.setTextSize((float) beanAnimation.fontSize[i8]);
                                str = beanAnimation.color[i8];
                                if (str.length() > 7) {
                                    i2 = i11;
                                } else {
                                    i2 = (Color.parseColor(str) >> 24) + i11;
                                }
                                paint.setColor((i2 << 24) | (Color.parseColor(str) & ViewCompat.MEASURED_SIZE_MASK));
                                canvas.drawText(beanAnimation.text[i8], ((float) i3) + f, (float) i12, paint);
                                f += paint.measureText(beanAnimation.text[i8]);
                            }
                        }
                    }
                }
            }
            i3 = i11;
            if (i == 2) {
                i3 += beanAnimation.offsetLandX;
            }
            i8 = this.c;
            i12 = beanAnimation.translateY;
            if (beanAnimation.translateYTime != null) {
                i6 = 0;
                if (beanAnimation.distanceShowInfo > 0) {
                    i6 = i8 / beanAnimation.distanceShowInfo;
                }
                iArr = beanAnimation.translateYTime;
                i11 = 0;
                while (i11 < iArr.length) {
                    iArr2 = iArr[i11];
                    i7 = iArr2[0] + (beanAnimation.distanceShowInfo * i6);
                    i9 = iArr2[1] + (beanAnimation.distanceShowInfo * i6);
                    if (i7 <= i8) {
                    }
                    i11++;
                }
            }
            i6 = i12;
            if (i == 2) {
                i12 = i6 + beanAnimation.offsetLandY;
            } else {
                i12 = i6;
            }
            i4 = this.c;
            i8 = beanAnimation.alpha;
            if (beanAnimation.alphaTime != null) {
                i6 = 0;
                if (beanAnimation.distanceShowInfo > 0) {
                    i6 = i4 / beanAnimation.distanceShowInfo;
                }
                iArr3 = beanAnimation.alphaTime;
                i11 = 0;
                while (i11 < iArr3.length) {
                    iArr4 = iArr3[i11];
                    i9 = iArr4[0] + (beanAnimation.distanceShowInfo * i6);
                    i10 = iArr4[1] + (beanAnimation.distanceShowInfo * i6);
                    if (i9 > i4) {
                    }
                    i11++;
                }
            }
            i11 = i8;
            paint = new Paint();
            paint.setAlpha(i11);
            paint.setFlags(1);
            paint.setAntiAlias(true);
            if (i2 > 0) {
                f = beanAnimation.degrees;
                if (TextUtils.isEmpty(beanAnimation.drawableUrl)) {
                    bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                } else {
                    bitmapById = AnimationBitmapManager.getBitmapByUrl(context, this.a, this.d, beanAnimation.drawableUrl);
                    if (bitmapById == null) {
                        bitmapById = AnimationBitmapManager.getBitmapById(context, this.d, i2);
                    }
                }
                if (beanAnimation.mInterfaceAnimationDraw != null) {
                    i12 = 0;
                    i8 = 0;
                    if (i == 2) {
                        i12 = beanAnimation.offsetLandX;
                        i8 = beanAnimation.offsetLandY;
                    }
                    beanAnimation.mInterfaceAnimationDraw.draw(canvas, paint, bitmapById, i12, i8, this.c, i);
                    return;
                }
                if (beanAnimation.isGift) {
                    giftWidth = ((float) AnimationGiftValues.getGiftWidth()) / ((float) bitmapById.getWidth());
                    giftHeight = ((float) AnimationGiftValues.getGiftHeight()) / ((float) bitmapById.getHeight());
                } else {
                    giftWidth = a(beanAnimation, this.c, i);
                    giftHeight = b(beanAnimation, this.c, i);
                }
                if (beanAnimation.scaleInCenter) {
                    if (beanAnimation.scaleWInfo != null) {
                        i3 = (int) (((float) i3) + ((((float) bitmapById.getWidth()) * (beanAnimation.scaleW - giftWidth)) / 2.0f));
                    }
                    if (beanAnimation.scaleHInfo != null) {
                        i12 = (int) (((float) i12) + ((((float) bitmapById.getHeight()) * (beanAnimation.scaleH - giftHeight)) / 2.0f));
                    }
                }
                a = a(beanAnimation, this.c);
                i3 = (int) (((float) i3) + (a[0] * ((float) bitmapById.getWidth())));
                i12 = (int) (((float) i12) + (a[1] * ((float) bitmapById.getHeight())));
                matrix = new Matrix();
                matrix.postScale(giftWidth, giftHeight);
                matrix.postRotate(f, (((float) bitmapById.getWidth()) * giftWidth) / 2.0f, (giftWidth * ((float) bitmapById.getHeight())) / 2.0f);
                matrix.postTranslate((float) i3, (float) i12);
                canvas.drawBitmap(bitmapById, matrix, paint);
            } else if (beanAnimation.text != null) {
                f = 0.0f;
                for (i8 = 0; i8 < beanAnimation.text.length; i8++) {
                    if (beanAnimation.bold) {
                        paint.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                    paint.setTextSize((float) beanAnimation.fontSize[i8]);
                    str = beanAnimation.color[i8];
                    if (str.length() > 7) {
                        i2 = (Color.parseColor(str) >> 24) + i11;
                    } else {
                        i2 = i11;
                    }
                    paint.setColor((i2 << 24) | (Color.parseColor(str) & ViewCompat.MEASURED_SIZE_MASK));
                    canvas.drawText(beanAnimation.text[i8], ((float) i3) + f, (float) i12, paint);
                    f += paint.measureText(beanAnimation.text[i8]);
                }
            }
        }
    }

    public void addOneTime() {
        this.c = (int) (((long) this.c) + 30);
        if (this.c >= this.b) {
            this.f = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.content.Context r11, android.graphics.Canvas r12, int r13) {
        /*
        r10 = this;
        r0 = 0;
        r1 = r10.f;
        if (r1 != 0) goto L_0x0094;
    L_0x0005:
        r1 = r10.e;
        r2 = r1.size();
        r1 = r10.c;	 Catch:{ Exception -> 0x008d }
        if (r1 < 0) goto L_0x0095;
    L_0x000f:
        r1 = r0;
    L_0x0010:
        if (r1 >= r2) goto L_0x0095;
    L_0x0012:
        r0 = r10.e;	 Catch:{ Exception -> 0x008d }
        r0 = r0.get(r1);	 Catch:{ Exception -> 0x008d }
        r0 = (cn.v6.sixrooms.bean.BeanAnimation) r0;	 Catch:{ Exception -> 0x008d }
        r3 = r0.scrollTime;	 Catch:{ Exception -> 0x008d }
        if (r3 == 0) goto L_0x004d;
    L_0x001e:
        r3 = r10.c;	 Catch:{ Exception -> 0x008d }
        r4 = r0.scrollTime;	 Catch:{ Exception -> 0x008d }
        r5 = 0;
        r4 = r4[r5];	 Catch:{ Exception -> 0x008d }
        if (r3 < r4) goto L_0x004d;
    L_0x0027:
        r3 = r10.c;	 Catch:{ Exception -> 0x008d }
        r4 = r0.scrollTime;	 Catch:{ Exception -> 0x008d }
        r5 = 1;
        r4 = r4[r5];	 Catch:{ Exception -> 0x008d }
        if (r3 >= r4) goto L_0x004d;
    L_0x0030:
        r3 = r0.translateY;	 Catch:{ Exception -> 0x008d }
        r4 = (long) r3;	 Catch:{ Exception -> 0x008d }
        r6 = 30;
        r3 = r0.scrollY;	 Catch:{ Exception -> 0x008d }
        r8 = 0;
        r3 = r3[r8];	 Catch:{ Exception -> 0x008d }
        r8 = (long) r3;	 Catch:{ Exception -> 0x008d }
        r6 = r6 * r8;
        r3 = r0.scrollTime;	 Catch:{ Exception -> 0x008d }
        r8 = 1;
        r3 = r3[r8];	 Catch:{ Exception -> 0x008d }
        r8 = r0.scrollTime;	 Catch:{ Exception -> 0x008d }
        r9 = 0;
        r8 = r8[r9];	 Catch:{ Exception -> 0x008d }
        r3 = r3 - r8;
        r8 = (long) r3;	 Catch:{ Exception -> 0x008d }
        r6 = r6 / r8;
        r4 = r4 + r6;
        r3 = (int) r4;	 Catch:{ Exception -> 0x008d }
        r0.translateY = r3;	 Catch:{ Exception -> 0x008d }
    L_0x004d:
        r3 = r0.showTime;	 Catch:{ Exception -> 0x008d }
        if (r3 == 0) goto L_0x006a;
    L_0x0051:
        r3 = r10.c;	 Catch:{ Exception -> 0x008d }
        r4 = r0.showTime;	 Catch:{ Exception -> 0x008d }
        r5 = 0;
        r4 = r4[r5];	 Catch:{ Exception -> 0x008d }
        if (r3 < r4) goto L_0x0066;
    L_0x005a:
        r3 = r10.c;	 Catch:{ Exception -> 0x008d }
        r4 = r0.showTime;	 Catch:{ Exception -> 0x008d }
        r5 = 1;
        r4 = r4[r5];	 Catch:{ Exception -> 0x008d }
        if (r3 >= r4) goto L_0x0066;
    L_0x0063:
        r10.a(r11, r12, r0, r13);	 Catch:{ Exception -> 0x008d }
    L_0x0066:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0010;
    L_0x006a:
        r3 = r0.distanceShowInfo;	 Catch:{ Exception -> 0x008d }
        if (r3 <= 0) goto L_0x0063;
    L_0x006e:
        r3 = r10.c;	 Catch:{ Exception -> 0x008d }
        r4 = r0.distanceShowInfo;	 Catch:{ Exception -> 0x008d }
        r3 = r3 % r4;
        r4 = r0.runTime;	 Catch:{ Exception -> 0x008d }
        if (r3 >= r4) goto L_0x0066;
    L_0x0077:
        r4 = r0.distanceShowTime;	 Catch:{ Exception -> 0x008d }
        if (r4 == 0) goto L_0x0089;
    L_0x007b:
        r4 = r0.distanceShowTime;	 Catch:{ Exception -> 0x008d }
        r5 = 0;
        r4 = r4[r5];	 Catch:{ Exception -> 0x008d }
        if (r3 < r4) goto L_0x0066;
    L_0x0082:
        r4 = r0.distanceShowTime;	 Catch:{ Exception -> 0x008d }
        r5 = 1;
        r4 = r4[r5];	 Catch:{ Exception -> 0x008d }
        if (r3 >= r4) goto L_0x0066;
    L_0x0089:
        r10.a(r11, r12, r0, r13);	 Catch:{ Exception -> 0x008d }
        goto L_0x0066;
    L_0x008d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0099 }
        r10.addOneTime();
    L_0x0094:
        return;
    L_0x0095:
        r10.addOneTime();
        goto L_0x0094;
    L_0x0099:
        r0 = move-exception;
        r10.addOneTime();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.animation.SurfaceAnimation.draw(android.content.Context, android.graphics.Canvas, int):void");
    }
}
