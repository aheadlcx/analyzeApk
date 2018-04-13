package cn.v6.sixrooms.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.animation.GiftAnimationManager.IAnimation;
import cn.v6.sixrooms.bitmap.AnimationBitmapManager;
import cn.v6.sixrooms.utils.DisPlayUtil;
import java.lang.reflect.Array;
import java.util.Arrays;

public class RedPackageAnimation implements IAnimation {
    private static Bitmap a = null;
    public static float[][] alphaArray = new float[][]{new float[]{0.0f, 1.0f}, new float[]{1.0f, 1.0f}, new float[]{1.0f, 0.0f}};
    public static final int animationDuration = 1200;
    private static float[] b = null;
    private static int[][] c = null;
    private static int d = 0;
    private static int e = 0;
    private static float f = 1.0f;
    private static int g = -1;
    private static int h = 120;
    private static float i = 1.1f;
    private static int j = 0;
    public static int[] moveY = null;
    public static float[][] scaleArray = new float[][]{new float[]{0.0f, 1.0f}};
    public static int[][] scaleTimeArray = new int[][]{new int[]{0, 500}};
    public static int[] startX;
    public static int[] startY;
    public int[][][] alphaTimeArray;
    public float[] degreeArray;
    public float[][] endAcc;
    public float[][] endSpeed;
    public boolean isEndSlowDown = false;
    private float k = 1.0f;
    private boolean l = false;
    private int m = 0;
    public int[][][] moveTimeArray = null;
    public float[][] rotateArray;
    public int[][] xArray;
    public int[][] yArray;

    public static void setOffset(int i) {
        j = i;
        initAnimationInfo(V6Coop.getInstance().getContext());
    }

    public static void initAnimationInfo(Context context) {
        if (context != null) {
            int i;
            int i2;
            if (context.getResources().getConfiguration().orientation == 1) {
                i = context.getResources().getDisplayMetrics().widthPixels;
                i2 = context.getResources().getDisplayMetrics().heightPixels;
            } else {
                i = context.getResources().getDisplayMetrics().heightPixels;
                i2 = context.getResources().getDisplayMetrics().widthPixels;
            }
            f = ((float) i) / 720.0f;
            a = AnimationBitmapManager.getBitmapById(context, g, R.drawable.pic_red_package);
            startX = new int[]{(i - (context.getResources().getDimensionPixelSize(R.dimen.full_screen_red_width) / 2)) - context.getResources().getDimensionPixelSize(R.dimen.portrait_full_screen_red_margin_right), (i2 - (context.getResources().getDimensionPixelSize(R.dimen.full_screen_red_width) / 2)) - context.getResources().getDimensionPixelSize(R.dimen.landscape_full_screen_red_margin_right)};
            startY = new int[]{(((i2 - DisPlayUtil.getStatusHeight(context)) - context.getResources().getDimensionPixelSize(R.dimen.full_screen_red_height)) - context.getResources().getDimensionPixelSize(R.dimen.full_screen_red_margin_bottom)) - j, (i - context.getResources().getDimensionPixelSize(R.dimen.full_screen_red_height)) - context.getResources().getDimensionPixelSize(R.dimen.full_screen_red_margin_bottom)};
            moveY = new int[]{i2 / 4, (i * 2) / 5};
            g = AnimationBitmapManager.getModelId();
            if (b == null) {
                b = new float[]{115.0f, 105.0f, 95.0f, 80.0f, 98.0f, 94.0f, 89.0f, 98.0f, 94.0f, 111.0f, 102.0f, 83.0f, 88.0f, 98.0f, 96.0f, 98.0f, 96.0f, 85.0f, 87.0f, 103.0f, 100.0f, 115.0f, 108.0f, 102.0f, 86.0f, 92.0f, 96.0f, 88.0f, 91.0f, 93.0f, 91.0f, 97.0f, 84.0f, 80.0f, 84.0f, 98.0f, 92.0f, 100.0f, 93.0f, 85.0f, 102.0f, 100.0f, 96.0f, 84.0f, 95.0f, 96.0f, 98.0f, 97.0f, 103.0f, 91.0f, 93.0f, 97.0f, 75.0f, 86.0f, 92.0f, 105.0f};
                c = (int[][]) Array.newInstance(Integer.TYPE, new int[]{moveY.length, 56});
                for (i2 = 0; i2 < moveY.length; i2++) {
                    c[i2] = new int[]{(moveY[i2] * 1) / 8, (moveY[i2] * 1) / 6, (moveY[i2] * 1) / 2, (moveY[i2] * 1) / 8, (moveY[i2] * 1) / 8, moveY[i2] / 3, (moveY[i2] * 1) / 8, (moveY[i2] * 1) / 8, (moveY[i2] * 1) / 11, moveY[i2] / 2, (moveY[i2] * 1) / 8, (moveY[i2] * 1) / 3, (moveY[i2] * 1) / 6, moveY[i2] / 2, (moveY[i2] * 1) / 8, moveY[i2] / 3, (moveY[i2] * 1) / 6, moveY[i2] / 3, (moveY[i2] * 1) / 8, (moveY[i2] * 1) / 7, (moveY[i2] * 1) / 6, (moveY[i2] * 5) / 10, (moveY[i2] * 1) / 10, moveY[i2] / 3, moveY[i2] / 6, moveY[i2] / 2, (moveY[i2] * 1) / 9, moveY[i2] / 3, (moveY[i2] * 1) / 6, moveY[i2] / 6, (moveY[i2] * 1) / 3, (moveY[i2] * 1) / 3, (moveY[i2] * 1) / 6, moveY[i2] / 4, moveY[i2] / 7, moveY[i2] / 3, (moveY[i2] * 3) / 10, (moveY[i2] * 2) / 10, moveY[i2] / 6, (moveY[i2] * 1) / 7, (moveY[i2] * 1) / 3, (moveY[i2] * 1) / 8, moveY[i2] / 6, (moveY[i2] * 1) / 3, (moveY[i2] * 1) / 6, moveY[i2] / 5, (moveY[i2] * 1) / 5, (moveY[i2] * 2) / 7, (moveY[i2] * 3) / 7, (moveY[i2] * 1) / 6, (moveY[i2] * 3) / 10, moveY[i2] / 3, moveY[i2] / 6, moveY[i2] / 2, (moveY[i2] * 1) / 7};
                }
            }
        }
    }

