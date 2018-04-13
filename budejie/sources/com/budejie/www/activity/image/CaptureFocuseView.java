package com.budejie.www.activity.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CaptureFocuseView extends View {
    private Paint a;

    public CaptureFocuseView(Context context) {
        super(context);
        a();
    }

    public CaptureFocuseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CaptureFocuseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setColor(-1);
        this.a.setStrokeWidth(4.0f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int i = width / 2;
        int i2 = height / 2;
        canvas.drawLine(0.0f, 0.0f, (float) width, 0.0f, this.a);
        canvas.drawLine((float) width, 0.0f, (float) width, (float) height, this.a);
        canvas.drawLine((float) width, (float) height, 0.0f, (float) height, this.a);
        canvas.drawLine(0.0f, (float) height, 0.0f, 0.0f, this.a);
        canvas.drawLine((float) i, 0.0f, (float) i, 10.0f, this.a);
        canvas.drawLine((float) i, (float) height, (float) i, (float) (height - 10), this.a);
        canvas.drawLine(0.0f, (float) i2, 10.0f, (float) i2, this.a);
        canvas.drawLine((float) width, (float) i2, (float) (width - 10), (float) i2, this.a);
    }
}
