package android.support.v4.print;

import android.graphics.Bitmap;
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

class b extends PrintDocumentAdapter {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ Bitmap c;
    final /* synthetic */ OnPrintFinishCallback d;
    final /* synthetic */ a e;
    private PrintAttributes f;

    b(a aVar, String str, int i, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        this.e = aVar;
        this.a = str;
        this.b = i;
        this.c = bitmap;
        this.d = onPrintFinishCallback;
    }

    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
        boolean z = true;
        this.f = printAttributes2;
        PrintDocumentInfo build = new Builder(this.a).setContentType(1).setPageCount(1).build();
        if (printAttributes2.equals(printAttributes)) {
            z = false;
        }
        layoutResultCallback.onLayoutFinished(build, z);
    }

    public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
        this.e.a(this.f, this.b, this.c, parcelFileDescriptor, cancellationSignal, writeResultCallback);
    }

    public void onFinish() {
        if (this.d != null) {
            this.d.onFinish();
        }
    }
}
