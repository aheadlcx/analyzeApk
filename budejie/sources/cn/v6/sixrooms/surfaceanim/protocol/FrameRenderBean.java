package cn.v6.sixrooms.surfaceanim.protocol;

public class FrameRenderBean extends RenderBean {
    private int a;
    private String b;
    private String c;
    private int d;

    public String getImgHeader() {
        return this.b;
    }

    public void setImgHeader(String str) {
        this.b = str;
    }

    public int getImgExtend() {
        return this.d;
    }

    public void setImgExtend(int i) {
        this.d = i;
    }

    public void setStep(int i) {
        this.a = i;
    }

    public int getStep() {
        return this.a;
    }

    public String getImgType() {
        return this.c;
    }

    public void setImgType(String str) {
        this.c = str;
    }
}
