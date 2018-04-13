package com.edmodo.cropper.a;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

public class c {
    public static Rect a(Bitmap bitmap, View view) {
        return b(bitmap.getWidth(), bitmap.getHeight(), view.getWidth(), view.getHeight());
    }

    public static Rect a(int i, int i2, int i3, int i4) {
        return b(i, i2, i3, i4);
    }

    private static Rect b(int i, int i2, int i3, int i4) {
        double d;
        int i5;
        int round;
        double d2 = Double.POSITIVE_INFINITY;
        double d3 = Double.POSITIVE_INFINITY;
        if (i3 < i) {
            d2 = ((double) i3) / ((double) i);
        }
        if (i4 < i2) {
            d3 = ((double) i4) / ((double) i2);
        }
        if (d2 == Double.POSITIVE_INFINITY && d3 == Double.POSITIVE_INFINITY) {
            d = (double) i;
            d2 = (double) i2;
        } else if (d2 <= d3) {
            d2 = (double) i3;
            d = d2;
            d2 = (((double) i2) * d2) / ((double) i);
        } else {
            d3 = (double) i4;
            d = (((double) i) * d3) / ((double) i2);
            d2 = d3;
        }
        if (d == ((double) i3)) {
            i5 = 0;
            round = (int) Math.round((((double) i4) - d2) / 2.0d);
        } else if (d2 == ((double) i4)) {
            i5 = (int) Math.round((((double) i3) - d) / 2.0d);
            round = 0;
        } else {
            i5 = (int) Math.round((((double) i3) - d) / 2.0d);
            round = (int) Math.round((((double) i4) - d2) / 2.0d);
        }
        return new Rect(i5, round, ((int) Math.ceil(d)) + i5, ((int) Math.ceil(d2)) + round);
    }
}
