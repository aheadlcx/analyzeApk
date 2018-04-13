package com.qq.e.ads.nativ;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.NVADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class NativeMediaAD {
    private volatile boolean a;
    private volatile boolean b;
    private List<Integer> c = new ArrayList();
    private volatile boolean d = false;
    private NVADI e;
    private NativeMediaADListener f;
    private BrowserType g;
    private DownAPPConfirmPolicy h;

    class ADListenerAdapter implements ADListener {
        private /* synthetic */ NativeMediaAD a;

        private ADListenerAdapter(NativeMediaAD nativeMediaAD) {
            this.a = nativeMediaAD;
        }

        public void onADEvent(ADEvent aDEvent) {
            if (this.a.f == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case 1:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        this.a.f.onNoAD(a.a(((Integer) aDEvent.getParas()[0]).intValue()));
                        return;
                    } else {
                        GDTLogger.e("AdEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                case 2:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                        this.a.f.onADLoaded((List) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                case 3:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        this.a.f.onADStatusChanged((NativeMediaADData) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                case 4:
                    if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof NativeADDataRef) && (aDEvent.getParas()[1] instanceof Integer)) {
                        this.a.f.onADError((NativeMediaADData) aDEvent.getParas()[0], a.a(((Integer) aDEvent.getParas()[1]).intValue()));
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                case 5:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        this.a.f.onADVideoLoaded((NativeMediaADData) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                case 6:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        this.a.f.onADExposure((NativeMediaADData) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                case 7:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        this.a.f.onADClicked((NativeMediaADData) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeMediaAD(" + aDEvent + ")");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public interface NativeMediaADListener {
        void onADClicked(NativeMediaADData nativeMediaADData);

        void onADError(NativeMediaADData nativeMediaADData, AdError adError);

        void onADExposure(NativeMediaADData nativeMediaADData);

        void onADLoaded(List<NativeMediaADData> list);

        void onADStatusChanged(NativeMediaADData nativeMediaADData);

        void onADVideoLoaded(NativeMediaADData nativeMediaADData);

        void onNoAD(AdError adError);
    }

    public NativeMediaAD(final Context context, final String str, final String str2, NativeMediaADListener nativeMediaADListener) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || context == null) {
            GDTLogger.e(String.format("NativeMediaAD Contructor paras error, appid=%s, posId=%s, context=%s", new Object[]{str, str2, context}));
            return;
        }
        this.a = true;
        if (a.a(context)) {
            this.b = true;
            this.f = nativeMediaADListener;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ NativeMediaAD d;

                public void run() {
                    if (GDTADManager.getInstance().initWith(context, str)) {
                        try {
                            final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                private /* synthetic */ AnonymousClass1 b;

                                /* JADX WARNING: inconsistent code. */
                                /* Code decompiled incorrectly, please refer to instructions dump. */
                                public void run() {
                                    /*
                                    r9 = this;
                                    r8 = 1;
                                    r0 = r0;	 Catch:{ Throwable -> 0x0086 }
                                    if (r0 == 0) goto L_0x0094;
                                L_0x0005:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r0;	 Catch:{ Throwable -> 0x0086 }
                                    r2 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r2 = r5;	 Catch:{ Throwable -> 0x0086 }
                                    r3 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r3 = r6;	 Catch:{ Throwable -> 0x0086 }
                                    r4 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r4 = r7;	 Catch:{ Throwable -> 0x0086 }
                                    r5 = new com.qq.e.ads.nativ.NativeMediaAD$ADListenerAdapter;	 Catch:{ Throwable -> 0x0086 }
                                    r6 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r6 = r6.d;	 Catch:{ Throwable -> 0x0086 }
                                    r7 = 0;
                                    r5.<init>();	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r1.getNativeVideoADDelegate(r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0086 }
                                    r0.e = r1;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = 1;
                                    r0.d = true;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.g;	 Catch:{ Throwable -> 0x0086 }
                                    if (r0 == 0) goto L_0x0049;
                                L_0x003a:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r1.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r1.g;	 Catch:{ Throwable -> 0x0086 }
                                    r0.setBrowserType(r1);	 Catch:{ Throwable -> 0x0086 }
                                L_0x0049:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.h;	 Catch:{ Throwable -> 0x0086 }
                                    if (r0 == 0) goto L_0x0062;
                                L_0x0053:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r1.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r1.h;	 Catch:{ Throwable -> 0x0086 }
                                    r0.setDownAPPConfirmPolicy(r1);	 Catch:{ Throwable -> 0x0086 }
                                L_0x0062:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.c;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r0.iterator();	 Catch:{ Throwable -> 0x0086 }
                                L_0x006e:
                                    r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0086 }
                                    if (r0 == 0) goto L_0x0094;
                                L_0x0074:
                                    r0 = r1.next();	 Catch:{ Throwable -> 0x0086 }
                                    r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x0086 }
                                    r2 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r2 = r2.d;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.intValue();	 Catch:{ Throwable -> 0x0086 }
                                    r2.loadAD(r0);	 Catch:{ Throwable -> 0x0086 }
                                    goto L_0x006e;
                                L_0x0086:
                                    r0 = move-exception;
                                    r1 = "Exception while init NativeMediaAD Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x009c }
                                    r0 = r9.b;
                                    r0 = r0.d;
                                    r0.d = true;
                                L_0x0093:
                                    return;
                                L_0x0094:
                                    r0 = r9.b;
                                    r0 = r0.d;
                                    r0.d = true;
                                    goto L_0x0093;
                                L_0x009c:
                                    r0 = move-exception;
                                    r1 = r9.b;
                                    r1 = r1.d;
                                    r1.d = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.nativ.NativeMediaAD.1.1.run():void");
                                }
                            });
                            return;
                        } catch (Throwable th) {
                            GDTLogger.e("Exception while init NativeMediaAD plugin", th);
                            return;
                        }
                    }
                    GDTLogger.e("Fail to init ADManager");
                }
            });
            return;
        }
        GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
    }

    public void loadAD(int i) {
        if (!this.a || !this.b) {
            GDTLogger.e("AD init Paras OR Context error, details in logs produced while init NativeMediaAD");
        } else if (!this.d) {
            this.c.add(Integer.valueOf(i));
        } else if (this.e != null) {
            this.e.loadAd(i);
        } else {
            GDTLogger.e("NativeMediaAD Init error, See More Logs");
        }
    }

    public void setBrowserType(BrowserType browserType) {
        this.g = browserType;
        if (this.e != null && browserType != null) {
            this.e.setBrowserType(browserType.value());
        }
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.h = downAPPConfirmPolicy;
        if (this.e != null && downAPPConfirmPolicy != null) {
            this.e.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }
}
