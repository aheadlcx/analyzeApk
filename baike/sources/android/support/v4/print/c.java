package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument.Page;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;

class c extends AsyncTask<Void, Void, Throwable> {
    final /* synthetic */ CancellationSignal a;
    final /* synthetic */ PrintAttributes b;
    final /* synthetic */ Bitmap c;
    final /* synthetic */ PrintAttributes d;
    final /* synthetic */ int e;
    final /* synthetic */ ParcelFileDescriptor f;
    final /* synthetic */ WriteResultCallback g;
    final /* synthetic */ a h;

    c(a aVar, CancellationSignal cancellationSignal, PrintAttributes printAttributes, Bitmap bitmap, PrintAttributes printAttributes2, int i, ParcelFileDescriptor parcelFileDescriptor, WriteResultCallback writeResultCallback) {
        this.h = aVar;
        this.a = cancellationSignal;
        this.b = printAttributes;
        this.c = bitmap;
        this.d = printAttributes2;
        this.e = i;
        this.f = parcelFileDescriptor;
        this.g = writeResultCallback;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Throwable) obj);
    }

    protected Throwable a(Void... voidArr) {
        Bitmap a;
        Throwable th = null;
        PrintedPdfDocument printedPdfDocument;
        try {
            if (!this.a.isCanceled()) {
                printedPdfDocument = new PrintedPdfDocument(this.h.a, this.b);
                a = this.h.a(this.c, this.b.getColorMode());
                if (!this.a.isCanceled()) {
                    RectF rectF;
                    Page startPage = printedPdfDocument.startPage(1);
                    if (this.h.d) {
                        rectF = new RectF(startPage.getInfo().getContentRect());
                    } else {
                        PrintedPdfDocument printedPdfDocument2 = new PrintedPdfDocument(this.h.a, this.d);
                        Page startPage2 = printedPdfDocument2.startPage(1);
                        rectF = new RectF(startPage2.getInfo().getContentRect());
                        printedPdfDocument2.finishPage(startPage2);
                        printedPdfDocument2.close();
                    }
                    Matrix a2 = this.h.a(a.getWidth(), a.getHeight(), rectF, this.e);
                    if (!this.h.d) {
                        a2.postTranslate(rectF.left, rectF.top);
                        startPage.getCanvas().clipRect(rectF);
                    }
                    startPage.getCanvas().drawBitmap(a, a2, null);
                    printedPdfDocument.finishPage(startPage);
                    if (this.a.isCanceled()) {
                        printedPdfDocument.close();
                        if (this.f != null) {
                            try {
                                this.f.close();
                            } catch (IOException e) {
                            }
                        }
                        if (a != this.c) {
                            a.recycle();
                        }
                    } else {
                        printedPdfDocument.writeTo(new FileOutputStream(this.f.getFileDescriptor()));
                        printedPdfDocument.close();
                        if (this.f != null) {
                            try {
                                this.f.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (a != this.c) {
                            a.recycle();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return th;
    }

    protected void a(Throwable th) {
        if (this.a.isCanceled()) {
            this.g.onWriteCancelled();
        } else if (th == null) {
            this.g.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
        } else {
            Log.e("PrintHelperApi19", "Error writing printed content", th);
            this.g.onWriteFailed(null);
        }
    }
}
