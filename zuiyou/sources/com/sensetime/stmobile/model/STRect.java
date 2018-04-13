package com.sensetime.stmobile.model;

import android.graphics.Rect;

public class STRect {
    private int bottom;
    private int left;
    private int right;
    private int top;

    public STRect(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public Rect getRect() {
        Rect rect = new Rect();
        rect.left = this.left;
        rect.top = this.top;
        rect.right = this.right;
        rect.bottom = this.bottom;
        return rect;
    }
}
