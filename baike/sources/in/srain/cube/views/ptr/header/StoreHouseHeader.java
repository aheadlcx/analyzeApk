package in.srain.cube.views.ptr.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Transformation;
import com.xiaomi.mipush.sdk.Constants;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import java.util.ArrayList;

public class StoreHouseHeader extends View implements PtrUIHandler {
    private int a = -1;
    private float b = 1.0f;
    private int c = -1;
    private float d = 0.7f;
    private int e = -1;
    private float f = 0.0f;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private float k = 0.4f;
    private float l = 1.0f;
    private float m = 0.4f;
    public ArrayList<StoreHouseBarItem> mItemList = new ArrayList();
    private int n = 1000;
    private int o = 1000;
    private int p = 400;
    private Transformation q = new Transformation();
    private boolean r = false;
    private a s = new a();
    private int t = -1;

    private class a implements Runnable {
        final /* synthetic */ StoreHouseHeader a;
        private int b;
        private int c;
        private int d;
        private int e;
        private boolean f;

        private a(StoreHouseHeader storeHouseHeader) {
            this.a = storeHouseHeader;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = true;
        }

        private void a() {
            this.f = true;
            this.b = 0;
            this.e = this.a.n / this.a.mItemList.size();
            this.c = this.a.o / this.e;
            this.d = (this.a.mItemList.size() / this.c) + 1;
            run();
        }

        public void run() {
            int i = this.b % this.c;
            for (int i2 = 0; i2 < this.d; i2++) {
                int i3 = (this.c * i2) + i;
                if (i3 <= this.b) {
                    StoreHouseBarItem storeHouseBarItem = (StoreHouseBarItem) this.a.mItemList.get(i3 % this.a.mItemList.size());
                    storeHouseBarItem.setFillAfter(false);
                    storeHouseBarItem.setFillEnabled(true);
                    storeHouseBarItem.setFillBefore(false);
                    storeHouseBarItem.setDuration((long) this.a.p);
                    storeHouseBarItem.start(this.a.l, this.a.m);
                }
            }
            this.b++;
            if (this.f) {
                this.a.postDelayed(this, (long) this.e);
            }
        }

        private void b() {
            this.f = false;
            this.a.removeCallbacks(this);
        }
    }

    public StoreHouseHeader(Context context) {
        super(context);
        a();
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        PtrLocalDisplay.init(getContext());
        this.a = PtrLocalDisplay.dp2px(1.0f);
        this.c = PtrLocalDisplay.dp2px(40.0f);
        this.e = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2;
    }

    private void setProgress(float f) {
        this.f = f;
    }

    public int getLoadingAniDuration() {
        return this.n;
    }

    public void setLoadingAniDuration(int i) {
        this.n = i;
        this.o = i;
    }

