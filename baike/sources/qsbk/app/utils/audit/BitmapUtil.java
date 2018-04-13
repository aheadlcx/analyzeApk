package qsbk.app.utils.audit;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import qsbk.app.utils.DebugUtil;

public final class BitmapUtil {
    private static final String a = BitmapUtil.class.getName();

    public static final Bitmap decodeBitmap(byte[] bArr, int i, int i2, boolean z) {
        Bitmap bitmap;
        OutOfMemoryError e;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int calDesiredHeight = calDesiredHeight(i3, i4, i, i2);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        options.inInputShareable = true;
        options.inPurgeable = true;
        options.inSampleSize = findBestSampleSize(i3, i4, i, calDesiredHeight);
        if (DebugUtil.DEBUG) {
            Log.e(a, String.format("actual(%1s, %2s),desired(%3s, %4s)", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(calDesiredHeight)}));
        }
        try {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (!z || decodeByteArray == null || (decodeByteArray.getWidth() >= i && decodeByteArray.getHeight() >= calDesiredHeight)) {
                bitmap = decodeByteArray;
            } else {
                bitmap = Bitmap.createScaledBitmap(decodeByteArray, i, calDesiredHeight, true);
            }
            if (!(bitmap == decodeByteArray || decodeByteArray == null)) {
                try {
                    decodeByteArray.recycle();
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    e.printStackTrace();
                    return bitmap;
                }
            }
        } catch (OutOfMemoryError e3) {
            OutOfMemoryError outOfMemoryError = e3;
            bitmap = null;
            e = outOfMemoryError;
            e.printStackTrace();
            return bitmap;
        }
        return bitmap;
    }

    public static final int findBestSampleSize(int i, int i2, int i3, int i4) {
        float f = 1.0f;
        while (((double) (f * 2.0f)) <= Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4))) {
            f *= 2.0f;
        }
        return (int) f;
    }

    public static final int calDesiredHeight(int i, int i2, int i3, int i4) {
        int i5;
        if (i3 > 0) {
            i5 = (int) (((float) i2) * (((float) i3) / ((float) i)));
        } else {
            i5 = i2;
        }
        return (i4 <= 0 || i5 <= i4) ? i5 : i4;
    }

    public static final int calDesiredHeight(byte[] bArr, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        return calDesiredHeight(options.outWidth, options.outHeight, i, i2);
    }
}
