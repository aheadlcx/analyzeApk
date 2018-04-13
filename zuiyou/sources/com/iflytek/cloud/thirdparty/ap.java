package com.iflytek.cloud.thirdparty;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cae.jni.CAEJni;
import java.io.UnsupportedEncodingException;

public class ap {
    private static ap a;
    private static int b = 0;
    private static int c = 1024;
    private boolean d = false;
    private ar e;
    private a f;
    private Handler g = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ ap a;

        public void handleMessage(Message message) {
            Bundle bundle;
            switch (message.what) {
                case 1:
                    String str = (String) message.obj;
                    if (this.a.e != null) {
                        this.a.e.a(str);
                        return;
                    }
                    return;
                case 2:
                    bundle = (Bundle) message.obj;
                    byte[] byteArray = bundle.getByteArray("audioData");
                    int i = bundle.getInt("dataLen");
                    int i2 = bundle.getInt("param1");
                    int i3 = bundle.getInt("param2");
                    if (this.a.e != null) {
                        this.a.e.a(byteArray, i, i2, i3);
                        return;
                    }
                    return;
                case 3:
                    aq aqVar = (aq) message.obj;
                    if (this.a.e != null) {
                        this.a.e.a(aqVar);
                        return;
                    }
                    return;
                case 4:
                    bundle = (Bundle) message.obj;
                    int i4 = bundle.getInt("type");
                    byte[] byteArray2 = bundle.getByteArray("param1Array");
                    byte[] byteArray3 = bundle.getByteArray("param2Array");
                    byte[] byteArray4 = bundle.getByteArray("param3Array");
                    if (this.a.e != null) {
                        this.a.e.a(i4, byteArray2, byteArray3, byteArray4);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private com.iflytek.cae.jni.CAEJni.a h = new com.iflytek.cae.jni.CAEJni.a();

    class a extends Thread {
        final /* synthetic */ ap a;
        private boolean b = false;

        public a(ap apVar) {
            this.a = apVar;
            super("CAE-Read16kAudioThread");
            setPriority(10);
        }

        public void a() {
            interrupt();
            this.b = true;
        }

        public void run() {
            super.run();
            while (!this.b) {
                byte[] bArr = new byte[ap.c];
                int CAERead16kAudio = CAEJni.CAERead16kAudio(bArr);
                if (CAERead16kAudio == 0) {
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (this.a.e != null) {
                    this.a.e.a(bArr, CAERead16kAudio, 0, 0);
                }
            }
        }
    }

    private ap() {
        if (this.f == null) {
            this.f = new a(this);
        }
    }

    public static ap a(String str, String str2, int i) {
        CAEJni.a(str);
        if (TextUtils.isEmpty(str2)) {
            a = null;
            as.b("Ivw resouce path is empty!");
            return null;
        }
        c = i;
        synchronized (ap.class) {
            if (a == null) {
                a = new ap();
                int CAENew = CAEJni.CAENew(str2, "ivwCb", "audioCb", "", a);
                if (CAENew == 0) {
                    a.e();
                    as.a("CAE engine create success. handle=" + b);
                } else {
                    as.b("CAE engine create fail. error=" + CAENew);
                    a = null;
                    b = 0;
                }
            } else {
                as.b("CAE engine has already existed!");
            }
        }
        return a;
    }

    public static int c() {
        return CAEJni.a() ? CAEJni.CAEGetChannel() : -1;
    }

    private void e() {
        if (this.f != null) {
            this.f.start();
        }
    }

    private void f() {
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
    }

    public void a() {
        synchronized (ap.class) {
            if (b != 0) {
                int CAEReset = CAEJni.CAEReset(b);
                if (CAEReset != 0) {
                    this.g.obtainMessage(3, new aq(CAEReset, "")).sendToTarget();
                    as.b("CAE engine reset fail. error=" + CAEReset);
                    b();
                } else {
                    this.d = false;
                    as.a("CAE engine reset sucess.");
                }
            } else {
                as.b("CAE engine is destroyed, invalid call!");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r6) {
        /*
        r5 = this;
        r1 = com.iflytek.cloud.thirdparty.ap.class;
        monitor-enter(r1);
        r0 = b;	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x000f;
    L_0x0007:
        r0 = "CAE engine is destroyed, invalid call!";
        com.iflytek.cloud.thirdparty.as.b(r0);	 Catch:{ all -> 0x003e }
        monitor-exit(r1);	 Catch:{ all -> 0x003e }
    L_0x000e:
        return;
    L_0x000f:
        r0 = b;	 Catch:{ all -> 0x003e }
        r0 = com.iflytek.cae.jni.CAEJni.CAESetRealBeam(r0, r6);	 Catch:{ all -> 0x003e }
        monitor-exit(r1);	 Catch:{ all -> 0x003e }
        if (r0 == 0) goto L_0x000e;
    L_0x0018:
        r1 = new java.lang.StringBuilder;
        r2 = "CAE engine setRealBeam fail. error=";
        r1.<init>(r2);
        r1 = r1.append(r0);
        r1 = r1.toString();
        com.iflytek.cloud.thirdparty.as.a(r1);
        r1 = r5.g;
        r2 = 3;
        r3 = new com.iflytek.cloud.thirdparty.aq;
        r4 = "";
        r3.<init>(r0, r4);
        r0 = r1.obtainMessage(r2, r3);
        r0.sendToTarget();
        goto L_0x000e;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.ap.a(int):void");
    }

    public void a(int i, byte[] bArr, byte[] bArr2) {
        synchronized (ap.class) {
            if (b != 0) {
                int CAESendMsg = CAEJni.CAESendMsg(b, i, bArr, bArr2);
                if (CAESendMsg != 0) {
                    this.g.obtainMessage(3, new aq(CAESendMsg, "")).sendToTarget();
                    as.b("Send message error. error=" + CAESendMsg);
                    b();
                }
            } else {
                as.b("CAE engine is destroyed, invalid call!");
            }
        }
    }

    public void a(ar arVar) {
        this.e = arVar;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            as.b("key or val is empty.");
            return;
        }
        synchronized (ap.class) {
            if (b != 0) {
                try {
                    CAEJni.CAESetWParam(b, str.getBytes("utf-8"), str2.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    as.b("key or value is not utf-8 encoded!");
                }
            } else {
                as.b("CAE engine is destroyed, invalid call!");
            }
        }
    }

    public void a(byte[] bArr, int i) {
        synchronized (ap.class) {
            if (b != 0) {
                int CAEAudioWrite = CAEJni.CAEAudioWrite(b, bArr, i);
                if (CAEAudioWrite != 0) {
                    this.g.obtainMessage(3, new aq(CAEAudioWrite, "")).sendToTarget();
                    as.b("Write audio error. error=" + CAEAudioWrite);
                    b();
                }
            } else {
                as.b("CAE engine is destroyed, invalid call!");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r5 = this;
        r1 = com.iflytek.cloud.thirdparty.ap.class;
        monitor-enter(r1);
        r0 = b;	 Catch:{ all -> 0x0063 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x0063 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = a;	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x0012;
    L_0x000d:
        r0 = a;	 Catch:{ all -> 0x0063 }
        r0.f();	 Catch:{ all -> 0x0063 }
    L_0x0012:
        r0 = b;	 Catch:{ all -> 0x0063 }
        r0 = com.iflytek.cae.jni.CAEJni.CAEDestroy(r0);	 Catch:{ all -> 0x0063 }
        if (r0 != 0) goto L_0x004f;
    L_0x001a:
        r2 = 0;
        b = r2;	 Catch:{ all -> 0x0063 }
        r2 = 0;
        a = r2;	 Catch:{ all -> 0x0063 }
        r2 = "CAE engine destroy sucess.";
        com.iflytek.cloud.thirdparty.as.a(r2);	 Catch:{ all -> 0x0063 }
    L_0x0026:
        monitor-exit(r1);	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x0008;
    L_0x0029:
        r1 = new java.lang.StringBuilder;
        r2 = "CAE engine destroy fail. error=";
        r1.<init>(r2);
        r1 = r1.append(r0);
        r1 = r1.toString();
        com.iflytek.cloud.thirdparty.as.a(r1);
        r1 = r5.g;
        r2 = 3;
        r3 = new com.iflytek.cloud.thirdparty.aq;
        r4 = "";
        r3.<init>(r0, r4);
        r0 = r1.obtainMessage(r2, r3);
        r0.sendToTarget();
        goto L_0x0008;
    L_0x004f:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r3 = "CAE engine destroy, error=";
        r2.<init>(r3);	 Catch:{ all -> 0x0063 }
        r2 = r2.append(r0);	 Catch:{ all -> 0x0063 }
        r2 = r2.toString();	 Catch:{ all -> 0x0063 }
        com.iflytek.cloud.thirdparty.as.a(r2);	 Catch:{ all -> 0x0063 }
        goto L_0x0026;
    L_0x0063:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0063 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.ap.b():void");
    }
}
