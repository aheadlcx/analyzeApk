package com.liulishuo.filedownloader.message;

public class e {
    private volatile g a;
    private volatile b b;

    public static final class a {
        private static final e a = new e();
    }

    public interface b {
        void a(MessageSnapshot messageSnapshot);
    }

    public static e a() {
        return a.a;
    }

    public void a(b bVar) {
        this.b = bVar;
        if (bVar == null) {
            this.a = null;
        } else {
            this.a = new g(5, bVar);
        }
    }

    public void a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot instanceof b) {
            if (this.b != null) {
                this.b.a(messageSnapshot);
            }
        } else if (this.a != null) {
            this.a.a(messageSnapshot);
        }
    }
}
