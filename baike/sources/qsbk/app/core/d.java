package qsbk.app.core;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class d extends FutureTask<Result> {
    final /* synthetic */ AsyncTask a;

    d(AsyncTask asyncTask, Callable callable) {
        this.a = asyncTask;
        super(callable);
    }

    protected void done() {
        try {
            AsyncTask.b(this.a, get());
        } catch (Throwable e) {
            Log.w("AsyncTask", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
        } catch (CancellationException e3) {
            AsyncTask.b(this.a, null);
        }
    }
}
