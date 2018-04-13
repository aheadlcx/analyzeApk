package cn.htjyb.c.b;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Pair;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class b {
    private static final SimpleDateFormat a = new SimpleDateFormat("yyyyMMddHHmmss");

    public static Bitmap a(String str, Options options) {
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Throwable th) {
            com.izuiyou.a.a.b.e(th.toString());
            return null;
        }
    }

    public static Pair<Integer, Integer> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Config.RGB_565;
        BitmapFactory.decodeFile(str, options);
        return new Pair(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
    }

    public static Bitmap a(String str, int i) {
        Bitmap bitmap = null;
        if (str == null) {
            return bitmap;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        if (i > 0) {
            options.inSampleSize = Math.max(options.outWidth / i, options.outHeight / i);
        }
        int b = b(str);
        try {
            bitmap = BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError e) {
            com.izuiyou.a.a.b.e(e.toString());
        }
        if (bitmap == null || b == 0) {
            return bitmap;
        }
        return a(bitmap, b);
    }

    public static int b(String str) {
        try {
            return a(new ExifInterface(str));
        } catch (Exception e) {
            return 0;
        }
    }

    public static int a(ExifInterface exifInterface) {
        int attributeInt = exifInterface.getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, -1);
        if (attributeInt != -1) {
            switch (attributeInt) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
            }
        }
        return 0;
    }

    public static boolean a(File file, File file2, int i, int i2) {
        Options options = new Options();
        if (i2 > 0) {
            options.inJustDecodeBounds = true;
            a(file.getPath(), options);
            com.izuiyou.a.a.b.a("src width: " + options.outWidth + ", height: " + options.outHeight);
            options.inJustDecodeBounds = false;
            options.inSampleSize = Math.max(options.outWidth / i2, options.outHeight / i2);
            com.izuiyou.a.a.b.a("inSampleSize: " + options.inSampleSize);
        }
        Bitmap a = a(file.getPath(), options);
        if (a == null) {
            com.izuiyou.a.a.b.d("decodeFile failed");
            return false;
        }
        a = a(a, b(file.getPath()), (float) i2, true);
        boolean a2 = a(a, file2, CompressFormat.JPEG, i);
        com.izuiyou.a.a.b.a("dst width: " + a.getWidth() + ", height: " + a.getHeight() + ", size: " + file2.length());
        a.recycle();
        return a2;
    }

    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        int width = bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth() : bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect();
        rect.left = (bitmap.getWidth() - width) / 2;
        rect.right = rect.left + width;
        rect.top = (bitmap.getHeight() - width) / 2;
        rect.bottom = rect.top + width;
        RectF rectF = new RectF(0.0f, 0.0f, (float) width, (float) width);
        float f = (float) i;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-1);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(FileDescriptor fileDescriptor, float f) {
        if (fileDescriptor == null) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = (int) Math.max(((float) options.outWidth) / f, ((float) options.outHeight) / f);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        if (decodeFileDescriptor != null) {
            return a(decodeFileDescriptor, 0, f, true);
        }
        return null;
    }

    public static Bitmap a(String str, float f) {
        if (str == null) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = (int) Math.max(((float) options.outWidth) / f, ((float) options.outHeight) / f);
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        Bitmap a = a(decodeFile, f);
        if (a != decodeFile) {
            decodeFile.recycle();
        }
        return a(a, b(str));
    }

    public static Bitmap a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return bitmap;
        }
        int i;
        int i2;
        int i3;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            i = (width - height) / 2;
            i2 = height;
            i3 = 0;
        } else {
            i3 = (height - width) / 2;
            height = width;
            i2 = width;
            i = 0;
        }
        float f2 = f / ((float) i2);
        if (((double) f2) >= 1.0d) {
            return Bitmap.createBitmap(bitmap, i, i3, i2, height);
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, i, i3, i2, height, matrix, false);
    }

    public static boolean a(Bitmap bitmap, File file, CompressFormat compressFormat, int i) {
        if (bitmap == null) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
        }
        try {
            return bitmap.compress(compressFormat, i, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            com.izuiyou.a.a.b.d(e.toString());
            return false;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (createBitmap == null || bitmap == createBitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, float f, boolean z) {
        Object obj = null;
        if (bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        if (i != 0) {
            matrix.setRotate((float) i);
            obj = 1;
        }
        com.izuiyou.a.a.b.e("maxSideLen:" + f + "  b.getWidth():" + bitmap.getWidth() + " b.getHeight():" + bitmap.getHeight());
        float min = Math.min(f / ((float) bitmap.getWidth()), f / ((float) bitmap.getHeight()));
        com.izuiyou.a.a.b.e("scale:" + min);
        if (min > 0.0f && ((double) min) < 0.99d) {
            matrix.postScale(min, min);
            obj = 1;
        }
        if (obj == null) {
            return bitmap;
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

    public static Bitmap a(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            return null;
        }
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f = (float) width;
            f2 = (float) width;
            f3 = (float) width;
            f4 = (float) width;
            f5 = (float) (width / 2);
            height = width;
            f6 = 0.0f;
        } else {
            f5 = (float) (height / 2);
            f6 = (float) ((width - height) / 2);
            f2 = ((float) width) - f6;
            f = (float) height;
            f3 = (float) height;
            f4 = (float) height;
            width = height;
        }
        Bitmap createBitmap = Bitmap.createBitmap(height, width, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f6, (int) null, (int) f2, (int) f);
        Rect rect2 = new Rect((int) null, (int) null, (int) f3, (int) f4);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f5, f5, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static String a(Uri uri, ContentResolver contentResolver) {
        Object obj;
        String str = null;
        if (uri != null) {
            Cursor query;
            String[] strArr = new String[]{"_data"};
            String string;
            try {
                query = contentResolver.query(uri, strArr, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            string = query.getString(query.getColumnIndex(strArr[0]));
                            str = string;
                            if (query != null) {
                                query.close();
                            }
                        }
                    } catch (Throwable th) {
                        Object obj2 = query;
                        obj = string;
                        if (query != null) {
                            query.close();
                        }
                        return str;
                    }
                }
                string = null;
                str = string;
            } catch (Throwable th2) {
                string = null;
                obj = string;
                if (query != null) {
                    query.close();
                }
                return str;
            }
            if (query != null) {
                query.close();
            }
        }
        return str;
    }

    public static boolean a(Intent intent, ContentResolver contentResolver, int i, File file) {
        Uri data = intent.getData();
        String a = a(data, contentResolver);
        if (a != null) {
            return a(new File(a), file, 80, i);
        }
        try {
            return a(a(contentResolver.openFileDescriptor(data, "r").getFileDescriptor(), (float) i), file, CompressFormat.JPEG, 80);
        } catch (Throwable th) {
            return false;
        }
    }

    public static Bitmap a(int i, Bitmap bitmap, int i2) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawARGB(0, 0, 0, 0);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            a(canvas, paint, i2, width, height);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            Rect rect = new Rect(0, 0, width, height);
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        } catch (Exception e) {
            return bitmap;
        }
    }

    private static void a(Canvas canvas, Paint paint, int i, int i2, int i3) {
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) i2, (float) i3), (float) i, (float) i, paint);
    }
}
