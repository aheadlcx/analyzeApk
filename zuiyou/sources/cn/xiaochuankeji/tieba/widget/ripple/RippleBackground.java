package cn.xiaochuankeji.tieba.widget.ripple;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import java.util.ArrayList;
import java.util.Iterator;

public class RippleBackground extends RelativeLayout {
    private int a;
    private float b;
    private float c;
    private int d;
    private int e;
    private int f;
    private float g;
    private float h;
    private int i;
    private Paint j;
    private boolean k = false;
    private AnimatorSet l;
    private ArrayList<Animator> m;
    private LayoutParams n;
    private ArrayList<a> o = new ArrayList();

    private class a extends View {
        final /* synthetic */ RippleBackground a;

        public a(RippleBackground rippleBackground, Context context) {
            this.a = rippleBackground;
            super(context);
            setVisibility(4);
        }

        protected void onDraw(Canvas canvas) {
            int min = Math.min(getWidth(), getHeight()) / 2;
            canvas.drawCircle((float) min, (float) min, ((float) min) - this.a.b, this.a.j);
        }
    }

    public RippleBackground(Context context) {
        super(context);
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            if (attributeSet == null) {
                throw new IllegalArgumentException("Attributes should be provided to this view,");
            }
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleBackground);
            this.a = obtainStyledAttributes.getColor(0, getResources().getColor(R.color.ripple_color));
            this.b = obtainStyledAttributes.getDimension(1, getResources().getDimension(R.dimen.rippleStrokeWidth));
            this.c = obtainStyledAttributes.getDimension(2, getResources().getDimension(R.dimen.rippleRadius));
            this.d = obtainStyledAttributes.getInt(3, 3000);
            this.e = obtainStyledAttributes.getInt(5, 6);
            this.g = obtainStyledAttributes.getFloat(6, 6.0f);
            this.i = obtainStyledAttributes.getInt(8, 0);
            this.h = obtainStyledAttributes.getFloat(7, 1.0f);
            obtainStyledAttributes.recycle();
            this.f = this.d / this.e;
            this.j = new Paint();
            this.j.setAntiAlias(true);
            if (this.i == 0) {
                this.b = 0.0f;
                this.j.setStyle(Style.FILL);
            } else {
                this.j.setStyle(Style.STROKE);
            }
            this.j.setColor(this.a);
            this.n = new LayoutParams((int) (2.0f * (this.c + this.b)), (int) (2.0f * (this.c + this.b)));
            this.n.addRule(13, -1);
            this.l = new AnimatorSet();
            this.l.setInterpolator(new AccelerateDecelerateInterpolator());
            this.m = new ArrayList();
            for (int i = 0; i < this.e; i++) {
                View aVar = new a(this, getContext());
                addView(aVar, this.n);
                this.o.add(aVar);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(aVar, "ScaleX", new float[]{1.0f, this.g});
                ofFloat.setRepeatCount(-1);
                ofFloat.setRepeatMode(1);
                ofFloat.setStartDelay((long) (this.f * i));
                ofFloat.setDuration((long) this.d);
                this.m.add(ofFloat);
                ofFloat = ObjectAnimator.ofFloat(aVar, "ScaleY", new float[]{1.0f, this.g});
                ofFloat.setRepeatCount(-1);
                ofFloat.setRepeatMode(1);
                ofFloat.setStartDelay((long) (this.f * i));
                ofFloat.setDuration((long) this.d);
                this.m.add(ofFloat);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(aVar, "Alpha", new float[]{this.h, 0.0f});
                ofFloat2.setRepeatCount(-1);
                ofFloat2.setRepeatMode(1);
                ofFloat2.setStartDelay((long) (this.f * i));
                ofFloat2.setDuration((long) this.d);
                this.m.add(ofFloat2);
            }
            this.l.playTogether(this.m);
        }
    }

    public void a() {
        if (!c()) {
            Iterator it = this.o.iterator();
            while (it.hasNext()) {
                ((a) it.next()).setVisibility(0);
            }
            this.l.start();
            this.k = true;
        }
    }

    public void b() {
        if (c()) {
            this.l.end();
            this.k = false;
        }
    }

    public boolean c() {
        return this.k;
    }
}
