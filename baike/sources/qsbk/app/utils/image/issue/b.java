package qsbk.app.utils.image.issue;

import java.io.File;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class b extends SimpleTask {
    final /* synthetic */ File a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ Logger d;

    b(Logger logger, File file, String str, boolean z) {
        this.d = logger;
        this.a = file;
        this.b = str;
        this.c = z;
    }

    public Object proccess() throws QiushibaikeException {
        try {
            this.d.writeFile(this.a, this.b, this.c);
            return null;
        } catch (Exception e) {
            throw new QiushibaikeException(e);
        }
    }
}
