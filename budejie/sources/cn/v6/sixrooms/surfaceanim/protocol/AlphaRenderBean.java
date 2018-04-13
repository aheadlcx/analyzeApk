package cn.v6.sixrooms.surfaceanim.protocol;

public class AlphaRenderBean extends TweenRenderBean {
    private int a;
    private int b;

    public int getStartValue() {
        return this.a;
    }

    public void setStartValue(int i) {
        this.a = i;
    }

    public int getEndValue() {
        return this.b;
    }

    public void setEndValue(int i) {
        this.b = i;
    }
}
