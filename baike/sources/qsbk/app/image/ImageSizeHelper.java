package qsbk.app.image;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.ImageSize;

public class ImageSizeHelper {
    private static volatile int[] a = null;
    private static volatile int[] b = null;
    private static volatile int[] c = null;

    public static int[] getRequestWidthAndMaxPixcel() {
        Context context = QsbkApp.mContext;
        if (a == null) {
            synchronized (ImageSizeHelper.class) {
                if (a == null) {
                    Resources resources = context.getResources();
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    int dimensionPixelSize = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_default_margin) * 2);
                    int i = (int) (((double) displayMetrics.heightPixels) * 1.5d);
                    a = new int[]{dimensionPixelSize, i};
                }
            }
        }
        return a;
    }

    public static int[] getVideoWidthHeightMaxPix() {
        Context context = QsbkApp.mContext;
        if (b == null) {
            synchronized (ImageSizeHelper.class) {
                if (b == null) {
                    Resources resources = context.getResources();
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    int dimensionPixelSize = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_default_margin) * 2);
                    int i = (int) (((double) displayMetrics.heightPixels) * 0.8d);
                    b = new int[]{dimensionPixelSize, i};
                }
            }
        }
        return b;
    }

    public static int[] getFullWidthVideoWidthHeightMax() {
        Context context = QsbkApp.mContext;
        if (c == null) {
            synchronized (ImageSizeHelper.class) {
                if (c == null) {
                    int i = context.getResources().getDisplayMetrics().widthPixels;
                    c = new int[]{i, i};
                }
            }
        }
        return c;
    }

    public static void calWidthAndHeight(int i, int i2, int[] iArr, ImageSize imageSize) {
        calWidthAndHeight(i, i2, iArr, imageSize, false);
    }

    public static int calWidthAndHeight(int i, int i2, int[] iArr, ImageSize imageSize, boolean z) {
        if (iArr == null || imageSize == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        int i3 = (int) ((((float) i) / ((float) width)) * ((float) height));
        if (i2 == -1 || i3 <= i2) {
            i2 = i3;
            i3 = i;
        } else {
            i3 = (int) (((float) (i2 * width)) / (((float) height) * 1.0f));
            if (z) {
                i = i3;
            } else {
                int i4 = i3;
                i3 = i;
                i = i4;
            }
        }
        iArr[0] = i3;
        iArr[1] = i2;
        return i;
    }
}
