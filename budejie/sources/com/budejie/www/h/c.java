package com.budejie.www.h;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.view.View;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.util.ai;

public class c {
    private static c b;
    private static float d = 1.0f;
    Dialog a;
    private Theme c;

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
                d = b.b() == R.style.ThemeLight ? 1.0f : 0.5f;
            }
            cVar = b;
        }
        return cVar;
    }

    public int a(int i) {
        if (i == 1) {
            return R.style.ThemeBlack;
        }
        return R.style.ThemeLight;
    }

    public int b() {
        return a(ai.a(BudejieApplication.g));
    }

    public float c() {
        return d;
    }

    public void d() {
        this.c = BudejieApplication.g.getResources().newTheme();
        this.c.applyStyle(a(ai.a(BudejieApplication.g)), true);
        d = b.b() == R.style.ThemeLight ? 1.0f : 0.5f;
    }

    public int b(int i) {
        if (this.c == null) {
            d();
        }
        TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(new int[]{i});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public ColorStateList c(int i) {
        if (this.c == null) {
            d();
        }
        TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(new int[]{i});
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        return colorStateList;
    }

    public static Bitmap a(View view) {
        Bitmap bitmap = null;
        Bitmap createBitmap;
        try {
            view.buildDrawingCache();
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                createBitmap = Bitmap.createBitmap(drawingCache.getWidth(), drawingCache.getHeight(), Config.RGB_565);
                try {
                    new Canvas(createBitmap).drawBitmap(drawingCache, 0.0f, 0.0f, null);
                    bitmap = createBitmap;
                } catch (Exception e) {
                    Exception e2 = e;
                    e2.printStackTrace();
                    bitmap = createBitmap;
                    view.destroyDrawingCache();
                    return bitmap;
                }
                try {
                    view.destroyDrawingCache();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Exception e32) {
            Exception exception = e32;
            createBitmap = null;
            e2 = exception;
            e2.printStackTrace();
            bitmap = createBitmap;
            view.destroyDrawingCache();
            return bitmap;
        }
        return bitmap;
    }

    public void a(Context context) {
        this.a = new Dialog(context, R.style.dialogTheme);
        this.a.getWindow().setType(2003);
        this.a.setContentView(R.layout.loaddialog);
        this.a.setCanceledOnTouchOutside(false);
        this.a.setCancelable(false);
        this.a.show();
    }

    public void e() {
        if (this.a != null && this.a.isShowing()) {
            try {
                this.a.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
