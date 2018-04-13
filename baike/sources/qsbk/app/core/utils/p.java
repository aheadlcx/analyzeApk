package qsbk.app.core.utils;

import java.io.File;
import qsbk.app.core.utils.TaskExecutor.SimpleTask;

class p extends SimpleTask {
    final /* synthetic */ File a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ Logger d;

    p(Logger logger, File file, String str, boolean z) {
        this.d = logger;
        this.a = file;
        this.b = str;
        this.c = z;
    }

    public Object proccess() throws Exception {
        try {
            this.d.writeFile(this.a, this.b, this.c);
            return null;
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }
}
