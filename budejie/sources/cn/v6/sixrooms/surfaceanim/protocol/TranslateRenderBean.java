package cn.v6.sixrooms.surfaceanim.protocol;

public class TranslateRenderBean extends TweenRenderBean {
    private PointI a;
    private PointI b;

    public PointI getStartValue() {
        return this.a;
    }

    public void setStartValue(PointI pointI) {
        this.a = pointI;
    }

    public PointI getEndValue() {
        return this.b;
    }

    public void setEndValue(PointI pointI) {
        this.b = pointI;
    }
}
