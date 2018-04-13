package cn.v6.sixrooms.base;

import android.os.Handler;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public abstract class VLResHandler {
    public static final int CANCEL = -1;
    public static final int CONCURRENT = -5;
    public static final int ERROR = -3;
    public static final int SHUTDOWN = -4;
    public static final int SUCCEED = 0;
    public static final int TIMEOUT = -2;
    private static final HashSet<VLResHandler> a = new HashSet();
    private boolean b;
    private int c;
    private String d;
    private Object e;
    private int f;
    private Handler g;
    private boolean h;
    private Object i;
    public String mCreateDesc;

    protected abstract void handler(boolean z);

    public static void cancelResHandlerByCallee(Object obj) {
        if (obj != null) {
            VLResHandler vLResHandler;
            List<VLResHandler> arrayList = new ArrayList();
            synchronized (a) {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    vLResHandler = (VLResHandler) it.next();
                    if (vLResHandler.i == obj) {
                        arrayList.add(vLResHandler);
                    }
                }
            }
            for (VLResHandler vLResHandler2 : arrayList) {
                vLResHandler2.handlerError(-1, "");
            }
        }
    }

    public VLResHandler() {
        a(0, null, null);
    }

    public VLResHandler(int i) {
        a(i, null, null);
    }

    public VLResHandler(Handler handler) {
        a(-1, handler, null);
    }

    public VLResHandler(int i, Object obj) {
        a(i, null, obj);
    }

    public VLResHandler(Object obj) {
        a(0, null, obj);
    }

    private void a(int i, Handler handler, Object obj) {
        this.b = false;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = i;
        this.g = handler;
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        this.mCreateDesc = stackTraceElement.getClassName() + "::" + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber();
        this.h = false;
        this.i = obj;
        if (obj != null) {
            synchronized (a) {
                a.add(this);
            }
        }
    }

    public int resThread() {
        return this.f;
    }

    public boolean succeed() {
        return this.b;
    }

    public int errorCode() {
        return this.c;
    }

    public String errorString() {
        if (this.d == null) {
            this.d = "";
        }
        return this.d;
    }

    public String errorMsg() {
        return "{" + this.c + ":" + errorString() + h.d;
    }

    public void setParam(Object obj) {
        this.e = obj;
    }

    public Object param() {
        return this.e;
    }

    public void handlerSuccess() {
        a(true, 0, null, null);
    }

    public void handlerError(int i, String str) {
        a(false, i, str, null);
    }

    public void handlerSuccess(VLResHandler vLResHandler) {
        a(true, 0, null, vLResHandler);
    }

    public void handlerError(int i, String str, VLResHandler vLResHandler) {
        a(false, i, str, vLResHandler);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r5, int r6, java.lang.String r7, cn.v6.sixrooms.base.VLResHandler r8) {
        /*
        r4 = this;
        r1 = a;
        monitor-enter(r1);
        r0 = r4.h;	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
    L_0x0008:
        return;
    L_0x0009:
        r0 = 1;
        r4.h = r0;	 Catch:{ all -> 0x002b }
        r0 = r4.i;	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x0015;
    L_0x0010:
        r0 = a;	 Catch:{ all -> 0x002b }
        r0.remove(r4);	 Catch:{ all -> 0x002b }
    L_0x0015:
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        r4.b = r5;
        r4.c = r6;
        r4.d = r7;
        r0 = r4.g;
        if (r0 == 0) goto L_0x002e;
    L_0x0020:
        r0 = r4.g;
        r1 = new cn.v6.sixrooms.base.j;
        r1.<init>(r4, r5, r8);
        r0.post(r1);
        goto L_0x0008;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002b }
        throw r0;
    L_0x002e:
        r0 = cn.v6.sixrooms.base.VLScheduler.instance;
        r1 = 0;
        r2 = r4.f;
        r3 = new cn.v6.sixrooms.base.k;
        r3.<init>(r4, r5, r8);
        r0.schedule(r1, r2, r3);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.base.VLResHandler.a(boolean, int, java.lang.String, cn.v6.sixrooms.base.VLResHandler):void");
    }
}
