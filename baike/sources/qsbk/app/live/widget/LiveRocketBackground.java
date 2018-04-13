package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class LiveRocketBackground extends ImageView {
    Rect a;
    Rect b;
    Rect c;
    private float d;
    private float e;
    private Handler f;
    private Bitmap g;
    private float h;
    private final int i;
    private float j;

    public LiveRocketBackground(Context context) {
        this(context, null);
    }

    public LiveRocketBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0.0f;
        this.e = 0.0f;
        this.i = 25;
        this.a = new Rect();
        this.b = new Rect();
        this.c = new Rect();
        a();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = (float) getWidth();
        float height = (float) getHeight();
        if (width > 0.0f && height > 0.0f) {
            if (this.g == null) {
                this.g = createRepeater(width, height);
                this.h = (((float) this.g.getHeight()) * width) / ((float) this.g.getWidth());
            }
            if (this.g != null) {
                canvas.clipRect(0.0f, 0.0f, width, height);
                this.a.set(0, 0, this.g.getWidth(), this.g.getHeight());
                this.b.set(0, (int) this.d, (int) width, (int) (this.d + this.h));
                this.c.set(0, (int) this.e, (int) width, (int) (this.e + this.h));
                canvas.drawBitmap(this.g, this.a, this.b, null);
                canvas.drawBitmap(this.g, this.a, this.c, null);
            }
        }
    }

    private void a() {
        this.j = (((float) WindowUtils.dp2Px(2000)) / 4.0f) / 10.0f;
        this.f = new hm(this);
    }

    public Bitmap createRepeater(float f, float f2) {
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.live_rocket_bg);
            if (decodeResource != null) {
                int i;
                int height = (((int) f) * decodeResource.getHeight()) / decodeResource.getWidth();
                int i2 = (int) (((((float) height) + f2) - 1.0f) / ((float) height));
                if (((float) height) >= f2 || ((int) f2) % height == 0) {
                    i = i2;
                } else {
                    i = i2 + 1;
                }
                int height2 = decodeResource.getHeight();
                i2 = i * height2;
                this.h = (float) (height * i);
                if (this.e == this.d) {
                    this.e = this.d - this.h;
                    if (this.e < (-this.h)) {
                        this.e = this.d + this.h;
                    }
                }
                Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), i2, Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                for (height = 0; height < i; height++) {
                    canvas.drawBitmap(decodeResource, 0.0f, (float) (height * height2), null);
                }
                decodeResource.recycle();
                return createBitmap;
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }

    public void show(int i) {
        this.f.removeMessages(1);
        this.f.sendEmptyMessageDelayed(1, (long) i);
    }

    public void clear() {
        this.f.removeMessages(1);
        setVisibility(8);
        this.d = 0.0f;
        this.e = 0.0f;
        if (this.g != null) {
            this.g.recycle();
            this.g = null;
        }
    }
}
