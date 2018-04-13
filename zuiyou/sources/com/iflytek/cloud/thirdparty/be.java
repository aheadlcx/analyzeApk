package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import java.util.HashSet;

public abstract class be extends Handler implements com.iflytek.cloud.thirdparty.cq.a {
    protected static final HashSet<be> y = new HashSet();
    private ce a = new ce();
    private volatile b b = b.init;
    private HandlerThread c;
    protected int r = 60000;
    public int s = 16000;
    protected Context t = null;
    protected volatile boolean u = false;
    protected long v = 0;
    protected int w = 20000;
    protected final cq x = cq.a((com.iflytek.cloud.thirdparty.cq.a) this);

    protected enum a {
        max,
        normal
    }

    protected enum b {
        init,
        start,
        recording,
        waitresult,
        exiting,
        exited
    }

    public be(Context context) {
        super(context.getMainLooper());
        this.t = context;
        this.u = false;
    }

    public be(Context context, HandlerThread handlerThread) {
        super(handlerThread.getLooper());
        this.c = handlerThread;
        this.t = context;
        this.u = false;
        y.add(this);
    }

    private void a() {
        if (this.c != null && this.c.isAlive()) {
            Object thread;
            y();
            if (this.t != null) {
                Looper mainLooper = this.t.getMainLooper();
                if (mainLooper != null) {
                    thread = mainLooper.getThread();
                    if (this.t == null || !this.c.equals(r0)) {
                        this.c.quit();
                        cb.a("quit current Msc Handler thread");
                    }
                    this.c = null;
                }
            }
            thread = null;
            this.c.quit();
            cb.a("quit current Msc Handler thread");
            this.c = null;
        }
        y.remove(this);
    }

    public static boolean u() {
        return y.isEmpty();
    }

    public String A() {
        return x() != null ? this.a.b("engine_type", "cloud") : "cloud";
    }

    public String B() {
        return "local".equalsIgnoreCase(A()) ? e() : f();
    }

    public void C() throws SecurityException {
        cb.a("startBluetooth enter");
        AudioManager audioManager = (AudioManager) this.t.getSystemService("audio");
        audioManager.setBluetoothScoOn(true);
        audioManager.startBluetoothSco();
    }

    public void D() {
        cb.a("stopBluetooth enter");
        try {
            AudioManager audioManager = (AudioManager) this.t.getSystemService("audio");
            audioManager.setBluetoothScoOn(false);
            audioManager.stopBluetoothSco();
        } catch (Throwable e) {
            cb.a(e);
        } catch (Throwable e2) {
            cb.a(e2);
        }
    }

    protected void a(int i) {
        a(obtainMessage(i), a.normal, false, 0);
    }

    protected void a(int i, a aVar, boolean z, int i2) {
        a(obtainMessage(i), aVar, z, i2);
    }

    protected void a(Message message) throws Throwable, SpeechError {
    }

    protected void a(Message message, a aVar, boolean z, int i) {
        if (w() == b.exited || w() == b.exiting) {
            cb.a("send msg failed while status is " + w());
            return;
        }
        switch (message.what) {
            case 0:
                a(b.start);
                break;
            case 3:
                a(b.waitresult);
                break;
            case 21:
                a(b.exiting);
                break;
        }
        if (z) {
            removeMessages(message.what);
        }
        if (aVar != a.max || i > 0) {
            sendMessageDelayed(message, (long) i);
        } else {
            sendMessageAtFrontOfQueue(message);
        }
    }

    protected void a(SpeechError speechError) {
        a(b.exited);
        y();
        cd.a(this.t).b();
    }

    protected synchronized void a(b bVar) {
        cb.a("curStatus=" + this.b + ",setStatus=" + bVar);
        if (this.b != b.exited) {
            if (this.b != b.exiting || bVar == b.exited) {
                cb.a("setStatus success=" + bVar);
                this.b = bVar;
                this.v = SystemClock.elapsedRealtime();
            }
        }
    }

    protected void a(ce ceVar) {
        this.a = ceVar.b();
        c();
    }

    protected void a_() {
        try {
            this.x.b();
        } catch (Throwable th) {
            cb.c("DC exception:");
            cb.a(th);
        }
        a(0, a.max, false, 0);
    }

    protected synchronized void b(SpeechError speechError) {
        if (speechError != null) {
            y();
        }
        try {
            this.x.a(speechError);
        } catch (Throwable th) {
            cb.c("DC exception:");
            cb.a(th);
        }
        d(obtainMessage(21, speechError));
    }

    public void b(boolean z) {
        this.u = true;
        y();
        b(null);
    }

    protected void c() {
        this.w = this.a.a(SpeechConstant.NET_TIMEOUT, this.w);
        this.s = this.a.a("sample_rate", this.s);
    }

    protected void d(Message message) {
        a(message, a.normal, false, 0);
    }

    public abstract String e();

