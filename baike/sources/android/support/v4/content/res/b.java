package android.support.v4.content.res;

import android.support.v4.content.res.ResourcesCompat.FontCallback;

class b implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ FontCallback b;

    b(FontCallback fontCallback, int i) {
        this.b = fontCallback;
        this.a = i;
    }

    public void run() {
        this.b.onFontRetrievalFailed(this.a);
    }
}
