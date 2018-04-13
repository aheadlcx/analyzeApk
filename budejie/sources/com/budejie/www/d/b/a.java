package com.budejie.www.d.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.renderscript.RSRuntimeException;
import com.budejie.www.d.a.b;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.j;
import com.bumptech.glide.load.f;

public class a implements f<Bitmap> {
    private static int a = 25;
    private static int b = 1;
    private Context c;
    private c d;
    private int e;
    private int f;

    public a(Context context, int i) {
        this(context, i.a(context).a(), i, b);
    }

    public a(Context context, c cVar, int i, int i2) {
        this.c = context.getApplicationContext();
        this.d = cVar;
        this.e = i;
        this.f = i2;
    }

    public j<Bitmap> a(j<Bitmap> jVar, int i, int i2) {
        Bitmap bitmap = (Bitmap) jVar.b();
        int width = bitmap.getWidth();
        int i3 = width / this.f;
        int height = bitmap.getHeight() / this.f;
        Bitmap a = this.d.a(i3, height, Config.ARGB_8888);
        if (a == null) {
            a = Bitmap.createBitmap(i3, height, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(a);
        canvas.scale(1.0f / ((float) this.f), 1.0f / ((float) this.f));
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (VERSION.SDK_INT >= 18) {
            try {
                bitmap = b.a(this.c, a, this.e);
            } catch (RSRuntimeException e) {
                bitmap = com.budejie.www.d.a.a.a(a, this.e, true);
            }
        } else {
            bitmap = com.budejie.www.d.a.a.a(a, this.e, true);
        }
        return com.bumptech.glide.load.resource.bitmap.c.a(bitmap, this.d);
    }

    public String a() {
        return "BlurTransformation(radius=" + this.e + ", sampling=" + this.f + ")";
    }
}
