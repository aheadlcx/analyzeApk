package qsbk.app.widget.barcode;

public class LogoZoomState extends ZoomState {
    protected float a = 0.1f;
    protected float b = 0.25f;

    public float getMinZoom() {
        return this.a;
    }

    public float getMaxZoom() {
        return this.b;
    }
}
