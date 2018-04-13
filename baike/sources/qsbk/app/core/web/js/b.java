package qsbk.app.core.web.js;

class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ExposeApi c;

    b(ExposeApi exposeApi, String str, String str2) {
        this.c = exposeApi;
        this.a = str;
        this.b = str2;
    }

    public void run() {
        this.c.b.onWebResp(this.a, this.b);
    }
}
