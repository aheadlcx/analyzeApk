package cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo;

import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.NonNull;

public class a {
    private float[] a = new float[2];
    private float[] b = new float[2];
    private float[] c = new float[2];
    private float[] d = new float[2];
    private float[] e = new float[2];
    private float[] f = new float[2];
    private float[] g = new float[2];
    private float[] h = new float[2];
    private float[] i = new float[2];
    private float[] j = new float[2];
    private float[] k = new float[2];
    private float[] l = new float[2];
    private float[] m = new float[2];
    private float[] n = new float[2];
    private float[] o = new float[2];
    private float[] p = new float[2];

    public static class a {
        public float[] a;
        public short[] b;
        public int c = 0;
        public int d = 0;

        public a(@NonNull float[] fArr, @NonNull short[] sArr) {
            this.a = fArr;
            this.b = sArr;
        }
    }

    @NonNull
    public a a(@NonNull RectF rectF, @NonNull RectF rectF2, @NonNull Point point) {
        return a(rectF, rectF2, point, 0.0f);
    }

    @NonNull
    public a a(@NonNull RectF rectF, @NonNull RectF rectF2, @NonNull Point point, float f) {
        float f2 = rectF2.left;
        float f3 = rectF2.right;
        float f4 = rectF2.bottom;
        float f5 = rectF2.top;
        float f6 = rectF.left;
        float f7 = rectF.top;
        float f8 = rectF.right;
        float f9 = rectF.bottom;
        this.m[0] = (f6 / ((float) point.x)) * rectF2.width();
        this.m[1] = (f6 / ((float) point.y)) * (-rectF2.height());
        this.n[0] = (f7 / ((float) point.x)) * rectF2.width();
        this.n[1] = (f7 / ((float) point.y)) * (-rectF2.height());
        this.o[0] = (f8 / ((float) point.x)) * rectF2.width();
        this.o[1] = (f8 / ((float) point.y)) * (-rectF2.height());
        this.p[0] = (f9 / ((float) point.x)) * rectF2.width();
        this.p[1] = (f9 / ((float) point.y)) * (-rectF2.height());
        this.a[0] = f2;
        this.a[1] = f5 - this.m[1];
        this.b[0] = f2;
        this.b[1] = this.p[1] + f4;
        this.c[0] = this.m[0] + f2;
        this.c[1] = f5;
        this.d[0] = f3 - this.n[0];
        this.d[1] = f5;
        this.e[0] = f3;
        this.e[1] = f5 - this.n[1];
        this.f[0] = f3;
        this.f[1] = this.o[1] + f4;
        this.g[0] = f2 + this.p[0];
        this.g[1] = f4;
        this.h[0] = f3 - this.o[0];
        this.h[1] = f4;
        this.i[0] = this.c[0];
        this.i[1] = this.a[1];
        this.j[0] = this.d[0];
        this.j[1] = this.e[1];
        this.l[0] = this.g[0];
        this.l[1] = this.b[1];
        this.k[0] = this.h[0];
        this.k[1] = this.f[1];
        float[] fArr = new float[260];
        short[] sArr = new short[102];
        a aVar = new a(fArr, sArr);
        a(aVar, new float[][]{this.i, this.j, this.l, this.k}, rectF2, f);
        aVar.c += 20;
        aVar.d += 6;
        a(aVar, new float[][]{this.a, this.i, this.b, this.l}, rectF2, f);
        aVar.c += 20;
        aVar.d += 6;
        a(aVar, new float[][]{this.j, this.e, this.k, this.f}, rectF2, f);
        aVar.c += 20;
        aVar.d += 6;
        a(aVar, new float[][]{this.c, this.i, this.d, this.j}, rectF2, f);
        aVar.c += 20;
        aVar.d += 6;
        a(aVar, new float[][]{this.l, this.g, this.k, this.h}, rectF2, f);
        aVar.c += 20;
        aVar.d += 6;
        a(aVar, this.i, this.m, 3.1415927f, 1.5707964f, 6, rectF2, f);
        aVar.c += 40;
        aVar.d += 18;
        a(aVar, this.j, this.n, 1.5707964f, 0.0f, 6, rectF2, f);
        aVar.c += 40;
        aVar.d += 18;
        a(aVar, this.k, this.o, 4.712389f, 6.2831855f, 6, rectF2, f);
        aVar.c += 40;
        aVar.d += 18;
        a(aVar, this.l, this.p, 3.1415927f, 4.712389f, 6, rectF2, f);
        return new a(fArr, sArr);
    }

