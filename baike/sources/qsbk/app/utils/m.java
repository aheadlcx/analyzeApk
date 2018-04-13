package qsbk.app.utils;

final class m extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    m(String str, String str2, String str3, String str4) {
        this.a = str2;
        this.b = str3;
        this.c = str4;
        super(str);
    }

    public void run() {
        if (this.a != null) {
            DebugUtil.debug("缓存到内存");
        }
        if (this.b != null) {
            DebugUtil.debug("缓存到SDcard");
            FileUtils.saveContent(this.b, this.c);
        }
        super.run();
    }
}
