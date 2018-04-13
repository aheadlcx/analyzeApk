package com.qq.e.ads.interstitial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.IADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;

@SuppressLint({"ViewConstructor"})
public class InterstitialAD {
    private IADI a;
    private InterstitialADListener b;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private volatile int f = 0;

    class ADListenerAdapter implements ADListener {
        private /* synthetic */ InterstitialAD a;

        private ADListenerAdapter(InterstitialAD interstitialAD) {
            this.a = interstitialAD;
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
                        GDTLogger.e("AdEvent.Paras error for InterstitialAD(" + aDEvent + ")");
                        return;
                    }
                case 2:
                    this.a.b.onADReceive();
                    return;
                case 3:
                    this.a.b.onADExposure();
                    return;
                case 4:
                    this.a.b.onADOpened();
                    return;
                case 5:
                    this.a.b.onADClicked();
                    return;
                case 6:
                    this.a.b.onADLeftApplication();
                    return;
                case 7:
                    this.a.b.onADClosed();
                    return;
                default:
                    return;
            }
        }
    }

    public InterstitialAD(final Activity activity, final String str, final String str2) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || activity == null) {
            GDTLogger.e(String.format("Interstitial Constructor paras error, appid=%s,posId=%s,context=%s", new Object[]{str, str2, activity}));
            return;
        }
        this.c = true;
        if (a.a((Context) activity)) {
            this.d = true;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ InterstitialAD d;

                public void run() {
                    if (GDTADManager.getInstance().initWith(activity, str)) {
                        try {
                            final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                private /* synthetic */ AnonymousClass1 b;

                                /* JADX WARNING: inconsistent code. */
                                /* Code decompiled incorrectly, please refer to instructions dump. */
                                public void run() {
                                    /*
                                    r6 = this;
                                    r5 = 1;
                                    r0 = r0;	 Catch:{ Throwable -> 0x004d }
                                    if (r0 == 0) goto L_0x005b;
                                L_0x0005:
                                    r0 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x004d }
                                    r1 = r0;	 Catch:{ Throwable -> 0x004d }
                                    r2 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r2 = r5;	 Catch:{ Throwable -> 0x004d }
                                    r3 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r3 = r6;	 Catch:{ Throwable -> 0x004d }
                                    r4 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r4 = r7;	 Catch:{ Throwable -> 0x004d }
                                    r1 = r1.getIADView(r2, r3, r4);	 Catch:{ Throwable -> 0x004d }
                                    r0.a = r1;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.a;	 Catch:{ Throwable -> 0x004d }
                                    r1 = new com.qq.e.ads.interstitial.InterstitialAD$ADListenerAdapter;	 Catch:{ Throwable -> 0x004d }
                                    r2 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r2 = r2.d;	 Catch:{ Throwable -> 0x004d }
                                    r3 = 0;
                                    r1.<init>();	 Catch:{ Throwable -> 0x004d }
                                    r0.setAdListener(r1);	 Catch:{ Throwable -> 0x004d }
                                    r0 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x004d }
                                    r1 = 1;
                                    r0.e = true;	 Catch:{ Throwable -> 0x004d }
                                L_0x003b:
                                    r0 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.f = r0.f - 1;	 Catch:{ Throwable -> 0x004d }
                                    if (r0 <= 0) goto L_0x005b;
                                L_0x0045:
                                    r0 = r6.b;	 Catch:{ Throwable -> 0x004d }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x004d }
                                    r0.loadAD();	 Catch:{ Throwable -> 0x004d }
                                    goto L_0x003b;
                                L_0x004d:
                                    r0 = move-exception;
                                    r1 = "Exception while init IAD Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x0063 }
                                    r0 = r6.b;
                                    r0 = r0.d;
                                    r0.e = true;
                                L_0x005a:
                                    return;
                                L_0x005b:
                                    r0 = r6.b;
                                    r0 = r0.d;
                                    r0.e = true;
                                    goto L_0x005a;
                                L_0x0063:
                                    r0 = move-exception;
                                    r1 = r6.b;
                                    r1 = r1.d;
                                    r1.e = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.interstitial.InterstitialAD.1.1.run():void");
                                }
                            });
                            return;
                        } catch (Throwable th) {
                            GDTLogger.e("Exception while init IAD plugin", th);
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

    public void closePopupWindow() {
        if (this.a != null) {
            this.a.closePopupWindow();
        }
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destory();
        }
    }

    public void loadAD() {
        if (!this.c || !this.d) {
            GDTLogger.e("InterstitialAD init Paras OR Context error,See More logs while new InterstitialAD");
        } else if (!this.e) {
            this.f++;
        } else if (this.a != null) {
            this.a.loadAd();
        } else {
            GDTLogger.e("InterstitialAD Init error,See More Logs");
        }
    }

    public void setADListener(InterstitialADListener interstitialADListener) {
        this.b = interstitialADListener;
    }

    public synchronized void show() {
        if (this.a != null) {
            this.a.show();
        }
    }

    public synchronized void show(Activity activity) {
        if (this.a != null) {
            this.a.show(activity);
        }
    }

    public synchronized void showAsPopupWindow() {
        if (this.a != null) {
            this.a.showAsPopupWindown();
        }
    }

    public synchronized void showAsPopupWindow(Activity activity) {
        if (this.a != null) {
            this.a.showAsPopupWindown(activity);
        }
    }
}
