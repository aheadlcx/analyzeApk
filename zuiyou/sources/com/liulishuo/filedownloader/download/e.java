package com.liulishuo.filedownloader.download;

import android.database.sqlite.SQLiteFullException;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.liulishuo.filedownloader.d.c;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.f;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public class e implements Callback {
    private final c a;
    private final com.liulishuo.filedownloader.b.a b;
    private final a c;
    private final int d;
    private final int e;
    private final int f;
    private long g;
    private Handler h;
    private HandlerThread i;
    private volatile boolean j = false;
    private volatile Thread k;
    private volatile long l = 0;
    private final AtomicLong m = new AtomicLong();
    private final AtomicBoolean n = new AtomicBoolean(false);
    private final AtomicBoolean o = new AtomicBoolean(false);
    private final AtomicBoolean p = new AtomicBoolean(true);

    public static class a {
        private boolean a;
        private Exception b;
        private int c;

        void a(boolean z) {
            this.a = z;
        }

        public boolean a() {
            return this.a;
        }

        void a(Exception exception) {
            this.b = exception;
        }

        void a(int i) {
            this.c = i;
        }

        public Exception b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }

    e(c cVar, int i, int i2, int i3) {
        this.a = cVar;
        this.b = c.a().c();
        if (i2 < 5) {
            i2 = 5;
        }
        this.e = i2;
        this.f = i3;
        this.c = new a();
        this.d = i;
    }

    public boolean a() {
        return this.i != null && this.i.isAlive();
    }

    void b() {
        if (this.h != null) {
            this.h.removeCallbacksAndMessages(null);
            this.i.quit();
            this.k = Thread.currentThread();
            while (this.j) {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
            }
            this.k = null;
        }
    }

    public void c() {
        this.a.a((byte) 1);
        this.b.f(this.a.a());
        a((byte) 1);
    }

    void d() {
        this.a.a((byte) 6);
        a((byte) 6);
        this.b.a(this.a.a());
    }

    void a(boolean z, long j, String str, String str2) throws IllegalArgumentException {
        String j2 = this.a.j();
        if (j2 == null || j2.equals(str)) {
            this.c.a(z);
            this.a.a((byte) 2);
            this.a.c(j);
            this.a.b(str);
            this.a.d(str2);
            this.b.a(this.a.a(), j, str, str2);
            a((byte) 2);
            this.g = a(j, (long) this.f);
            this.o.compareAndSet(false, true);
            return;
        }
        throw new IllegalArgumentException(f.a("callback onConnected must with precondition succeed, but the etag is changes(%s != %s)", str, j2));
    }

    void e() {
        this.i = new HandlerThread("source-status-callback");
        this.i.start();
        this.h = new Handler(this.i.getLooper(), this);
    }

    void a(long j) {
        this.m.addAndGet(j);
        this.a.b(j);
        b(SystemClock.elapsedRealtime());
        if (this.h == null) {
            i();
        } else if (this.n.get()) {
            a(this.h.obtainMessage(3));
        }
    }

    void a(Exception exception, int i) {
        this.m.set(0);
        if (this.h == null) {
            b(exception, i);
        } else {
            a(this.h.obtainMessage(5, i, 0, exception));
        }
    }

    void f() {
        l();
    }

    void a(Exception exception) {
        c(exception);
    }

    void g() throws IOException {
        if (!k()) {
            j();
        }
    }

    private synchronized void a(Message message) {
        if (this.i.isAlive()) {
            try {
                this.h.sendMessage(message);
            } catch (IllegalStateException e) {
                if (this.i.isAlive()) {
                    throw e;
                } else if (d.a) {
                    d.c(this, "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread.", Integer.valueOf(message.what));
                }
            }
        } else if (d.a) {
            d.c(this, "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread.", Integer.valueOf(message.what));
        }
    }

    private static long a(long j, long j2) {
        if (j2 <= 0) {
            return -1;
        }
        if (j == -1) {
            return 1;
        }
        long j3 = j / j2;
        if (j3 > 0) {
            return j3;
        }
        return 1;
    }

    private Exception b(Exception exception) {
        String e = this.a.e();
        if ((!this.a.i() && !com.liulishuo.filedownloader.g.e.a().f) || !(exception instanceof IOException) || !new File(e).exists()) {
            return exception;
        }
        long f = f.f(e);
        if (f > 4096) {
            return exception;
        }
        long j = 0;
        File file = new File(e);
        if (file.exists()) {
            j = file.length();
        } else {
            d.a((Object) this, (Throwable) exception, "Exception with: free space isn't enough, and the target file not exist.", new Object[0]);
        }
        if (VERSION.SDK_INT >= 9) {
            return new FileDownloadOutOfSpaceException(f, 4096, j, exception);
        }
        return new FileDownloadOutOfSpaceException(f, 4096, j);
    }

    private void a(SQLiteFullException sQLiteFullException) {
        int a = this.a.a();
        if (d.a) {
            d.c(this, "the data of the task[%d] is dirty, because the SQLite full exception[%s], so remove it from the database directly.", Integer.valueOf(a), sQLiteFullException.toString());
        }
        this.a.c(sQLiteFullException.toString());
        this.a.a((byte) -1);
        this.b.e(a);
        this.b.d(a);
    }

    private void h() throws IOException {
        Throwable th;
        String e = this.a.e();
        String d = this.a.d();
        File file = new File(e);
        int i;
        try {
            File file2 = new File(d);
            if (file2.exists()) {
                long length = file2.length();
                if (file2.delete()) {
                    d.d(this, "The target file([%s], [%d]) will be replaced with the new downloaded file[%d]", d, Long.valueOf(length), Long.valueOf(file.length()));
                } else {
                    throw new IOException(f.a("Can't delete the old file([%s], [%d]), so can't replace it with the new downloaded one.", d, Long.valueOf(length)));
                }
            }
            i = !file.renameTo(file2) ? 1 : 0;
            if (i != 0) {
                try {
                    throw new IOException(f.a("Can't rename the  temp downloaded file(%s) to the target file(%s)", e, d));
                } catch (Throwable th2) {
                    th = th2;
                    d.d(this, "delete the temp file(%s) failed, on completed downloading.", e);
                    throw th;
                }
            } else if (i != 0 && file.exists() && !file.delete()) {
                d.d(this, "delete the temp file(%s) failed, on completed downloading.", e);
            }
        } catch (Throwable th3) {
            th = th3;
            i = 1;
            if (!(i == 0 || !file.exists() || file.delete())) {
                d.d(this, "delete the temp file(%s) failed, on completed downloading.", e);
            }
            throw th;
        }
    }

    public boolean handleMessage(Message message) {
        this.j = true;
        switch (message.what) {
            case 3:
                try {
                    i();
                    break;
                } catch (Throwable th) {
                    this.j = false;
                    if (this.k != null) {
                        LockSupport.unpark(this.k);
                    }
                }
            case 5:
                b((Exception) message.obj, message.arg1);
                break;
        }
        this.j = false;
        if (this.k != null) {
            LockSupport.unpark(this.k);
        }
        return true;
    }

    private void i() {
        if (this.a.g() == this.a.h()) {
            this.b.a(this.a.a(), this.a.g());
            return;
        }
        if (this.o.compareAndSet(true, false)) {
            if (d.a) {
                d.b(this, "handleProgress update model's status with progress", new Object[0]);
            }
            this.a.a((byte) 3);
        }
        if (this.n.compareAndSet(true, false)) {
            if (d.a) {
                d.b(this, "handleProgress notify user progress status", new Object[0]);
            }
            a((byte) 3);
        }
    }

    private void j() throws IOException {
        h();
        this.a.a((byte) -3);
        this.b.b(this.a.a(), this.a.h());
        this.b.d(this.a.a());
        a((byte) -3);
        if (com.liulishuo.filedownloader.g.e.a().g) {
            com.liulishuo.filedownloader.services.f.a(this.a);
        }
    }

    private boolean k() {
        if (this.a.i()) {
            this.a.c(this.a.g());
        } else if (this.a.g() != this.a.h()) {
            a(new FileDownloadGiveUpRetryException(f.a("sofar[%d] not equal total[%d]", Long.valueOf(this.a.g()), Long.valueOf(this.a.h()))));
            return true;
        }
        return false;
    }

    private void b(Exception exception, int i) {
        Throwable b = b(exception);
        this.c.a((Exception) b);
        this.c.a(this.d - i);
        this.a.a((byte) 5);
        this.a.c(b.toString());
        this.b.a(this.a.a(), b);
        a((byte) 5);
    }

    private void l() {
        this.a.a((byte) -2);
        this.b.c(this.a.a(), this.a.g());
        a((byte) -2);
    }

    private void c(Exception exception) {
        Exception e;
        Throwable b = b(exception);
        if (b instanceof SQLiteFullException) {
            a((SQLiteFullException) b);
        } else {
            try {
                this.a.a((byte) -1);
                this.a.c(exception.toString());
                this.b.a(this.a.a(), b, this.a.g());
            } catch (SQLiteFullException e2) {
                e = e2;
                a((SQLiteFullException) e);
            }
        }
        this.c.a(e);
        a((byte) -1);
    }

    private void b(long j) {
        boolean z;
        if (this.p.compareAndSet(true, false)) {
            z = true;
        } else {
            z = this.g != -1 && this.m.get() >= this.g && j - this.l >= ((long) this.e);
        }
        if (z && this.n.compareAndSet(false, true)) {
            if (d.a) {
                d.b(this, "inspectNeedCallbackToUser need callback to user", new Object[0]);
            }
            this.l = j;
            this.m.set(0);
        }
    }

    private void a(byte b) {
        if (b != (byte) -2) {
            com.liulishuo.filedownloader.message.e.a().a(com.liulishuo.filedownloader.message.f.a(b, this.a, this.c));
        } else if (d.a) {
            d.c(this, "High concurrent cause, Already paused and we don't need to call-back to Task in here, %d", Integer.valueOf(this.a.a()));
        }
    }
}
