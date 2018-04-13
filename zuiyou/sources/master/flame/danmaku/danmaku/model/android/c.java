package master.flame.danmaku.danmaku.model.android;

import android.os.Build.VERSION;

public class c {
    public static final c a = new c(16, 0.3f, 0, 50, 0.01f);
    public static final c b = new c(16, 0.5f, -1, 50, 0.005f);
    public static final c c = a;
    public int d = 16;
    public float e = 0.3f;
    public long f = 0;
    public float g = 0.01f;
    public int h = 0;
    public int i = 20;
    public int j = 150;

    public c(int i, float f, long j, int i2, float f2) {
        this.d = i;
        if (VERSION.SDK_INT >= 19) {
            this.d = 32;
        }
        this.e = f;
        this.f = j;
        this.h = i2;
        this.g = f2;
    }
}
