package qsbk.app.utils;

import android.content.Context;
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
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.common.util.UriUtil;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtils {
    public static android.graphics.Bitmap scaleBitmapIfNecessary(android.graphics.Bitmap r7, int r8, int r9, boolean r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0036 in list [B:15:0x003a]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = r7.getWidth();
        r4 = r7.getHeight();
        if (r3 > r8) goto L_0x000c;
    L_0x000a:
        if (r4 <= r9) goto L_0x0036;
    L_0x000c:
        r0 = (float) r8;
        r1 = (float) r3;
        r0 = r0 / r1;
        r1 = (float) r9;
        r2 = (float) r4;
        r1 = r1 / r2;
        r0 = java.lang.Math.min(r0, r1);
        r5 = new android.graphics.Matrix;
        r5.<init>();
        r5.postScale(r0, r0);
        r1 = 0;
        r2 = 0;
        r6 = 1;
        r0 = r7;
        r0 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6);	 Catch:{ OutOfMemoryError -> 0x0037, all -> 0x0048 }
        if (r10 == 0) goto L_0x0059;
    L_0x0028:
        if (r0 == 0) goto L_0x0059;
    L_0x002a:
        if (r7 == r0) goto L_0x0059;
    L_0x002c:
        r1 = r7.isRecycled();
        if (r1 != 0) goto L_0x0059;
    L_0x0032:
        r7.recycle();
        r7 = r0;
    L_0x0036:
        return r7;
    L_0x0037:
        r0 = move-exception;
        if (r10 == 0) goto L_0x0036;
    L_0x003a:
        if (r7 == 0) goto L_0x0036;
    L_0x003c:
        if (r7 == r7) goto L_0x0036;
    L_0x003e:
        r0 = r7.isRecycled();
        if (r0 != 0) goto L_0x0036;
    L_0x0044:
        r7.recycle();
        goto L_0x0036;
    L_0x0048:
        r0 = move-exception;
        if (r10 == 0) goto L_0x0058;
    L_0x004b:
        if (r7 == 0) goto L_0x0058;
    L_0x004d:
        if (r7 == r7) goto L_0x0058;
    L_0x004f:
        r1 = r7.isRecycled();
        if (r1 != 0) goto L_0x0058;
    L_0x0055:
        r7.recycle();
    L_0x0058:
        throw r0;
    L_0x0059:
        r7 = r0;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.utils.ImageUtils.scaleBitmapIfNecessary(android.graphics.Bitmap, int, int, boolean):android.graphics.Bitmap");
    }

    public static byte[] bmpToByteArray(Bitmap bitmap, boolean z) {
        int width;
        int width2;
        byte[] toByteArray;
        Bitmap createBitmap = Bitmap.createBitmap(80, 80, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        if (bitmap.getHeight() > bitmap.getWidth()) {
            width = bitmap.getWidth();
            width2 = bitmap.getWidth();
        } else {
            width = bitmap.getHeight();
            width2 = bitmap.getHeight();
        }
        while (true) {
            canvas.drawBitmap(bitmap, new Rect(0, 0, width, width2), new Rect(0, 0, 80, 80), null);
            if (z) {
                bitmap.recycle();
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, 95, byteArrayOutputStream);
            createBitmap.recycle();
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                width = bitmap.getHeight();
                width2 = bitmap.getHeight();
            }
        }
        return toByteArray;
    }

    public static byte[] bmpToByteArray(Bitmap bitmap, int i, boolean z) {
        int width;
        int width2;
        byte[] toByteArray;
        double sqrt = Math.sqrt((double) (i / 2));
        Bitmap createBitmap = Bitmap.createBitmap((int) sqrt, (int) sqrt, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        if (bitmap.getHeight() > bitmap.getWidth()) {
            width = bitmap.getWidth();
            width2 = bitmap.getWidth();
        } else {
            width = bitmap.getHeight();
            width2 = bitmap.getHeight();
        }
        while (true) {
            canvas.drawBitmap(bitmap, new Rect(0, 0, width, width2), new Rect(0, 0, (int) sqrt, (int) sqrt), null);
            if (z) {
                bitmap.recycle();
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(CompressFormat.JPEG, 95, byteArrayOutputStream);
            createBitmap.recycle();
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                width = bitmap.getHeight();
                width2 = bitmap.getHeight();
            }
        }
        return toByteArray;
    }

    public static Bitmap picRotate(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = ((float) width) / ((float) width);
        float f2 = ((float) height) / ((float) height);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f = (float) i;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap watermark(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        float f;
        float f2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f3;
        if (width <= height || width < i) {
            if (width < height) {
                if (width >= i) {
                    if (height < i2) {
                        f3 = ((float) i) / ((float) width);
                        f = f3;
                        f2 = f3;
                    } else if (width / height >= i / i2) {
                        f3 = ((float) i) / ((float) width);
                        f = f3;
                        f2 = f3;
                    } else {
                        f3 = ((float) i2) / ((float) height);
                        f = f3;
                        f2 = f3;
                    }
                } else if (height >= i2) {
                    f3 = ((float) i2) / ((float) height);
                    f = f3;
                    f2 = f3;
                }
            } else if (width >= i) {
                f3 = ((float) i) / ((float) width);
                f = f3;
                f2 = f3;
            }
            f = 1.0f;
            f2 = 1.0f;
        } else {
            f3 = ((float) i) / ((float) width);
            f = f3;
            f2 = f3;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f);
        Bitmap bitmap3 = new BitmapDrawable(Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)).getBitmap();
        if (bitmap3.getWidth() <= Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
            return bitmap3;
        }
        int height2 = bitmap2.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap((int) (((float) width) * f2), (int) (((float) height) * f), Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap3, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap2, 10.0f, (float) ((createBitmap.getHeight() - height2) - 10), null);
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap createBitmapForWatermark(Bitmap bitmap) {
        float f = 1.0f;
        if (bitmap == null) {
            return null;
        }
        float f2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height || width < 500) {
            if (width < height) {
                if (width >= 500) {
                    if (height < ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE) {
                        f = 500.0f / ((float) width);
                        f2 = f;
                    } else if (width / height >= 0) {
                        f = 500.0f / ((float) width);
                        f2 = f;
                    } else {
                        f = 800.0f / ((float) height);
                        f2 = f;
                    }
                } else if (height >= ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE) {
                    f = 800.0f / ((float) height);
                    f2 = f;
                }
            } else if (width >= 500) {
                f = 500.0f / ((float) width);
                f2 = f;
            }
            f2 = 1.0f;
        } else {
            f = 500.0f / ((float) width);
            f2 = f;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f);
        return new BitmapDrawable(Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)).getBitmap();
    }

    public static void compressBitmap(String str) {
        int i = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i2 = (int) (((float) options.outHeight) / 500.0f);
        if (i2 > 0) {
            i = i2;
        }
        options.inSampleSize = i;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(DeviceUtils.getSDPath() + "/qsbk/send/send.png"));
            if (decodeFile.compress(CompressFormat.JPEG, 100, fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap scaleBitmapIfNecessaryUseScale(Bitmap bitmap, int i, int i2, boolean z) {
        Bitmap createBitmap;
        Bitmap bitmap2;
        Throwable th;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= i && height <= i2) {
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) width), ((float) i2) / ((float) height));
        int i3 = (int) (((float) width) * min);
        int i4 = (int) (min * ((float) height));
        try {
            createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
            try {
                new Canvas(createBitmap).drawBitmap(bitmap, new Rect(0, 0, width, height), new Rect(0, 0, i3, i4), new Paint(2));
                if (!(!z || createBitmap == null || bitmap == createBitmap || bitmap.isRecycled())) {
                    bitmap.recycle();
                    return createBitmap;
                }
            } catch (OutOfMemoryError e) {
                if (!(!z || createBitmap == null || bitmap == createBitmap || bitmap.isRecycled())) {
                    bitmap.recycle();
                    return createBitmap;
                }
                return createBitmap;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                bitmap2 = createBitmap;
                th = th3;
                if (!(!z || r1 == null || bitmap == r1 || bitmap.isRecycled())) {
                    bitmap.recycle();
                }
                throw th;
            }
        } catch (OutOfMemoryError e2) {
            createBitmap = bitmap;
            bitmap.recycle();
            return createBitmap;
        } catch (Throwable th4) {
            th = th4;
            bitmap2 = bitmap;
            bitmap.recycle();
            throw th;
        }
        return createBitmap;
    }

    public static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        float f = ((float) i3) / ((float) i2);
        float f2 = ((float) i4) / ((float) i);
        if (Util.isLongImage(i4, i3)) {
            i3 = (int) Math.floor((double) Math.max(2.0f, f2));
        } else {
            i3 = (int) Math.floor((double) Math.max(f, f2));
        }
        if (i3 < 1) {
            return 1;
        }
        return i3;
    }

    public static Options decodeBitmapOpt(Context context, String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            InputStream openInputStream;
            if (str.startsWith("content") || str.startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
                openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
            } else {
                openInputStream = new FileInputStream(str);
            }
            BitmapFactory.decodeStream(openInputStream, null, options);
            return options;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap decodeBitmap(Context context, String str, int i, int i2, Config config) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        if (config != null) {
            options.inPreferredConfig = config;
        } else {
            options.inPreferredConfig = Config.RGB_565;
        }
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            InputStream openInputStream;
            if (str.startsWith("content") || str.startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
                openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
            } else {
                openInputStream = new FileInputStream(str);
            }
            BitmapFactory.decodeStream(openInputStream, null, options);
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inScaled = false;
            try {
                if (str.startsWith("content") || str.startsWith(UriUtil.LOCAL_FILE_SCHEME)) {
                    openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
                } else {
                    openInputStream = new FileInputStream(str);
                }
                try {
                    return BitmapFactory.decodeStream(openInputStream, null, options);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (Exception e2) {
                return null;
            }
        } catch (Exception e3) {
            return null;
        }
    }

    public static Bitmap fitRotate(Bitmap bitmap, int i, int i2, int i3, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            throw new NullPointerException("the src bitmap is null or is recycled");
        } else if (i == 0 || i % 360 == 0) {
            return bitmap;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float min = Math.min(((float) i3) / ((float) width), ((float) i2) / ((float) height));
            Matrix matrix = new Matrix();
            matrix.postScale(min, min);
            matrix.postRotate((float) i);
            try {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                if (!z || createBitmap == null || createBitmap == bitmap) {
                    return createBitmap;
                }
                bitmap.recycle();
                return createBitmap;
            } catch (OutOfMemoryError e) {
                if (!z || null == null || bitmap == null) {
                    return null;
                }
                bitmap.recycle();
                return null;
            } catch (Throwable th) {
                if (!(!z || null == null || bitmap == null)) {
                    bitmap.recycle();
                }
            }
        }
    }
}
