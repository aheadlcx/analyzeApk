package cn.v6.sixrooms.surfaceanim.protocol;

public class TweenRenderBean extends RenderBean {
    private int a;
    private PointI[] b;

    public int getConversion() {
        return this.a;
    }

    public void setConversion(int i) {
        this.a = i;
    }

    public PointI[] getPoints() {
        return this.b;
    }

    public void setPoints(PointI[] pointIArr) {
        this.b = pointIArr;
    }
}
