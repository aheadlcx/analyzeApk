package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import cn.xiaochuankeji.tieba.R;

public class PostPercentBar extends View {
    private int a;
    private boolean b;
    private RectF c;
    private RectF d;
    private Paint e;
    private Paint f;
    private float g;
    private Animation h = new Animation(this) {
        final /* synthetic */ PostPercentBar a;

        {
            this.a = r1;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            this.a.g = f;
            this.a.invalidate();
        }
    };

    public PostPercentBar(Context context) {
        super(context);
        a(context);
    }

    public PostPercentBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PostPercentBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.h.setDuration(400);
        this.c = new RectF();
        this.d = new RectF();
        Resources resources = context.getResources();
        this.e = new Paint();
        this.e.setColor(resources.getColor(R.color.CM));
        this.e.setAntiAlias(true);
        this.f = new Paint();
        this.f.setColor(resources.getColor(R.color.CH));
        this.f.setAntiAlias(true);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        float f = (float) (height / 2);
        int i = (this.a * width) / 100;
        int i2 = width - i;
        if (this.b) {
            if (i2 > 0) {
                i2 += height;
            }
        } else if (i > 0) {
            i += height;
        }
        i2 = (int) (((float) i2) * this.g);
        this.c.set((float) (width - ((int) (((float) i) * this.g))), 0.0f, (float) width, (float) height);
        this.d.set(0.0f, 0.0f, (float) i2, (float) height);
        if (this.b) {
            canvas.drawRoundRect(this.d, f, f, this.f);
            canvas.drawRoundRect(this.c, f, f, this.e);
            return;
        }
        canvas.drawRoundRect(this.c, f, f, this.e);
        canvas.drawRoundRect(this.d, f, f, this.f);
    }
}
