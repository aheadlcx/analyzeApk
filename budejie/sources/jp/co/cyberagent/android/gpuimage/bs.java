package jp.co.cyberagent.android.gpuimage;

import android.graphics.Point;
import android.graphics.PointF;
import android.opengl.GLES20;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class bs extends ab {
    private int[] g = new int[]{-1};
    private int h;
    private PointF[] i;
    private PointF[] j;
    private PointF[] k;
    private PointF[] l;
    private ArrayList<Float> m;
    private ArrayList<Float> n;
    private ArrayList<Float> o;
    private ArrayList<Float> p;

    public bs() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n uniform sampler2D inputImageTexture;\n uniform sampler2D toneCurveTexture;\n\n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     lowp float redCurveValue = texture2D(toneCurveTexture, vec2(textureColor.r, 0.0)).r;\n     lowp float greenCurveValue = texture2D(toneCurveTexture, vec2(textureColor.g, 0.0)).g;\n     lowp float blueCurveValue = texture2D(toneCurveTexture, vec2(textureColor.b, 0.0)).b;\n\n     gl_FragColor = vec4(redCurveValue, greenCurveValue, blueCurveValue, textureColor.a);\n }");
        PointF[] pointFArr = new PointF[]{new PointF(0.0f, 0.0f), new PointF(0.5f, 0.5f), new PointF(1.0f, 1.0f)};
        this.i = pointFArr;
        this.j = pointFArr;
        this.k = pointFArr;
        this.l = pointFArr;
    }

    public void a() {
        super.a();
        this.h = GLES20.glGetUniformLocation(l(), "toneCurveTexture");
        GLES20.glActiveTexture(33987);
        GLES20.glGenTextures(1, this.g, 0);
        GLES20.glBindTexture(3553, this.g[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    public void c_() {
        super.c_();
        a(this.i);
        b(this.j);
        c(this.k);
        d(this.l);
    }

    protected void g() {
        if (this.g[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.g[0]);
            GLES20.glUniform1i(this.h, 3);
        }
    }

    public void a(InputStream inputStream) {
        try {
            b(inputStream);
            short b = b(inputStream);
            ArrayList arrayList = new ArrayList(b);
            for (short s = (short) 0; s < b; s++) {
                short b2 = b(inputStream);
                Object obj = new PointF[b2];
                for (short s2 = (short) 0; s2 < b2; s2++) {
                    obj[s2] = new PointF(((float) b(inputStream)) * 0.003921569f, ((float) b(inputStream)) * 0.003921569f);
                }
                arrayList.add(obj);
            }
            inputStream.close();
            this.i = (PointF[]) arrayList.get(0);
            this.j = (PointF[]) arrayList.get(1);
            this.k = (PointF[]) arrayList.get(2);
            this.l = (PointF[]) arrayList.get(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private short b(InputStream inputStream) throws IOException {
        return (short) ((inputStream.read() << 8) | inputStream.read());
    }

    public void a(PointF[] pointFArr) {
        this.i = pointFArr;
        this.m = e(this.i);
        c();
    }

    public void b(PointF[] pointFArr) {
        this.j = pointFArr;
        this.n = e(this.j);
        c();
    }

    public void c(PointF[] pointFArr) {
        this.k = pointFArr;
        this.o = e(this.k);
        c();
    }

    public void d(PointF[] pointFArr) {
        this.l = pointFArr;
        this.p = e(this.l);
        c();
    }

    private void c() {
        a(new Runnable(this) {
            final /* synthetic */ bs a;

            {
                this.a = r1;
            }

            public void run() {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, this.a.g[0]);
                if (this.a.n.size() >= 256 && this.a.o.size() >= 256 && this.a.p.size() >= 256 && this.a.m.size() >= 256) {
                    byte[] bArr = new byte[1024];
                    for (int i = 0; i < 256; i++) {
                        bArr[(i * 4) + 2] = (byte) (((int) Math.min(Math.max(((Float) this.a.m.get(i)).floatValue() + (((float) i) + ((Float) this.a.p.get(i)).floatValue()), 0.0f), 255.0f)) & 255);
                        bArr[(i * 4) + 1] = (byte) (((int) Math.min(Math.max(((Float) this.a.m.get(i)).floatValue() + (((float) i) + ((Float) this.a.o.get(i)).floatValue()), 0.0f), 255.0f)) & 255);
                        bArr[i * 4] = (byte) (((int) Math.min(Math.max(((Float) this.a.m.get(i)).floatValue() + (((float) i) + ((Float) this.a.n.get(i)).floatValue()), 0.0f), 255.0f)) & 255);
                        bArr[(i * 4) + 3] = (byte) -1;
                    }
                    GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
                }
            }
        });
    }

    private ArrayList<Float> e(PointF[] pointFArr) {
        if (pointFArr == null || pointFArr.length <= 0) {
            return null;
        }
        int i;
        PointF[] pointFArr2 = (PointF[]) pointFArr.clone();
        Arrays.sort(pointFArr2, new Comparator<PointF>(this) {
            final /* synthetic */ bs a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((PointF) obj, (PointF) obj2);
            }

            public int a(PointF pointF, PointF pointF2) {
                if (pointF.x < pointF2.x) {
                    return -1;
                }
                if (pointF.x > pointF2.x) {
                    return 1;
                }
                return 0;
            }
        });
        Point[] pointArr = new Point[pointFArr2.length];
        for (int i2 = 0; i2 < pointFArr.length; i2++) {
            PointF pointF = pointFArr2[i2];
            pointArr[i2] = new Point((int) (pointF.x * 255.0f), (int) (pointF.y * 255.0f));
        }
        ArrayList a = a(pointArr);
        Point point = (Point) a.get(0);
        if (point.x > 0) {
            for (i = point.x; i >= 0; i--) {
                a.add(0, new Point(i, 0));
            }
        }
        point = (Point) a.get(a.size() - 1);
        if (point.x < 255) {
            for (i = point.x + 1; i <= 255; i++) {
                a.add(new Point(i, 255));
            }
        }
        ArrayList<Float> arrayList = new ArrayList(a.size());
        Iterator it = a.iterator();
        while (it.hasNext()) {
            float f;
            point = (Point) it.next();
            Point point2 = new Point(point.x, point.x);
            float sqrt = (float) Math.sqrt(Math.pow((double) (point2.x - point.x), 2.0d) + Math.pow((double) (point2.y - point.y), 2.0d));
            if (point2.y > point.y) {
                f = -sqrt;
            } else {
                f = sqrt;
            }
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    private ArrayList<Point> a(Point[] pointArr) {
        ArrayList b = b(pointArr);
        int size = b.size();
        if (size < 1) {
            return null;
        }
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = ((Double) b.get(i)).doubleValue();
        }
        ArrayList<Point> arrayList = new ArrayList(size + 1);
        for (int i2 = 0; i2 < size - 1; i2++) {
            Point point = pointArr[i2];
            Point point2 = pointArr[i2 + 1];
            for (int i3 = point.x; i3 < point2.x; i3++) {
                double d = ((double) (i3 - point.x)) / ((double) (point2.x - point.x));
                double d2 = 1.0d - d;
                double d3 = (double) (point2.x - point.x);
                d = ((((((d * d) * d) - d) * dArr[i2 + 1]) + ((((d2 * d2) * d2) - d2) * dArr[i2])) * ((d3 * d3) / 6.0d)) + ((((double) point.y) * d2) + (((double) point2.y) * d));
                if (d > 255.0d) {
                    d = 255.0d;
                } else if (d < 0.0d) {
                    d = 0.0d;
                }
                arrayList.add(new Point(i3, (int) Math.round(d)));
            }
        }
        if (arrayList.size() == 255) {
            arrayList.add(pointArr[pointArr.length - 1]);
        }
        return arrayList;
    }

    private ArrayList<Double> b(Point[] pointArr) {
        int length = pointArr.length;
        if (length <= 1) {
            return null;
        }
        int i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, 3});
        double[] dArr2 = new double[length];
        dArr[0][1] = 1.0d;
        dArr[0][0] = 0.0d;
        dArr[0][2] = 0.0d;
        for (i = 1; i < length - 1; i++) {
            Point point = pointArr[i - 1];
            Point point2 = pointArr[i];
            Point point3 = pointArr[i + 1];
            dArr[i][0] = ((double) (point2.x - point.x)) / 6.0d;
            dArr[i][1] = ((double) (point3.x - point.x)) / 3.0d;
            dArr[i][2] = ((double) (point3.x - point2.x)) / 6.0d;
            dArr2[i] = (((double) (point3.y - point2.y)) / ((double) (point3.x - point2.x))) - (((double) (point2.y - point.y)) / ((double) (point2.x - point.x)));
        }
        dArr2[0] = 0.0d;
        dArr2[length - 1] = 0.0d;
        dArr[length - 1][1] = 1.0d;
        dArr[length - 1][0] = 0.0d;
        dArr[length - 1][2] = 0.0d;
        for (i = 1; i < length; i++) {
            double d = dArr[i][0] / dArr[i - 1][1];
            double[] dArr3 = dArr[i];
            dArr3[1] = dArr3[1] - (dArr[i - 1][2] * d);
            dArr[i][0] = 0.0d;
            dArr2[i] = dArr2[i] - (d * dArr2[i - 1]);
        }
        for (i = length - 2; i >= 0; i--) {
            d = dArr[i][2] / dArr[i + 1][1];
            dArr3 = dArr[i];
            dArr3[1] = dArr3[1] - (dArr[i + 1][0] * d);
            dArr[i][2] = 0.0d;
            dArr2[i] = dArr2[i] - (d * dArr2[i + 1]);
        }
        ArrayList<Double> arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(Double.valueOf(dArr2[i2] / dArr[i2][1]));
        }
        return arrayList;
    }
}
