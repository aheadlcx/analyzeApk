package qsbk.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Checkable;
import qsbk.app.R;

public class CheckedImageView extends QBImageView implements Checkable {
    private boolean a = false;
    private boolean b = true;
    private Drawable c;
    private Drawable d;
    private float e;
    private boolean f = false;
    private OnCheckedChangeListener g;

    public interface OnCheckedChangeListener {
        void onCheckedChange(CheckedImageView checkedImageView, boolean z, boolean z2);
    }

    public CheckedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public CheckedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CheckedImageView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.c = getResources().getDrawable(R.drawable.image_picker_checked);
        this.d = getResources().getDrawable(R.drawable.image_picker_unchecked);
        this.e = getResources().getDisplayMetrics().density * 6.0f;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.b) {
            return;
        }
        int intrinsicWidth;
        int width;
        int paddingTop;
        if (this.a) {
            if (this.c != null) {
                intrinsicWidth = this.c.getIntrinsicWidth();
                width = (int) ((((float) (getWidth() - getPaddingRight())) - this.e) - ((float) intrinsicWidth));
                paddingTop = (int) (((float) getPaddingTop()) + this.e);
                this.c.setBounds(width, paddingTop, intrinsicWidth + width, this.c.getIntrinsicHeight() + paddingTop);
                this.c.draw(canvas);
            }
        } else if (this.d != null) {
            intrinsicWidth = this.d.getIntrinsicWidth();
            width = (int) ((((float) (getWidth() - getPaddingRight())) - this.e) - ((float) intrinsicWidth));
            paddingTop = (int) (((float) getPaddingTop()) + this.e);
            this.d.setBounds(width, paddingTop, intrinsicWidth + width, this.d.getIntrinsicHeight() + paddingTop);
            this.d.draw(canvas);
        }
    }

    private int getCheckWidth() {
        int i = 0;
        if (this.c != null) {
            i = this.c.getIntrinsicWidth();
        }
        if (this.d != null) {
            return Math.max(i, this.d.getIntrinsicWidth());
        }
        return i;
    }

    private int getCheckHeight() {
        int i = 0;
        if (this.c != null) {
            i = this.c.getIntrinsicHeight();
        }
        if (this.d != null) {
            return Math.max(i, this.d.getIntrinsicHeight());
        }
        return i;
    }

    public void setCheckable(boolean z) {
        this.b = z;
        postInvalidate();
    }

    public boolean isChecked() {
        return this.a;
    }

    public void setChecked(boolean z) {
        if (this.b) {
            if (this.a != z) {
                postInvalidate();
            }
            this.a = z;
            if (this.g != null) {
                this.g.onCheckedChange(this, this.a, false);
            }
        }
    }

    public void toggle() {
        a(false);
    }

    private void a(boolean z) {
        if (this.b) {
            this.a = !this.a;
            if (this.g != null) {
                this.g.onCheckedChange(this, this.a, z);
            }
            postInvalidate();
        }
    }

    public void setCheckedDrawable(Drawable drawable) {
        this.c = drawable;
        postInvalidate();
    }

    public void setUnCheckedDrawable(Drawable drawable) {
        this.c = drawable;
        postInvalidate();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.g = onCheckedChangeListener;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                boolean z = x >= (((float) (getWidth() - getPaddingRight())) - this.e) - ((float) getCheckWidth()) && y <= (((float) getPaddingTop()) + this.e) + ((float) getCheckHeight());
                this.f = z;
                break;
            case 1:
                if (this.f && x >= (((float) (getWidth() - getPaddingRight())) - this.e) - ((float) getCheckWidth()) && y <= (((float) getPaddingTop()) + this.e) + ((float) getCheckHeight()) && this.g != null) {
                    a(true);
                    break;
                }
        }
        if (this.f || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }
}
