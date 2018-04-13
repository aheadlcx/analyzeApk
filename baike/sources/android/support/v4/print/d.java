package android.support.v4.print;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.support.v4.print.PrintHelper.OnPrintFinishCallback;

class d extends PrintDocumentAdapter {
    AsyncTask<Uri, Boolean, Bitmap> a;
    Bitmap b = null;
    final /* synthetic */ String c;
    final /* synthetic */ Uri d;
    final /* synthetic */ OnPrintFinishCallback e;
    final /* synthetic */ int f;
    final /* synthetic */ a g;
    private PrintAttributes h;

    d(a aVar, String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i) {
        this.g = aVar;
        this.c = str;
        this.d = uri;
        this.e = onPrintFinishCallback;
        this.f = i;
    }

    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
        boolean z = true;
        synchronized (this) {
            this.h = printAttributes2;
        }
        if (cancellationSignal.isCanceled()) {
            layoutResultCallback.onLayoutCancelled();
        } else if (this.b != null) {
            PrintDocumentInfo build = new Builder(this.c).setContentType(1).setPageCount(1).build();
            if (printAttributes2.equals(printAttributes)) {
                z = false;
            }
            layoutResultCallback.onLayoutFinished(build, z);
        } else {
            this.a = new e(this, cancellationSignal, printAttributes2, printAttributes, layoutResultCallback).execute(new Uri[0]);
        }
    }

    private void a() {
        synchronized (this.g.h) {
            if (this.g.b != null) {
                this.g.b.requestCancelDecode();
                this.g.b = null;
            }
        }
    }

    public void onFinish() {
        super.onFinish();
        a();
        if (this.a != null) {
            this.a.cancel(true);
        }
        if (this.e != null) {
            this.e.onFinish();
        }
        if (this.b != null) {
            this.b.recycle();
            this.b = null;
        }
    }

    public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
        this.g.a(this.h, this.f, this.b, parcelFileDescriptor, cancellationSignal, writeResultCallback);
    }
}
