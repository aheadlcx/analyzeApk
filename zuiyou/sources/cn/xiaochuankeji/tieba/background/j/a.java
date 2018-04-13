package cn.xiaochuankeji.tieba.background.j;

public class a {
    private static a a;
    private c b;

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void b() {
        this.b = null;
    }

    public void c() {
        if (this.b != null) {
            this.b.setViewDownloadState(true);
        }
    }

    public void a(boolean z) {
        if (this.b != null) {
            this.b.setViewDownloadState(false);
        }
    }

    public void a(int i, int i2) {
        if (this.b != null) {
            this.b.a(i, i2);
        }
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.b();
        }
    }

    public void b(String str) {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    public boolean d() {
        return this.b != null;
    }
}
