package com.budejie.www.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class al {
    private Context a;
    private View b;
    private boolean c;
    private boolean d;
    private List<Bitmap> e = new ArrayList();
    private b f;
    private int g;
    private int h;
    private int i;
    private String j = (Environment.getExternalStorageDirectory().toString() + "/budejie");

    public interface b {
        void a();

        void a(String str);

        void b();
    }

    public al(Context context, View view, int i) {
        this.a = context;
        this.b = view;
        this.g = i;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(boolean z, b bVar) {
        this.c = z;
        this.f = bVar;
        if (this.f != null) {
            this.f.b();
        }
        MotionEvent obtain = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, (float) (this.b.getWidth() / 2), (float) (this.b.getHeight() / 2), 0);
        this.b.dispatchTouchEvent(obtain);
        obtain.setAction(2);
        this.i = ViewConfiguration.get(this.b.getContext()).getScaledTouchSlop();
        obtain.setLocation(obtain.getX(), obtain.getY() - ((float) (this.i + 1)));
        this.b.dispatchTouchEvent(obtain);
        obtain.setLocation(obtain.getX(), (float) (this.b.getHeight() / 2));
        this.b.postDelayed(new al$1(this, bVar, obtain), 2);
    }

    @NonNull
    private String a(MotionEvent motionEvent) {
        Bitmap b;
        Bitmap bitmap;
        int height;
        int i;
        Bitmap createBitmap;
        try {
            if (this.h != 0) {
                b = h.b(h.a(this.a, this.h), (float) this.b.getWidth());
            } else {
                b = null;
            }
            bitmap = b;
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        if (((this.b.getHeight() / 2) - ((int) motionEvent.getY())) % this.b.getHeight() == 0) {
            this.e.add(a());
        } else {
            b = a();
            height = (this.b.getHeight() / 2) - ((int) motionEvent.getY());
            this.e.add(Bitmap.createBitmap(b, 0, this.b.getHeight() - (height % this.b.getHeight()), this.b.getWidth(), height % this.b.getHeight()));
            b.recycle();
        }
        height = ((this.e.size() - 1) * this.b.getHeight()) + ((Bitmap) this.e.get(this.e.size() - 1)).getHeight();
        if (this.e.size() > 1) {
            i = this.i;
        } else {
            i = 0;
        }
        int i2 = height - i;
        if (bitmap != null) {
            createBitmap = Bitmap.createBitmap(this.b.getWidth(), bitmap.getHeight() + i2, Config.RGB_565);
        } else {
            createBitmap = Bitmap.createBitmap(this.b.getWidth(), i2, Config.RGB_565);
        }
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        int i3 = 0;
        while (i3 < this.e.size()) {
            b = (Bitmap) this.e.get(i3);
            canvas.drawBitmap(b, 0.0f, (float) ((i3 * this.b.getHeight()) - (i3 == 0 ? 0 : this.i)), null);
            b.recycle();
            i3++;
        }
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, (float) i2, null);
            bitmap.recycle();
        }
        return a(createBitmap, this.j);
    }

    private String a(Bitmap bitmap, String str) {
        String str2 = System.currentTimeMillis() + ".jpg";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(str, str2);
        if (h.a(bitmap, file, CompressFormat.JPEG, 80)) {
            return file.getAbsolutePath();
        }
        return "";
    }

    public void a(boolean z) {
        this.c = true;
        this.d = z;
    }

    private Bitmap a() {
        Bitmap createBitmap = Bitmap.createBitmap(this.b.getWidth(), this.b.getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        this.b.draw(canvas);
        return createBitmap;
    }
}
