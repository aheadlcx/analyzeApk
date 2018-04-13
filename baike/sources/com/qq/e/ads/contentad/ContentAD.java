package com.qq.e.ads.contentad;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.qq.e.comm.a;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.CAI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentAD {
    private BrowserType a;
    private DownAPPConfirmPolicy b;
    private CAI c;
    private boolean d;
    private ContentADListener e;
    private boolean f;
    private List<Map<String, Object>> g = new ArrayList();
    private boolean h = false;

    class ADListenerAdapter implements ADListener {
        private /* synthetic */ ContentAD a;

        private ADListenerAdapter(ContentAD contentAD) {
            this.a = contentAD;
        }

        public void onADEvent(ADEvent aDEvent) {
            if (this.a.e == null) {
                GDTLogger.i("No DevADListener Binded");
                return;
            }
            switch (aDEvent.getType()) {
                case 1:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        this.a.e.onNoContentAD(((Integer) aDEvent.getParas()[0]).intValue());
                        return;
                    } else {
                        GDTLogger.e("AdEvent.Paras error for ContentAD(" + aDEvent + ")");
                        return;
                    }
                case 2:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof List)) {
                        this.a.e.onContentADLoaded((List) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for ContentAD(" + aDEvent + ")");
                        return;
                    }
                case 3:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        this.a.e.onContentADStatusChanged((NativeMediaADData) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for ContentAD(" + aDEvent + ")");
                        return;
                    }
                case 4:
                    if (aDEvent.getParas().length == 2 && (aDEvent.getParas()[0] instanceof ContentAdData) && (aDEvent.getParas()[1] instanceof Integer)) {
                        this.a.e.onContentADError((ContentAdData) aDEvent.getParas()[0], ((Integer) aDEvent.getParas()[1]).intValue());
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for ContentAD(" + aDEvent + ")");
                        return;
                    }
                case 5:
                    if (aDEvent.getParas().length == 1 && (aDEvent.getParas()[0] instanceof NativeMediaADData)) {
                        this.a.e.onADVideoLoaded((NativeMediaADData) aDEvent.getParas()[0]);
                        return;
                    } else {
                        GDTLogger.e("ADEvent.Paras error for ContentAD(" + aDEvent + ")");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public interface ContentADListener {
        void onADVideoLoaded(ContentAdData contentAdData);

        void onContentADError(ContentAdData contentAdData, int i);

        void onContentADLoaded(List<ContentAdData> list);

        void onContentADStatusChanged(ContentAdData contentAdData);

        void onNoContentAD(int i);
    }

    public ContentAD(final Context context, final String str, final String str2, ContentADListener contentADListener) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || context == null) {
            GDTLogger.e(String.format("GDTContentAd Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, context}));
            return;
        }
        this.d = true;
        if (a.a(context)) {
            this.f = true;
            this.e = contentADListener;
            GDTADManager.INIT_EXECUTOR.execute(new Runnable(this) {
                final /* synthetic */ ContentAD d;

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
                                    r0 = r0;	 Catch:{ Throwable -> 0x00a6 }
                                    if (r0 == 0) goto L_0x00b4;
                                L_0x0005:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r0;	 Catch:{ Throwable -> 0x00a6 }
                                    r2 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r2 = r5;	 Catch:{ Throwable -> 0x00a6 }
                                    r3 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r3 = r6;	 Catch:{ Throwable -> 0x00a6 }
                                    r4 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r4 = r7;	 Catch:{ Throwable -> 0x00a6 }
                                    r5 = new com.qq.e.ads.contentad.ContentAD$ADListenerAdapter;	 Catch:{ Throwable -> 0x00a6 }
                                    r6 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r6 = r6.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r7 = 0;
                                    r5.<init>();	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r1.getContentAdDelegate(r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00a6 }
                                    r0.c = r1;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = 1;
                                    r0.h = true;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.a;	 Catch:{ Throwable -> 0x00a6 }
                                    if (r0 == 0) goto L_0x0049;
                                L_0x003a:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r1.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r1.a;	 Catch:{ Throwable -> 0x00a6 }
                                    r0.setBrowserType(r1);	 Catch:{ Throwable -> 0x00a6 }
                                L_0x0049:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.b;	 Catch:{ Throwable -> 0x00a6 }
                                    if (r0 == 0) goto L_0x0062;
                                L_0x0053:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r1.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r1.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0.setDownAPPConfirmPolicy(r1);	 Catch:{ Throwable -> 0x00a6 }
                                L_0x0062:
                                    r0 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.g;	 Catch:{ Throwable -> 0x00a6 }
                                    r2 = r0.iterator();	 Catch:{ Throwable -> 0x00a6 }
                                L_0x006e:
                                    r0 = r2.hasNext();	 Catch:{ Throwable -> 0x00a6 }
                                    if (r0 == 0) goto L_0x00b4;
                                L_0x0074:
                                    r0 = r2.next();	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = (java.util.Map) r0;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = "page_number";
                                    r1 = r0.get(r1);	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = (java.lang.Integer) r1;	 Catch:{ Throwable -> 0x00a6 }
                                    r3 = r1.intValue();	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = "channel";
                                    r1 = r0.get(r1);	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = (java.lang.Integer) r1;	 Catch:{ Throwable -> 0x00a6 }
                                    r1 = r1.intValue();	 Catch:{ Throwable -> 0x00a6 }
                                    r4 = "is_manual_operation";
                                    r0 = r0.get(r4);	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = (java.lang.Boolean) r0;	 Catch:{ Throwable -> 0x00a6 }
                                    r0 = r0.booleanValue();	 Catch:{ Throwable -> 0x00a6 }
                                    r4 = r9.b;	 Catch:{ Throwable -> 0x00a6 }
                                    r4 = r4.d;	 Catch:{ Throwable -> 0x00a6 }
                                    r4.loadAD(r3, r1, r0);	 Catch:{ Throwable -> 0x00a6 }
                                    goto L_0x006e;
                                L_0x00a6:
                                    r0 = move-exception;
                                    r1 = "Exception while init Native Core";
                                    com.qq.e.comm.util.GDTLogger.e(r1, r0);	 Catch:{ all -> 0x00bc }
                                    r0 = r9.b;
                                    r0 = r0.d;
                                    r0.h = true;
                                L_0x00b3:
                                    return;
                                L_0x00b4:
                                    r0 = r9.b;
                                    r0 = r0.d;
                                    r0.h = true;
                                    goto L_0x00b3;
                                L_0x00bc:
                                    r0 = move-exception;
                                    r1 = r9.b;
                                    r1 = r1.d;
                                    r1.h = true;
                                    throw r0;
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.contentad.ContentAD.1.1.run():void");
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

    public void loadAD(int i, int i2, boolean z) {
        if (!this.d || !this.f) {
            GDTLogger.e("AD init Paras OR Context error,details in logs produced while init ContentAD");
        } else if (!this.h) {
            Map hashMap = new HashMap();
            hashMap.put("page_number", Integer.valueOf(i));
            hashMap.put("channel", Integer.valueOf(i2));
            hashMap.put("is_manual_operation", Boolean.valueOf(z));
            this.g.add(hashMap);
        } else if (this.c != null) {
            this.c.loadAd(i, i2, z);
        } else {
            GDTLogger.e("ContentAD Init error,See More Logs");
        }
    }

    public void setBrowserType(BrowserType browserType) {
        this.a = browserType;
        if (this.c != null && browserType != null) {
            this.c.setBrowserType(browserType.value());
        }
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.b = downAPPConfirmPolicy;
        if (this.c != null && downAPPConfirmPolicy != null) {
            this.c.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }
}