    public abstract String f();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0057 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = this;
        r0 = r5.what;
        switch(r0) {
            case 21: goto L_0x0058;
            default: goto L_0x0005;
        };
    L_0x0005:
        r1 = 0;
        r0 = r5.what;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        switch(r0) {
            case 8: goto L_0x0063;
            default: goto L_0x000b;
        };	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
    L_0x000b:
        r0 = com.iflytek.cloud.SpeechUtility.getUtility();	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r0 != 0) goto L_0x0098;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
    L_0x0011:
        r0 = 1;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = r5.what;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r0 != r2) goto L_0x0098;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
    L_0x0016:
        r0 = "SDK is not init while session begin";	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        com.iflytek.cloud.thirdparty.cb.c(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0 = new com.iflytek.cloud.SpeechError;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = 20015; // 0x4e2f float:2.8047E-41 double:9.8887E-320;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        throw r0;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
    L_0x0024:
        r0 = move-exception;
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0 = new com.iflytek.cloud.SpeechError;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = 20010; // 0x4e2a float:2.804E-41 double:9.8863E-320;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r0 == 0) goto L_0x0057;
    L_0x0031:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r4.z();
        r1 = r1.append(r2);
        r2 = " occur Error = ";
        r1 = r1.append(r2);
        r2 = r0.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.iflytek.cloud.thirdparty.cb.a(r1);
        r4.b(r0);
    L_0x0057:
        return;
    L_0x0058:
        r0 = r5.obj;
        r0 = (com.iflytek.cloud.SpeechError) r0;
        r4.a(r0);
        r4.a();
        goto L_0x0057;
    L_0x0063:
        r0 = new com.iflytek.cloud.SpeechError;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = 20002; // 0x4e22 float:2.8029E-41 double:9.8823E-320;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        throw r0;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
    L_0x006b:
        r0 = move-exception;
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r0 == 0) goto L_0x0057;
    L_0x0071:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r4.z();
        r1 = r1.append(r2);
        r2 = " occur Error = ";
        r1 = r1.append(r2);
        r2 = r0.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.iflytek.cloud.thirdparty.cb.a(r1);
        r4.b(r0);
        goto L_0x0057;
    L_0x0098:
        r4.a(r5);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r1 == 0) goto L_0x0057;
    L_0x009d:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = r4.z();
        r0 = r0.append(r2);
        r2 = " occur Error = ";
        r0 = r0.append(r2);
        r2 = r1.toString();
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.iflytek.cloud.thirdparty.cb.a(r0);
        r4.b(r1);
        goto L_0x0057;
    L_0x00c4:
        r0 = move-exception;
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = new com.iflytek.cloud.SpeechError;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r2 == 0) goto L_0x0057;
    L_0x00cf:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r4.z();
        r0 = r0.append(r1);
        r1 = " occur Error = ";
        r0 = r0.append(r1);
        r1 = r2.toString();
        r0 = r0.append(r1);
        r0 = r0.toString();
        com.iflytek.cloud.thirdparty.cb.a(r0);
        r4.b(r2);
        goto L_0x0057;
    L_0x00f7:
        r0 = move-exception;
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0 = new com.iflytek.cloud.SpeechError;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = 20021; // 0x4e35 float:2.8055E-41 double:9.8917E-320;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r0 == 0) goto L_0x0057;
    L_0x0104:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r4.z();
        r1 = r1.append(r2);
        r2 = " occur Error = ";
        r1 = r1.append(r2);
        r2 = r0.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.iflytek.cloud.thirdparty.cb.a(r1);
        r4.b(r0);
        goto L_0x0057;
    L_0x012c:
        r0 = move-exception;
        com.iflytek.cloud.thirdparty.cb.a(r0);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0 = new com.iflytek.cloud.SpeechError;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r2 = 20999; // 0x5207 float:2.9426E-41 double:1.0375E-319;	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0024, SpeechError -> 0x006b, Exception -> 0x00c4, UnsatisfiedLinkError -> 0x00f7, Throwable -> 0x012c, all -> 0x0161 }
        if (r0 == 0) goto L_0x0057;
    L_0x0139:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r4.z();
        r1 = r1.append(r2);
        r2 = " occur Error = ";
        r1 = r1.append(r2);
        r2 = r0.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.iflytek.cloud.thirdparty.cb.a(r1);
        r4.b(r0);
        goto L_0x0057;
    L_0x0161:
        r0 = move-exception;
        if (r1 == 0) goto L_0x018a;
    L_0x0164:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r4.z();
        r2 = r2.append(r3);
        r3 = " occur Error = ";
        r2 = r2.append(r3);
        r3 = r1.toString();
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.iflytek.cloud.thirdparty.cb.a(r2);
        r4.b(r1);
    L_0x018a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.be.handleMessage(android.os.Message):void");
    }

    public boolean m() {
        return false;
    }

    protected void p() {
        removeMessages(8);
        a(8, a.normal, false, this.w);
    }

    public String q() {
        return this.a.b("pte", "utf-8");
    }

    public String r() {
        return this.a.b(SpeechConstant.TEXT_ENCODING, "utf-8");
    }

    public String s() {
        return this.a.b("rse", "utf-8");
    }

    public int t() {
        return this.s;
    }

    public boolean v() {
        return (this.b == b.exited || this.b == b.exiting || this.b == b.init) ? false : true;
    }

    protected synchronized b w() {
        return this.b;
    }

    public ce x() {
        return this.a;
    }

    protected void y() {
        cb.a("clear all message");
        for (int i = 0; i < 20; i++) {
            removeMessages(i);
        }
    }

    protected String z() {
        return getClass().toString();
    }
}
