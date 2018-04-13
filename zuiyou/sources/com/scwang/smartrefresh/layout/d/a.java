package com.scwang.smartrefresh.layout.d;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

public class a extends Drawable implements Animatable {
    private int a = 0;
    private ValueAnimator b;
    private Path c = new Path();
    private Paint d = new Paint();

    public a() {
        this.d.setStyle(Style.FILL);
        this.d.setAntiAlias(true);
        this.d.setColor(-5592406);
        a();
    }

    public void a(int i) {
        this.d.setColor(i);
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        canvas.save();
        canvas.rotate((float) this.a, (float) (width / 2), (float) (height / 2));
        int max = Math.max(1, width / 20);
        for (int i = 0; i < 12; i++) {
            this.c.reset();
            this.c.addCircle((float) (width - max), (float) (height / 2), (float) max, Direction.CW);
            this.c.addRect((float) (width - (max * 5)), (float) ((height / 2) - max), (float) (width - max), (float) ((height / 2) + max), Direction.CW);
            this.c.addCircle((float) (width - (max * 5)), (float) (height / 2), (float) max, Direction.CW);
            this.d.setAlpha((i + 5) * 17);
            canvas.rotate(30.0f, (float) (width / 2), (float) (height / 2));
            canvas.drawPath(this.c, this.d);
        }
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.d.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    private void a() {
        this.b = ValueAnimator.ofInt(new int[]{30, 3600});
        this.b.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.a = (((Integer) valueAnimator.getAnimatedValue()).intValue() / 30) * 30;
                this.a.invalidateSelf();
            }
        });
        this.b.setDuration(10000);
        this.b.setInterpolator(new LinearInterpolator());
        this.b.setRepeatCount(-1);
        this.b.setRepeatMode(1);
    }

    public void start() {
        if (!this.b.isRunning()) {
            this.b.start();
        }
    }

    public void stop() {
        if (this.b.isRunning()) {
            this.b.cancel();
        }
    }

    public boolean isRunning() {
        return this.b.isRunning();
    }
}
