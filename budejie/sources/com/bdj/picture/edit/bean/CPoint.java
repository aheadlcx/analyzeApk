package com.bdj.picture.edit.bean;

import android.graphics.Point;

public class CPoint extends Point implements Cloneable {
    public CPoint(int i, int i2) {
        super(i, i2);
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
}
