package qsbk.app.core.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.Button;
import qsbk.app.core.R;
import qsbk.app.core.utils.WindowUtils;

public class SwitchButton extends Button {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private OnTriggerListener q;
    private Paint r;
    private RectF s;

    public interface OnTriggerListener {
        void off();

        void on();
    }

    public SwitchButton(Context context) {
        this(context, null, 0);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = WindowUtils.dp2Px(2);
        this.f = -1;
        this.k = false;
        this.l = false;
        this.m = true;
        this.r = new Paint();
        this.s = new RectF();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SwitchButton, i, 0);
        this.g = obtainStyledAttributes.getColor(R.styleable.SwitchButton_OnColor, Color.parseColor("#FFFDDB2E"));
        this.h = obtainStyledAttributes.getColor(R.styleable.SwitchButton_bgOffColor, -7829368);
        obtainStyledAttributes.recycle();
        this.n = ViewConfiguration.get(context).getScaledTouchSlop();
        this.j = this.c + this.e;
        setClickable(true);
        this.r.setStyle(Style.FILL);
        this.r.setAntiAlias(true);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.d = i2 / 2;
        this.e = (i2 / 2) - this.c;
        if (this.m) {
            this.j = (this.a - this.c) - this.e;
            this.i = this.g;
        } else {
            this.j = this.c + this.e;
            this.i = this.h;
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.r.setColor(this.i);
        this.s.set(0.0f, 0.0f, (float) this.a, (float) this.b);
        canvas.drawRoundRect(this.s, (float) this.d, (float) this.d, this.r);
        this.r.setColor(this.f);
        canvas.drawCircle((float) this.j, (float) (this.c + this.e), (float) this.e, this.r);
    }

    public void init(boolean z) {
        this.m = z;
        if (z) {
            this.j = (this.a - this.c) - this.e;
            this.i = this.g;
            return;
        }
        this.j = this.c + this.e;
        this.i = this.h;
    }

    public void trigger(boolean z) {
        this.m = z;
        if (this.a > 0) {
            if (z) {
                a((this.a - this.c) - this.e);
                this.j = (this.a - this.c) - this.e;
            } else {
                a(this.c + this.e);
                this.j = this.c + this.e;
            }
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (a(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean a(MotionEvent motionEvent) {
        boolean z = true;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        this.j = x;
        if (this.j > (this.a - this.c) - this.e) {
            this.j = (this.a - this.c) - this.e;
        } else if (this.j < this.c + this.e) {
            this.j = this.c + this.e;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                if (new Rect(0, 0, getWidth(), getHeight()).contains(x, y)) {
                    this.k = true;
                    getParent().requestDisallowInterceptTouchEvent(true);
                    this.o = x;
                    this.p = y;
                    break;
                }
                break;
            case 1:
            case 3:
                if (this.l) {
                    int i;
                    if (this.j <= this.a / 2) {
                        this.i = this.h;
                        i = this.c + this.e;
                    } else {
                        this.i = this.g;
                        i = (this.a - this.c) - this.e;
                    }
                    a(i);
                    this.l = false;
                } else {
                    if (this.m) {
                        z = false;
                    }
                    this.m = z;
                    trigger(this.m);
                }
                this.k = false;
                break;
            case 2:
                float f = ((float) ((this.j - this.c) - this.e)) / ((float) ((this.a - (this.c * 2)) - (this.e * 2)));
                if (this.k) {
                    if (Math.abs(x - this.o) > this.n || Math.abs(y - this.p) > this.n) {
                        this.l = true;
                        this.k = false;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                } else if (this.l) {
                    this.i = Color.argb(255, (int) (((float) Color.red(this.h)) + ((((float) Color.red(this.g)) - ((float) Color.red(this.h))) * f)), (int) (((float) Color.green(this.h)) + ((((float) Color.green(this.g)) - ((float) Color.green(this.h))) * f)), (int) ((f * (((float) Color.blue(this.g)) - ((float) Color.blue(this.h)))) + ((float) Color.blue(this.h))));
                    invalidate();
                    return true;
                }
                break;
        }
        return false;
    }

    private void a(int i) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.j, i});
        ofInt.addUpdateListener(new x(this));
        ofInt.setDuration(200);
        ofInt.addListener(new y(this, i));
        ofInt.start();
    }

    public void setTriggerListener(OnTriggerListener onTriggerListener) {
        this.q = onTriggerListener;
    }
}
