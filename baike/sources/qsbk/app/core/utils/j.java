package qsbk.app.core.utils;

final class j extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    j(String str, String str2, String str3, String str4) {
        this.a = str2;
        this.b = str3;
        this.c = str4;
        super(str);
    }

    public void run() {
        if (this.a != null) {
        }
        if (this.b != null) {
            FileUtils.saveContent(this.b, this.c);
        }
        super.run();
    }
}
