package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import cn.xiaochuankeji.tieba.ui.utils.e;

public abstract class a extends View {
    protected Drawable a;
    protected Drawable b;
    protected Drawable c;
    protected Drawable d;
    private Rect e = new Rect();
    private Rect f = new Rect();
    private int g;
    private Animation h = new Animation(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            this.a.g = (int) (100.0f * f);
            this.a.invalidate();
        }
    };

    protected abstract void a(Resources resources);

    public a(Context context) {
        super(context);
        a(context);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.h.setDuration(400);
        a(context.getResources());
    }

    public void a(boolean z, boolean z2) {
        boolean isSelected = isSelected();
        setSelected(z);
        if (!isSelected && z && z2) {
            startAnimation(this.h);
        }
        a();
    }

    private void a() {
        this.a.setAlpha(255);
        this.a.setBounds(this.e);
        this.b.setAlpha(255);
        this.b.setBounds(this.f);
        this.d.setAlpha(255);
        this.d.setBounds(this.e);
        this.c.setAlpha(255);
        this.c.setBounds(this.f);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.e.set(0, 0, i, i2);
        int intrinsicWidth = (i - this.b.getIntrinsicWidth()) / 2;
        int intrinsicHeight = (i2 - this.b.getIntrinsicHeight()) / 2;
        this.f.set(intrinsicWidth, intrinsicHeight, this.b.getIntrinsicWidth() + intrinsicWidth, this.b.getIntrinsicHeight() + intrinsicHeight);
        a();
    }

    protected void onAnimationEnd() {
        super.onAnimationEnd();
        a();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isSelected()) {
            this.a.draw(canvas);
            this.b.draw(canvas);
        } else if (this.h.hasEnded()) {
            this.d.draw(canvas);
            this.c.draw(canvas);
        } else {
            a(canvas, this.g);
        }
    }

    private void a(Canvas canvas, int i) {
        float f;
        if (i <= 38) {
            this.d.setAlpha(255);
            this.d.setBounds(e.a(this.e, 0.7f));
            this.d.draw(canvas);
            float f2 = 1.0f - (((float) i) / 38.0f);
            this.a.setAlpha((int) (255.0f * (1.0f - (((float) i) / 38.0f))));
            this.a.setBounds(e.a(this.e, f2));
            this.a.draw(canvas);
            f = (float) (1 - (i / 76));
            this.b.setAlpha(255);
            this.b.setBounds(e.a(this.f, f));
            this.b.draw(canvas);
        } else if (i <= 53) {
            this.d.setAlpha(255);
            this.d.setBounds(e.a(this.e, 0.7f));
            this.d.draw(canvas);
        } else if (i <= 100) {
            f = (((float) (i - 53)) / 156.0f) + 0.7f;
            this.d.setAlpha(255);
            this.d.setBounds(e.a(this.e, f));
            this.d.draw(canvas);
            f = ((float) (i - 53)) / 47.0f;
            this.c.setAlpha(255);
            this.c.setBounds(e.a(this.f, f));
            this.c.draw(canvas);
        }
    }
}
