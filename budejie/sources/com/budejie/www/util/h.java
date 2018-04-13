package com.budejie.www.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import com.budejie.www.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class h {
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static Context e;
    private static int f;

    public static Bitmap a(Bitmap bitmap, int i) {
        aa.c("BitmapUtil", "rotate bitmap , type = " + i);
        b = bitmap.getHeight();
        a = bitmap.getWidth();
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        float f = 0.0f;
        switch (i) {
            case 1:
                f = -90.0f;
                break;
            case 2:
                f = 90.0f;
                break;
            case 3:
                f = 180.0f;
                break;
            default:
                aa.e("BitmapUtil", "error , rotate bitmap , type = " + i);
                break;
        }
        return d(bitmap, f);
    }

    public static Bitmap b(Bitmap bitmap, int i) {
        b = bitmap.getHeight();
        a = bitmap.getWidth();
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        float f = 0.0f;
        switch (i) {
            case 1:
                f = 90.0f;
                break;
            case 2:
                f = 180.0f;
                break;
            case 3:
                f = 270.0f;
                break;
        }
        return d(bitmap, f);
    }

    private static Bitmap d(Bitmap bitmap, float f) {
        if (bitmap == null || f == 0.0f) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        aa.a("BitmapUtil", "旋转之后的Bitmap宽高：" + bitmap.getWidth() + ":::" + bitmap.getHeight());
        return bitmap;
    }

    public static void a(Bitmap bitmap) {
        aa.a("BitmapUtil", "recycleBitmap , Bitmap =  " + bitmap);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static boolean a(Bitmap bitmap, String str, CompressFormat compressFormat) {
        boolean z = false;
        File file = new File(str);
        if (!bitmap.isRecycled()) {
            try {
                z = bitmap.compress(compressFormat, 80, new FileOutputStream(file, false));
            } catch (Exception e) {
                aa.e("BitmapUtil", "saveBitmapToFile Exception , " + e.toString());
            }
            if (!z) {
                file.delete();
            }
        }
        return z;
    }

    public static Bitmap a(String str, float f, float f2) {
        float f3;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (f > 0.0f) {
            f3 = f;
        } else {
            f3 = (float) i;
        }
        if (((float) i) > f3) {
            i2 = (int) (((float) options.outWidth) / f3);
        } else {
            i2 = 1;
        }
        if (i2 <= 0) {
            i2 = 1;
        }
        options.inSampleSize = i2;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inJustDecodeBounds = false;
        return a(BitmapFactory.decodeFile(str, options), f, f2);
    }

    public static Bitmap b(String str, float f, float f2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth;
        i = (int) (((float) options.outHeight) / f);
        if (i <= 0) {
            i = 1;
        }
        options.inSampleSize = i;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inJustDecodeBounds = false;
        return c(BitmapFactory.decodeFile(str, options), f2);
    }

    public static Bitmap a(Bitmap bitmap, float f, float f2) {
        float f3;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float f4 = f / ((float) width);
        f = (c - d) - an.a(e, 60);
        if (height > f) {
            f3 = ((float) f) / ((float) height);
        } else {
            f3 = f4;
        }
        matrix.postScale(f4, f3);
        return c(Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true), f2);
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (((float) height) <= f) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float f2 = f / ((float) height);
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap b(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float f2 = f / ((float) width);
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    @SuppressLint({"NewApi"})
    public static Bitmap c(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        aa.a("LAG", "" + bitmap.getWidth() + bitmap.getHeight());
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 80;
        bitmap.compress(CompressFormat.JPEG, 80, byteArrayOutputStream);
        while (f > 0.0f && ((float) (byteArrayOutputStream.toByteArray().length / 1024)) > f) {
            byteArrayOutputStream.reset();
            i -= 10;
            if (i <= 0) {
                break;
            }
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    public static Bitmap a(Activity activity, int i, int i2, int i3, int i4) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap createBitmap = Bitmap.createBitmap(decorView.getDrawingCache(), i, i2, i3, i4);
        decorView.destroyDrawingCache();
        return createBitmap;
    }

    public static Bitmap a(String str, boolean z) {
        if (str == null) {
            return null;
        }
        Options options = new Options();
        if (z) {
            options.inPreferredConfig = Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
        } else {
            options.inSampleSize = 1;
            options.inPreferredConfig = Config.ARGB_8888;
        }
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap a(byte[] bArr, Options options) {
        try {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, float f, boolean z) {
        if (bitmap == null) {
            return bitmap;
        }
        if (i == 0 && ((float) bitmap.getWidth()) <= f + 10.0f && ((float) bitmap.getHeight()) <= f + 10.0f) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        if (i != 0) {
            matrix.setRotate((float) i);
        }
        float min = Math.min(f / ((float) bitmap.getWidth()), f / ((float) bitmap.getHeight()));
        if (min < 1.0f) {
            matrix.postScale(min, min);
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (createBitmap == null || bitmap == createBitmap) {
                return bitmap;
            }
            if (z) {
                bitmap.recycle();
            }
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }

    public static boolean a(Bitmap bitmap, File file, CompressFormat compressFormat, int i) {
        if (file.isFile()) {
            file.delete();
        }
        try {
            return bitmap.compress(compressFormat, i, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static Bitmap b(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            height = width;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, height, height, null, false);
    }

    public static Bitmap a(int i, Bitmap bitmap) {
        int height = (i / bitmap.getHeight()) - 1;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() * height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        for (int i2 = 0; i2 < height; i2++) {
            canvas.drawBitmap(bitmap, 0.0f, (float) (bitmap.getHeight() * i2), null);
        }
        return createBitmap;
    }

    public static boolean a(Context context, List<Bitmap> list) {
        int x = an.x(context);
        double d = 0.0d;
        for (Bitmap bitmap : list) {
            d = ((double) ((bitmap.getHeight() * x) / bitmap.getWidth())) + d;
        }
        if (d / ((double) x) > 15.0d) {
            return true;
        }
        return false;
    }

    public static String a(Context context, List<Bitmap> list, boolean z) {
        Bitmap b = b(context, (List) list, z);
        if (b == null) {
            return null;
        }
        String str = System.currentTimeMillis() + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/budejie");
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(Environment.getExternalStorageDirectory().toString() + "/budejie", str);
        a(b, file, CompressFormat.JPEG, 80);
        return file.getAbsolutePath();
    }

    @Nullable
    private static Bitmap b(Context context, List<Bitmap> list, boolean z) {
        int i = 0;
        int x = (an.x(context) * 4) / 5;
        int i2 = 0;
        for (Bitmap b : list) {
            Bitmap b2;
            i2 = b(b2, (float) x).getHeight() + i2;
        }
        if (i2 <= 0) {
            return null;
        }
        Bitmap createBitmap;
        if (z) {
            createBitmap = Bitmap.createBitmap(x, i2, Config.RGB_565);
        } else {
            createBitmap = Bitmap.createBitmap(x, i2, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(context.getResources().getColor(R.color.white));
        for (Bitmap b22 : list) {
            b22 = b(b22, (float) x);
            canvas.drawBitmap(b22, 0.0f, (float) i, new Paint());
            i += b22.getHeight();
        }
        return createBitmap;
    }

    public static Bitmap a(Context context, int i) {
        return ((BitmapDrawable) context.getResources().getDrawable(i)).getBitmap();
    }

    public static ArrayList<Bitmap> a(Bitmap bitmap, int i, int i2) {
        ArrayList<Bitmap> arrayList = new ArrayList(i * i2);
        int width = bitmap.getWidth() / i2;
        int height = bitmap.getHeight() / i;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                arrayList.add(Bitmap.createBitmap(bitmap, i4 * width, i3 * height, width, height));
            }
        }
        return arrayList;
    }
}
