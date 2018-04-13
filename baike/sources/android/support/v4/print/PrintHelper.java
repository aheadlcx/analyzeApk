package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    private final f a;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    interface f {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException;

        void setColorMode(int i);

        void setOrientation(int i);

        void setScaleMode(int i);
    }

    @RequiresApi(19)
    private static class a implements f {
        final Context a;
        Options b = null;
        protected boolean c = true;
        protected boolean d = true;
        int e = 2;
        int f = 2;
        int g;
        private final Object h = new Object();

        a(Context context) {
            this.a = context;
        }

        public void setScaleMode(int i) {
            this.e = i;
        }

        public int getScaleMode() {
            return this.e;
        }

        public void setColorMode(int i) {
            this.f = i;
        }

        public void setOrientation(int i) {
            this.g = i;
        }

        public int getOrientation() {
            if (this.g == 0) {
                return 1;
            }
            return this.g;
        }

        public int getColorMode() {
            return this.f;
        }

        private static boolean b(Bitmap bitmap) {
            return bitmap.getWidth() <= bitmap.getHeight();
        }

        protected Builder a(PrintAttributes printAttributes) {
            Builder minMargins = new Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
            if (printAttributes.getColorMode() != 0) {
                minMargins.setColorMode(printAttributes.getColorMode());
            }
            return minMargins;
        }

        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            if (bitmap != null) {
                MediaSize mediaSize;
                int i = this.e;
                PrintManager printManager = (PrintManager) this.a.getSystemService("print");
                if (b(bitmap)) {
                    mediaSize = MediaSize.UNKNOWN_PORTRAIT;
                } else {
                    mediaSize = MediaSize.UNKNOWN_LANDSCAPE;
                }
                printManager.print(str, new b(this, str, i, bitmap, onPrintFinishCallback), new Builder().setMediaSize(mediaSize).setColorMode(this.f).build());
            }
        }

        private Matrix a(int i, int i2, RectF rectF, int i3) {
            Matrix matrix = new Matrix();
            float width = rectF.width() / ((float) i);
            if (i3 == 2) {
                width = Math.max(width, rectF.height() / ((float) i2));
            } else {
                width = Math.min(width, rectF.height() / ((float) i2));
            }
            matrix.postScale(width, width);
            matrix.postTranslate((rectF.width() - (((float) i) * width)) / 2.0f, (rectF.height() - (width * ((float) i2))) / 2.0f);
            return matrix;
        }

        private void a(PrintAttributes printAttributes, int i, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
            PrintAttributes printAttributes2;
            if (this.d) {
                printAttributes2 = printAttributes;
            } else {
                printAttributes2 = a(printAttributes).setMinMargins(new Margins(0, 0, 0, 0)).build();
            }
            new c(this, cancellationSignal, printAttributes2, bitmap, printAttributes, i, parcelFileDescriptor, writeResultCallback).execute(new Void[0]);
        }

        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
            PrintDocumentAdapter dVar = new d(this, str, uri, onPrintFinishCallback, this.e);
            PrintManager printManager = (PrintManager) this.a.getSystemService("print");
            Builder builder = new Builder();
            builder.setColorMode(this.f);
            if (this.g == 1 || this.g == 0) {
                builder.setMediaSize(MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.g == 2) {
                builder.setMediaSize(MediaSize.UNKNOWN_PORTRAIT);
            }
            printManager.print(str, dVar, builder.build());
        }

        private Bitmap a(Uri uri) throws FileNotFoundException {
            int i = 1;
            Bitmap bitmap = null;
            if (uri == null || this.a == null) {
                throw new IllegalArgumentException("bad argument to getScaledBitmap");
            }
            Options options = new Options();
            options.inJustDecodeBounds = true;
            a(uri, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            if (i2 > 0 && i3 > 0) {
                int max = Math.max(i2, i3);
                while (max > 3500) {
                    max >>>= 1;
                    i <<= 1;
                }
                if (i > 0 && Math.min(i2, i3) / i > 0) {
                    Options options2;
                    synchronized (this.h) {
                        this.b = new Options();
                        this.b.inMutable = true;
                        this.b.inSampleSize = i;
                        options2 = this.b;
                    }
                    try {
                        bitmap = a(uri, options2);
                        synchronized (this.h) {
                            this.b = null;
                        }
                    } catch (Throwable th) {
                        synchronized (this.h) {
                            this.b = null;
                        }
                    }
                }
            }
            return bitmap;
        }

        private Bitmap a(Uri uri, Options options) throws FileNotFoundException {
            InputStream inputStream = null;
            if (uri == null || this.a == null) {
                throw new IllegalArgumentException("bad argument to loadBitmap");
            }
            try {
                inputStream = this.a.getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e) {
                        Log.w("PrintHelperApi19", "close fail ", e);
                    }
                }
                return decodeStream;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2) {
                        Log.w("PrintHelperApi19", "close fail ", e2);
                    }
                }
            }
        }

        private Bitmap a(Bitmap bitmap, int i) {
            if (i != 1) {
                return bitmap;
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            canvas.setBitmap(null);
            return createBitmap;
        }
    }

    @RequiresApi(20)
    private static class b extends a {
        b(Context context) {
            super(context);
            this.c = false;
        }
    }

    @RequiresApi(23)
    private static class c extends b {
        protected Builder a(PrintAttributes printAttributes) {
            Builder a = super.a(printAttributes);
            if (printAttributes.getDuplexMode() != 0) {
                a.setDuplexMode(printAttributes.getDuplexMode());
            }
            return a;
        }

        c(Context context) {
            super(context);
            this.d = false;
        }
    }

    @RequiresApi(24)
    private static class d extends c {
        d(Context context) {
            super(context);
            this.d = true;
            this.c = true;
        }
    }

    private static final class e implements f {
        int a;
        int b;
        int c;

        private e() {
            this.a = 2;
            this.b = 2;
            this.c = 1;
        }

        public void setScaleMode(int i) {
            this.a = i;
        }

        public int getScaleMode() {
            return this.a;
        }

        public int getColorMode() {
            return this.b;
        }

        public void setColorMode(int i) {
            this.b = i;
        }

        public void setOrientation(int i) {
            this.c = i;
        }

        public int getOrientation() {
            return this.c;
        }

        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        }

        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) {
        }
    }

    public static boolean systemSupportsPrint() {
        return VERSION.SDK_INT >= 19;
    }

    public PrintHelper(Context context) {
        if (VERSION.SDK_INT >= 24) {
            this.a = new d(context);
        } else if (VERSION.SDK_INT >= 23) {
            this.a = new c(context);
        } else if (VERSION.SDK_INT >= 20) {
            this.a = new b(context);
        } else if (VERSION.SDK_INT >= 19) {
            this.a = new a(context);
        } else {
            this.a = new e();
        }
    }

    public void setScaleMode(int i) {
        this.a.setScaleMode(i);
    }

    public int getScaleMode() {
        return this.a.getScaleMode();
    }

    public void setColorMode(int i) {
        this.a.setColorMode(i);
    }

    public int getColorMode() {
        return this.a.getColorMode();
    }

    public void setOrientation(int i) {
        this.a.setOrientation(i);
    }

    public int getOrientation() {
        return this.a.getOrientation();
    }

    public void printBitmap(String str, Bitmap bitmap) {
        this.a.printBitmap(str, bitmap, null);
    }

    public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        this.a.printBitmap(str, bitmap, onPrintFinishCallback);
    }

    public void printBitmap(String str, Uri uri) throws FileNotFoundException {
        this.a.printBitmap(str, uri, null);
    }

    public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        this.a.printBitmap(str, uri, onPrintFinishCallback);
    }
}
