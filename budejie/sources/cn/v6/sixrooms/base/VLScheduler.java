package cn.v6.sixrooms.base;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

public class VLScheduler {
    public static final int THREAD_BG_HIGH = 1;
    public static final int THREAD_BG_IDLE = 4;
    public static final int THREAD_BG_LOW = 3;
    public static final int THREAD_BG_NORMAL = 2;
    public static final int THREAD_MAIN = 0;
    public static final VLScheduler instance = new VLScheduler();
    private List<BlockItem> a = new ArrayList();
    private int b = 0;
    private Handler c = null;
    private HandlerThread d = new HandlerThread("high_" + toString());
    private Handler e = null;
    private HandlerThread f = new HandlerThread("normal_" + toString());
    private Handler g = null;
    private HandlerThread h = new HandlerThread("low_" + toString());
    private Handler i = null;
    private HandlerThread j = new HandlerThread("idle_" + toString());
    private Handler k = null;

    protected static class BlockItem implements Runnable {
        public static final int STATUS_CANCELED = 3;
        public static final int STATUS_IDLED = 0;
        public static final int STATUS_RUNNING = 2;
        public static final int STATUS_SCHEDULED = 1;
        public VLBlock mBlock = null;
        public Handler mHandler = null;
        public int mStatus = 0;

