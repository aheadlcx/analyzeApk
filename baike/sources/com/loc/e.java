package com.loc;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobstat.Config;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public final class e {
    private boolean A = true;
    private String B = "";
    private String C = "jsonp1";
    String a = null;
    b b = null;
    AMapLocation c = null;
    a d = null;
    Context e = null;
    bu f = null;
    boolean g = false;
    HashMap<Messenger, Long> h = new HashMap();
    db i = null;
    long j = 0;
    long k = 0;
    String l = null;
    AMapLocationClientOption m = new AMapLocationClientOption();
    ServerSocket n = null;
    boolean o = false;
    Socket p = null;
    boolean q = false;
    c r = null;
    private boolean s = false;
    private boolean t = false;
    private long u = 0;
    private long v = 0;
    private AMapLocationServer w = null;
    private long x = 0;
    private int y = 0;
    private h z = null;

    public class a extends Handler {
        final /* synthetic */ e a;

        public a(e eVar, Looper looper) {
            this.a = eVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Bundle data;
            e eVar;
            Throwable th;
            Messenger messenger = null;
            try {
                data = message.getData();
                try {
                    messenger = message.replyTo;
                    if (!(data == null || data.isEmpty())) {
                        Object string = data.getString("c");
                        e eVar2 = this.a;
                        if (TextUtils.isEmpty(eVar2.l)) {
                            eVar2.l = cw.d(eVar2.e);
                        }
                        if (TextUtils.isEmpty(string) || !string.equals(eVar2.l)) {
                            string = null;
                        } else {
                            int i = 1;
                        }
                        if (string == null) {
                            if (message.what == 1) {
                                eVar = this.a;
                                AMapLocation a = e.a(10, "invalid handlder scode!!!");
                                this.a.a(messenger, a, a.k(), 0);
                                return;
                            }
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        cw.a(th, "APSServiceCore", "ActionHandler handlerMessage");
                        switch (message.what) {
                            case 0:
                                this.a.a(data);
                                e.a(this.a, messenger, data);
                                break;
                            case 1:
                                this.a.a(data);
                                e.b(this.a, messenger, data);
                                break;
                            case 2:
                                if (data != null) {
                                    return;
                                }
                                return;
                            case 3:
                                if (data != null) {
                                    return;
                                }
                                return;
                            case 4:
                                this.a.a(data);
                                e.a(this.a);
                                break;
                            case 5:
                                this.a.a(data);
                                eVar = this.a;
                                try {
                                    if (!cv.e()) {
                                        eVar.f.d();
                                    } else if (!de.d(eVar.e)) {
                                        eVar.f.d();
                                    }
                                    eVar.d.sendEmptyMessageDelayed(5, (long) (cv.d() * 1000));
                                    break;
                                } catch (Throwable th3) {
                                    cw.a(th3, "APSServiceCore", "doOffFusion");
                                    break;
                                }
                            case 7:
                                this.a.a(data);
                                e.b(this.a);
                                break;
                            case 9:
                                this.a.a(data);
                                e.a(this.a, messenger);
                                break;
                            case 10:
                                this.a.a(data);
                                this.a.a(messenger, data);
                                break;
                            case 11:
                                this.a.b();
                                break;
                            case 12:
                                this.a.h.remove(messenger);
                                break;
                        }
                    } catch (Throwable th32) {
                        cw.a(th32, "actionHandler", "handleMessage");
                        return;
                    }
                    super.handleMessage(message);
                }
            } catch (Throwable th4) {
                th32 = th4;
                data = null;
                cw.a(th32, "APSServiceCore", "ActionHandler handlerMessage");
                switch (message.what) {
                    case 0:
                        this.a.a(data);
                        e.a(this.a, messenger, data);
                        break;
                    case 1:
                        this.a.a(data);
                        e.b(this.a, messenger, data);
                        break;
                    case 2:
                        if (data != null) {
                            return;
                        }
                        return;
                    case 3:
                        if (data != null) {
                            return;
                        }
                        return;
                    case 4:
                        this.a.a(data);
                        e.a(this.a);
                        break;
                    case 5:
                        this.a.a(data);
                        eVar = this.a;
                        if (!cv.e()) {
                            eVar.f.d();
                        } else if (de.d(eVar.e)) {
                            eVar.f.d();
                        }
                        eVar.d.sendEmptyMessageDelayed(5, (long) (cv.d() * 1000));
                        break;
                    case 7:
                        this.a.a(data);
                        e.b(this.a);
                        break;
                    case 9:
                        this.a.a(data);
                        e.a(this.a, messenger);
                        break;
                    case 10:
                        this.a.a(data);
                        this.a.a(messenger, data);
                        break;
                    case 11:
                        this.a.b();
                        break;
                    case 12:
                        this.a.h.remove(messenger);
                        break;
                }
                super.handleMessage(message);
            }
            switch (message.what) {
                case 0:
                    this.a.a(data);
                    e.a(this.a, messenger, data);
                    break;
                case 1:
                    this.a.a(data);
                    e.b(this.a, messenger, data);
                    break;
                case 2:
                    if (data != null && !data.isEmpty()) {
                        this.a.a(null);
                        eVar = this.a;
                        if (!eVar.q) {
                            eVar.r = new c(eVar);
                            eVar.r.start();
                            eVar.q = true;
                            break;
                        }
                    }
                    return;
                    break;
                case 3:
                    if (data != null && !data.isEmpty()) {
                        this.a.a(null);
                        this.a.a();
                        break;
                    }
                    return;
                case 4:
                    this.a.a(data);
                    e.a(this.a);
                    break;
                case 5:
                    this.a.a(data);
                    eVar = this.a;
                    if (!cv.e()) {
                        eVar.f.d();
                    } else if (de.d(eVar.e)) {
                        eVar.f.d();
                    }
                    eVar.d.sendEmptyMessageDelayed(5, (long) (cv.d() * 1000));
                    break;
                case 7:
                    this.a.a(data);
                    e.b(this.a);
                    break;
                case 9:
                    this.a.a(data);
                    e.a(this.a, messenger);
                    break;
                case 10:
                    this.a.a(data);
                    this.a.a(messenger, data);
                    break;
                case 11:
                    this.a.b();
                    break;
                case 12:
                    this.a.h.remove(messenger);
                    break;
            }
            super.handleMessage(message);
        }
    }

    class b extends HandlerThread {
        final /* synthetic */ e a;

        public b(e eVar, String str) {
            this.a = eVar;
            super(str);
        }

        protected final void onLooperPrepared() {
            try {
                this.a.z = new h(this.a.e);
            } catch (Throwable th) {
                cw.a(th, "actionHandler", "onLooperPrepared");
                return;
            }
            this.a.f = new bu();
            super.onLooperPrepared();
        }
    }

    class c extends Thread {
        final /* synthetic */ e a;

        c(e eVar) {
            this.a = eVar;
        }

        public final void run() {
            try {
                if (!this.a.o) {
                    this.a.o = true;
                    this.a.n = new ServerSocket(43689);
                }
                while (this.a.o && this.a.n != null) {
                    this.a.p = this.a.n.accept();
                    e.a(this.a, this.a.p);
                }
            } catch (Throwable th) {
                cw.a(th, "APSServiceCore", "run");
            }
            super.run();
        }
    }

    public e(Context context) {
        this.e = context;
    }

    private static AMapLocationServer a(int i, String str) {
        try {
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(i);
            aMapLocationServer.setLocationDetail(str);
            return aMapLocationServer;
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    private void a(Bundle bundle) {
        try {
            if (!this.s) {
                cw.a(this.e);
                if (bundle != null) {
                    this.m = cw.a(bundle.getBundle("optBundle"));
                }
                this.s = true;
                this.f.a(this.e);
                this.f.a();
                a(this.m);
                this.f.b();
            }
        } catch (Throwable th) {
            this.A = false;
            this.B = th.getMessage();
            cw.a(th, "APSServiceCore", "init");
        }
    }

    private void a(Messenger messenger) {
        try {
            if (cv.d(this.e)) {
                a(messenger, 100, null);
            }
            if (cv.a()) {
                this.d.sendEmptyMessage(4);
            }
            if (cv.c() && cv.d() > 2) {
                this.d.sendEmptyMessage(5);
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "checkConfig");
        }
    }

    private static void a(Messenger messenger, int i, Bundle bundle) {
        if (messenger != null) {
            try {
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = i;
                messenger.send(obtain);
            } catch (Throwable th) {
                cw.a(th, "APSServiceCore", "sendMessage");
            }
        }
    }

    private void a(Messenger messenger, AMapLocation aMapLocation, String str, int i) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString("nb", str);
        bundle.putInt("originalLocType", i);
        this.h.put(messenger, Long.valueOf(de.b()));
        a(messenger, 1, bundle);
    }

    private void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (this.f != null) {
                this.f.a(aMapLocationClientOption);
                this.g = aMapLocationClientOption.isKillProcess();
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "setExtra");
        }
    }

    static /* synthetic */ void a(e eVar) {
        try {
            if (eVar.y < cv.b()) {
                eVar.y++;
                eVar.f.d();
                eVar.d.sendEmptyMessageDelayed(4, 2000);
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "doGpsFusion");
        }
    }

    static /* synthetic */ void a(e eVar, Messenger messenger) {
        try {
            eVar.b(messenger);
            cv.f(eVar.e);
            try {
                bu buVar = eVar.f;
                Context context = eVar.e;
                buVar.g();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            cw.a(th2, "APSServiceCore", "doCallOtherSer");
        }
    }

    static /* synthetic */ void a(e eVar, Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && !eVar.t) {
                    eVar.t = true;
                    eVar.b(messenger);
                    cv.f(eVar.e);
                    try {
                        bu buVar = eVar.f;
                        Context context = eVar.e;
                        buVar.f();
                    } catch (Throwable th) {
                    }
                    eVar.a(messenger);
                    if (cv.a(eVar.x) && "1".equals(bundle.getString("isCacheLoc"))) {
                        eVar.x = de.b();
                        eVar.f.d();
                    }
                    eVar.d();
                }
            } catch (Throwable th2) {
                cw.a(th2, "APSServiceCore", "doInitAuth");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.loc.e r7, java.net.Socket r8) {
        /*
        r1 = 0;
        if (r8 != 0) goto L_0x0004;
    L_0x0003:
        return;
    L_0x0004:
        r3 = com.loc.cw.e;	 Catch:{ Throwable -> 0x009c }
        r2 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0130, all -> 0x01a8 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x0130, all -> 0x01a8 }
        r4 = r8.getInputStream();	 Catch:{ Throwable -> 0x0130, all -> 0x01a8 }
        r5 = "UTF-8";
        r0.<init>(r4, r5);	 Catch:{ Throwable -> 0x0130, all -> 0x01a8 }
        r2.<init>(r0);	 Catch:{ Throwable -> 0x0130, all -> 0x01a8 }
        r7.a(r2);	 Catch:{ Throwable -> 0x01ef, all -> 0x01e8 }
        r1 = r7.c();	 Catch:{ Throwable -> 0x01ef, all -> 0x01e8 }
        if (r1 != 0) goto L_0x01fb;
    L_0x001f:
        r0 = r7.w;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        if (r0 != 0) goto L_0x00a6;
    L_0x0023:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0.<init>();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.C;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "&&";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.C;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "({'package':'";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "','error_code':8,'error':'unknown error'})";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
    L_0x0050:
        r4 = r7.e;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = com.loc.de.d(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        if (r4 == 0) goto L_0x0085;
    L_0x0058:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0.<init>();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.C;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "&&";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.C;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "({'package':'";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "','error_code':36,'error':'app is background'})";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
    L_0x0085:
        com.loc.cw.e = r3;	 Catch:{ Throwable -> 0x009c }
        r7.b(r0);	 Catch:{ Throwable -> 0x0105 }
        r2.close();	 Catch:{ Throwable -> 0x0092 }
        r8.close();	 Catch:{ Throwable -> 0x0092 }
        goto L_0x0003;
    L_0x0092:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "invoke part4";
        com.loc.cw.a(r0, r1, r2);	 Catch:{ Throwable -> 0x009c }
        goto L_0x0003;
    L_0x009c:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "invoke part5";
        com.loc.cw.a(r0, r1, r2);
        goto L_0x0003;
    L_0x00a6:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0.<init>();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.C;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "&&";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.C;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "({'package':'";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.a;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "','error_code':0,'error':'','location':{'y':";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.w;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r4.getLatitude();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = ",'precision':";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.w;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r4.getAccuracy();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = ",'x':";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r7.w;	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = r4.getLongitude();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r4 = "},'version_code':'3.4.0','version':'3.4.0'})";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x01f5, all -> 0x01e8 }
        goto L_0x0050;
    L_0x0105:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r3 = "invoke part3";
        com.loc.cw.a(r0, r1, r3);	 Catch:{ all -> 0x011f }
        r2.close();	 Catch:{ Throwable -> 0x0115 }
        r8.close();	 Catch:{ Throwable -> 0x0115 }
        goto L_0x0003;
    L_0x0115:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "invoke part4";
        com.loc.cw.a(r0, r1, r2);	 Catch:{ Throwable -> 0x009c }
        goto L_0x0003;
    L_0x011f:
        r0 = move-exception;
        r2.close();	 Catch:{ Throwable -> 0x0127 }
        r8.close();	 Catch:{ Throwable -> 0x0127 }
    L_0x0126:
        throw r0;	 Catch:{ Throwable -> 0x009c }
    L_0x0127:
        r1 = move-exception;
        r2 = "APSServiceCore";
        r3 = "invoke part4";
        com.loc.cw.a(r1, r2, r3);	 Catch:{ Throwable -> 0x009c }
        goto L_0x0126;
    L_0x0130:
        r0 = move-exception;
        r2 = r1;
    L_0x0132:
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ea }
        r4.<init>();	 Catch:{ all -> 0x01ea }
        r5 = r7.C;	 Catch:{ all -> 0x01ea }
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r5 = "&&";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r5 = r7.C;	 Catch:{ all -> 0x01ea }
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r5 = "({'package':'";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r5 = r7.a;	 Catch:{ all -> 0x01ea }
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r5 = "','error_code':1,'error':'params error'})";
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ea }
        r2 = r4.toString();	 Catch:{ all -> 0x01ea }
        r4 = "APSServiceCore";
        r5 = "invoke part2";
        com.loc.cw.a(r0, r4, r5);	 Catch:{ all -> 0x01ea }
        com.loc.cw.e = r3;	 Catch:{ Throwable -> 0x009c }
        r7.b(r2);	 Catch:{ Throwable -> 0x017d }
        r1.close();	 Catch:{ Throwable -> 0x0173 }
        r8.close();	 Catch:{ Throwable -> 0x0173 }
        goto L_0x0003;
    L_0x0173:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "invoke part4";
        com.loc.cw.a(r0, r1, r2);	 Catch:{ Throwable -> 0x009c }
        goto L_0x0003;
    L_0x017d:
        r0 = move-exception;
        r2 = "APSServiceCore";
        r3 = "invoke part3";
        com.loc.cw.a(r0, r2, r3);	 Catch:{ all -> 0x0197 }
        r1.close();	 Catch:{ Throwable -> 0x018d }
        r8.close();	 Catch:{ Throwable -> 0x018d }
        goto L_0x0003;
    L_0x018d:
        r0 = move-exception;
        r1 = "APSServiceCore";
        r2 = "invoke part4";
        com.loc.cw.a(r0, r1, r2);	 Catch:{ Throwable -> 0x009c }
        goto L_0x0003;
    L_0x0197:
        r0 = move-exception;
        r1.close();	 Catch:{ Throwable -> 0x019f }
        r8.close();	 Catch:{ Throwable -> 0x019f }
    L_0x019e:
        throw r0;	 Catch:{ Throwable -> 0x009c }
    L_0x019f:
        r1 = move-exception;
        r2 = "APSServiceCore";
        r3 = "invoke part4";
        com.loc.cw.a(r1, r2, r3);	 Catch:{ Throwable -> 0x009c }
        goto L_0x019e;
    L_0x01a8:
        r0 = move-exception;
        r2 = r1;
    L_0x01aa:
        com.loc.cw.e = r3;	 Catch:{ Throwable -> 0x009c }
        r7.b(r1);	 Catch:{ Throwable -> 0x01bf }
        r2.close();	 Catch:{ Throwable -> 0x01b6 }
        r8.close();	 Catch:{ Throwable -> 0x01b6 }
    L_0x01b5:
        throw r0;	 Catch:{ Throwable -> 0x009c }
    L_0x01b6:
        r1 = move-exception;
        r2 = "APSServiceCore";
        r3 = "invoke part4";
        com.loc.cw.a(r1, r2, r3);	 Catch:{ Throwable -> 0x009c }
        goto L_0x01b5;
    L_0x01bf:
        r1 = move-exception;
        r3 = "APSServiceCore";
        r4 = "invoke part3";
        com.loc.cw.a(r1, r3, r4);	 Catch:{ all -> 0x01d7 }
        r2.close();	 Catch:{ Throwable -> 0x01ce }
        r8.close();	 Catch:{ Throwable -> 0x01ce }
        goto L_0x01b5;
    L_0x01ce:
        r1 = move-exception;
        r2 = "APSServiceCore";
        r3 = "invoke part4";
        com.loc.cw.a(r1, r2, r3);	 Catch:{ Throwable -> 0x009c }
        goto L_0x01b5;
    L_0x01d7:
        r0 = move-exception;
        r2.close();	 Catch:{ Throwable -> 0x01df }
        r8.close();	 Catch:{ Throwable -> 0x01df }
    L_0x01de:
        throw r0;	 Catch:{ Throwable -> 0x009c }
    L_0x01df:
        r1 = move-exception;
        r2 = "APSServiceCore";
        r3 = "invoke part4";
        com.loc.cw.a(r1, r2, r3);	 Catch:{ Throwable -> 0x009c }
        goto L_0x01de;
    L_0x01e8:
        r0 = move-exception;
        goto L_0x01aa;
    L_0x01ea:
        r0 = move-exception;
        r6 = r1;
        r1 = r2;
        r2 = r6;
        goto L_0x01aa;
    L_0x01ef:
        r0 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0132;
    L_0x01f5:
        r0 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0132;
    L_0x01fb:
        r0 = r1;
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.e.a(com.loc.e, java.net.Socket):void");
    }

    private void a(BufferedReader bufferedReader) throws IOException {
        String readLine = bufferedReader.readLine();
        int i = 30000;
        if (readLine != null && readLine.length() > 0) {
            String[] split = readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split != null && split.length > 1) {
                split = split[1].split("\\?");
                if (split != null && split.length > 1) {
                    String[] split2 = split[1].split(com.alipay.sdk.sys.a.b);
                    if (split2 != null && split2.length > 0) {
                        int i2 = 30000;
                        for (String split3 : split2) {
                            String[] split4 = split3.split("=");
                            if (split4 != null && split4.length > 1) {
                                if ("to".equals(split4[0])) {
                                    i2 = Integer.parseInt(split4[1]);
                                }
                                if (com.alipay.sdk.authjs.a.c.equals(split4[0])) {
                                    this.C = split4[1];
                                }
                            }
                        }
                        i = i2;
                    }
                }
            }
        }
        cw.e = i;
    }

    private void b(Messenger messenger) {
        try {
            bu buVar = this.f;
            bu.b(this.e);
            if (cv.q()) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("ngpsAble", cv.s());
                a(messenger, 7, bundle);
                cv.r();
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "initAuth");
        }
    }

    static /* synthetic */ void b(e eVar) {
        try {
            if (cv.a(eVar.e, eVar.u)) {
                eVar.u = de.a();
                eVar.f.d();
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "doNGps");
        }
    }

    static /* synthetic */ void b(e eVar, Messenger messenger, Bundle bundle) {
        String str = null;
        int i = 0;
        if (eVar.h.containsKey(messenger)) {
            if (de.b() - ((Long) eVar.h.get(messenger)).longValue() < 800) {
                return;
            }
        }
        if (bundle != null && !bundle.isEmpty()) {
            if (eVar.A) {
                AMapLocationClientOption a = cw.a(bundle.getBundle("optBundle"));
                eVar.a(a);
                long b = de.b();
                if (eVar.w == null || eVar.w.getErrorCode() != 0 || b - eVar.v >= 600) {
                    int i2;
                    da daVar = new da();
                    daVar.a(de.b());
                    try {
                        eVar.w = eVar.f.c();
                        if (eVar.w != null) {
                            i = eVar.w.getLocationType();
                        }
                        daVar.a(eVar.w);
                        b = 0;
                        if (eVar.w != null) {
                            b = eVar.w.getTime();
                        }
                        eVar.w = eVar.f.a(eVar.w, new String[0]);
                        eVar.w.setTime(b);
                        i2 = i;
                    } catch (Throwable th) {
                        cw.a(th, "APSServiceCore", "doLocation");
                        return;
                    }
                    daVar.b(de.b());
                    if (eVar.w != null && eVar.w.getErrorCode() == 0) {
                        eVar.v = de.b();
                    }
                    if (eVar.w == null) {
                        eVar.w = a(8, "loc is null");
                    }
                    if (eVar.w != null) {
                        str = eVar.w.k();
                    }
                    AMapLocation aMapLocation = eVar.w;
                    try {
                        if (a.isLocationCacheEnable() && eVar.z != null) {
                            aMapLocation = eVar.z.b(eVar.w, str);
                        }
                    } catch (Throwable th2) {
                        cw.a(th2, "APSServiceCore", "fixLastLocation");
                    }
                    eVar.a(messenger, aMapLocation, str, i2);
                    db.a(eVar.e, daVar);
                    i = i2;
                } else {
                    eVar.a(messenger, eVar.w, eVar.w.k(), 4);
                }
                eVar.b(messenger);
                if (cv.h(eVar.e)) {
                    eVar.a(messenger);
                }
                if (cv.a(eVar.x) && eVar.w != null && (r1 == 2 || r1 == 4)) {
                    eVar.x = de.b();
                    eVar.f.d();
                }
                eVar.d();
                return;
            }
            eVar.w = a(9, "init error : " + eVar.B);
            eVar.a(messenger, eVar.w, eVar.w.k(), 0);
        }
    }

    private void b(String str) throws UnsupportedEncodingException, IOException {
        PrintStream printStream = new PrintStream(this.p.getOutputStream(), true, "UTF-8");
        printStream.println("HTTP/1.0 200 OK");
        printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
        printStream.println();
        printStream.println(str);
        printStream.close();
    }

    private String c() {
        String str = null;
        long currentTimeMillis = System.currentTimeMillis();
        if ((this.w == null || currentTimeMillis - this.w.getTime() > Config.BPLUS_DELAY_TIME) && !de.d(this.e)) {
            try {
                this.w = this.f.c();
                if (this.w.getErrorCode() != 0) {
                    str = this.C + "&&" + this.C + "({'package':'" + this.a + "','error_code':" + this.w.getErrorCode() + ",'error':'" + this.w.getErrorInfo() + "'})";
                }
            } catch (Throwable th) {
                cw.a(th, "APSServiceCore", "invoke part1");
            }
        }
        return str;
    }

    private void d() {
        try {
            if (this.f != null) {
                this.f.j();
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "startColl");
        }
    }

    public final void a() {
        try {
            if (this.p != null) {
                this.p.close();
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "doStopScocket 1");
        }
        try {
            if (this.n != null) {
                this.n.close();
            }
        } catch (Throwable th2) {
            cw.a(th2, "APSServiceCore", "doStopScocket 2");
        }
        try {
            if (this.r != null) {
                this.r.interrupt();
            }
        } catch (Throwable th3) {
        }
        this.r = null;
        this.n = null;
        this.o = false;
        this.q = false;
    }

    final void a(Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && cv.y()) {
                    float a;
                    double d = bundle.getDouble("lat");
                    double d2 = bundle.getDouble("lon");
                    if (this.c != null) {
                        a = de.a(new double[]{d, d2, this.c.getLatitude(), this.c.getLongitude()});
                        if (a < ((float) (cv.z() * 3))) {
                            Bundle bundle2 = new Bundle();
                            bundle2.setClassLoader(AMapLocation.class.getClassLoader());
                            bundle2.putInt("lMaxGeoDis", cv.z() * 3);
                            bundle2.putInt("lMinGeoDis", cv.z());
                            bundle2.putParcelable("loc", this.c);
                            a(messenger, 6, bundle2);
                        }
                    } else {
                        a = -1.0f;
                    }
                    if (a == -1.0f || a > ((float) cv.z())) {
                        a(null);
                        this.c = this.f.a(d, d2);
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "APSServiceCore", "doLocationGeo");
            }
        }
    }

    public final void b() {
        try {
            this.h.clear();
            this.h = null;
            if (this.f != null) {
                bu buVar = this.f;
                bu.b(this.e);
            }
            if (this.d != null) {
                this.d.removeCallbacksAndMessages(null);
            }
            if (this.b != null) {
                if (VERSION.SDK_INT >= 18) {
                    cz.a(this.b, HandlerThread.class, "quitSafely", new Object[0]);
                } else {
                    this.b.quit();
                }
            }
        } catch (Throwable th) {
            cw.a(th, "APSServiceCore", "threadDestroy");
            return;
        }
        this.b = null;
        this.d = null;
        if (this.z != null) {
            this.z.b();
            this.z = null;
        }
        a();
        this.s = false;
        this.t = false;
        this.f.e();
        db.a(this.e);
        if (!(this.i == null || this.j == 0 || this.k == 0)) {
            long b = de.b() - this.j;
            db.a(this.e, this.i.c(this.e), this.i.d(this.e), this.k, b);
            this.i.e(this.e);
        }
        z.a();
        if (this.g) {
            Process.killProcess(Process.myPid());
        }
    }
}
