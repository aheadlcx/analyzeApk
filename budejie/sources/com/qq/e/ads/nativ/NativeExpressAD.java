package com.qq.e.ads.nativ;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.NEADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class NativeExpressAD {
    private NEADI a;
    private NativeExpressADListener b;
    private boolean c;
    private boolean d;
    private List<Integer> e = new ArrayList();
    private boolean f = false;
    private BrowserType g;
    private DownAPPConfirmPolicy h;
    private VideoOption i;

    class ADListenerAdapter implements ADListener {
        private /* synthetic */ NativeExpressAD a;

        private ADListenerAdapter(NativeExpressAD nativeExpressAD) {
            this.a = nativeExpressAD;
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
                        GDTLogger.e("AdEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 2:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                        this.a.b.onADLoaded((List) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 3:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onRenderFail((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 4:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onRenderSuccess((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 5:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onADExposure((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 6:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onADClicked((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 7:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onADClosed((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 8:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onADLeftApplication((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 9:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onADOpenOverlay((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                case 10:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeExpressADView)) {
                        this.a.b.onADCloseOverlay((NativeExpressADView) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Params error for NativeExpressAD(" + aDEvent + ")");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public interface NativeExpressADListener {
        void onADClicked(NativeExpressADView nativeExpressADView);

        void onADCloseOverlay(NativeExpressADView nativeExpressADView);

        void onADClosed(NativeExpressADView nativeExpressADView);

        void onADExposure(NativeExpressADView nativeExpressADView);

        void onADLeftApplication(NativeExpressADView nativeExpressADView);

        void onADLoaded(List<NativeExpressADView> list);

        void onADOpenOverlay(NativeExpressADView nativeExpressADView);

        void onNoAD(AdError adError);

        void onRenderFail(NativeExpressADView nativeExpressADView);

        void onRenderSuccess(NativeExpressADView nativeExpressADView);
    }

    public NativeExpressAD(Context context, ADSize aDSize, String str, String str2, NativeExpressADListener nativeExpressADListener) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || aDSize == null || context == null) {
            GDTLogger.e(String.format("NativeExpressAD Constructor params error, adSize=%s, appid=%s, posId=%s, context=%s", new Object[]{aDSize, str, str2, context}));
            return;
        }
        this.c = true;
        if (a.a(context)) {
            this.d = true;
            this.b = nativeExpressADListener;
            final Context context2 = context;
            final String str3 = str;
            final ADSize aDSize2 = aDSize;
            final String str4 = str2;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ NativeExpressAD e;

                public void run() {
                    if (GDTADManager.getInstance().initWith(context2, str3)) {
                        try {
                            final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                private /* synthetic */ AnonymousClass1 b;

                                /* JADX WARNING: inconsistent code. */
                                /* Code decompiled incorrectly, please refer to instructions dump. */
                                public void run() {
                                    /*
                                    r10 = this;
                                    r9 = 1;
                                    r0 = r0;	 Catch:{ Throwable -> 0x00a3 }
                                    if (r0 == 0) goto L_0x00b1;
                                L_0x0005:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r6 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r2;	 Catch:{ Throwable -> 0x00a3 }
                                    r2 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r2 = r4;	 Catch:{ Throwable -> 0x00a3 }
                                    r3 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r3 = r3;	 Catch:{ Throwable -> 0x00a3 }
                                    r4 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r4 = r5;	 Catch:{ Throwable -> 0x00a3 }
                                    r5 = new com.qq.e.ads.nativ.NativeExpressAD$ADListenerAdapter;	 Catch:{ Throwable -> 0x00a3 }
                                    r7 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r7 = r7.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r8 = 0;
                                    r5.<init>();	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.getNativeExpressADDelegate(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a3 }
                                    r6.a = r0;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = 1;
                                    r0.f = true;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.g;	 Catch:{ Throwable -> 0x00a3 }
                                    if (r0 == 0) goto L_0x004d;
                                L_0x003e:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r1.g;	 Catch:{ Throwable -> 0x00a3 }
                                    r0.setBrowserType(r1);	 Catch:{ Throwable -> 0x00a3 }
                                L_0x004d:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.h;	 Catch:{ Throwable -> 0x00a3 }
                                    if (r0 == 0) goto L_0x0066;
                                L_0x0057:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r1.h;	 Catch:{ Throwable -> 0x00a3 }
                                    r0.setDownAPPConfirmPolicy(r1);	 Catch:{ Throwable -> 0x00a3 }
                                L_0x0066:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.i;	 Catch:{ Throwable -> 0x00a3 }
                                    if (r0 == 0) goto L_0x007f;
                                L_0x0070:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r1.i;	 Catch:{ Throwable -> 0x00a3 }
                                    r0.setVideoOption(r1);	 Catch:{ Throwable -> 0x00a3 }
                                L_0x007f:
                                    r0 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r1 = r0.iterator();	 Catch:{ Throwable -> 0x00a3 }
                                L_0x008b:
                                    r0 = r1.hasNext();	 Catch:{ Throwable -> 0x00a3 }
                                    if (r0 == 0) goto L_0x00b1;
                                L_0x0091:
                                    r0 = r1.next();	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x00a3 }
                                    r2 = r10.b;	 Catch:{ Throwable -> 0x00a3 }
                                    r2 = r2.e;	 Catch:{ Throwable -> 0x00a3 }
                                    r0 = r0.intValue();	 Catch:{ Throwable -> 0x00a3 }
                                    r2.loadAD(r0);	 Catch:{ Throwable -> 0x00a3 }
                                    goto L_0x008b;
                                L_0x00a3:
                                    r0 = move-exception;
                                    r1 = "Exception while init Native Express AD Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x00b9 }
                                    r0 = r10.b;
                                    r0 = r0.e;
                                    r0.f = true;
                                L_0x00b0:
                                    return;
                                L_0x00b1:
                                    r0 = r10.b;
                                    r0 = r0.e;
                                    r0.f = true;
                                    goto L_0x00b0;
                                L_0x00b9:
                                    r0 = move-exception;
                                    r1 = r10.b;
                                    r1 = r1.e;
                                    r1.f = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.nativ.NativeExpressAD.1.1.run():void");
                                }
                            });
                            return;
                        } catch (Throwable th) {
                            GDTLogger.e("Exception while init Native Express AD plugin", th);
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
            GDTLogger.e("AD init Params OR Context error, details in logs produced while init NativeExpressAD");
        } else if (!this.f) {
            this.e.add(Integer.valueOf(i));
        } else if (this.a != null) {
            this.a.loadAd(i);
        } else {
            GDTLogger.e("Native Express AD Init error, see more logs");
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

    public void setVideoOption(VideoOption videoOption) {
        this.i = videoOption;
        if (this.a != null && videoOption != null) {
            this.a.setVideoOption(videoOption);
        }
    }
}
