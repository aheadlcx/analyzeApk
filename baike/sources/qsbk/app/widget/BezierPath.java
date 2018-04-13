package qsbk.app.widget;

import android.graphics.Path;

public class BezierPath extends Path {
    private a a;
    private double b = 0.8d;

    private static class a {
        float a;
        float b;

        a(float f, float f2) {
            this.a = f;
            this.b = f2;
        }
    }

    private static final a a(a aVar, a aVar2, a aVar3) {
        return new a((aVar2.a * 2.0f) - ((aVar.a + aVar3.a) / 2.0f), (aVar2.b * 2.0f) - ((aVar.b + aVar3.b) / 2.0f));
    }

    private static final double a(a aVar, a aVar2) {
        return Math.sqrt((double) (((aVar.a - aVar2.a) * (aVar.a - aVar2.a)) + ((aVar.b - aVar2.b) * (aVar.b - aVar2.b))));
    }

    private static final a b(a aVar, a aVar2) {
        return new a((aVar.a + aVar2.a) / 2.0f, (aVar.b + aVar2.b) / 2.0f);
    }

    private static final double c(a aVar, a aVar2) {
        if (aVar.a > aVar2.a) {
            return Math.atan((double) ((aVar2.b - aVar.b) / (aVar2.a - aVar.a)));
        }
        if (aVar.a < aVar2.a) {
            return Math.atan((double) ((aVar2.b - aVar.b) / (aVar2.a - aVar.a))) + 3.141592653589793d;
        }
        if (aVar2.b - aVar.b >= 0.0f) {
            return 1.5707963267948966d;
        }
        return -1.5707963267948966d;
    }

    public double getContractionFactor() {
        return this.b;
    }

    public void setContractionFactor(double d) {
        this.b = d;
    }

    public void addBezierThroughPoints(float[] fArr, float[] fArr2) {
        if (fArr != null && fArr2 != null && fArr.length == fArr2.length) {
            a aVar;
            a aVar2;
            int length = fArr.length;
            reset();
            this.a = new a(fArr[0], fArr2[0]);
            moveTo(fArr[0], fArr2[0]);
            if (length < 3) {
                switch (length) {
                    case 2:
                        aVar = new a(fArr[0], fArr2[0]);
                        aVar2 = new a(fArr[1], fArr2[1]);
                        aVar = a(aVar, aVar, aVar2);
                        quadTo(aVar.a, aVar.b, aVar2.a, aVar2.b);
                        this.a = aVar2;
                        break;
                }
            }
            a aVar3 = null;
            aVar2 = null;
            int i = 0;
            a aVar4 = this.a;
            aVar = null;
            while (i < length) {
                a aVar5;
                a aVar6;
                a aVar7 = new a(fArr[i], fArr2[i]);
                if (i > 0) {
                    aVar = b(this.a, aVar4);
                    aVar2 = b(aVar4, aVar7);
                    double a = a(aVar, aVar2);
                    double c = c(aVar2, aVar);
                    aVar2 = new a((float) (((double) aVar4.a) - (((0.5d * this.b) * a) * Math.cos(c))), (float) (((double) aVar4.b) - (((0.5d * this.b) * a) * Math.sin(c))));
                    aVar5 = new a((float) (((double) aVar4.a) + (((0.5d * this.b) * a) * Math.cos(c))), (float) (((a * (0.5d * this.b)) * Math.sin(c)) + ((double) aVar4.b)));
                    aVar6 = aVar2;
                } else {
                    aVar5 = aVar;
                    aVar6 = aVar2;
                }
                if (i == 1) {
                    quadTo(aVar6.a, aVar6.b, aVar4.a, aVar4.b);
                    this.a = aVar4;
                } else if (i > 1 && i < length - 1) {
                    cubicTo(aVar3.a, aVar3.b, aVar6.a, aVar6.b, aVar4.a, aVar4.b);
                    this.a = aVar4;
                } else if (i == length - 1) {
                    cubicTo(aVar3.a, aVar3.b, aVar6.a, aVar6.b, aVar4.a, aVar4.b);
                    quadTo(aVar5.a, aVar5.b, aVar7.a, aVar7.b);
                    this.a = aVar7;
                }
                i++;
                aVar2 = aVar6;
                aVar3 = aVar5;
                aVar4 = aVar7;
                aVar = aVar5;
            }
        }
    }
}
