package qsbk.app.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.SeekBar;
import qsbk.app.core.R;

public class VolumeControllerView extends SeekBar {
    private int a;
    private int b;
    private Paint c;
    private Paint d;
    private int e;
    private int f;
    private AlphaAnimation g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private Runnable m;
    private Runnable n;

    private void a() {
        if (this.h == 0) {
            this.h = getWidth();
            if (this.l == 1) {
                this.i = (int) ((((float) (this.e + -1 >= 0 ? this.e - 1 : 0)) / ((float) this.f)) * ((float) this.h));
            } else {
                this.j = (int) ((((float) (this.e + 1 <= this.f ? this.e + 1 : this.f)) / ((float) this.f)) * ((float) this.h));
            }
        }
        this.k = (int) ((((float) this.e) / ((float) this.f)) * ((float) this.h));
    }

    public VolumeControllerView(Context context) {
        this(context, null);
    }

    public VolumeControllerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VolumeControllerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = -1;
        this.b = -7829368;
        this.c = new Paint();
        this.d = new Paint();
        this.m = new ac(this);
        this.n = new ae(this);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.VolumeControllerView, i, 0);
        this.a = obtainStyledAttributes.getColor(R.styleable.VolumeControllerView_firstColor, this.a);
        this.b = obtainStyledAttributes.getColor(R.styleable.VolumeControllerView_secondColor, this.b);
        obtainStyledAttributes.recycle();
        this.c.setColor(this.a);
        this.c.setStyle(Style.FILL);
        this.d.setColor(this.b);
        this.d.setStyle(Style.FILL);
        setVisibility(8);
    }

    public void draw(Canvas canvas) {
        if (this.l == 1) {
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.k, (float) getHeight()), 2.0f, 2.0f, this.d);
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.i, (float) getHeight()), 2.0f, 2.0f, this.c);
            return;
        }
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.k, (float) getHeight()), 2.0f, 2.0f, this.c);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) this.j, (float) getHeight()), 2.0f, 2.0f, this.d);
    }

    public void setVolume(int i, int i2, int i3) {
        setVisibility(0);
        this.e = i;
        this.f = i2;
        this.l = i3;
        Animation animation = getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        clearAnimation();
        if (this.g != null) {
            this.g.setAnimationListener(null);
            setAnimation(null);
        }
        removeCallbacks(this.n);
        removeCallbacks(this.m);
        post(this.n);
        postDelayed(this.m, 1000);
    }
}
