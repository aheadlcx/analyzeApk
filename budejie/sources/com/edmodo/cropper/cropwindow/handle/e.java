package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.a.a;
import com.edmodo.cropper.cropwindow.edge.Edge;

class e extends c {
    private Edge a;

    e(Edge edge) {
        super(null, edge);
        this.a = edge;
    }

    void a(float f, float f2, float f3, Rect rect, float f4) {
        this.a.adjustCoordinate(f, f2, rect, f4, f3);
        float coordinate = Edge.LEFT.getCoordinate();
        float coordinate2 = Edge.TOP.getCoordinate();
        float coordinate3 = Edge.RIGHT.getCoordinate();
        float coordinate4 = Edge.BOTTOM.getCoordinate();
        coordinate = (a.b(coordinate, coordinate3, f3) - (coordinate4 - coordinate2)) / 2.0f;
        coordinate2 -= coordinate;
        coordinate += coordinate4;
        Edge.TOP.setCoordinate(coordinate2);
        Edge.BOTTOM.setCoordinate(coordinate);
        if (Edge.TOP.isOutsideMargin(rect, f4) && !this.a.isNewRectangleOutOfBounds(Edge.TOP, rect, f3)) {
            Edge.BOTTOM.offset(-Edge.TOP.snapToRect(rect));
            this.a.adjustCoordinate(f3);
        }
        if (Edge.BOTTOM.isOutsideMargin(rect, f4) && !this.a.isNewRectangleOutOfBounds(Edge.BOTTOM, rect, f3)) {
            Edge.TOP.offset(-Edge.BOTTOM.snapToRect(rect));
            this.a.adjustCoordinate(f3);
        }
    }
}
