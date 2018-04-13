package qsbk.app.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class RangedProgressTextView extends TextView {
    public static final int DEFAULT_MAX_PROGRESS = 100;
    public static final int MIN_PROGRESS = 0;
    private int a = 0;
    private int b = 100;

    public RangedProgressTextView(Context context) {
        super(context);
    }

    public RangedProgressTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RangedProgressTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setMaxProgress(int i) {
        if (i < this.a) {
            throw new IllegalArgumentException(String.format("%s is smaller than min %s", new Object[]{Integer.valueOf(i), Integer.valueOf(this.a)}));
        } else {
            this.b = i;
        }
    }

    public void setMinProgress(int i) {
        if (i < 0 || i > this.b) {
            i = 0;
        }
        this.a = i;
    }

    public void setProgress(int i) {
        int min = Math.min(Math.max(i, this.a), this.b);
        setText(min + "%");
        Drawable background = getBackground();
        if (background != null) {
            background.setLevel((100 - min) * 100);
        }
    }
}
