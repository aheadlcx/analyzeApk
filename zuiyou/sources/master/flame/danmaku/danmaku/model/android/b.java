package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.n;

public abstract class b {
    protected a a;

    public static abstract class a {
        public abstract void a(d dVar);

        public abstract void a(d dVar, boolean z);
    }

    public abstract void a();

    public abstract void a(d dVar, Canvas canvas, float f, float f2, boolean z, master.flame.danmaku.danmaku.model.android.a.a aVar);

    public abstract void a(d dVar, TextPaint textPaint, boolean z);

    public void a(d dVar, boolean z) {
        if (this.a != null) {
            this.a.a(dVar, z);
        }
    }

    public boolean a(d dVar, Canvas canvas, float f, float f2, Paint paint, TextPaint textPaint) {
        n d = dVar.d();
        if (d != null) {
            g gVar = (g) d.a();
            if (gVar != null) {
                return gVar.a(canvas, f, f2, paint);
            }
        }
        return false;
    }

    public void a(d dVar) {
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void b(d dVar) {
        if (this.a != null) {
            this.a.a(dVar);
        }
    }
}
