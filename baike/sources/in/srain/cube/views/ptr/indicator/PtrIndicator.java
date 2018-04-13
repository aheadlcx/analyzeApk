package in.srain.cube.views.ptr.indicator;

import android.graphics.PointF;

public class PtrIndicator {
    public static final int POS_START = 0;
    protected int a = 0;
    private PointF b = new PointF();
    private float c;
    private float d;
    private int e = 0;
    private int f = 0;
    private int g;
    private int h = 0;
    private float i = 1.2f;
    private float j = 1.7f;
    private boolean k = false;
    private int l = -1;
    private int m = 0;

    public boolean isUnderTouch() {
        return this.k;
    }

    public float getResistance() {
        return this.j;
    }

    public void setResistance(float f) {
        this.j = f;
    }

    public void onRelease() {
        this.k = false;
    }

    public void onUIRefreshComplete() {
        this.m = this.e;
    }

    public boolean goDownCrossFinishPosition() {
        return this.e >= this.m;
    }

    protected void a(float f, float f2, float f3, float f4) {
        a(f3, f4 / this.j);
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        this.i = f;
        this.a = (int) (((float) this.g) * f);
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.i;
    }

    public int getOffsetToRefresh() {
        return this.a;
    }

    public void setOffsetToRefresh(int i) {
        this.i = (((float) this.g) * 1.0f) / ((float) i);
        this.a = i;
    }

    public void onPressDown(float f, float f2) {
        this.k = true;
        this.h = this.e;
        this.b.set(f, f2);
    }

    public final void onMove(float f, float f2) {
        a(f, f2, f - this.b.x, f2 - this.b.y);
        this.b.set(f, f2);
    }

    protected void a(float f, float f2) {
        this.c = f;
        this.d = f2;
    }

    public float getOffsetX() {
        return this.c;
    }

    public float getOffsetY() {
        return this.d;
    }

    public int getLastPosY() {
        return this.f;
    }

    public int getCurrentPosY() {
        return this.e;
    }

    public final void setCurrentPos(int i) {
        this.f = this.e;
        this.e = i;
        a(i, this.f);
    }

    protected void a(int i, int i2) {
    }

    public int getHeaderHeight() {
        return this.g;
    }

    public void setHeaderHeight(int i) {
        this.g = i;
        a();
    }

    protected void a() {
        this.a = (int) (this.i * ((float) this.g));
    }

    public void convertFrom(PtrIndicator ptrIndicator) {
        this.e = ptrIndicator.e;
        this.f = ptrIndicator.f;
        this.g = ptrIndicator.g;
    }

    public boolean hasLeftStartPosition() {
        return this.e > 0;
    }

    public boolean hasJustLeftStartPosition() {
        return this.f == 0 && hasLeftStartPosition();
    }

    public boolean hasJustBackToStartPosition() {
        return this.f != 0 && isInStartPosition();
    }

    public boolean isOverOffsetToRefresh() {
        return this.e >= getOffsetToRefresh();
    }

    public boolean hasMovedAfterPressedDown() {
        return this.e != this.h;
    }

    public boolean isInStartPosition() {
        return this.e == 0;
    }

    public boolean crossRefreshLineFromTopToBottom() {
        return this.f < getOffsetToRefresh() && this.e >= getOffsetToRefresh();
    }

    public boolean hasJustReachedHeaderHeightFromTopToBottom() {
        return this.f < this.g && this.e >= this.g;
    }

    public boolean isOverOffsetToKeepHeaderWhileLoading() {
        return this.e > getOffsetToKeepHeaderWhileLoading();
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        this.l = i;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return this.l >= 0 ? this.l : this.g;
    }

    public boolean isAlreadyHere(int i) {
        return this.e == i;
    }

    public float getLastPercent() {
        return this.g == 0 ? 0.0f : (((float) this.f) * 1.0f) / ((float) this.g);
    }

    public float getCurrentPercent() {
        return this.g == 0 ? 0.0f : (((float) this.e) * 1.0f) / ((float) this.g);
    }

    public boolean willOverTop(int i) {
        return i < 0;
    }
}
