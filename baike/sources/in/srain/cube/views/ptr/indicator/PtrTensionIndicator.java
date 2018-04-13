package in.srain.cube.views.ptr.indicator;

public class PtrTensionIndicator extends PtrIndicator {
    private float b = 0.5f;
    private float c;
    private float d;
    private float e = 0.0f;
    private float f;
    private int g;
    private float h = -1.0f;

    public void onPressDown(float f, float f2) {
        super.onPressDown(f, f2);
        this.c = f2;
        this.d = (float) getCurrentPosY();
    }

    public void onRelease() {
        super.onRelease();
        this.g = getCurrentPosY();
        this.h = this.f;
    }

    public void onUIRefreshComplete() {
        this.g = getCurrentPosY();
        this.h = getOverDragPercent();
    }

    public void setHeaderHeight(int i) {
        super.setHeaderHeight(i);
        this.e = (((float) i) * 4.0f) / 5.0f;
    }

    protected void a(float f, float f2, float f3, float f4) {
        if (f2 < this.c) {
            super.a(f, f2, f3, f4);
            return;
        }
        float f5 = ((f2 - this.c) * this.b) + this.d;
        float f6 = f5 / this.e;
        if (f6 < 0.0f) {
            a(f3, 0.0f);
            return;
        }
        this.f = f6;
        f6 = Math.min(1.0f, Math.abs(f6));
        f5 = Math.max(0.0f, Math.min(f5 - this.e, this.e * 2.0f) / this.e);
        a(f, (float) (((int) ((((((float) (((double) (f5 / 4.0f)) - Math.pow((double) (f5 / 4.0f), 2.0d))) * 2.0f) * this.e) / 2.0f) + (f6 * this.e))) - getCurrentPosY()));
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return getOffsetToRefresh();
    }

    public int getOffsetToRefresh() {
        return (int) this.e;
    }

    public float getOverDragPercent() {
        if (isUnderTouch()) {
            return this.f;
        }
        if (this.h <= 0.0f) {
            return (1.0f * ((float) getCurrentPosY())) / ((float) getOffsetToKeepHeaderWhileLoading());
        }
        return (this.h * ((float) getCurrentPosY())) / ((float) this.g);
    }
}
