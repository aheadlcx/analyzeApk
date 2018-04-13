package cn.xiaochuankeji.tieba.ui.widget.updown;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import cn.xiaochuankeji.tieba.ui.utils.e;

public abstract class a extends View {
    protected Drawable a;
    protected Drawable b;
    protected Drawable c;
    private boolean d;
    private long e;
    private Rect f = new Rect();
    private Rect g = new Rect();

    protected abstract void a(int i, int i2, Rect rect);

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
        a(context.getResources());
        setLongClickable(true);
    }

    public void a(boolean z, boolean z2) {
        boolean isSelected = isSelected();
        setSelected(z);
        if (!isSelected && z && z2 && !this.d) {
            this.d = true;
            this.e = System.currentTimeMillis();
        }
        a();
        invalidate();
    }

    private void a() {
        this.a.setBounds(this.f);
        this.c.setBounds(this.g);
        this.b.setBounds(this.f);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int intrinsicWidth = (i - this.c.getIntrinsicWidth()) / 2;
        int intrinsicHeight = (i2 - this.c.getIntrinsicHeight()) / 2;
        this.g.set(intrinsicWidth, intrinsicHeight, this.c.getIntrinsicWidth() + intrinsicWidth, this.c.getIntrinsicHeight() + intrinsicHeight);
        a(i, i2, this.f);
        a();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            if (this.d) {
                if (!a(canvas, (int) (((System.currentTimeMillis() - this.e) * 100) / 250))) {
                    this.d = false;
                    a();
                }
                invalidate();
            }
            if (!this.d) {
                this.c.draw(canvas);
                this.b.draw(canvas);
                return;
            }
            return;
        }
        this.a.draw(canvas);
    }

    private boolean a(Canvas canvas, int i) {
        if (i >= 10 && i <= 80) {
            this.c.setBounds(e.a(this.g, (((float) i) - 10.0f) / 70.0f));
            this.c.draw(canvas);
        } else if (i > 80) {
            this.c.draw(canvas);
        }
        if (i <= 40) {
            this.a.setBounds(e.a(this.f, 1.0f - (((float) i) / 40.0f)));
            this.a.draw(canvas);
        }
        if (i >= 75 && i <= 100) {
            this.b.setBounds(e.a(this.f, (((float) i) - 75.0f) / 25.0f));
            this.b.draw(canvas);
        }
        if (i <= 100) {
            return true;
        }
        return false;
    }
}
