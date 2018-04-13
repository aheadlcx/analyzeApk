package cn.xiaochuankeji.tieba.common.b;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    private static final String a = a.class.getName();
    private final String b;
    private AtomicBoolean c;
    private a d;

    public interface a {
        void a(a aVar);
    }

    public void a() {
        this.c.set(true);
        synchronized (this) {
            new File(this.b).delete();
        }
        if (this.d != null) {
            this.d.a(this);
        }
    }
}
