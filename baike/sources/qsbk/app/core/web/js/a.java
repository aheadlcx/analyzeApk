package qsbk.app.core.web.js;

class a implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ ExposeApi e;

    a(ExposeApi exposeApi, String str, String str2, String str3, String str4) {
        this.e = exposeApi;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public void run() {
        this.e.a.exec(this.a, this.b, this.c, this.d);
    }
}
