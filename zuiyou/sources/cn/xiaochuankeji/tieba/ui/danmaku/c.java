package cn.xiaochuankeji.tieba.ui.danmaku;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.lang.ref.SoftReference;
import master.flame.danmaku.danmaku.model.android.i;
import master.flame.danmaku.danmaku.model.d;

public class c extends i {
    public void a(d dVar, TextPaint textPaint, boolean z) {
        if (dVar.c instanceof Spanned) {
            if (this.a != null) {
                this.a.a(dVar, z);
            }
            CharSequence charSequence = dVar.c;
            if (charSequence != null) {
                StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, ((int) StaticLayout.getDesiredWidth(dVar.c, textPaint)) + 1, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
                if (staticLayout.getHeight() > 0) {
                    dVar.p = (float) staticLayout.getWidth();
                    dVar.q = (float) staticLayout.getHeight();
                    dVar.e = new SoftReference(staticLayout);
                    return;
                }
            }
        }
        super.a(dVar, textPaint, z);
    }

    public void a(d dVar, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (dVar.e == null) {
            super.a(dVar, str, canvas, f, f2, paint);
        }
    }

    public void a(d dVar, String str, Canvas canvas, float f, float f2, TextPaint textPaint, boolean z) {
        if (dVar.e == null) {
            super.a(dVar, str, canvas, f, f2, textPaint, z);
            return;
        }
        StaticLayout staticLayout;
        StaticLayout staticLayout2 = (StaticLayout) ((SoftReference) dVar.e).get();
        Object obj = (dVar.I & 1) != 0 ? 1 : null;
        Object obj2 = (dVar.I & 2) != 0 ? 1 : null;
        if (obj2 != null || staticLayout2 == null) {
            if (obj2 != null) {
                dVar.I &= -3;
            } else if (this.a != null) {
                this.a.a(dVar, z);
            }
            CharSequence charSequence = dVar.c;
            if (charSequence != null) {
                if (obj != null) {
                    staticLayout2 = new StaticLayout(charSequence, textPaint, ((int) StaticLayout.getDesiredWidth(dVar.c, textPaint)) + 1, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                    dVar.p = (float) staticLayout2.getWidth();
                    dVar.q = (float) staticLayout2.getHeight();
                    dVar.I &= -2;
                } else {
                    staticLayout2 = new StaticLayout(charSequence, textPaint, (int) dVar.p, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
                }
                dVar.e = new SoftReference(staticLayout2);
                staticLayout = staticLayout2;
            } else {
                return;
            }
        }
        staticLayout = staticLayout2;
        Object obj3 = null;
        if (!(f == 0.0f || f2 == 0.0f)) {
            canvas.save();
            canvas.translate(f, textPaint.ascent() + f2);
            obj3 = 1;
        }
        staticLayout.draw(canvas);
        if (obj3 != null) {
            canvas.restore();
        }
    }

    public void a() {
        super.a();
        System.gc();
    }

    public void a(d dVar) {
        super.a(dVar);
        if (dVar.e instanceof SoftReference) {
            ((SoftReference) dVar.e).clear();
        }
    }

    public void b(d dVar) {
        a(dVar);
        super.b(dVar);
    }
}
