package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ExpPrgressView extends ProgressBar {
    public ExpPrgressView(Context context) {
        this(context, null);
    }

    public ExpPrgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpPrgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
