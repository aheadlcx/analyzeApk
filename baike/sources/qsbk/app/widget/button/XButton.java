package qsbk.app.widget.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class XButton extends Button {
    private Bitmap a = null;
    private Drawable b = null;

    public XButton(Context context) {
        super(context);
    }

    public XButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public XButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void reset() {
        this.b = null;
        this.a = null;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (this.b == null) {
            this.b = getBackground();
        }
        if (this.a == null) {
            int width = getWidth();
            int height = getHeight();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            layout(0, 0, width, height);
            draw(canvas);
            int[] iArr = new int[(width * height)];
            createBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            while (i != iArr.length) {
                iArr[i] = a(iArr[i]);
                i++;
            }
            this.a = Bitmap.createBitmap(iArr, width, height, Config.ARGB_8888);
            setWidth(width);
            setHeight(height);
        }
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                setBackgroundDrawable(new BitmapDrawable(this.a));
                break;
            default:
                setBackgroundDrawable(this.b);
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private int a(int i) {
        int i2 = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8;
        int i3 = (16711680 & i) >> 16;
        int i4 = i >> 24;
        return a((((float) (i & 255)) * 0.5f) + ((float) 50)) | ((a((((float) i2) * 0.5f) + ((float) 50)) << 8) | ((a((((float) i3) * 0.5f) + ((float) 50)) << 16) | (i4 << 24)));
    }

    private int a(float f) {
        int i = (int) f;
        if (i > 255) {
            return 255;
        }
        return i;
    }
}
