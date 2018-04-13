package com.bdj.picture.edit.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.bdj.picture.edit.bean.c;
import com.bdj.picture.edit.bean.d;
import com.bdj.picture.edit.bean.f;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class i {
    public static Bitmap a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        Log.d("LuoYer", "densityDpi: " + context.getResources().getDisplayMetrics().densityDpi);
        return BitmapFactory.decodeResource(context.getResources(), identifier);
    }

    public static Bitmap b(Context context, String str) {
        if (e.a(context, str)) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(str));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static Bitmap a(Bitmap[] bitmapArr, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(createBitmap);
        for (Bitmap drawBitmap : bitmapArr) {
            canvas.drawBitmap(drawBitmap, 0.0f, 0.0f, paint);
        }
        canvas.save();
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap a(View view, int i, int i2) {
        if (view == null) {
            return null;
        }
        view.measure(MeasureSpec.makeMeasureSpec(i, 1073741824), MeasureSpec.makeMeasureSpec(i2, 1073741824));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public static Bitmap a(Context context, Bitmap bitmap, String str, d dVar) {
        if (bitmap == null) {
            return null;
        }
        View textView = new TextView(context);
        textView.setGravity(17);
        textView.setPadding(dVar.g(), dVar.h(), dVar.i(), dVar.j());
        textView.setText(str);
        if (!TextUtils.isEmpty(dVar.k())) {
            textView.setTextColor(Color.parseColor(dVar.k()));
        }
        textView.setTextSize(2, (float) a(context, b.a(str.length(), dVar.l())));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap a = a(new Bitmap[]{bitmap, a(textView, width, height)}, width, height);
        r3.recycle();
        return a;
    }

    public static int a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void a(Canvas canvas, f fVar, View view) {
        Log.e("savePic", "widgets.get_zoomProportion()=" + fVar.g());
        Bitmap a = a(view, view.getWidth(), view.getHeight());
        Log.d("savePic", "bm.getWidth()=" + a.getWidth());
        Log.d("savePic", "bm.getHeight()=" + a.getHeight());
        a = a(a, (float) fVar.g());
        Log.d("savePic", "after bm.getWidth()=" + a.getWidth());
        Log.d("savePic", "after bm.getHeight()=" + a.getHeight());
        c d = fVar.c().d();
        canvas.drawBitmap(a, (float) (((double) d.a()) * fVar.g()), (float) (((double) d.b()) * fVar.g()), new Paint());
    }

    private static Bitmap a(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
