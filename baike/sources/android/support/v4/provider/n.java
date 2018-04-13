package android.support.v4.provider;

import android.graphics.Typeface;

class n implements Runnable {
    final /* synthetic */ Typeface a;
    final /* synthetic */ e b;

    n(e eVar, Typeface typeface) {
        this.b = eVar;
        this.a = typeface;
    }

    public void run() {
        this.b.d.onTypefaceRetrieved(this.a);
    }
}