    public static void clearCache() {
        AnimationBitmapManager.recycleModelBitmap(g);
    }

    public int getRunTime() {
        return this.m;
    }

    public void draw(Context context, Canvas canvas, int i) {
        if (this.m >= h + 1200) {
            this.l = true;
            return;
        }
        Paint paint = new Paint();
        paint.setAlpha((int) (c(this, this.m, i) * 255.0f));
        paint.setFlags(1);
        paint.setAntiAlias(true);
        Matrix matrix = new Matrix();
        float a = a(this, this.m);
        matrix.postScale(f * a, a * f);
        matrix.postRotate((float) b(this, this.m, i), (float) (a.getWidth() / 2), (float) (a.getHeight() / 2));
        int[] a2 = a(this, this.m, i);
        matrix.postTranslate((float) a2[0], (float) a2[1]);
        canvas.drawBitmap(a, matrix, paint);
        this.m = (int) (((float) this.m) + (30.0f * this.k));
    }

    public boolean isFinish() {
        return this.l;
    }

    private static int[] a(RedPackageAnimation redPackageAnimation, int i, int i2) {
        int i3 = i2 == 4 ? 0 : 1;
        int[] iArr = new int[2];
        int i4 = (int) (((float) i) * redPackageAnimation.k);
        int i5 = 0;
        while (i5 < redPackageAnimation.moveTimeArray[i3].length) {
            int[] iArr2 = redPackageAnimation.moveTimeArray[i3][i5];
            if (i4 < iArr2[0] || i4 >= iArr2[1]) {
                i5++;
            } else {
                int i6;
                int i7;
                if (i5 > 0) {
                    int i8 = 0;
                    i6 = 0;
                    i7 = 0;
                    while (i8 < i5) {
                        int i9 = redPackageAnimation.xArray[i3][i8] + i7;
                        i7 = redPackageAnimation.yArray[i3][i8] + i6;
                        i8++;
                        i6 = i7;
                        i7 = i9;
                    }
                } else {
                    i6 = 0;
                    i7 = 0;
                }
                if (i5 == redPackageAnimation.moveTimeArray[0].length - 1 && redPackageAnimation.isEndSlowDown) {
                    iArr[1] = (int) (((-(((float) (i4 - iArr2[0])) * (redPackageAnimation.endSpeed[i3][1] + ((redPackageAnimation.endAcc[i3][1] * ((float) (i4 - iArr2[0]))) / 2.0f)))) + ((float) startY[i3])) - ((float) i6));
                    iArr[0] = (int) ((((float) startX[i3]) + (((float) (i4 - iArr2[0])) * (redPackageAnimation.endSpeed[i3][0] + ((redPackageAnimation.endAcc[i3][0] * ((float) (i4 - iArr2[0]))) / 2.0f)))) + ((float) i7));
                } else {
                    iArr[1] = ((((-redPackageAnimation.yArray[i3][i5]) * (i4 - iArr2[0])) / (iArr2[1] - iArr2[0])) + startY[i3]) - i6;
                    iArr[0] = (startX[i3] + ((redPackageAnimation.xArray[i3][i5] * (i4 - iArr2[0])) / (iArr2[1] - iArr2[0]))) + i7;
                }
                return iArr;
            }
        }
        return iArr;
    }

