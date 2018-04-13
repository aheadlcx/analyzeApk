package cn.v6.sixrooms.surfaceanim.protocol;

public class ScaleRenderBean extends TweenRenderBean {
    private PointF a;
    private PointF b;
    private PointF c;
    private PointI[] d;
    private PointI[] e;

    public PointF getStartValue() {
        return this.a;
    }

    public void setStartValue(PointF pointF) {
        this.a = pointF;
    }

    public PointF getEndValue() {
        return this.b;
    }

    public void setEndValue(PointF pointF) {
        this.b = pointF;
    }

    public PointF getAxisPoint() {
        return this.c;
    }

    public void setAxisPoint(PointF pointF) {
        this.c = pointF;
    }

    public PointI[] getPointsX() {
        return this.d;
    }

    public void setPointsX(PointI[] pointIArr) {
        this.d = pointIArr;
    }

    public PointI[] getPointsY() {
        return this.e;
    }

    public void setPointsY(PointI[] pointIArr) {
        this.e = pointIArr;
    }
}
