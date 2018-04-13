package cn.v6.sixrooms.surfaceanim.protocol;

import java.io.Serializable;

public class SceneBean implements Serializable {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private PointI[] g = new PointI[2];
    private int h;
    private int i;
    private ElementBean[] j;

    public ElementBean[] getElements() {
        return this.j;
    }

    public void setElements(ElementBean[] elementBeanArr) {
        this.j = elementBeanArr;
    }

    public int getExtend() {
        return this.a;
    }

    public void setExtend(int i) {
        this.a = i;
    }

    public int getId() {
        return this.b;
    }

    public void setId(int i) {
        this.b = i;
    }

    public int getResolutionW() {
        return this.c;
    }

    public void setResolutionW(int i) {
        this.c = i;
    }

    public int getResolutionY() {
        return this.d;
    }

    public void setResolutionY(int i) {
        this.d = i;
    }

    public int getFps() {
        return this.e;
    }

    public void setFps(int i) {
        this.e = i;
    }

    public int getSupportScreen() {
        return this.f;
    }

    public void setSupportScreen(int i) {
        this.f = i;
    }

    public PointI[] getPosition() {
        return this.g;
    }

    public void setPosition(PointI[] pointIArr) {
        this.g = pointIArr;
    }

    public int getFrames() {
        return this.h;
    }

    public void setFrames(int i) {
        this.h = i;
    }

    public int getEleCount() {
        return this.i;
    }

    public void setEleCount(int i) {
        this.i = i;
    }
}
