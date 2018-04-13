package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapUtils {

    public interface CallBack {
        void handler(Bitmap bitmap);
    }

    public static void getBitmap(String str, CallBack callBack) {
        new b(str, callBack).execute(new Void[0]);
    }

    public static Bitmap getCircleBitmap2(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() + 5, Config.ARGB_8888);
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        paint2.setColor(-7829368);
        paint2.setAntiAlias(true);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) ((bitmap.getHeight() / 2) + 5), (float) (bitmap.getWidth() / 2), paint2);
        canvas.drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint);
        return createBitmap;
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        new Canvas(createBitmap).drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), (float) (bitmap.getWidth() / 2), paint);
        return createBitmap;
    }

    public static Bitmap getSmallCircleBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = width > height ? (float) (height / 2) : (float) (width / 2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        new Canvas(createBitmap).drawCircle((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), f, paint);
        return createBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Drawable createDrawable(Activity activity, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(activity.getWindowManager().getDefaultDisplay().getWidth(), activity.getWindowManager().getDefaultDisplay().getHeight(), Config.ARGB_8888);
        if (createBitmap == null) {
            return null;
        }
        new Canvas(createBitmap).drawColor(i);
        return new BitmapDrawable(activity.getResources(), createBitmap);
    }

    public static Bitmap cutScreenBmp(Activity activity) {
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        View decorView = activity.getWindow().getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
        decorView.draw(new Canvas(createBitmap));
        return Bitmap.createBitmap(createBitmap, 0, i, width, height - i);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int i, int i2, int i3) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        if (i4 > i3) {
            return Math.round(((float) i3) / ((float) i2));
        }
        return Math.round(((float) i4) / ((float) i));
    }

    public static int readPictureDegree(String str) {
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap getRotateBitmap(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap createBitmap(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        switch (i) {
            case 1:
                canvas.drawBitmap(bitmap2, (float) i2, (float) i2, null);
                break;
            case 2:
                canvas.drawBitmap(bitmap2, (float) i2, (float) i2, null);
                canvas.drawBitmap(bitmap2, (float) (width - i3), (float) i2, null);
                break;
            case 3:
                break;
            case 4:
                canvas.drawBitmap(bitmap2, (float) i2, (float) i2, null);
                canvas.drawBitmap(bitmap2, (float) (width - i3), (float) i2, null);
                canvas.drawBitmap(bitmap2, (float) i2, (float) (height - i3), null);
                canvas.drawBitmap(bitmap2, (float) (width - i3), (float) (height - i3), null);
                break;
            case 5:
                canvas.drawBitmap(bitmap2, (float) i2, (float) i2, null);
                canvas.drawBitmap(bitmap2, (float) (width - i3), (float) i2, null);
                canvas.drawBitmap(bitmap2, (float) i2, (float) (height - i3), null);
                canvas.drawBitmap(bitmap2, (float) (width - i3), (float) (height - i3), null);
                canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2), (float) ((height - height2) / 2), null);
                break;
        }
        canvas.drawBitmap(bitmap2, (float) i2, (float) i2, null);
        canvas.drawBitmap(bitmap2, (float) (width - i3), (float) i2, null);
        canvas.drawBitmap(bitmap2, (float) i2, (float) (height - i3), null);
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, float f) {
        Bitmap bitmap2;
        if (f == 1.0f) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
        }
        if (bitmap2 != bitmap) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, float f, float f2) {
        return Bitmap.createScaledBitmap(bitmap, (int) f, (int) f2, true);
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) i) / ((float) width);
        float f2 = ((float) i2) / ((float) height);
        Matrix matrix = new Matrix();
        if (f > ((float) i2)) {
            f2 = f;
        }
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static byte[] Bitmap2Bytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap Bytes2Bimap(byte[] bArr) {
        if (bArr.length != 0) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    public static Bitmap getMdpiBitmap(Context context, int i) {
        Options options = new Options();
        options.inTargetDensity = 160;
        options.inPreferredConfig = Config.ARGB_8888;
        return BitmapFactory.decodeResource(context.getResources(), i, options);
    }

    public static Bitmap rotateBitampAndRecycle(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.setRotate(f, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return createBitmap;
    }

    public static Bitmap getMdpiBitmap(Context context, int i, float f) {
        Bitmap mdpiBitmap = getMdpiBitmap(context, i);
        if (f != 0.0f) {
            return rotateBitampAndRecycle(mdpiBitmap, f);
        }
        return mdpiBitmap;
    }

    public static Bitmap zoomBitmapByOptions(Resources resources, int i) {
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        return BitmapFactory.decodeResource(resources, i, options);
    }
}