    public StoreHouseHeader setLineWidth(int i) {
        this.a = i;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            ((StoreHouseBarItem) this.mItemList.get(i2)).setLineWidth(i);
        }
        return this;
    }

    public StoreHouseHeader setTextColor(int i) {
        this.t = i;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            ((StoreHouseBarItem) this.mItemList.get(i2)).setColor(i);
        }
        return this;
    }

    public StoreHouseHeader setDropHeight(int i) {
        this.c = i;
        return this;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec((getTopOffset() + this.h) + getBottomOffset(), 1073741824));
        this.i = (getMeasuredWidth() - this.g) / 2;
        this.j = getTopOffset();
        this.c = getTopOffset();
    }

    private int getTopOffset() {
        return getPaddingTop() + PtrLocalDisplay.dp2px(10.0f);
    }

    private int getBottomOffset() {
        return getPaddingBottom() + PtrLocalDisplay.dp2px(10.0f);
    }

    public void initWithString(String str) {
        initWithString(str, 25);
    }

    public void initWithString(String str, int i) {
        initWithPointList(StoreHousePath.getPath(str, ((float) i) * 0.01f, 14));
    }

    public void initWithStringArray(int i) {
        String[] stringArray = getResources().getStringArray(i);
        ArrayList arrayList = new ArrayList();
        for (String split : stringArray) {
            String[] split2 = split.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            Object obj = new float[4];
            for (int i2 = 0; i2 < 4; i2++) {
                obj[i2] = Float.parseFloat(split2[i2]);
            }
            arrayList.add(obj);
        }
        initWithPointList(arrayList);
    }

    public float getScale() {
        return this.b;
    }

    public void setScale(float f) {
        this.b = f;
    }

    public void initWithPointList(ArrayList<float[]> arrayList) {
        int i;
        if (this.mItemList.size() > 0) {
            i = 1;
        } else {
            i = 0;
        }
        this.mItemList.clear();
        int i2 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        while (i2 < arrayList.size()) {
            float[] fArr = (float[]) arrayList.get(i2);
            PointF pointF = new PointF(((float) PtrLocalDisplay.dp2px(fArr[0])) * this.b, ((float) PtrLocalDisplay.dp2px(fArr[1])) * this.b);
            PointF pointF2 = new PointF(((float) PtrLocalDisplay.dp2px(fArr[2])) * this.b, ((float) PtrLocalDisplay.dp2px(fArr[3])) * this.b);
            float max = Math.max(Math.max(f2, pointF.x), pointF2.x);
            float max2 = Math.max(Math.max(f, pointF.y), pointF2.y);
            StoreHouseBarItem storeHouseBarItem = new StoreHouseBarItem(i2, pointF, pointF2, this.t, this.a);
            storeHouseBarItem.resetPosition(this.e);
            this.mItemList.add(storeHouseBarItem);
            i2++;
            f = max2;
            f2 = max;
        }
        this.g = (int) Math.ceil((double) f2);
        this.h = (int) Math.ceil((double) f);
        if (i != 0) {
            requestLayout();
        }
    }

    private void b() {
        this.r = true;
        this.s.a();
        invalidate();
    }

    private void c() {
        this.r = false;
        this.s.b();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.f;
        int save = canvas.save();
        int size = this.mItemList.size();
        for (int i = 0; i < size; i++) {
            canvas.save();
            StoreHouseBarItem storeHouseBarItem = (StoreHouseBarItem) this.mItemList.get(i);
            float f2 = storeHouseBarItem.midPoint.x + ((float) this.i);
            float f3 = storeHouseBarItem.midPoint.y + ((float) this.j);
            if (this.r) {
                storeHouseBarItem.getTransformation(getDrawingTime(), this.q);
                canvas.translate(f2, f3);
            } else if (f == 0.0f) {
                storeHouseBarItem.resetPosition(this.e);
            } else {
                float f4 = ((1.0f - this.d) * ((float) i)) / ((float) size);
                float f5 = (1.0f - this.d) - f4;
                if (f == 1.0f || f >= 1.0f - f5) {
                    canvas.translate(f2, f3);
                    storeHouseBarItem.setAlpha(this.k);
                } else {
                    if (f <= f4) {
                        f4 = 0.0f;
                    } else {
                        f4 = Math.min(1.0f, (f - f4) / this.d);
                    }
                    f2 += storeHouseBarItem.translationX * (1.0f - f4);
                    f3 += ((float) (-this.c)) * (1.0f - f4);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(360.0f * f4);
                    matrix.postScale(f4, f4);
                    matrix.postTranslate(f2, f3);
                    storeHouseBarItem.setAlpha(f4 * this.k);
                    canvas.concat(matrix);
                }
            }
            storeHouseBarItem.draw(canvas);
            canvas.restore();
        }
        if (this.r) {
            invalidate();
        }
        canvas.restoreToCount(save);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        c();
        for (int i = 0; i < this.mItemList.size(); i++) {
            ((StoreHouseBarItem) this.mItemList.get(i)).resetPosition(this.e);
        }
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        b();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        c();
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        setProgress(Math.min(1.0f, ptrIndicator.getCurrentPercent()));
        invalidate();
    }
}
