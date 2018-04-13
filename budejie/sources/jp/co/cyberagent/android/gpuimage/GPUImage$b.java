package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.AsyncTask;
import java.io.IOException;

abstract class GPUImage$b extends AsyncTask<Void, Void, Bitmap> {
    private final GPUImage a;
    final /* synthetic */ GPUImage b;
    private int c;
    private int d;

    protected abstract int a() throws IOException;

    protected abstract Bitmap a(Options options);

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap) obj);
    }

    public GPUImage$b(GPUImage gPUImage, GPUImage gPUImage2) {
        this.b = gPUImage;
        this.a = gPUImage2;
    }

    protected Bitmap a(Void... voidArr) {
        if (GPUImage.c(this.b) != null && GPUImage.c(this.b).b() == 0) {
            try {
                synchronized (GPUImage.c(this.b).b) {
                    GPUImage.c(this.b).b.wait(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.c = GPUImage.d(this.b);
        this.d = GPUImage.e(this.b);
        return b();
    }

    protected void a(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        this.a.b();
        this.a.a(bitmap);
    }

    private Bitmap b() {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        a(options);
        int i = 1;
        while (true) {
            boolean z;
            if (options.outWidth / i > this.c) {
                z = true;
            } else {
                z = false;
            }
            if (!a(z, options.outHeight / i > this.d)) {
                break;
            }
            i++;
        }
        i--;
        if (i < 1) {
            i = 1;
        }
        Options options2 = new Options();
        options2.inSampleSize = i;
        options2.inPreferredConfig = Config.RGB_565;
        options2.inPurgeable = true;
        options2.inTempStorage = new byte[32768];
        Bitmap a = a(options2);
        if (a == null) {
            return null;
        }
        return b(c(a));
    }

    private Bitmap b(Bitmap bitmap) {
        int[] a = a(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, a[0], a[1], true);
        if (createScaledBitmap != bitmap) {
            bitmap.recycle();
            bitmap = createScaledBitmap;
            System.gc();
        }
        if (GPUImage.f(this.b) != GPUImage$ScaleType.CENTER_CROP) {
            return bitmap;
        }
        int i = a[0] - this.c;
        int i2 = a[1] - this.d;
        createScaledBitmap = Bitmap.createBitmap(bitmap, i / 2, i2 / 2, a[0] - i, a[1] - i2);
        if (createScaledBitmap == bitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return createScaledBitmap;
    }

    private int[] a(int i, int i2) {
        float f = ((float) i) / ((float) this.c);
        float f2 = ((float) i2) / ((float) this.d);
        int i3 = GPUImage.f(this.b) == GPUImage$ScaleType.CENTER_CROP ? f > f2 ? 1 : 0 : f < f2 ? 1 : 0;
        if (i3 != 0) {
            f = (float) this.d;
            f2 = (f / ((float) i2)) * ((float) i);
        } else {
            f2 = (float) this.c;
            f = (f2 / ((float) i)) * ((float) i2);
        }
        return new int[]{Math.round(f2), Math.round(f)};
    }

    private boolean a(boolean z, boolean z2) {
        boolean z3 = false;
        if (GPUImage.f(this.b) != GPUImage$ScaleType.CENTER_CROP) {
            if (z || z2) {
                z3 = true;
            }
            return z3;
        } else if (z && z2) {
            return true;
        } else {
            return false;
        }
    }

    private Bitmap c(Bitmap bitmap) {
        IOException iOException;
        if (bitmap == null) {
            return null;
        }
        try {
            int a = a();
            if (a == 0) {
                return bitmap;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate((float) a);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            try {
                bitmap.recycle();
                return createBitmap;
            } catch (IOException e) {
                bitmap = createBitmap;
                iOException = e;
                iOException.printStackTrace();
                return bitmap;
            }
        } catch (IOException e2) {
            iOException = e2;
            iOException.printStackTrace();
            return bitmap;
        }
    }
}
