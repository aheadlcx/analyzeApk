package com.budejie.www.activity.video.barrage.a;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v4.internal.view.SupportMenu;

public class d {
    public static Paint a = new Paint();
    public static Paint b;
    public static RectF c = new RectF();
    private static boolean d = true;
    private static boolean e = true;

    static {
        a.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        a.setColor(0);
    }

    public static void a(boolean z, boolean z2) {
        d = z;
        e = z2;
    }

    public static void a(Canvas canvas, String str) {
        if (b == null) {
            b = new Paint();
            b.setColor(SupportMenu.CATEGORY_MASK);
            b.setTextSize(30.0f);
        }
        int height = canvas.getHeight() - 50;
        a(canvas, 10.0f, (float) (height - 50), (float) ((int) (b.measureText(str) + 20.0f)), (float) canvas.getHeight());
        canvas.drawText(str, 10.0f, (float) height, b);
    }

    public static void a(Canvas canvas) {
        if (!d) {
            c.set(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
            a(canvas, c);
        } else if (e) {
            canvas.drawColor(0, Mode.CLEAR);
        } else {
            canvas.drawColor(0);
        }
    }

    public static void a(Canvas canvas, float f, float f2, float f3, float f4) {
        c.set(f, f2, f3, f4);
        a(canvas, c);
    }

    private static void a(Canvas canvas, RectF rectF) {
        if (rectF.width() > 0.0f && rectF.height() > 0.0f) {
            canvas.drawRect(rectF, a);
        }
    }
}
