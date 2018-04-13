package cn.v6.sixrooms.surfaceanim.protocol;

import java.io.Serializable;

public class RenderBean implements Serializable {
    private int a;
    private int b;

    public void setStartFrame(int i) {
        this.a = i;
    }

    public int getStartFrame() {
        return this.a;
    }

    public void setEndFrame(int i) {
        this.b = i;
    }

    public int getEndFrame() {
        return this.b;
    }
}