        protected BlockItem() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r5 = this;
            r4 = 2;
            r0 = 1;
            r1 = 0;
            r2 = cn.v6.sixrooms.base.VLScheduler.instance;
            monitor-enter(r2);
            r3 = r5.mStatus;	 Catch:{ all -> 0x002e }
            if (r3 == r0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r2);	 Catch:{ all -> 0x002e }
        L_0x000b:
            return;
        L_0x000c:
            r3 = 2;
            r5.mStatus = r3;	 Catch:{ all -> 0x002e }
            monitor-exit(r2);	 Catch:{ all -> 0x002e }
            r2 = r5.mBlock;
            if (r2 == 0) goto L_0x001a;
        L_0x0014:
            r2 = r5.mBlock;	 Catch:{ Throwable -> 0x0031 }
            r3 = 0;
            r2.process(r3);	 Catch:{ Throwable -> 0x0031 }
        L_0x001a:
            r2 = r5.mStatus;
            if (r2 != r4) goto L_0x0036;
        L_0x001e:
            cn.v6.sixrooms.base.VLDebug.Assert(r0);
            r1 = cn.v6.sixrooms.base.VLScheduler.instance;
            monitor-enter(r1);
            r0 = cn.v6.sixrooms.base.VLScheduler.instance;	 Catch:{ all -> 0x002b }
            r0.a(r5);	 Catch:{ all -> 0x002b }
            monitor-exit(r1);	 Catch:{ all -> 0x002b }
            goto L_0x000b;
        L_0x002b:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002b }
            throw r0;
        L_0x002e:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x002e }
            throw r0;
        L_0x0031:
            r2 = move-exception;
            r2.printStackTrace();
            goto L_0x001a;
        L_0x0036:
            r0 = r1;
            goto L_0x001e;
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.base.VLScheduler.BlockItem.run():void");
        }
    }

    public long getThreadId(int i) {
        if (i == 0) {
            return Looper.getMainLooper().getThread().getId();
        }
        if (i == 1) {
            return (long) this.d.getThreadId();
        }
        if (i == 2) {
            return (long) this.f.getThreadId();
        }
        if (i == 3) {
            return (long) this.h.getThreadId();
        }
        if (i == 4) {
            return (long) this.j.getThreadId();
        }
        return -1;
    }

    public synchronized Handler getHandler(int i) {
        Handler handler;
        boolean z = true;
        synchronized (this) {
            if (i == 0) {
                if (this.c == null) {
                    this.c = new Handler(Looper.getMainLooper());
                }
                handler = this.c;
            } else if (i == 1) {
                if (this.e == null) {
                    this.d.start();
                    this.d.setPriority(10);
                    this.e = new Handler(this.d.getLooper());
                }
                if (this.d == null) {
                    z = false;
                }
                VLDebug.Assert(z);
                handler = this.e;
            } else if (i == 2) {
                if (this.g == null) {
                    this.f.start();
                    this.f.setPriority(5);
                    this.g = new Handler(this.f.getLooper());
                }
                if (this.f == null) {
                    z = false;
                }
                VLDebug.Assert(z);
                handler = this.g;
            } else if (i == 3) {
                if (this.i == null) {
                    this.h.start();
                    this.h.setPriority(1);
                    this.i = new Handler(this.h.getLooper());
                }
                if (this.h == null) {
                    z = false;
                }
                VLDebug.Assert(z);
                handler = this.i;
            } else if (i == 4) {
                if (this.k == null) {
                    this.j.start();
                    this.j.setPriority(1);
                    this.k = new Handler(this.j.getLooper());
                }
                if (this.j == null) {
                    z = false;
                }
                VLDebug.Assert(z);
                handler = this.k;
            } else {
                VLDebug.Assert(false);
                handler = null;
            }
        }
        return handler;
    }

    public void run(int i, VLBlock vLBlock) {
        if (Thread.currentThread().getId() == getThreadId(i)) {
            vLBlock.process(false);
        } else {
            schedule(0, 0, vLBlock);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized cn.v6.sixrooms.base.VLBlock schedule(int r5, int r6, cn.v6.sixrooms.base.VLBlock r7) {
        /*
        r4 = this;
        r0 = 1;
        monitor-enter(r4);
        if (r7 == 0) goto L_0x0055;
    L_0x0004:
        r1 = r7.mRefBlockItem;	 Catch:{ all -> 0x005e }
        if (r1 != 0) goto L_0x0055;
    L_0x0008:
        r1 = r7.mFlag;	 Catch:{ all -> 0x005e }
        if (r1 != 0) goto L_0x0055;
    L_0x000c:
        if (r5 < 0) goto L_0x0055;
    L_0x000e:
        cn.v6.sixrooms.base.VLDebug.Assert(r0);	 Catch:{ all -> 0x005e }
        r0 = 1;
        r7.mFlag = r0;	 Catch:{ all -> 0x005e }
        r1 = 0;
        r0 = r4.a;	 Catch:{ all -> 0x005e }
        r2 = r0.iterator();	 Catch:{ all -> 0x005e }
    L_0x001b:
        r0 = r2.hasNext();	 Catch:{ all -> 0x005e }
        if (r0 == 0) goto L_0x0061;
    L_0x0021:
        r0 = r2.next();	 Catch:{ all -> 0x005e }
        r0 = (cn.v6.sixrooms.base.VLScheduler.BlockItem) r0;	 Catch:{ all -> 0x005e }
        r3 = r0.mStatus;	 Catch:{ all -> 0x005e }
        if (r3 != 0) goto L_0x001b;
    L_0x002b:
        if (r0 != 0) goto L_0x0037;
    L_0x002d:
        r0 = new cn.v6.sixrooms.base.VLScheduler$BlockItem;	 Catch:{ all -> 0x005e }
        r0.<init>();	 Catch:{ all -> 0x005e }
        r1 = r4.a;	 Catch:{ all -> 0x005e }
        r1.add(r0);	 Catch:{ all -> 0x005e }
    L_0x0037:
        r0.mBlock = r7;	 Catch:{ all -> 0x005e }
        r1 = r4.getHandler(r6);	 Catch:{ all -> 0x005e }
        r0.mHandler = r1;	 Catch:{ all -> 0x005e }
        r1 = 1;
        r0.mStatus = r1;	 Catch:{ all -> 0x005e }
        r1 = r0.mBlock;	 Catch:{ all -> 0x005e }
        r1.mRefBlockItem = r0;	 Catch:{ all -> 0x005e }
        r1 = r4.b;	 Catch:{ all -> 0x005e }
        r1 = r1 + 1;
        r4.b = r1;	 Catch:{ all -> 0x005e }
        if (r5 != 0) goto L_0x0057;
    L_0x004e:
        r1 = r0.mHandler;	 Catch:{ all -> 0x005e }
        r1.post(r0);	 Catch:{ all -> 0x005e }
    L_0x0053:
        monitor-exit(r4);
        return r7;
    L_0x0055:
        r0 = 0;
        goto L_0x000e;
    L_0x0057:
        r1 = r0.mHandler;	 Catch:{ all -> 0x005e }
        r2 = (long) r5;	 Catch:{ all -> 0x005e }
        r1.postDelayed(r0, r2);	 Catch:{ all -> 0x005e }
        goto L_0x0053;
    L_0x005e:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0061:
        r0 = r1;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.base.VLScheduler.schedule(int, int, cn.v6.sixrooms.base.VLBlock):cn.v6.sixrooms.base.VLBlock");
    }

    private void a(BlockItem blockItem) {
        boolean z = false;
        if (blockItem.mBlock != null) {
            blockItem.mBlock.mFlag = false;
            blockItem.mBlock.mRefBlockItem = null;
        }
        blockItem.mBlock = null;
        blockItem.mHandler = null;
        blockItem.mStatus = 0;
        this.b--;
        if (this.b >= 0) {
            z = true;
        }
        VLDebug.Assert(z);
    }

    public synchronized boolean cancel(VLBlock vLBlock, boolean z) {
        boolean z2 = true;
        synchronized (this) {
            if (vLBlock != null) {
                if (vLBlock.mRefBlockItem != null) {
                    BlockItem blockItem = vLBlock.mRefBlockItem;
                    if (blockItem.mBlock == vLBlock && blockItem.mStatus == 1) {
                        blockItem.mStatus = 3;
                        blockItem.mHandler.removeCallbacks(blockItem);
                        if (z) {
                            blockItem.mHandler.postAtFrontOfQueue(new l(this, blockItem));
                        } else {
                            synchronized (this) {
                                a(blockItem);
                            }
                        }
                    } else {
                        z2 = false;
                    }
                }
            }
            z2 = false;
        }
        return z2;
    }
}
