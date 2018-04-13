package android.support.v4.content;

import android.os.Binder;
import android.os.Process;

class c extends c<Params, Result> {
    final /* synthetic */ ModernAsyncTask a;

    c(ModernAsyncTask modernAsyncTask) {
        this.a = modernAsyncTask;
    }

    public Result call() throws Exception {
        this.a.i.set(true);
        Object obj = null;
        try {
            Process.setThreadPriority(10);
            obj = this.a.a(this.b);
            Binder.flushPendingCommands();
            this.a.d(obj);
            return obj;
        } catch (Throwable th) {
            this.a.d(obj);
        }
    }
}
