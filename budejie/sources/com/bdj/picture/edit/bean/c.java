package com.bdj.picture.edit.bean;

public class c implements Cloneable {
    private int a;
    private int b;
    private int c;
    private int d;

    public c(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public String toString() {
        return "Location [left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + "]";
    }
}
