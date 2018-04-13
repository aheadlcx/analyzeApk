package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class LoadingBar extends ImageView {
    private Bitmap a;
    private int b = 25;
    private float c;
    private float d;
    private Rect e = new Rect();
    private boolean f;

    public LoadingBar(Context context) {
        super(context);
    }

    public LoadingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null && this.a != null && !this.a.isRecycled()) {
            canvas.getClipBounds(this.e);
            while (this.c <= ((float) (-this.a.getWidth()))) {
                this.c += (float) this.a.getWidth();
            }
            float f = this.c;
            int i = 0;
            while (f < ((float) this.e.width())) {
                int width = this.a.getWidth();
                canvas.drawBitmap(this.a, f, 0.0f, null);
                f += (float) width;
                i++;
            }
            if (this.f) {
                this.c -= this.d;
                postInvalidateDelayed((long) this.b);
            }
        }
    }

    private void a() {
        this.d = (((float) WindowUtils.dp2Px(20)) / ((float) ((1000 / this.b) / 10))) / 10.0f;
        try {
            this.a = BitmapFactory.decodeResource(getResources(), R.drawable.live_connecting);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
    }

    public void show(int i) {
        setVisibility(0);
        if (!this.f) {
            this.f = true;
            postInvalidateDelayed(i > this.b ? (long) i : (long) this.b);
        }
    }

    public void stop() {
        if (this.f) {
            this.f = false;
            invalidate();
        }
    }

    public void hide() {
        stop();
        setVisibility(8);
    }

    public void releaseResource() {
        hide();
        if (this.a != null) {
            this.a.recycle();
            this.a = null;
        }
    }

    public void setSpeed(int i) {
        this.d = (((float) WindowUtils.dp2Px(i)) / ((float) ((1000 / this.b) / 10))) / 10.0f;
        if (this.f) {
            invalidate();
        }
    }
}
