package android.support.v4.graphics.drawable;

import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;

public class IconCompat {
    private final int a;
    private Object b;
    private int c;
    private int d;

    public static IconCompat createWithResource(Context context, @DrawableRes int i) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.c = i;
        iconCompat.b = context;
        return iconCompat;
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.b = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(5);
        iconCompat.b = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithData(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("Data must not be null.");
        }
        IconCompat iconCompat = new IconCompat(3);
        iconCompat.b = bArr;
        iconCompat.c = i;
        iconCompat.d = i2;
        return iconCompat;
    }

    public static IconCompat createWithContentUri(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }
        IconCompat iconCompat = new IconCompat(4);
        iconCompat.b = str;
        return iconCompat;
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri != null) {
            return createWithContentUri(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    private IconCompat(int i) {
        this.a = i;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        switch (this.a) {
            case 1:
                return Icon.createWithBitmap((Bitmap) this.b);
            case 2:
                return Icon.createWithResource((Context) this.b, this.c);
            case 3:
                return Icon.createWithData((byte[]) this.b, this.c, this.d);
            case 4:
                return Icon.createWithContentUri((String) this.b);
            case 5:
                if (VERSION.SDK_INT >= 26) {
                    return Icon.createWithAdaptiveBitmap((Bitmap) this.b);
                }
                return Icon.createWithBitmap(a((Bitmap) this.b));
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(Intent intent) {
        switch (this.a) {
            case 1:
                intent.putExtra("android.intent.extra.shortcut.ICON", (Bitmap) this.b);
                return;
            case 2:
                intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext((Context) this.b, this.c));
                return;
            case 5:
                intent.putExtra("android.intent.extra.shortcut.ICON", a((Bitmap) this.b));
                return;
            default:
                throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        }
    }

    @VisibleForTesting
    static Bitmap a(Bitmap bitmap) {
        int min = (int) (0.6666667f * ((float) Math.min(bitmap.getWidth(), bitmap.getHeight())));
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = ((float) min) * 0.5f;
        float f2 = 0.9166667f * f;
        float f3 = 0.010416667f * ((float) min);
        paint.setColor(0);
        paint.setShadowLayer(f3, 0.0f, 0.020833334f * ((float) min), 1023410176);
        canvas.drawCircle(f, f, f2, paint);
        paint.setShadowLayer(f3, 0.0f, 0.0f, 503316480);
        canvas.drawCircle(f, f, f2, paint);
        paint.clearShadowLayer();
        paint.setColor(-16777216);
        Shader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((-(bitmap.getWidth() - min)) / 2), (float) ((-(bitmap.getHeight() - min)) / 2));
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f, f, f2, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }
}
