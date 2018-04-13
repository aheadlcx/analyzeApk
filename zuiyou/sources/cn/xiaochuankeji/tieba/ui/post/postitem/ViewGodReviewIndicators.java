package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import c.a.d.a.a;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;

public class ViewGodReviewIndicators extends View implements u {
    private int[] a;
    private int[] b;
    private RectF[] c;
    private int d;
    private Paint e;
    private Paint f;
    private boolean g;
    private RectF h;
    private float i;
    private Animation j;

    public ViewGodReviewIndicators(Context context) {
        super(context);
        this.a = new int[7];
        this.b = new int[7];
        this.c = new RectF[7];
        this.j = new Animation(this) {
            final /* synthetic */ ViewGodReviewIndicators a;

            {
                this.a = r1;
            }

            protected void applyTransformation(float f, Transformation transformation) {
                super.applyTransformation(f, transformation);
                this.a.i = f;
                this.a.invalidate();
            }
        };
        a();
    }

    public ViewGodReviewIndicators(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new int[7];
        this.b = new int[7];
        this.c = new RectF[7];
        this.j = /* anonymous class already generated */;
        a();
    }

    public ViewGodReviewIndicators(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new int[7];
        this.b = new int[7];
        this.c = new RectF[7];
        this.j = /* anonymous class already generated */;
        a();
    }

    private void a() {
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.f = new Paint();
        this.f.setAntiAlias(true);
        d();
        this.j.setDuration(400);
        this.h = new RectF();
    }

    public void a(int i, b bVar) {
        if (bVar.d < 0) {
            bVar.d = 2;
        }
        if (bVar.e < 0) {
            bVar.e = 6;
        }
        b(i, bVar);
        this.d = (bVar.b + bVar.d) + bVar.c;
        if (bVar.f != 0) {
            int i2;
            if (bVar.f > 0) {
                if (this.a[this.d + 1] == 1) {
                    this.d++;
                    invalidate();
                } else {
                    i2 = (bVar.e - bVar.d) - bVar.c;
                    if (bVar.d == 0) {
                        bVar.c--;
                    } else {
                        bVar.d--;
                    }
                    if (i2 >= i - 1) {
                        bVar.e--;
                    } else {
                        bVar.e = 6;
                    }
                    b();
                    b(i, bVar);
                    a(true);
                }
                bVar.b++;
            } else {
                if (this.a[this.d - 1] == 1) {
                    this.d--;
                    invalidate();
                } else {
                    if (bVar.c < 0) {
                        bVar.c++;
                    } else {
                        bVar.d++;
                    }
                    i2 = bVar.e + 1;
                    bVar.e = i2;
                    bVar.e = Math.min(6, i2);
                    b();
                    b(i, bVar);
                    a(false);
                }
                bVar.b--;
            }
        }
        bVar.f = 0;
    }

    private void b(int i, b bVar) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            this.a[i2] = 0;
        }
        if (i < 6) {
            this.a[3] = 1;
            bVar.d = 3;
            bVar.e = 3;
            if (i > 1) {
                this.a[4] = 1;
                bVar.e = 4;
            }
            if (i > 2) {
                this.a[2] = 1;
                bVar.d = 2;
            }
            if (i > 3) {
                this.a[5] = 1;
                bVar.e = 5;
            }
            if (i > 4) {
                this.a[1] = 1;
                bVar.d = 1;
                return;
            }
            return;
        }
        this.a[4] = 1;
        this.a[3] = 1;
        this.a[2] = 1;
        if (bVar.d < 2) {
            this.a[1] = 2;
        }
        if (bVar.d < 1) {
            this.a[0] = 3;
        }
        if (bVar.e > 4) {
            this.a[5] = 2;
        }
        if (bVar.e > 5) {
            this.a[6] = 3;
        }
    }

    private void b() {
        for (int i = 0; i < this.a.length; i++) {
            this.b[i] = this.a[i];
        }
    }

    private void a(boolean z) {
        this.g = z;
        startAnimation(this.j);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5 = 0;
        super.onSizeChanged(i, i2, i3, i4);
        int i6 = (i - (i2 * 7)) / 6;
        int i7 = 0;
        while (i5 < this.c.length) {
            this.c[i5] = new RectF((float) i7, 0.0f, (float) (i7 + i2), (float) i2);
            i7 = (i7 + i2) + i6;
            i5++;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.j.hasStarted() || this.j.hasEnded()) {
            for (int i = 0; i < this.c.length; i++) {
                int i2 = this.a[i];
                if (i2 != 0) {
                    RectF rectF = this.c[i];
                    float a = a(i2);
                    if (i == this.d) {
                        canvas.drawOval(a(rectF, a), this.f);
                    } else {
                        canvas.drawOval(a(rectF, a), this.e);
                    }
                }
            }
            return;
        }
        a(canvas, this.i);
    }

    private void a(Canvas canvas, float f) {
        int i = this.g ? -1 : 1;
        int i2 = 0;
        while (i2 < this.b.length) {
            int i3 = this.b[i2];
            if (i3 != 0 && i2 + i >= 0 && i2 + i < this.b.length) {
                int i4 = this.a[i2 + i];
                if (i4 != 0) {
                    float a = a(i3);
                    a += (a(i4) - a) * f;
                    RectF rectF = this.c[i2];
                    float f2 = (this.c[i2 + i].left - rectF.left) * f;
                    this.h.set(rectF);
                    this.h.offset(f2, 0.0f);
                    if (i2 == this.d - i) {
                        canvas.drawOval(a(this.h, a), this.f);
                    } else {
                        canvas.drawOval(a(this.h, a), this.e);
                    }
                }
            }
            i2++;
        }
    }

    private float a(int i) {
        if (i == 1) {
            return 1.0f;
        }
        if (i == 2) {
            return 0.66666f;
        }
        if (i == 3) {
            return 0.33333f;
        }
        return 1.0f;
    }

    public RectF a(RectF rectF, float f) {
        RectF rectF2 = new RectF();
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float width = (rectF.width() * f) / 2.0f;
        float height = (rectF.height() * f) / 2.0f;
        rectF2.set(centerX - width, centerY - height, centerX + width, centerY + height);
        return rectF2;
    }

    public void d() {
        int a = a.a().a((int) R.color.CT_6);
        int a2 = a.a().a((int) R.color.CH_1);
        if (this.e != null) {
            this.e.setColor(a);
        }
        if (this.f != null) {
            this.f.setColor(a2);
        }
        invalidate();
    }
}
