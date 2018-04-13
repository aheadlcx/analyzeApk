package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RecordAnimView extends View {
    private c a;
    private c b;
    private c c;
    private c d;
    private b e;
    private Timer f;
    @SuppressLint({"HandlerLeak"})
    private Handler g = new Handler(this) {
        final /* synthetic */ RecordAnimView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.a.h += this.a.a.g;
            if (this.a.a.h > this.a.a.c) {
                this.a.a.h = 0.0f;
            }
            this.a.b.h += this.a.b.g;
            if (this.a.b.h > this.a.b.c) {
                this.a.b.h = 0.0f;
            }
            this.a.c.h += this.a.c.g;
            if (this.a.c.h > this.a.c.c) {
                this.a.c.h = 0.0f;
            }
            this.a.d.h += this.a.d.g;
            if (this.a.d.h > this.a.d.c) {
                this.a.d.h = 0.0f;
            }
            this.a.invalidate();
        }
    };

    private class a {
        float a;
        float b;
        final /* synthetic */ RecordAnimView c;

        a(RecordAnimView recordAnimView, float f, float f2) {
            this.c = recordAnimView;
            this.a = f;
            this.b = f2;
        }
    }

    class b extends TimerTask {
        Handler a;
        final /* synthetic */ RecordAnimView b;

        b(RecordAnimView recordAnimView, Handler handler) {
            this.b = recordAnimView;
            this.a = handler;
        }

        public void run() {
            this.b.g.sendMessage(this.b.g.obtainMessage());
        }
    }

    private class c {
        List<a> a = new ArrayList(9);
        float b;
        float c;
        Paint d = new Paint(1);
        Path e = new Path();
        float f;
        float g;
        float h;
        final /* synthetic */ RecordAnimView i;

        c(RecordAnimView recordAnimView, int i, int i2, float f) {
            this.i = recordAnimView;
            this.d.setStyle(Style.STROKE);
            this.d.setStrokeWidth((float) i2);
            this.d.setColor(i);
            this.g = f;
        }
    }

    public RecordAnimView(Context context) {
        super(context);
        c();
    }

    public RecordAnimView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public RecordAnimView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        this.a = new c(this, 1727987712, 4, 2.0f);
        this.b = new c(this, 587137024, 6, 1.4f);
        this.c = new c(this, -1141536886, 3, 3.0f);
        this.d = new c(this, 1156941706, 4, 1.2f);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float size = (float) MeasureSpec.getSize(i2);
        float size2 = (float) MeasureSpec.getSize(i);
        this.a.f = size / 2.0f;
        this.a.b = size / 2.0f;
        this.a.c = size2;
        a(this.a);
        this.b.f = size / 2.0f;
        this.b.b = size / 3.0f;
        this.b.c = size2;
        a(this.b);
        this.c.f = size / 2.0f;
        this.c.b = size / 2.0f;
        this.c.c = size2;
        a(this.c);
        this.d.f = size / 2.0f;
        this.d.b = size / 3.0f;
        this.d.c = size2;
        a(this.d);
    }

    private void a(c cVar) {
        for (int i = 0; i < 9; i++) {
            float f;
            if (i % 2 == 0) {
                f = cVar.f;
            } else if (i % 4 == 1) {
                f = cVar.f - cVar.b;
            } else {
                f = cVar.f + cVar.b;
            }
            cVar.a.add(new a(this, (cVar.c / 4.0f) * ((float) i), f));
        }
    }

    protected void onDraw(Canvas canvas) {
        a(canvas, this.a);
        a(canvas, this.b);
        a(canvas, this.c);
        a(canvas, this.d);
    }

    private void a(Canvas canvas, c cVar) {
        int i = 0;
        cVar.e.reset();
        cVar.e.moveTo(((a) cVar.a.get(0)).a - cVar.h, (cVar.f * 2.0f) + cVar.b);
        cVar.e.lineTo(((a) cVar.a.get(0)).a - cVar.h, cVar.f);
        while (i < 4) {
            cVar.e.quadTo(((a) cVar.a.get((i * 2) + 1)).a - cVar.h, ((a) cVar.a.get((i * 2) + 1)).b, ((a) cVar.a.get((i * 2) + 2)).a - cVar.h, ((a) cVar.a.get((i * 2) + 2)).b);
            i++;
        }
        cVar.e.lineTo(((a) cVar.a.get(8)).a - cVar.h, (cVar.f * 2.0f) + cVar.b);
        cVar.e.close();
        canvas.drawPath(cVar.e, cVar.d);
    }

    public void a() {
        b();
        this.e = new b(this, this.g);
        this.f = new Timer();
        this.f.schedule(this.e, 0, 2);
    }

    public void b() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
    }
}
