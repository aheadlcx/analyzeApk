package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.cropwindow.edge.a;

class b extends c {
    b(Edge edge, Edge edge2) {
        super(edge, edge2);
    }

    void a(float f, float f2, float f3, Rect rect, float f4) {
        a a = a(f, f2, f3);
        Edge edge = a.a;
        Edge edge2 = a.b;
        edge.adjustCoordinate(f, f2, rect, f4, f3);
        edge2.adjustCoordinate(f3);
        if (edge2.isOutsideMargin(rect, f4)) {
            edge2.snapToRect(rect);
            edge.adjustCoordinate(f3);
        }
    }
}