    private static double b(RedPackageAnimation redPackageAnimation, int i, int i2) {
        int i3 = i2 == 4 ? 0 : 1;
        int i4 = (int) (((float) i) * redPackageAnimation.k);
        int i5 = 0;
        while (i5 < redPackageAnimation.moveTimeArray[i3].length) {
            int[] iArr = redPackageAnimation.moveTimeArray[i3][i5];
            if (i4 < iArr[0] || i4 >= iArr[1]) {
                i5++;
            } else {
                float f = redPackageAnimation.rotateArray[i5][0];
                float f2 = redPackageAnimation.rotateArray[i5][1];
                if (f > 270.0f) {
                    if (f2 <= 270.0f) {
                        f += (((f2 + 360.0f) - f) * ((float) (i4 - iArr[0]))) / ((float) (iArr[1] - iArr[0]));
                        if (f >= 360.0f) {
                            f -= 360.0f;
                        }
                    }
                    f += ((f2 - f) * ((float) (i4 - iArr[0]))) / ((float) (iArr[1] - iArr[0]));
                } else {
                    if (f2 > 270.0f) {
                        f -= (((360.0f - f2) + f) * ((float) (i4 - iArr[0]))) / ((float) (iArr[1] - iArr[0]));
                        if (f < 0.0f) {
                            f += 360.0f;
                        }
                    }
                    f += ((f2 - f) * ((float) (i4 - iArr[0]))) / ((float) (iArr[1] - iArr[0]));
                }
                return (double) f;
            }
        }
        return 0.0d;
    }

    private static float a(RedPackageAnimation redPackageAnimation, int i) {
        float f = 1.0f;
        int i2 = (int) (((float) i) * redPackageAnimation.k);
        for (int i3 = 0; i3 < scaleTimeArray.length; i3++) {
            int[] iArr = scaleTimeArray[i3];
            for (int i4 = 0; i4 < iArr.length; i4++) {
                if (i2 >= iArr[0] && i2 < iArr[1]) {
                    f = scaleArray[i3][0] + (((scaleArray[i3][1] - scaleArray[i3][0]) * ((float) (i2 - iArr[0]))) / ((float) (iArr[1] - iArr[0])));
                    break;
                }
            }
        }
        return f;
    }

    private static float c(RedPackageAnimation redPackageAnimation, int i, int i2) {
        int i3;
        if (i2 == 4) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        int i4 = (int) (((float) i) * redPackageAnimation.k);
        float f = 1.0f;
        for (int i5 = 0; i5 < redPackageAnimation.alphaTimeArray[i3].length; i5++) {
            int[] iArr = redPackageAnimation.alphaTimeArray[i3][i5];
            for (int i6 = 0; i6 < iArr.length; i6++) {
                if (i4 >= iArr[0] && i4 < iArr[1]) {
                    f = alphaArray[i5][0] + (((alphaArray[i5][1] - alphaArray[i5][0]) * ((float) (i4 - iArr[0]))) / ((float) (iArr[1] - iArr[0])));
                    break;
                }
            }
        }
        return f;
    }

