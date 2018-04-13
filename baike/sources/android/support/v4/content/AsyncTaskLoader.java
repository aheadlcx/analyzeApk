package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.os.OperationCanceledException;
import android.support.v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AsyncTaskLoader<D> extends Loader<D> {
    volatile a a;
    volatile a b;
    long c;
    long d;
    Handler e;
    private final Executor f;

    final class a extends ModernAsyncTask<Void, Void, D> implements Runnable {
        boolean a;
        final /* synthetic */ AsyncTaskLoader b;
        private final CountDownLatch c = new CountDownLatch(1);

        a(AsyncTaskLoader asyncTaskLoader) {
            this.b = asyncTaskLoader;
        }

        protected D a(Void... voidArr) {
            try {
                return this.b.d();
            } catch (OperationCanceledException e) {
                if (isCancelled()) {
                    return null;
                }
                throw e;
            }
        }

        protected void a(D d) {
            try {
                this.b.b(this, d);
            } finally {
                this.c.countDown();
            }
        }

        protected void b(D d) {
            try {
                this.b.a(this, d);
            } finally {
                this.c.countDown();
            }
        }

        public void run() {
            this.a = false;
            this.b.c();
        }

        public void waitForLoader() {
            try {
                this.c.await();
            } catch (InterruptedException e) {
            }
        }
    }

    @Nullable
    public abstract D loadInBackground();

    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.d = -10000;
        this.f = executor;
    }

    public void setUpdateThrottle(long j) {
        this.c = j;
        if (j != 0) {
            this.e = new Handler();
        }
    }

    protected void a() {
        super.a();
        cancelLoad();
        this.a = new a(this);
        c();
    }

    protected boolean b() {
        boolean z = false;
        if (this.a != null) {
            if (!this.r) {
                this.u = true;
            }
            if (this.b != null) {
                if (this.a.a) {
                    this.a.a = false;
                    this.e.removeCallbacks(this.a);
                }
                this.a = null;
            } else if (this.a.a) {
                this.a.a = false;
                this.e.removeCallbacks(this.a);
                this.a = null;
            } else {
                z = this.a.cancel(false);
                if (z) {
                    this.b = this.a;
                    cancelLoadInBackground();
                }
                this.a = null;
            }
        }
        return z;
    }

    public void onCanceled(@Nullable D d) {
    }

    void c() {
        if (this.b == null && this.a != null) {
            if (this.a.a) {
                this.a.a = false;
                this.e.removeCallbacks(this.a);
            }
            if (this.c <= 0 || SystemClock.uptimeMillis() >= this.d + this.c) {
                this.a.executeOnExecutor(this.f, (Void[]) null);
                return;
            }
            this.a.a = true;
            this.e.postAtTime(this.a, this.d + this.c);
        }
    }

    void a(a aVar, D d) {
        onCanceled(d);
        if (this.b == aVar) {
            rollbackContentChanged();
            this.d = SystemClock.uptimeMillis();
            this.b = null;
            deliverCancellation();
            c();
        }
    }

    void b(a aVar, D d) {
        if (this.a != aVar) {
            a(aVar, d);
        } else if (isAbandoned()) {
            onCanceled(d);
        } else {
            commitContentChanged();
            this.d = SystemClock.uptimeMillis();
            this.a = null;
            deliverResult(d);
        }
    }

    @Nullable
    protected D d() {
        return loadInBackground();
    }

    public void cancelLoadInBackground() {
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.b != null;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.waitForLoader();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.a != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.a);
            printWriter.print(" waiting=");
            printWriter.println(this.a.a);
        }
        if (this.b != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.b);
            printWriter.print(" waiting=");
            printWriter.println(this.b.a);
        }
        if (this.c != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.c, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.d, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }
}
