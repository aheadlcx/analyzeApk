package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import qsbk.app.R;

public final class AspectRatioImageView extends QBImageView {
    public static final int RESIZE_MODE_FILL = 3;
    public static final int RESIZE_MODE_FIT = 0;
    public static final int RESIZE_MODE_FIXED_HEIGHT = 2;
    public static final int RESIZE_MODE_FIXED_WIDTH = 1;
    public static final int RESIZE_MODE_ZOOM = 4;
    private float a;
    private int b;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResizeMode {
    }

    public AspectRatioImageView(Context context) {
        this(context, null);
    }

    public AspectRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.AspectRatioImageView, 0, 0);
            try {
                this.b = obtainStyledAttributes.getInt(0, 0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setAspectRatio(float f) {
        if (this.a != f) {
            this.a = f;
            requestLayout();
        }
    }

    public int getResizeMode() {
        return this.b;
    }

    public void setResizeMode(int i) {
        if (this.b != i) {
            this.b = i;
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.b != 3 && this.a > 0.0f) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f = (this.a / (((float) measuredWidth) / ((float) measuredHeight))) - 1.0f;
            if (Math.abs(f) > 0.01f) {
                switch (this.b) {
                    case 1:
                        measuredHeight = (int) (((float) measuredWidth) / this.a);
                        break;
                    case 2:
                        measuredWidth = (int) (((float) measuredHeight) * this.a);
                        break;
                    case 4:
                        if (f <= 0.0f) {
                            measuredHeight = (int) (((float) measuredWidth) / this.a);
                            break;
                        } else {
                            measuredWidth = (int) (((float) measuredHeight) * this.a);
                            break;
                        }
                    default:
                        if (f <= 0.0f) {
                            measuredWidth = (int) (((float) measuredHeight) * this.a);
                            break;
                        } else {
                            measuredHeight = (int) (((float) measuredWidth) / this.a);
                            break;
                        }
                }
                setMeasuredDimension(MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
        }
    }
}