    public static RedPackageAnimation getInitRedPackageAnimation(float f) {
        int i;
        if (((d * 4) + 4) - 1 >= b.length) {
            d = 0;
        }
        float[] copyOfRange = Arrays.copyOfRange(b, d * 4, (d * 4) + 4);
        d++;
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{moveY.length, 4});
        if (((e * 4) + 4) - 1 >= c[0].length) {
            e = 0;
        }
        for (int i2 = 0; i2 < moveY.length; i2++) {
            System.arraycopy(c[i2], e * 4, iArr[i2], 0, 4);
        }
        e++;
        RedPackageAnimation redPackageAnimation = new RedPackageAnimation();
        redPackageAnimation.k = f;
        redPackageAnimation.degreeArray = copyOfRange;
        redPackageAnimation.yArray = iArr;
        redPackageAnimation.xArray = (int[][]) Array.newInstance(Integer.TYPE, new int[]{moveY.length, 4});
        redPackageAnimation.moveTimeArray = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{2, copyOfRange.length, 2});
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{moveY.length, copyOfRange.length});
        float[] fArr2 = new float[moveY.length];
        redPackageAnimation.rotateArray = (float[][]) Array.newInstance(Float.TYPE, new int[]{copyOfRange.length, 2});
        redPackageAnimation.alphaTimeArray = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{2, 3, 2});
        for (i = 0; i < copyOfRange.length; i++) {
            float f2;
            float f3 = copyOfRange[i];
            if (redPackageAnimation.degreeArray[i] <= 90.0f) {
                f2 = 90.0f - redPackageAnimation.degreeArray[i];
            } else {
                f2 = 360.0f - (redPackageAnimation.degreeArray[i] - 90.0f);
            }
            if (i == 0) {
                redPackageAnimation.rotateArray[i][0] = f2;
                redPackageAnimation.rotateArray[i][1] = f2;
            } else {
                redPackageAnimation.rotateArray[i][0] = redPackageAnimation.rotateArray[i - 1][1];
                redPackageAnimation.rotateArray[i][1] = f2;
            }
            for (int i3 = 0; i3 < moveY.length; i3++) {
                if (f3 > 90.0f) {
                    fArr[i3][i] = (float) (((double) iArr[i3][i]) / Math.sin(Math.toRadians((double) (180.0f - f3))));
                    fArr2[i3] = fArr2[i3] + fArr[i3][i];
                    redPackageAnimation.xArray[i3][i] = (int) (((double) (-fArr[i3][i])) / Math.tan(Math.toRadians((double) (180.0f - f3))));
                } else {
                    fArr[i3][i] = (float) (((double) iArr[i3][i]) / Math.sin(Math.toRadians((double) f3)));
                    fArr2[i3] = fArr2[i3] + fArr[i3][i];
                    redPackageAnimation.xArray[i3][i] = (int) (((double) fArr[i3][i]) / Math.tan(Math.toRadians((double) f3)));
                }
            }
        }
        redPackageAnimation.endSpeed = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 2});
        redPackageAnimation.endAcc = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 2});
        redPackageAnimation.moveTimeArray[0][0][0] = 0;
        redPackageAnimation.moveTimeArray[1][0][0] = 0;
        int[] iArr2 = new int[]{h + 1200, h + 1200};
        redPackageAnimation.endAcc = (float[][]) Array.newInstance(Float.TYPE, new int[]{2, 2});
        for (i = 0; i < moveY.length; i++) {
            for (int i4 = 0; i4 < fArr[i].length; i4++) {
                if (i4 != 0) {
                    redPackageAnimation.moveTimeArray[i][i4][0] = redPackageAnimation.moveTimeArray[i][i4 - 1][1];
                }
                if (i4 == fArr[i].length - 1) {
                    redPackageAnimation.moveTimeArray[i][i4][1] = iArr2[i];
                    if (redPackageAnimation.isEndSlowDown) {
                        int i5 = iArr2[i] - redPackageAnimation.moveTimeArray[i][i4][0];
                        f3 = ((float) redPackageAnimation.xArray[i][redPackageAnimation.xArray.length - 1]) / ((float) (1200 - redPackageAnimation.moveTimeArray[i][redPackageAnimation.xArray.length - 1][0]));
                        float f4 = ((float) redPackageAnimation.yArray[i][redPackageAnimation.yArray.length - 1]) / ((float) (1200 - redPackageAnimation.moveTimeArray[i][redPackageAnimation.xArray.length - 1][0]));
                        redPackageAnimation.endSpeed[i][0] = f3;
                        redPackageAnimation.endSpeed[i][1] = f4;
                        redPackageAnimation.endAcc[i][0] = (float) (((double) ((((float) redPackageAnimation.xArray[i][redPackageAnimation.xArray.length - 1]) - (f3 * ((float) i5))) * 2.0f)) / Math.pow((double) i5, 2.0d));
                        redPackageAnimation.endAcc[i][1] = (float) (((double) ((((float) redPackageAnimation.yArray[i][redPackageAnimation.yArray.length - 1]) - (f4 * ((float) i5))) * 2.0f)) / Math.pow((double) i5, 2.0d));
                    }
                } else {
                    redPackageAnimation.moveTimeArray[i][i4][1] = redPackageAnimation.moveTimeArray[i][i4][0] + ((int) ((fArr[i][i4] * 1200.0f) / fArr2[i]));
                }
            }
        }
        int[][][] iArr3 = redPackageAnimation.alphaTimeArray;
        r4 = new int[3][];
        r4[1] = new int[]{300, iArr2[0] / 2};
        r4[2] = new int[]{iArr2[0] / 2, iArr2[0]};
        iArr3[0] = r4;
        iArr3 = redPackageAnimation.alphaTimeArray;
        r4 = new int[3][];
        r4[1] = new int[]{300, iArr2[1] / 2};
        r4[2] = new int[]{iArr2[1] / 2, iArr2[1]};
        iArr3[1] = r4;
        return redPackageAnimation;
    }
}