    private void a(@NonNull a aVar, @NonNull float[][] fArr, @NonNull RectF rectF, float f) {
        float[] fArr2 = aVar.a;
        short[] sArr = aVar.b;
        int i = aVar.d;
        int i2 = aVar.c;
        int i3 = 0;
        for (float[] fArr3 : fArr) {
            int i4 = (i3 * 5) + i2;
            fArr2[i4 + 0] = fArr3[0];
            fArr2[i4 + 1] = fArr3[1];
            fArr2[i4 + 2] = f;
            fArr2[i4 + 3] = (fArr3[0] - rectF.left) / rectF.width();
            fArr2[i4 + 4] = (fArr3[1] - rectF.bottom) / (-rectF.height());
            i3++;
        }
        int i5 = i2 / 5;
        sArr[i + 0] = (short) i5;
        sArr[i + 1] = (short) (i5 + 1);
        sArr[i + 2] = (short) (i5 + 2);
        sArr[i + 3] = (short) (i5 + 1);
        sArr[i + 4] = (short) (i5 + 2);
        sArr[i + 5] = (short) (i5 + 3);
    }

    private void a(@NonNull a aVar, @NonNull float[] fArr, float[] fArr2, float f, float f2, int i, @NonNull RectF rectF, float f3) {
        float[] fArr3 = aVar.a;
        short[] sArr = aVar.b;
        int i2 = aVar.c;
        int i3 = aVar.d;
        int i4 = 0;
        while (i4 < i) {
            int i5;
            int i6 = (i2 + (i4 * 5)) + (i4 > 0 ? 10 : 0);
            float f4 = ((f2 - f) * (((float) i4) / ((float) i))) + f;
            float f5 = ((f2 - f) * (((float) (i4 + 1)) / ((float) i))) + f;
            if (i4 == 0) {
                fArr3[i6 + 0] = fArr[0];
                fArr3[i6 + 1] = fArr[1];
                fArr3[i6 + 2] = f3;
                fArr3[i6 + 3] = (fArr3[i6 + 0] - rectF.left) / rectF.width();
                fArr3[i6 + 4] = (fArr3[i6 + 1] - rectF.bottom) / (-rectF.height());
                fArr3[i6 + 5] = fArr[0] + (fArr2[0] * ((float) Math.cos((double) f4)));
                fArr3[i6 + 6] = (((float) Math.sin((double) f4)) * fArr2[1]) + fArr[1];
                fArr3[i6 + 7] = f3;
                fArr3[i6 + 8] = (fArr3[i6 + 5] - rectF.left) / rectF.width();
                fArr3[i6 + 9] = (fArr3[i6 + 6] - rectF.bottom) / (-rectF.height());
                i5 = 10;
            } else {
                i5 = 0;
            }
            i5 += i6;
            fArr3[i5 + 0] = fArr[0] + (fArr2[0] * ((float) Math.cos((double) f5)));
            fArr3[i5 + 1] = (((float) Math.sin((double) f5)) * fArr2[1]) + fArr[1];
            fArr3[i5 + 2] = f3;
            fArr3[i5 + 3] = (fArr3[i5 + 0] - rectF.left) / rectF.width();
            fArr3[i5 + 4] = (fArr3[i5 + 1] - rectF.bottom) / (-rectF.height());
            i5 = i2 / 5;
            sArr[((i4 * 3) + i3) + 0] = (short) i5;
            sArr[((i4 * 3) + i3) + 1] = (short) ((i5 + i4) + 1);
            sArr[((i4 * 3) + i3) + 2] = (short) ((i5 + i4) + 2);
            i4++;
        }
    }
}
