package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.cropwindow.edge.a;

abstract class c {
    private Edge a;
    private Edge b;
    private a c = new a(this.a, this.b);

    abstract void a(float f, float f2, float f3, Rect rect, float f4);

    c(Edge edge, Edge edge2) {
        this.a = edge;
        this.b = edge2;
    }

    void a(float f, float f2, Rect rect, float f3) {
        a a = a();
        Edge edge = a.a;
        Edge edge2 = a.b;
        if (edge != null) {
            edge.adjustCoordinate(f, f2, rect, f3, 1.0f);
        }
        if (edge2 != null) {
            edge2.adjustCoordinate(f, f2, rect, f3, 1.0f);
        }
    }

    a a() {
        return this.c;
    }

    a a(float f, float f2, float f3) {
        if (a(f, f2) > f3) {
            this.c.a = this.b;
            this.c.b = this.a;
        } else {
            this.c.a = this.a;
            this.c.b = this.b;
        }
        return this.c;
    }

    private float a(float f, float f2) {
        float coordinate = this.b == Edge.LEFT ? f : Edge.LEFT.getCoordinate();
        float coordinate2 = this.a == Edge.TOP ? f2 : Edge.TOP.getCoordinate();
        if (this.b != Edge.RIGHT) {
            f = Edge.RIGHT.getCoordinate();
        }
        if (this.a != Edge.BOTTOM) {
            f2 = Edge.BOTTOM.getCoordinate();
        }
        return com.edmodo.cropper.a.a.a(coordinate, coordinate2, f, f2);
    }
}
