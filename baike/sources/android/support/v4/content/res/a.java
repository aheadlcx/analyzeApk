package android.support.v4.content.res;

import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat.FontCallback;

class a implements Runnable {
    final /* synthetic */ Typeface a;
    final /* synthetic */ FontCallback b;

    a(FontCallback fontCallback, Typeface typeface) {
        this.b = fontCallback;
        this.a = typeface;
    }

    public void run() {
        this.b.onFontRetrieved(this.a);
    }
}
