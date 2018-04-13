package cn.xiaochuankeji.tieba.widget.daynight;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import cn.xiaochuankeji.tieba.R;

public class DayNightSwitch extends View implements AnimatorListener {
    private boolean a;
    private GradientDrawable b;
    private BitmapDrawable c;
    private BitmapDrawable d;
    private BitmapDrawable e;
    private BitmapDrawable f;
    private float g;
    private boolean h;
    private int i;
    private b j;
    private a k;

    public DayNightSwitch(Context context) {
        this(context, null, 0);
    }

    public DayNightSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DayNightSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        setWillNotDraw(false);
        this.g = 0.0f;
        this.h = false;
        this.i = 500;
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DayNightSwitch a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        this.b = new GradientDrawable(Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#BB149EFF"), Color.parseColor("#99149EFF")});
        this.b.setGradientType(0);
        this.c = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.dark_background);
        this.d = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.img_sun);
        this.e = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.img_moon);
        this.f = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.img_clouds);
    }

    public void a() {
        boolean z = true;
        if (!this.a) {
            this.a = true;
            if (this.h) {
                z = false;
            }
            this.h = z;
            if (this.j != null) {
                this.j.a(this.h);
            }
            b();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() - getHeight();
        this.c.setBounds(0, 0, getWidth(), getHeight());
        this.c.setAlpha((int) (this.g * 255.0f));
        this.c.draw(canvas);
        this.b.setCornerRadius((float) (getHeight() / 2));
        this.b.setBounds(0, 0, getWidth(), getHeight());
        this.b.setAlpha(255 - ((int) (this.g * 255.0f)));
        this.b.draw(canvas);
        this.e.setBounds(width - ((int) (this.g * ((float) width))), 0, getWidth() - ((int) (this.g * ((float) width))), getHeight());
        this.e.setAlpha((int) (this.g * 255.0f));
        this.e.getBitmap();
        this.d.setBounds(width - ((int) (this.g * ((float) width))), 0, getWidth() - ((int) (((float) width) * this.g)), getHeight());
        this.d.setAlpha(255 - ((int) (this.g * 255.0f)));
        this.e.draw(canvas);
        this.d.draw(canvas);
        this.e.setAlpha((int) (this.g * 255.0f));
        this.f.setAlpha(((double) this.g) <= 0.5d ? 255 - ((int) (((((double) this.g) - 0.5d) * 2.0d) * 255.0d)) : 0);
        width = (int) (((float) (getHeight() / 2)) - (this.g * ((float) (getHeight() / 2))));
        this.f.setBounds(width, 0, getHeight() + width, getHeight());
        this.f.draw(canvas);
    }

    private void b() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (this.g == 1.0f) {
            ofFloat.setFloatValues(new float[]{1.0f, 0.0f});
        }
        ofFloat.setDuration((long) this.i);
        ofFloat.addListener(this);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ DayNightSwitch a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.g = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (this.a.k != null) {
                    this.a.k.a(this.a.g);
                }
                this.a.invalidate();
            }
        });
        ofFloat.start();
    }

    public void onAnimationStart(Animator animator) {
        if (this.k != null) {
            this.k.a();
        }
    }

    public void onAnimationEnd(Animator animator) {
        this.a = false;
        if (this.k != null) {
            this.k.b();
        }
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void a(boolean z, boolean z2) {
        this.h = z;
        this.g = z ? 1.0f : 0.0f;
        invalidate();
        if (this.j != null && z2) {
            this.j.a(z);
        }
    }

    public void setListener(b bVar) {
        this.j = bVar;
    }

    public void setAnimListener(a aVar) {
        this.k = aVar;
    }

    public void setDuration(int i) {
        this.i = i;
    }
}
