package qsbk.app.cache;

import android.content.Context;

final class c extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    c(String str, Context context, String str2, String str3) {
        this.a = context;
        this.b = str2;
        this.c = str3;
        super(str);
    }

    public void run() {
        FileCache.cacheTextToDiskSync(this.a, this.b, this.c);
    }
}
