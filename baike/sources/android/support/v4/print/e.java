package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.print.PrintAttributes;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import java.io.FileNotFoundException;

class e extends AsyncTask<Uri, Boolean, Bitmap> {
    final /* synthetic */ CancellationSignal a;
    final /* synthetic */ PrintAttributes b;
    final /* synthetic */ PrintAttributes c;
    final /* synthetic */ LayoutResultCallback d;
    final /* synthetic */ d e;

    e(d dVar, CancellationSignal cancellationSignal, PrintAttributes printAttributes, PrintAttributes printAttributes2, LayoutResultCallback layoutResultCallback) {
        this.e = dVar;
        this.a = cancellationSignal;
        this.b = printAttributes;
        this.c = printAttributes2;
        this.d = layoutResultCallback;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Uri[]) objArr);
    }

    protected /* synthetic */ void onCancelled(Object obj) {
        b((Bitmap) obj);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap) obj);
    }

    protected void onPreExecute() {
        this.a.setOnCancelListener(new f(this));
    }

    protected Bitmap a(Uri... uriArr) {
        try {
            return this.e.g.a(this.e.d);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    protected void a(Bitmap bitmap) {
        boolean z = true;
        super.onPostExecute(bitmap);
        if (bitmap != null && (!this.e.g.c || this.e.g.g == 0)) {
            MediaSize mediaSize;
            synchronized (this) {
                mediaSize = this.e.h.getMediaSize();
            }
            if (!(mediaSize == null || mediaSize.isPortrait() == a.b(bitmap))) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90.0f);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
        }
        this.e.b = bitmap;
        if (bitmap != null) {
            PrintDocumentInfo build = new Builder(this.e.c).setContentType(1).setPageCount(1).build();
            if (this.b.equals(this.c)) {
                z = false;
            }
            this.d.onLayoutFinished(build, z);
        } else {
            this.d.onLayoutFailed(null);
        }
        this.e.a = null;
    }

    protected void b(Bitmap bitmap) {
        this.d.onLayoutCancelled();
        this.e.a = null;
    }
}
