package qsbk.app.cache;

import android.content.Context;

final class b extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    b(String str, Context context, String str2, String str3) {
        this.a = context;
        this.b = str2;
        this.c = str3;
        super(str);
    }

    public void run() {
        if (FileCache.b(this.a, this.b)) {
            FileCache.writeFile(this.a, this.b, this.c);
        }
    }
}
