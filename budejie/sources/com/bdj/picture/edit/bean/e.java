package com.bdj.picture.edit.bean;

public class e implements Cloneable {
    private CPoint a;
    private float b;
    private float c;
    private c d;

    public e(CPoint cPoint, float f, float f2) {
        this.a = cPoint;
        this.b = f;
        this.c = f2;
    }

    public CPoint a() {
        return this.a;
    }

    public void a(CPoint cPoint) {
        this.a = cPoint;
    }

    public float b() {
        return this.b;
    }

    public void a(float f) {
        this.b = f;
    }

    public float c() {
        return this.c;
    }

    public void b(float f) {
        this.c = f;
    }

    public c d() {
        return this.d;
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    public Object clone() {
        e eVar = null;
        try {
            eVar = (e) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        eVar.a = (CPoint) this.a.clone();
        eVar.d = (c) this.d.clone();
        return eVar;
    }
}
