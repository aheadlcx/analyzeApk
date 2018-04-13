package cn.v6.sixrooms.surfaceanim.protocol;

public class XfermodeBean extends RenderBean {
    public static final int NONE_LAYER = 0;
    public static final int NONE_MODE = -1;
    public static final int RESTORE_LAYER = 2;
    public static final int SAVE_LAYER = 1;
    private int a = -1;
    private int b;

    public void setLayer(int i) {
        this.b = i;
    }

    public int getLayer() {
        return this.b;
    }

    public int getMode() {
        return this.a;
    }

    public void setMode(int i) {
        this.a = i;
    }
}
