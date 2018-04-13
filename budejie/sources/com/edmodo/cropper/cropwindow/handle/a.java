package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;

class a extends c {
    a() {
        super(null, null);
    }

    void a(float f, float f2, Rect rect, float f3) {
        float coordinate = Edge.LEFT.getCoordinate();
        float coordinate2 = Edge.TOP.getCoordinate();
        coordinate = f - ((coordinate + Edge.RIGHT.getCoordinate()) / 2.0f);
        coordinate2 = f2 - ((coordinate2 + Edge.BOTTOM.getCoordinate()) / 2.0f);
        Edge.LEFT.offset(coordinate);
        Edge.TOP.offset(coordinate2);
        Edge.RIGHT.offset(coordinate);
        Edge.BOTTOM.offset(coordinate2);
        if (Edge.LEFT.isOutsideMargin(rect, f3)) {
            Edge.RIGHT.offset(Edge.LEFT.snapToRect(rect));
        } else if (Edge.RIGHT.isOutsideMargin(rect, f3)) {
            Edge.LEFT.offset(Edge.RIGHT.snapToRect(rect));
        }
        if (Edge.TOP.isOutsideMargin(rect, f3)) {
            Edge.BOTTOM.offset(Edge.TOP.snapToRect(rect));
        } else if (Edge.BOTTOM.isOutsideMargin(rect, f3)) {
            Edge.TOP.offset(Edge.BOTTOM.snapToRect(rect));
        }
    }

    void a(float f, float f2, float f3, Rect rect, float f4) {
        a(f, f2, rect, f4);
    }
}
