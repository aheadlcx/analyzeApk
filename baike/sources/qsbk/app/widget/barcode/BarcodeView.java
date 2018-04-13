package qsbk.app.widget.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import java.util.Observable;
import java.util.Observer;
import qsbk.app.R;
import qsbk.app.utils.LogUtil;

public class BarcodeView extends View implements Observer {
    private static final String c = BarcodeView.class.getName();
    protected final Paint a = new Paint(2);
    protected Bitmap b;
    private final Rect d = new Rect();
    private final Rect e = new Rect();
    private final Rect f = new Rect();
    private final Rect g = new Rect();
    private final Rect h = new Rect();
    private final Paint i = new Paint();
    private float j;
    private ZoomState k;
    private Bitmap l;
    private Bitmap m;
    private Rect n;
    private OnCloseListener o;

    public interface OnCloseListener {
        void onClose();
    }

    public BarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i.setColor(getResources().getColor(R.color.qb_mask_bg));
    }

    public Bitmap getLeftTopImage() {
        return this.m;
    }

    public void setLeftTopImage(Bitmap bitmap) {
        this.m = bitmap;
    }

    public Bitmap getRightBottomImage() {
        return this.l;
    }

    public void setRightBottomImage(Bitmap bitmap) {
        this.l = bitmap;
    }

    public void setImage(Bitmap bitmap) {
        this.b = bitmap;
        a();
        invalidate();
    }

    public ZoomState getZoomState() {
        return this.k;
    }

    public void setZoomState(ZoomState zoomState) {
        if (this.k != null) {
            this.k.deleteObserver(this);
        }
        this.k = zoomState;
        this.k.addObserver(this);
        invalidate();
    }

    public Rect getInnerRect() {
        return this.f;
    }

    public void setBoundsRect(Rect rect) {
        this.n = rect;
    }

    private void a() {
        if (this.b != null) {
            this.j = (((float) this.b.getWidth()) / ((float) this.b.getHeight())) / (((float) getWidth()) / ((float) getHeight()));
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.b != null && this.k != null) {
            int width = getWidth();
            int height = getHeight();
            int width2 = this.b.getWidth();
            int height2 = this.b.getHeight();
            float panX = this.k.getPanX();
            float panY = this.k.getPanY();
            float zoomX = (this.k.getZoomX(this.j) * ((float) width)) / ((float) width2);
            float zoomY = (this.k.getZoomY(this.j) * ((float) height)) / ((float) height2);
            this.d.left = (int) ((panX * ((float) width2)) - (((float) width) / (zoomX * 2.0f)));
            this.d.top = (int) ((panY * ((float) height2)) - (((float) height) / (zoomY * 2.0f)));
            this.d.right = (int) ((((float) width) / zoomX) + ((float) this.d.left));
            this.d.bottom = (int) ((((float) height) / zoomY) + ((float) this.d.top));
            if (this.d.left >= 0 || this.d.top >= 0 || this.d.bottom <= height2 || this.d.right <= width2) {
                this.d.left = this.e.left;
                this.d.bottom = this.e.bottom;
                this.d.right = this.e.right;
                this.d.top = this.e.top;
                if (this.d.left >= 0) {
                    this.d.left = 1;
                }
                if (this.d.right <= width2) {
                    this.d.right = width2 + 1;
                }
                if (this.d.top >= 0) {
                    this.d.top = 1;
                }
                if (this.d.bottom <= height2) {
                    this.d.bottom = height2;
                }
            } else {
                Rect rect;
                this.f.left = getLeft();
                this.f.top = getTop();
                this.f.right = getRight();
                this.f.bottom = getBottom();
                if (this.d.left < 0) {
                    rect = this.f;
                    rect.left = (int) (((float) rect.left) + (((float) (-this.d.left)) * zoomX));
                    this.d.left = 0;
                }
                if (this.d.right > width2) {
                    rect = this.f;
                    rect.right = (int) (((float) rect.right) - (((float) (this.d.right - width2)) * zoomX));
                    this.d.right = width2;
                }
                if (this.d.top < 0) {
                    rect = this.f;
                    rect.top = (int) (((float) rect.top) + (((float) (-this.d.top)) * zoomY));
                    this.d.top = 0;
                }
                if (this.d.bottom > height2) {
                    rect = this.f;
                    rect.bottom = (int) (((float) rect.bottom) - (((float) (this.d.bottom - height2)) * zoomY));
                    this.d.bottom = height2;
                }
                b();
                this.e.left = this.d.left;
                this.e.bottom = this.d.bottom;
                this.e.right = this.d.right;
                this.e.top = this.d.top;
            }
            LogUtil.d("srcRect:" + this.d.toString());
            canvas.drawRect(this.f, this.i);
            canvas.drawBitmap(this.b, this.d, this.f, this.a);
            a(canvas, this.f);
            b(canvas, this.f);
        }
    }

    private void a(Canvas canvas, Rect rect) {
        if (this.m != null) {
            int width = this.m.getWidth();
            int height = this.m.getHeight();
            this.h.left = rect.left - (width / 2);
            this.h.top = rect.top - (height / 2);
            this.h.bottom = height + this.h.top;
            this.h.right = width + this.h.left;
            canvas.drawBitmap(this.m, null, this.h, this.a);
        }
    }

    private void b(Canvas canvas, Rect rect) {
        if (this.l != null) {
            int width = this.l.getWidth();
            int height = this.l.getHeight();
            this.g.right = rect.right + (width / 2);
            this.g.bottom = rect.bottom + (height / 2);
            this.g.top = this.g.bottom - height;
            this.g.left = this.g.right - width;
            canvas.drawBitmap(this.l, null, this.g, this.a);
        }
    }

    public Rect getRightBottomRect() {
        return this.g;
    }

    private synchronized void b() {
        if (this.n != null) {
            int i = this.f.right - this.f.left;
            int i2 = this.f.bottom - this.f.top;
            if (this.f.left <= this.n.left) {
                this.f.left = this.n.left;
                this.f.right = this.f.left + i;
            }
            if (this.f.right >= this.n.right) {
                this.f.right = this.n.right;
                this.f.left = this.f.right - i;
            }
            if (this.f.top <= this.n.top) {
                this.f.top = this.n.top;
                this.f.bottom = this.f.top + i2;
            }
            if (this.f.bottom >= this.n.bottom) {
                this.f.bottom = this.n.bottom;
                this.f.top = this.f.bottom - i2;
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a();
    }

    public void update(Observable observable, Object obj) {
        invalidate();
    }

    public OnCloseListener getOnCloseListener() {
        return this.o;
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.o = onCloseListener;
    }
}
