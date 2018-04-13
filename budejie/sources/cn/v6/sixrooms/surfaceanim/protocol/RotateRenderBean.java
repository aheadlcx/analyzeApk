package cn.v6.sixrooms.surfaceanim.protocol;

public class RotateRenderBean extends TweenRenderBean {
    private float a;
    private float b;
    private PointF c;

    public float getStartValue() {
        return this.a;
    }

    public void setStartValue(float f) {
        this.a = f;
    }

    public float getEndValue() {
        return this.b;
    }

    public void setEndValue(float f) {
        this.b = f;
    }

    public PointF getAxisPoint() {
        return this.c;
    }

    public void setAxisPoint(PointF pointF) {
        this.c = pointF;
    }
}
