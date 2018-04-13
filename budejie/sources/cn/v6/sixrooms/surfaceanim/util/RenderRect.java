package cn.v6.sixrooms.surfaceanim.util;

public class RenderRect {
    private int a;
    private int b;

    public synchronized void setWidth(int i) {
        this.a = i;
    }

    public synchronized void setHeight(int i) {
        this.b = i;
    }

    public synchronized int getWidth() {
        return this.a;
    }

    public synchronized int getHeight() {
        return this.b;
    }
}
