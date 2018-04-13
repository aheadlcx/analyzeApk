package cn.v6.sixrooms.presenter;

final class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ g d;

    h(g gVar, String str, String str2, String str3) {
        this.d = gVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void run() {
        this.d.a.a.c.setGeetestData(this.a, this.b, this.c);
        this.d.a.a.a.showLoading();
        this.d.a.a.c.login();
    }
}
