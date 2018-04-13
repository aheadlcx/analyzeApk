package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;

public enum Handle {
    TOP_LEFT(new b(Edge.TOP, Edge.LEFT)),
    TOP_RIGHT(new b(Edge.TOP, Edge.RIGHT)),
    BOTTOM_LEFT(new b(Edge.BOTTOM, Edge.LEFT)),
    BOTTOM_RIGHT(new b(Edge.BOTTOM, Edge.RIGHT)),
    LEFT(new e(Edge.LEFT)),
    TOP(new d(Edge.TOP)),
    RIGHT(new e(Edge.RIGHT)),
    BOTTOM(new d(Edge.BOTTOM)),
    CENTER(new a());
    
    private c mHelper;

    private Handle(c cVar) {
        this.mHelper = cVar;
    }

    public void updateCropWindow(float f, float f2, Rect rect, float f3) {
        this.mHelper.a(f, f2, rect, f3);
    }

    public void updateCropWindow(float f, float f2, float f3, Rect rect, float f4) {
        this.mHelper.a(f, f2, f3, rect, f4);
    }
}
