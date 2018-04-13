package cn.xiaochuankeji.tieba.ui.videomaker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;
import java.util.Iterator;

public class VideoRecordProgressView extends View {
    private Drawable a;
    private Paint b;
    private Paint c;
    private Paint d;
    private RectF e;
    private int f;
    private int g;
    private int h;
    private ArrayList<Integer> i = new ArrayList();
    private Activity j;
    private boolean k;
    private a l;
    private Rect m = new Rect();
    private boolean n;
    private b o;
    private float p;
    private int q;

    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    private class b extends FrameLayout {
        final /* synthetic */ VideoRecordProgressView a;

        public b(VideoRecordProgressView videoRecordProgressView, Context context) {
            this.a = videoRecordProgressView;
            super(context);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            super.onTouchEvent(motionEvent);
            if (motionEvent.getAction() == 0) {
                this.a.f();
            }
            return true;
        }
    }

    public VideoRecordProgressView(Context context) {
        super(context);
    }

    public VideoRecordProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoRecordProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(Activity activity) {
        this.j = activity;
        this.a = new ColorDrawable(Color.parseColor("#19000000"));
        this.b = new Paint();
        this.b.setColor(-2283420);
        this.c = new Paint();
        this.c.setColor(-1998772124);
        this.d = new Paint();
        this.d.setColor(-1);
        this.d.setAntiAlias(true);
        this.p = (float) e.a(5.0f);
        this.q = e.a(20.0f);
        this.e = new RectF();
    }

    public void setListener(a aVar) {
        this.l = aVar;
    }

    public void setMinDuration(int i) {
        this.f = i;
    }

    public void setMaxDuration(int i) {
        this.g = i;
    }

    public void a() {
        if (!this.k) {
            this.k = true;
        }
    }

    public void setProgress(int i) {
        if (this.k) {
            int i2 = this.h;
            this.h = i;
            if (i2 < this.f && this.h >= this.f && this.l != null) {
                this.l.c();
            }
            if (i2 < this.g && this.h >= this.g) {
                this.h = this.g;
                c();
                if (this.l != null) {
                    this.l.d();
                }
            }
            invalidate();
        }
    }

    public void setCurrentProgress(int i) {
        if (this.k) {
            int i2 = 0;
            if (!this.i.isEmpty()) {
                i2 = ((Integer) this.i.get(this.i.size() - 1)).intValue();
            }
            setProgress(i2 + i);
        }
    }

    public int b() {
        return this.h;
    }

    public void c() {
        boolean z = false;
        if (this.k) {
            this.k = false;
            if (!this.i.isEmpty()) {
                z = ((Integer) this.i.get(this.i.size() - 1)).intValue();
            }
            if (z < this.h) {
                this.i.add(Integer.valueOf(this.h));
                g();
                invalidate();
            }
        }
    }

    public void d() {
        if (!this.i.isEmpty()) {
            this.i.remove(this.i.size() - 1);
            if (this.i.isEmpty()) {
                this.h = 0;
            } else {
                this.h = ((Integer) this.i.get(this.i.size() - 1)).intValue();
            }
            g();
            invalidate();
        }
    }

    private void g() {
        if (this.i.isEmpty() || this.g <= 0) {
            this.m.left = 0;
            this.m.right = 0;
            this.m.top = 0;
            this.m.bottom = 0;
            return;
        }
        int intValue;
        int intValue2 = ((Integer) this.i.get(this.i.size() - 1)).intValue();
        if (this.i.size() > 1) {
            intValue = ((Integer) this.i.get(this.i.size() - 2)).intValue();
        } else {
            intValue = 0;
        }
        intValue = (int) (((((float) intValue) * 1.0f) / ((float) this.g)) * ((float) getWidth()));
        intValue2 = (int) (((((float) intValue2) * 1.0f) / ((float) this.g)) * ((float) getWidth()));
        this.m.left = intValue - this.q;
        this.m.right = intValue2 + this.q;
        this.m.top = 0;
        this.m.bottom = getHeight();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a.setBounds(0, (int) (((float) i2) - this.p), i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (this.k || this.i.isEmpty()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            g();
            Rect rect;
            if (this.m.left < 0) {
                rect = this.m;
                rect.right += this.q;
            } else if (this.m.right > getWidth()) {
                rect = this.m;
                rect.left -= this.q;
            }
            if (this.m.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
        } else if (action == 1) {
            if (!this.m.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
            e();
            return true;
        }
        return false;
    }

    public void e() {
        this.n = true;
        invalidate();
        ViewGroup viewGroup = (ViewGroup) this.j.findViewById(16908290);
        if (this.o == null) {
            this.o = new b(this, this.j);
        }
        viewGroup.addView(this.o);
        if (this.l != null) {
            this.l.a();
        }
    }

    public void f() {
        this.n = false;
        invalidate();
        ((ViewGroup) this.j.findViewById(16908290)).removeView(this.o);
        if (this.l != null) {
            this.l.b();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.draw(canvas);
        if (this.g > 0) {
            a(canvas);
            if (this.f > 0 && this.h < this.f) {
                a(canvas, this.f);
            }
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                a(canvas, ((Integer) it.next()).intValue());
            }
        }
    }

    private void a(Canvas canvas) {
        float f = 0.0f;
        float height = ((float) getHeight()) - this.p;
        float a;
        if (this.k) {
            if (this.i.size() > 0) {
                a = a(((Integer) this.i.get(this.i.size() - 1)).intValue());
                canvas.drawRect(0.0f, height, a, height + this.p, this.c);
                f = a;
            }
            canvas.drawRect(f, height, a(this.h), height + this.p, this.b);
        } else if (this.i.size() > 1) {
            a = a(((Integer) this.i.get(this.i.size() - 2)).intValue());
            canvas.drawRect(0.0f, height, a, height + this.p, this.c);
            canvas.drawRect(a, height, a(this.h), height + this.p, this.b);
        } else {
            canvas.drawRect(0.0f, height, a(this.h), height + this.p, this.b);
        }
    }

    private void a(Canvas canvas, int i) {
        float a = (float) e.a(2.0f);
        float a2 = a(i) - (a / 2.0f);
        float height = ((float) getHeight()) - this.p;
        this.e.set(a2, height, a2 + a, this.p + height);
        canvas.drawRoundRect(this.e, a / 2.0f, a / 2.0f, this.d);
    }

    private float a(int i) {
        return ((((float) i) * 1.0f) / ((float) this.g)) * ((float) getWidth());
    }
}
