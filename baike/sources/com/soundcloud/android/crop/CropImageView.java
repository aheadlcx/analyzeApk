package com.soundcloud.android.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.soundcloud.android.crop.ImageViewTouchBase.Recycler;
import java.util.ArrayList;
import java.util.Iterator;

public class CropImageView extends ImageViewTouchBase {
    ArrayList<k> a = new ArrayList();
    k b;
    Context c;
    private float k;
    private float l;
    private int m;
    private int n;

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ Matrix getUnrotatedMatrix() {
        return super.getUnrotatedMatrix();
    }

    public /* bridge */ /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public /* bridge */ /* synthetic */ boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    public /* bridge */ /* synthetic */ void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
    }

    public /* bridge */ /* synthetic */ void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        super.setImageBitmapResetBase(bitmap, z);
    }

    public /* bridge */ /* synthetic */ void setImageRotateBitmapResetBase(o oVar, boolean z) {
        super.setImageRotateBitmapResetBase(oVar, z);
    }

    public /* bridge */ /* synthetic */ void setRecycler(Recycler recycler) {
        super.setRecycler(recycler);
    }

    public CropImageView(Context context) {
        super(context);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f.getBitmap() != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                k kVar = (k) it.next();
                kVar.c.set(getUnrotatedMatrix());
                kVar.invalidate();
                if (kVar.hasFocus()) {
                    b(kVar);
                }
            }
        }
    }

    protected void a(float f, float f2, float f3) {
        super.a(f, f2, f3);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            k kVar = (k) it.next();
            kVar.c.set(getUnrotatedMatrix());
            kVar.invalidate();
        }
    }

    protected void a(float f, float f2) {
        super.a(f, f2);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            k kVar = (k) it.next();
            kVar.c.postTranslate(f, f2);
            kVar.invalidate();
        }
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (((CropImageActivity) this.c).isSaving()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    k kVar = (k) it.next();
                    int hit = kVar.getHit(motionEvent.getX(), motionEvent.getY());
                    if (hit != 1) {
                        this.m = hit;
                        this.b = kVar;
                        this.k = motionEvent.getX();
                        this.l = motionEvent.getY();
                        this.n = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.b.setMode(hit == 32 ? b.Move : b.Grow);
                        break;
                    }
                }
                break;
            case 1:
                if (this.b != null) {
                    b(this.b);
                    this.b.setMode(b.None);
                }
                this.b = null;
                a();
                break;
            case 2:
                if (this.b != null && motionEvent.getPointerId(motionEvent.getActionIndex()) == this.n) {
                    this.b.a(this.m, motionEvent.getX() - this.k, motionEvent.getY() - this.l);
                    this.k = motionEvent.getX();
                    this.l = motionEvent.getY();
                }
                if (getScale() == 1.0f) {
                    a();
                    break;
                }
                break;
        }
        return true;
    }

    private void a(k kVar) {
        Rect rect = kVar.b;
        int max = Math.max(0, getLeft() - rect.left);
        int min = Math.min(0, getRight() - rect.right);
        int max2 = Math.max(0, getTop() - rect.top);
        int min2 = Math.min(0, getBottom() - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 == 0) {
            max2 = min2;
        }
        if (max != 0 || max2 != 0) {
            b((float) max, (float) max2);
        }
    }

    private void b(k kVar) {
        Rect rect = kVar.b;
        float width = (float) getWidth();
        float max = Math.max(1.0f, Math.min((width / ((float) rect.width())) * 0.6f, (((float) getHeight()) / ((float) rect.height())) * 0.6f) * getScale());
        if (((double) (Math.abs(max - getScale()) / max)) > 0.1d) {
            float[] fArr = new float[]{kVar.a.centerX(), kVar.a.centerY()};
            getUnrotatedMatrix().mapPoints(fArr);
            a(max, fArr[0], fArr[1], 300.0f);
        }
        a(kVar);
    }

    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((k) it.next()).a(canvas);
        }
    }

    public void add(k kVar) {
        this.a.add(kVar);
        invalidate();
    }
}
