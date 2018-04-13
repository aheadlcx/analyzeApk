package jp.co.cyberagent.android.gpuimage;

public class x extends a {
    private float g;

    public x() {
        this(1.0f);
    }

    public x(float f) {
        this.g = f;
    }

    public void a() {
        super.a();
        b(this.g);
    }

    public void b(float f) {
        this.g = f;
        a(new float[]{-2.0f * f, -f, 0.0f, -f, 1.0f, f, 0.0f, f, 2.0f * f});
    }
}
