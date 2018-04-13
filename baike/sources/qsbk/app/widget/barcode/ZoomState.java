package qsbk.app.widget.barcode;

import java.util.Observable;

public class ZoomState extends Observable {
    private static float a = -100.0f;
    private static float b = 100.0f;
    private static float e = -100.0f;
    private static float f = 100.0f;
    protected float c = 0.2f;
    protected float d = 0.4f;
    private float g;
    private float h;
    private float i;

    public float getPanX() {
        return this.h;
    }

    public void setPanX(float f) {
        if (f != this.h) {
            this.h = f;
            setChanged();
        }
    }

    public float getPanY() {
        return this.i;
    }

    public void setPanY(float f) {
        if (f != this.i) {
            this.i = f;
            setChanged();
        }
    }

    public float getZoom() {
        return this.g;
    }

    public void setZoom(float f) {
        float a = a(f);
        if (a != this.g) {
            this.g = a;
            setChanged();
        }
    }

    public float getZoomX(float f) {
        return Math.min(this.g, this.g * f);
    }

    public float getZoomY(float f) {
        return Math.min(this.g, this.g / f);
    }

    private float a(float f) {
        if (f > getMaxZoom()) {
            return getMaxZoom();
        }
        if (f < getMinZoom()) {
            return getMinZoom();
        }
        return f;
    }

    public float getMaxZoom() {
        return this.d;
    }

    public float getMinZoom() {
        return this.c;
    }
}
