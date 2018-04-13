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
import com.qq.e.comm.pi.NADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class NativeAD {
    private NADI a;
    private NativeAdListener b;
    private boolean c;
    private boolean d;
    private List<Integer> e = new ArrayList();
    private boolean f = false;
    private BrowserType g;
    private DownAPPConfirmPolicy h;

    class ADListenerAdapter implements ADListener {
        private /* synthetic */ NativeAD a;

        private ADListenerAdapter(NativeAD nativeAD) {
            this.a = nativeAD;
        }

        public void onADEvent(ADEvent aDEvent) {
            if (this.a.b == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case 1:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        this.a.b.onNoAD(a.a(((Integer) aDEvent.getParas()[0]).intValue()));
                        return;
                    } else {
                        GDTLogger.e("AdEvent.Paras error for NativeAD(" + aDEvent + ")");
                        return;
                    }
                case 2:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                        this.a.b.onADLoaded((List) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeAD(" + aDEvent + ")");
                        return;
                    }
                case 3:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeADDataRef)) {
                        this.a.b.onADStatusChanged((NativeADDataRef) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeAD(" + aDEvent + ")");
                        return;
                    }
                case 4:
                    if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof NativeADDataRef) && (aDEvent.getParas()[1] instanceof Integer)) {
                        this.a.b.onADError((NativeADDataRef) aDEvent.getParas()[0], a.a(((Integer) aDEvent.getParas()[1]).intValue()));
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for NativeAD(" + aDEvent + ")");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public interface NativeAdListener {
        void onADError(NativeADDataRef nativeADDataRef, AdError adError);

        void onADLoaded(List<NativeADDataRef> list);

        void onADStatusChanged(NativeADDataRef nativeADDataRef);

        void onNoAD(AdError adError);
    }

    public NativeAD(final Context context, final String str, final String str2, NativeAdListener nativeAdListener) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || context == null) {
            GDTLogger.e(String.format("GDTNativeAd Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, context}));
            return;
        }
        this.c = true;
        if (a.a(context)) {
            this.d = true;
            this.b = nativeAdListener;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ NativeAD d;

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
                                    r5 = new com.qq.e.ads.nativ.NativeAD$ADListenerAdapter;	 Catch:{ Throwable -> 0x0086 }
                                    r6 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r6 = r6.d;	 Catch:{ Throwable -> 0x0086 }
                                    r7 = 0;
                                    r5.<init>();	 Catch:{ Throwable -> 0x0086 }
                                    r1 = r1.getNativeADDelegate(r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0086 }
                                    r0.a = r1;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x0086 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x0086 }
                                    r1 = 1;
                                    r0.f = true;	 Catch:{ Throwable -> 0x0086 }
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
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x0086 }
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
                                    r1 = "Exception while init Native Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x009c }
                                    r0 = r9.b;
                                    r0 = r0.d;
                                    r0.f = true;
                                L_0x0093:
                                    return;
                                L_0x0094:
                                    r0 = r9.b;
                                    r0 = r0.d;
                                    r0.f = true;
                                    goto L_0x0093;
                                L_0x009c:
                                    r0 = move-exception;
                                    r1 = r9.b;
                                    r1 = r1.d;
                                    r1.f = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.nativ.NativeAD.1.1.run():void");
                                }
                            });
                            return;
                        } catch (Throwable th) {
                            GDTLogger.e("Exception while init Native plugin", th);
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
        if (!this.c || !this.d) {
            GDTLogger.e("AD init Paras OR Context error,details in logs produced while init NativeAD");
        } else if (!this.f) {
            this.e.add(Integer.valueOf(i));
        } else if (this.a != null) {
            this.a.loadAd(i);
        } else {
            GDTLogger.e("NativeAD Init error,See More Logs");
        }
    }

    public void setBrowserType(BrowserType browserType) {
        this.g = browserType;
        if (this.a != null && browserType != null) {
            this.a.setBrowserType(browserType.value());
        }
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.h = downAPPConfirmPolicy;
        if (this.a != null && downAPPConfirmPolicy != null) {
            this.a.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }
}
