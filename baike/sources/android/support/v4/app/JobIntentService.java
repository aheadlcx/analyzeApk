package android.support.v4.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final Object h = new Object();
    static final HashMap<ComponentName, h> i = new HashMap();
    b a;
    h b;
    a c;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    final ArrayList<d> g;

    final class a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ JobIntentService a;

        a(JobIntentService jobIntentService) {
            this.a = jobIntentService;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onCancelled(Object obj) {
            a((Void) obj);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            b((Void) obj);
        }

        protected Void a(Void... voidArr) {
            while (true) {
                e c = this.a.c();
                if (c == null) {
                    return null;
                }
                this.a.a(c.getIntent());
                c.complete();
            }
        }

        protected void a(Void voidR) {
            this.a.b();
        }

        protected void b(Void voidR) {
            this.a.b();
        }
    }

    interface b {
        IBinder compatGetBinder();

        e dequeueWork();
    }

    static abstract class h {
        final ComponentName c;
        boolean d;
        int e;

        abstract void a(Intent intent);

        h(Context context, ComponentName componentName) {
            this.c = componentName;
        }

        void a(int i) {
            if (!this.d) {
                this.d = true;
                this.e = i;
            } else if (this.e != i) {
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.e);
            }
        }

        public void serviceStartReceived() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceProcessingFinished() {
        }
    }

    static final class c extends h {
        boolean a;
        boolean b;
        private final Context f;
        private final WakeLock g;
        private final WakeLock h;

        c(Context context, ComponentName componentName) {
            super(context, componentName);
            this.f = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.g = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.g.setReferenceCounted(false);
            this.h = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.h.setReferenceCounted(false);
        }

        void a(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.c);
            if (this.f.startService(intent2) != null) {
                synchronized (this) {
                    if (!this.a) {
                        this.a = true;
                        if (!this.b) {
                            this.g.acquire(60000);
                        }
                    }
                }
            }
        }

        public void serviceStartReceived() {
            synchronized (this) {
                this.a = false;
            }
        }

        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.b) {
                    this.b = true;
                    this.h.acquire(600000);
                    this.g.release();
                }
            }
        }

        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.b) {
                    if (this.a) {
                        this.g.acquire(60000);
                    }
                    this.b = false;
                    this.h.release();
                }
            }
        }
    }

    interface e {
        void complete();

        Intent getIntent();
    }

    final class d implements e {
        final Intent a;
        final int b;
        final /* synthetic */ JobIntentService c;

        d(JobIntentService jobIntentService, Intent intent, int i) {
            this.c = jobIntentService;
            this.a = intent;
            this.b = i;
        }

        public Intent getIntent() {
            return this.a;
        }

        public void complete() {
            this.c.stopSelf(this.b);
        }
    }

    @RequiresApi(26)
    static final class f extends JobServiceEngine implements b {
        final JobIntentService a;
        final Object b = new Object();
        JobParameters c;

        final class a implements e {
            final JobWorkItem a;
            final /* synthetic */ f b;

            a(f fVar, JobWorkItem jobWorkItem) {
                this.b = fVar;
                this.a = jobWorkItem;
            }

            public Intent getIntent() {
                return this.a.getIntent();
            }

            public void complete() {
                synchronized (this.b.b) {
                    if (this.b.c != null) {
                        this.b.c.completeWork(this.a);
                    }
                }
            }
        }

        f(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.a = jobIntentService;
        }

        public IBinder compatGetBinder() {
            return getBinder();
        }

        public boolean onStartJob(JobParameters jobParameters) {
            this.c = jobParameters;
            this.a.a(false);
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            boolean a = this.a.a();
            synchronized (this.b) {
                this.c = null;
            }
            return a;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.v4.app.JobIntentService.e dequeueWork() {
            /*
            r3 = this;
            r0 = 0;
            r1 = r3.b;
            monitor-enter(r1);
            r2 = r3.c;	 Catch:{ all -> 0x0026 }
            if (r2 != 0) goto L_0x000a;
        L_0x0008:
            monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        L_0x0009:
            return r0;
        L_0x000a:
            r2 = r3.c;	 Catch:{ all -> 0x0026 }
            r2 = r2.dequeueWork();	 Catch:{ all -> 0x0026 }
            monitor-exit(r1);	 Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x0009;
        L_0x0013:
            r0 = r2.getIntent();
            r1 = r3.a;
            r1 = r1.getClassLoader();
            r0.setExtrasClassLoader(r1);
            r0 = new android.support.v4.app.JobIntentService$f$a;
            r0.<init>(r3, r2);
            goto L_0x0009;
        L_0x0026:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0026 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.JobIntentService.f.dequeueWork():android.support.v4.app.JobIntentService$e");
        }
    }

    @RequiresApi(26)
    static final class g extends h {
        private final JobInfo a;
        private final JobScheduler b;

        g(Context context, ComponentName componentName, int i) {
            super(context, componentName);
            a(i);
            this.a = new Builder(i, this.c).setOverrideDeadline(0).build();
            this.b = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        void a(Intent intent) {
            this.b.enqueue(this.a, new JobWorkItem(intent));
        }
    }

    protected abstract void a(@NonNull Intent intent);

    public JobIntentService() {
        if (VERSION.SDK_INT >= 26) {
            this.g = null;
        } else {
            this.g = new ArrayList();
        }
    }

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT >= 26) {
            this.a = new f(this);
            this.b = null;
            return;
        }
        this.a = null;
        this.b = a(this, new ComponentName(this, getClass()), false, 0);
    }

    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (this.g == null) {
            return 2;
        }
        this.b.serviceStartReceived();
        synchronized (this.g) {
            ArrayList arrayList = this.g;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new d(this, intent, i2));
            a(true);
        }
        return 3;
    }

    public IBinder onBind(@NonNull Intent intent) {
        if (this.a != null) {
            return this.a.compatGetBinder();
        }
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            synchronized (this.g) {
                this.f = true;
                this.b.serviceProcessingFinished();
            }
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class cls, int i, @NonNull Intent intent) {
        enqueueWork(context, new ComponentName(context, cls), i, intent);
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName componentName, int i, @NonNull Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (h) {
            h a = a(context, componentName, true, i);
            a.a(i);
            a.a(intent);
        }
    }

    static h a(Context context, ComponentName componentName, boolean z, int i) {
        h hVar = (h) i.get(componentName);
        if (hVar == null) {
            if (VERSION.SDK_INT < 26) {
                hVar = new c(context, componentName);
            } else if (z) {
                hVar = new g(context, componentName, i);
            } else {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            i.put(componentName, hVar);
        }
        return hVar;
    }

    public void setInterruptIfStopped(boolean z) {
        this.d = z;
    }

    public boolean isStopped() {
        return this.e;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    boolean a() {
        if (this.c != null) {
            this.c.cancel(this.d);
        }
        this.e = true;
        return onStopCurrentWork();
    }

    void a(boolean z) {
        if (this.c == null) {
            this.c = new a(this);
            if (this.b != null && z) {
                this.b.serviceProcessingStarted();
            }
            this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    void b() {
        if (this.g != null) {
            synchronized (this.g) {
                this.c = null;
                if (this.g != null && this.g.size() > 0) {
                    a(false);
                } else if (!this.f) {
                    this.b.serviceProcessingFinished();
                }
            }
        }
    }

    e c() {
        if (this.a != null) {
            return this.a.dequeueWork();
        }
        synchronized (this.g) {
            if (this.g.size() > 0) {
                e eVar = (e) this.g.remove(0);
                return eVar;
            }
            return null;
        }
    }
}
