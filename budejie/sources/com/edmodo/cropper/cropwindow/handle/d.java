package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.a.a;
import com.edmodo.cropper.cropwindow.edge.Edge;

class d extends c {
    private Edge a;

    d(Edge edge) {
        super(edge, null);
        this.a = edge;
    }

    void a(float f, float f2, float f3, Rect rect, float f4) {
        this.a.adjustCoordinate(f, f2, rect, f4, f3);
        float coordinate = Edge.LEFT.getCoordinate();
        float coordinate2 = Edge.TOP.getCoordinate();
        float coordinate3 = Edge.RIGHT.getCoordinate();
        coordinate2 = (a.a(coordinate2, Edge.BOTTOM.getCoordinate(), f3) - (coordinate3 - coordinate)) / 2.0f;
        coordinate -= coordinate2;
        coordinate2 += coordinate3;
        Edge.LEFT.setCoordinate(coordinate);
        Edge.RIGHT.setCoordinate(coordinate2);
        if (Edge.LEFT.isOutsideMargin(rect, f4) && !this.a.isNewRectangleOutOfBounds(Edge.LEFT, rect, f3)) {
            Edge.RIGHT.offset(-Edge.LEFT.snapToRect(rect));
            this.a.adjustCoordinate(f3);
        }
        if (Edge.RIGHT.isOutsideMargin(rect, f4) && !this.a.isNewRectangleOutOfBounds(Edge.RIGHT, rect, f3)) {
            Edge.LEFT.offset(-Edge.RIGHT.snapToRect(rect));
            this.a.adjustCoordinate(f3);
        }
    }
}
