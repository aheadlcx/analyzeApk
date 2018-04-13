package android.support.v4.provider;

import android.content.Context;
import java.util.concurrent.Callable;

final class b implements Callable<a> {
    final /* synthetic */ Context a;
    final /* synthetic */ FontRequest b;
    final /* synthetic */ int c;
    final /* synthetic */ String d;

    b(Context context, FontRequest fontRequest, int i, String str) {
        this.a = context;
        this.b = fontRequest;
        this.c = i;
        this.d = str;
    }

    public a call() throws Exception {
        a a = FontsContractCompat.b(this.a, this.b, this.c);
        if (a.a != null) {
            FontsContractCompat.a.put(this.d, a.a);
        }
        return a;
    }
}
