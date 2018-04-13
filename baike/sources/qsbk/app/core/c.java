package qsbk.app.core;

import android.os.Binder;
import android.os.Process;

class c extends AsyncTask$d<Params, Result> {
    final /* synthetic */ AsyncTask a;

    c(AsyncTask asyncTask) {
        this.a = asyncTask;
        super();
    }

    public Result call() throws Exception {
        AsyncTask.a(this.a).set(true);
        Process.setThreadPriority(10);
        Object a = this.a.a(this.b);
        Binder.flushPendingCommands();
        return AsyncTask.a(this.a, a);
    }
}
