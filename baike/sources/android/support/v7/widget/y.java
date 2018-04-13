package android.support.v7.widget;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import java.lang.ref.WeakReference;

class y extends FontCallback {
    final /* synthetic */ WeakReference a;
    final /* synthetic */ x b;

    y(x xVar, WeakReference weakReference) {
        this.b = xVar;
        this.a = weakReference;
    }

    public void onFontRetrieved(@NonNull Typeface typeface) {
        this.b.a(this.a, typeface);
    }

    public void onFontRetrievalFailed(int i) {
    }
}
