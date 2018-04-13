package qsbk.app.widget.clip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ClipView extends View {
    private Paint a;
    private int b;
    private int c;

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClipView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        this.c = height;
        this.b = (this.c / 8) * 5;
        if (this.b > width) {
            this.b = width;
        }
        this.a.setColor(Integer.MIN_VALUE);
        canvas.drawRect(0.0f, 0.0f, (float) ((width - this.b) / 2), (float) height, this.a);
        canvas.drawRect((float) ((this.b + width) / 2), 0.0f, (float) width, (float) height, this.a);
        this.a.setColor(-1);
        this.a.setStrokeWidth(1.0f);
        canvas.drawLine((float) ((width - this.b) / 2), 0.0f, (float) ((this.b + width) / 2), 0.0f, this.a);
        canvas.drawLine((float) ((width - this.b) / 2), (float) (height - 1), (float) ((this.b + width) / 2), (float) (height - 1), this.a);
        canvas.drawLine((float) ((width - this.b) / 2), 0.0f, (float) ((width - this.b) / 2), (float) height, this.a);
        canvas.drawLine((float) ((this.b + width) / 2), 0.0f, (float) ((this.b + width) / 2), (float) height, this.a);
    }

    public int getClipWidth() {
        return this.b;
    }

    public int getClipHeight() {
        return this.c;
    }
}
