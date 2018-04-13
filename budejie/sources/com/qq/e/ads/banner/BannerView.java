package com.qq.e.ads.banner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.qq.e.ads.cfg.BannerRollAnimation;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.BVI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;

@SuppressLint({"ViewConstructor"})
public class BannerView extends FrameLayout {
    private BVI a;
    private BannerADListener b;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private Integer f;
    private BannerRollAnimation g;
    private DownAPPConfirmPolicy h;
    private Boolean i;
    private volatile int j = 0;

    class ADListenerAdapter implements ADListener {
        private /* synthetic */ BannerView a;

        private ADListenerAdapter(BannerView bannerView) {
            this.a = bannerView;
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
                        GDTLogger.e("AdEvent.Paras error for Banner(" + aDEvent + ")");
                        return;
                    }
                case 2:
                    this.a.b.onADReceiv();
                    return;
                case 3:
                    this.a.b.onADExposure();
                    return;
                case 4:
                    this.a.b.onADClosed();
                    return;
                case 5:
                    this.a.b.onADClicked();
                    return;
                case 6:
                    this.a.b.onADLeftApplication();
                    return;
                case 7:
                    this.a.b.onADOpenOverlay();
                    return;
                case 8:
                    this.a.b.onADCloseOverlay();
                    return;
                default:
                    return;
            }
        }
    }

    public BannerView(Activity activity, ADSize aDSize, String str, String str2) {
        super(activity);
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || activity == null) {
            GDTLogger.e(String.format("Banner ADView Constructor params error, appid=%s,posId=%s,context=%s", new Object[]{str, str2, activity}));
            return;
        }
        this.c = true;
        if (a.a((Context) activity)) {
            this.d = true;
            setLayoutParams(new LayoutParams(-1, -2));
            final Activity activity2 = activity;
            final String str3 = str;
            final ADSize aDSize2 = aDSize;
            final String str4 = str2;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ BannerView e;

                public void run() {
                    if (GDTADManager.getInstance().initWith(activity2, str3)) {
                        try {
                            final POFactory pOFactory = GDTADManager.getInstance().getPM().getPOFactory();
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                private /* synthetic */ AnonymousClass1 b;

                                /* JADX WARNING: inconsistent code. */
                                /* Code decompiled incorrectly, please refer to instructions dump. */
                                public void run() {
                                    /*
                                    r7 = this;
                                    r6 = 1;
                                    r0 = r0;	 Catch:{ Throwable -> 0x00d0 }
                                    if (r0 == 0) goto L_0x00de;
                                L_0x0005:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r0;	 Catch:{ Throwable -> 0x00d0 }
                                    r2 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r2 = r2;	 Catch:{ Throwable -> 0x00d0 }
                                    r3 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r3 = r4;	 Catch:{ Throwable -> 0x00d0 }
                                    r4 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r4 = r3;	 Catch:{ Throwable -> 0x00d0 }
                                    r5 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r5 = r5;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.getBannerView(r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00d0 }
                                    r0.a = r1;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.a;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = new com.qq.e.ads.banner.BannerView$ADListenerAdapter;	 Catch:{ Throwable -> 0x00d0 }
                                    r2 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r2 = r2.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r3 = 0;
                                    r1.<init>();	 Catch:{ Throwable -> 0x00d0 }
                                    r0.setAdListener(r1);	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.a;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.getView();	 Catch:{ Throwable -> 0x00d0 }
                                    r0.addView(r1);	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = 1;
                                    r0.e = true;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.h;	 Catch:{ Throwable -> 0x00d0 }
                                    if (r0 == 0) goto L_0x006b;
                                L_0x005c:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.h;	 Catch:{ Throwable -> 0x00d0 }
                                    r0.setDownConfirmPilicy(r1);	 Catch:{ Throwable -> 0x00d0 }
                                L_0x006b:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.f;	 Catch:{ Throwable -> 0x00d0 }
                                    if (r0 == 0) goto L_0x0088;
                                L_0x0075:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.f;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.intValue();	 Catch:{ Throwable -> 0x00d0 }
                                    r0.setRefresh(r1);	 Catch:{ Throwable -> 0x00d0 }
                                L_0x0088:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.g;	 Catch:{ Throwable -> 0x00d0 }
                                    if (r0 == 0) goto L_0x00a1;
                                L_0x0092:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.g;	 Catch:{ Throwable -> 0x00d0 }
                                    r0.setRollAnimation(r1);	 Catch:{ Throwable -> 0x00d0 }
                                L_0x00a1:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.i;	 Catch:{ Throwable -> 0x00d0 }
                                    if (r0 == 0) goto L_0x00be;
                                L_0x00ab:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.i;	 Catch:{ Throwable -> 0x00d0 }
                                    r1 = r1.booleanValue();	 Catch:{ Throwable -> 0x00d0 }
                                    r0.setShowClose(r1);	 Catch:{ Throwable -> 0x00d0 }
                                L_0x00be:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.j = r0.j - 1;	 Catch:{ Throwable -> 0x00d0 }
                                    if (r0 <= 0) goto L_0x00de;
                                L_0x00c8:
                                    r0 = r7.b;	 Catch:{ Throwable -> 0x00d0 }
                                    r0 = r0.e;	 Catch:{ Throwable -> 0x00d0 }
                                    r0.loadAD();	 Catch:{ Throwable -> 0x00d0 }
                                    goto L_0x00be;
                                L_0x00d0:
                                    r0 = move-exception;
                                    r1 = "Exception while init Banner Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x00e6 }
                                    r0 = r7.b;
                                    r0 = r0.e;
                                    r0.e = true;
                                L_0x00dd:
                                    return;
                                L_0x00de:
                                    r0 = r7.b;
                                    r0 = r0.e;
                                    r0.e = true;
                                    goto L_0x00dd;
                                L_0x00e6:
                                    r0 = move-exception;
                                    r1 = r7.b;
                                    r1 = r1.e;
                                    r1.e = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.banner.BannerView.1.1.run():void");
                                }
                            });
                            return;
                        } catch (Throwable th) {
                            GDTLogger.e("Exception while init Banner plugin", th);
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

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public void loadAD() {
        if (!this.c || !this.d) {
            GDTLogger.e("Banner init Paras OR Context error,See More logs while new BannerView");
        } else if (!this.e) {
            this.j++;
        } else if (this.a != null) {
            this.a.fetchAd();
        } else {
            GDTLogger.e("Banner Init error,See More Logs");
        }
    }

    public void setADListener(BannerADListener bannerADListener) {
        this.b = bannerADListener;
    }

    public void setDownConfirmPilicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.h = downAPPConfirmPolicy;
        if (downAPPConfirmPolicy != null && this.a != null) {
            this.a.setDownAPPConfirmPolicy(downAPPConfirmPolicy.value());
        }
    }

    public void setRefresh(int i) {
        this.f = Integer.valueOf(i);
        if (i < 30 && i != 0) {
            i = 30;
        } else if (i > 120) {
            i = 120;
        }
        if (this.a != null) {
            this.a.setRefresh(i);
        }
    }

    public void setRollAnimation(BannerRollAnimation bannerRollAnimation) {
        this.g = bannerRollAnimation;
        if (bannerRollAnimation != null && this.a != null) {
            this.a.setRollAnimation(bannerRollAnimation.value());
        }
    }

    public void setShowClose(boolean z) {
        this.i = Boolean.valueOf(z);
        if (this.a != null) {
            this.a.setShowCloseButton(z);
        }
    }
}
