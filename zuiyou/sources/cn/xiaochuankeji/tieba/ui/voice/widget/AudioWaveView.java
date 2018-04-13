package cn.xiaochuankeji.tieba.ui.voice.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AudioWaveView extends View {
    private int a;
    private int b;
    private int c;
    private float d;
    private float e;
    private int f;
    private List<a> g = new ArrayList();
    private List<a> h = new ArrayList();
    private int i;
    private int j;
    private Paint k;
    private Paint l;
    private int m;
    private boolean n;

    public AudioWaveView(Context context) {
        super(context);
        e();
    }

    public AudioWaveView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public AudioWaveView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    private void e() {
        this.a = a.a().a((int) R.color.CM);
        this.b = a.a().a((int) R.color.voice_line_color);
        this.c = a.a().a((int) R.color.CH);
        this.d = (float) e.a(1.3f);
        this.e = (float) e.a(2.2f);
        this.f = e.a(7.0f);
        this.k = new Paint();
        this.k.setStrokeWidth(1.0f);
        this.k.setColor(a.a().a((int) R.color.CL));
        this.l = new Paint();
        this.l.setStrokeWidth(this.d);
        this.l.setAntiAlias(true);
        this.l.setColor(this.a);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.j == 0) {
            this.j = (int) (((float) getWidth()) / (this.e + this.d));
        }
    }

    public void a(int i) {
        this.n = true;
        if (i > this.i) {
            this.i = i;
        }
        a aVar = new a();
        aVar.a = i;
        aVar.b = 2;
        this.g.add(aVar);
        if (this.g.size() > this.j) {
            this.h = this.g.subList(this.g.size() - this.j, this.g.size());
        } else {
            this.h = this.g;
        }
        invalidate();
    }

    public void a() {
        this.m = 0;
    }

    public void setProgress(float f) {
        int i = 0;
        if (f >= 1.0f) {
            f = 1.0f;
        }
        int size = (int) (((float) this.g.size()) * f);
        if (size == 0) {
            a();
        } else if (size >= this.m) {
            this.m = size;
            this.h = null;
            if (this.g.size() <= this.j) {
                this.h = this.g;
                while (i < this.h.size()) {
                    if (i <= size) {
                        ((a) this.h.get(i)).b = 2;
                    } else {
                        ((a) this.h.get(i)).b = 1;
                    }
                    i++;
                }
            } else if (size < this.j) {
                this.h = this.g.subList(0, this.j);
                while (i < this.h.size()) {
                    if (i <= size) {
                        ((a) this.h.get(i)).b = 2;
                    } else {
                        ((a) this.h.get(i)).b = 1;
                    }
                    i++;
                }
            } else {
                this.h = this.g.subList(size - this.j, size);
                while (i < this.h.size()) {
                    ((a) this.h.get(i)).b = 2;
                    i++;
                }
            }
            invalidate();
        }
    }

    public void b() {
        if (this.g.size() > 0) {
            ((a) this.g.get(this.g.size() - 1)).c = true;
        }
        invalidate();
    }

    public void c() {
        if (this.g.size() > 1) {
            int i;
            for (int size = this.g.size() - 2; size >= 0; size--) {
                if (((a) this.g.get(size)).c) {
                    i = size;
                    break;
                }
            }
            i = 0;
            Collection arrayList = new ArrayList();
            arrayList.addAll(this.g.subList(i + 1, this.g.size()));
            this.g.removeAll(arrayList);
            if (this.g.size() < this.j) {
                this.h = this.g;
            } else {
                this.h = this.g.subList(this.g.size() - this.j, this.g.size());
            }
        } else {
            this.h.clear();
        }
        invalidate();
    }

    public void d() {
        this.m = 0;
        this.i = 0;
        this.h.clear();
        this.g.clear();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        if (this.n) {
            canvas.drawLine(0.0f, (float) (getHeight() / 2), (float) getWidth(), (float) (getHeight() / 2), this.k);
        }
        for (int i = 0; i < this.h.size(); i++) {
            a aVar = (a) this.h.get(i);
            float f = (this.d / 2.0f) + (((float) i) * (this.e + this.d));
            if (aVar.c) {
                this.l.setColor(this.c);
                canvas.drawLine(f, 0.0f, f, (float) getHeight(), this.l);
            } else {
                int sqrt = (int) (Math.sqrt((double) (((float) aVar.a) / ((float) this.i))) * ((double) (getHeight() - (this.f * 2))));
                float height = (float) ((getHeight() - sqrt) / 2);
                float f2 = height + ((float) sqrt);
                if (aVar.b == 2) {
                    this.l.setColor(this.a);
                } else if (aVar.b == 1) {
                    this.l.setColor(this.b);
                }
                canvas.drawLine(f, height, f, f2, this.l);
            }
        }
    }
}
