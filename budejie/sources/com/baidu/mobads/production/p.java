package com.baidu.mobads.production;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mobads.command.c.a;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerEventListener;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

public class p implements IXAdContainerEventListener {
    private Context a;
    private final a b;
    private AtomicBoolean c;
    private AtomicBoolean d;
    private AtomicBoolean e;
    private AtomicBoolean f;
    private int g = 0;
    private int h = 2;
    private int i = 15;
    private int j = 0;
    private int k = 2;
    private int l = 15;

    public p(Context context, a aVar) {
        this.a = context;
        this.b = aVar;
        this.c = new AtomicBoolean(false);
        this.d = new AtomicBoolean(false);
        this.e = new AtomicBoolean(false);
        this.f = new AtomicBoolean(false);
    }

    private void a(Context context, String str, String str2) {
        try {
            this.g = 0;
            Timer timer = new Timer();
            timer.schedule(new q(this, XAdSDKFoundationFacade.getInstance().getPackageUtils(), context, str2, timer, str), 0, 1000);
        } catch (Exception e) {
        }
    }

    private void a(Context context, String str) {
        try {
            this.j = 0;
            Timer timer = new Timer();
            timer.schedule(new r(this, XAdSDKFoundationFacade.getInstance().getPackageUtils(), context, str, timer), 0, 1000);
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAdClicked(com.baidu.mobads.interfaces.IXAdContainer r25, com.baidu.mobads.interfaces.IXAdInstanceInfo r26, java.lang.Boolean r27, java.util.HashMap<java.lang.String, java.lang.Object> r28) {
        /*
        r24 = this;
        r6 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r19 = r6.getCommonUtils();
        r6 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r6 = r6.getSystemUtils();
        r14 = r6;
        r14 = (com.baidu.mobads.utils.o) r14;
        r6 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r15 = r6.getAdConstants();
        r6 = com.baidu.mobads.utils.XAdSDKFoundationFacade.getInstance();
        r6 = r6.getPackageUtils();
        r0 = r24;
        r0 = r0.b;
        r20 = r0;
        r7 = r25.getAdContainerContext();
        r21 = r7.getAdResource();
        r7 = 0;
        r9 = java.lang.Boolean.valueOf(r7);
        r10 = r26.getClickThroughUrl();
        r11 = r26.getActionType();
        r12 = new java.util.ArrayList;
        r12.<init>();
        r13 = r26.getThirdClickTrackingUrls();
        r7 = 0;
        r8 = r7;
    L_0x0049:
        r7 = r13.size();
        if (r8 >= r7) goto L_0x0073;
    L_0x004f:
        r7 = r13.get(r8);
        r7 = (java.lang.String) r7;
        r16 = "\\$\\{PROGRESS\\}";
        r22 = r25.getPlayheadTime();
        r0 = r22;
        r0 = (int) r0;
        r17 = r0;
        r17 = java.lang.String.valueOf(r17);
        r0 = r16;
        r1 = r17;
        r7 = r7.replaceAll(r0, r1);
        r12.add(r7);
        r7 = r8 + 1;
        r8 = r7;
        goto L_0x0049;
    L_0x0073:
        r7 = new java.util.HashSet;
        r7.<init>();
        r7.addAll(r12);
        r0 = r24;
        r0.a(r7);
        r7 = r15.getActTypeOpenExternalApp();
        if (r11 != r7) goto L_0x01a9;
    L_0x0086:
        r7 = 1;
        r14 = java.lang.Boolean.valueOf(r7);
        r7 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00ff }
        r7.<init>(r10);	 Catch:{ Exception -> 0x00ff }
        r13 = r7;
    L_0x0091:
        r7 = "page";
        r8 = "";
        r8 = r13.optString(r7, r8);
        r7 = r20.getApplicationContext();
        r9 = r26.getAppPackageName();
        r10 = 366; // 0x16e float:5.13E-43 double:1.81E-321;
        r11 = "fb_act";
        r12 = 0;
        r11 = r13.optInt(r11, r12);
        r12 = "version";
        r16 = 0;
        r0 = r16;
        r12 = r13.optInt(r12, r0);
        r6 = r6.sendAPOInfo(r7, r8, r9, r10, r11, r12);
        if (r6 == 0) goto L_0x0123;
    L_0x00ba:
        r6 = r27.booleanValue();
        if (r6 == 0) goto L_0x00ce;
    L_0x00c0:
        r6 = new com.baidu.mobads.command.b.a;
        r0 = r20;
        r1 = r26;
        r2 = r21;
        r6.<init>(r0, r1, r2, r8);
        r6.a();
    L_0x00ce:
        r6 = r20.getApplicationContext();
        r7 = r26.getAppPackageName();
        r0 = r24;
        r0.a(r6, r8, r7);
        r7 = r14;
    L_0x00dc:
        r6 = r7.booleanValue();
        if (r6 == 0) goto L_0x00f0;
    L_0x00e2:
        r0 = r24;
        r6 = r0.b;
        r7 = new com.baidu.mobads.f.a;
        r8 = "AdClickThru";
        r7.<init>(r8);
        r6.dispatchEvent(r7);
    L_0x00f0:
        r0 = r24;
        r6 = r0.b;
        r7 = new com.baidu.mobads.f.a;
        r8 = "AdUserClick";
        r7.<init>(r8);
        r6.dispatchEvent(r7);
    L_0x00fe:
        return;
    L_0x00ff:
        r7 = move-exception;
        r7 = new org.json.JSONObject;	 Catch:{ Exception -> 0x032a }
        r8 = r26.getAppOpenStrs();	 Catch:{ Exception -> 0x032a }
        r7.<init>(r8);	 Catch:{ Exception -> 0x032a }
        r8 = new com.baidu.mobads.openad.d.a;	 Catch:{ Exception -> 0x032a }
        r8.<init>();	 Catch:{ Exception -> 0x032a }
        r9 = new com.baidu.mobads.openad.d.c;	 Catch:{ Exception -> 0x032a }
        r11 = "";
        r9.<init>(r10, r11);	 Catch:{ Exception -> 0x032a }
        r10 = 1;
        r9.e = r10;	 Catch:{ Exception -> 0x032a }
        r10 = 1;
        r10 = java.lang.Boolean.valueOf(r10);	 Catch:{ Exception -> 0x032a }
        r8.a(r9, r10);	 Catch:{ Exception -> 0x032a }
        r13 = r7;
        goto L_0x0091;
    L_0x0123:
        r6 = "fb_act";
        r7 = 0;
        r6 = r13.optInt(r6, r7);
        r7 = new org.json.JSONObject;
        r7.<init>();
        r9 = new com.baidu.mobads.vo.XAdInstanceInfo;
        r9.<init>(r7);
        r7 = r15.getActTypeLandingPage();
        if (r6 != r7) goto L_0x0164;
    L_0x013a:
        r6 = r15.getActTypeLandingPage();
        r9.setActionType(r6);
        r6 = "fallback";
        r7 = "";
        r6 = r13.optString(r6, r7);
        r9.setClickThroughUrl(r6);
        r6 = r26.getTitle();
        r9.setTitle(r6);
        r6 = 1;
        r9.setInapp(r6);
        r0 = r24;
        r1 = r25;
        r2 = r27;
        r3 = r28;
        r0.onAdClicked(r1, r9, r2, r3);
        goto L_0x00ce;
    L_0x0164:
        r7 = r15.getActTypeDownload();
        if (r6 != r7) goto L_0x00ce;
    L_0x016a:
        r6 = r15.getActTypeDownload();
        r9.setActionType(r6);
        r6 = "fallback";
        r7 = "";
        r6 = r13.optString(r6, r7);
        r9.setClickThroughUrl(r6);
        r6 = r26.getTitle();
        r9.setTitle(r6);
        r6 = r26.getQueryKey();
        r9.setQueryKey(r6);
        r6 = 1;
        r9.setInapp(r6);
        r6 = 1;
        r9.setAPOOpen(r6);
        r9.setPage(r8);
        r6 = r26.getAppPackageName();
        r9.setAppPackageName(r6);
        r0 = r24;
        r1 = r25;
        r2 = r27;
        r3 = r28;
        r0.onAdClicked(r1, r9, r2, r3);
        goto L_0x00ce;
    L_0x01a9:
        r6 = r15.getActTypeDownload();
        if (r11 != r6) goto L_0x0211;
    L_0x01af:
        r0 = r24;
        r1 = r28;
        r2 = r20;
        r3 = r21;
        r4 = r26;
        r6 = r0.a(r1, r2, r3, r4);
        if (r6 != 0) goto L_0x0327;
    L_0x01bf:
        r6 = 0;
        r18 = java.lang.Boolean.valueOf(r6);
        r6 = r27.booleanValue();
        if (r6 == 0) goto L_0x0332;
    L_0x01ca:
        r7 = r20.getApplicationContext();
        r8 = 525; // 0x20d float:7.36E-43 double:2.594E-321;
        r9 = "click";
        r6 = r20.getProdInfo();
        r10 = r6.getProdType();
        r11 = r26.getAppPackageName();
        r6 = r20.getApplicationContext();
        r0 = r19;
        r12 = r0.getAppId(r6);
        r6 = r20.getProdInfo();
        r13 = r6.getAdPlacementId();
        r14 = r14.getPhoneOSBrand();
        r15 = android.os.Build.MODEL;
        r16 = android.os.Build.VERSION.RELEASE;
        r17 = android.os.Build.VERSION.SDK_INT;
        r6 = r19;
        r6.sendDownloadAdLog(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
        r6 = new com.baidu.mobads.command.a.a;
        r0 = r20;
        r1 = r26;
        r2 = r21;
        r6.<init>(r0, r1, r2);
        r6.a();
        r7 = r18;
        goto L_0x00dc;
    L_0x0211:
        r6 = r15.getActTypeLandingPage();
        if (r11 == r6) goto L_0x021d;
    L_0x0217:
        r6 = r15.getActTypeOpenMap();
        if (r11 != r6) goto L_0x0288;
    L_0x021d:
        r0 = r24;
        r6 = r0.b;
        r6 = r6.getProdInfo();
        r6 = r6.getProdType();
        r7 = r15.getProductionTypeSplash();
        if (r6 == r7) goto L_0x032f;
    L_0x022f:
        r6 = 1;
        r6 = java.lang.Boolean.valueOf(r6);
        r7 = r6;
    L_0x0235:
        r6 = r27.booleanValue();
        if (r6 == 0) goto L_0x00dc;
    L_0x023b:
        r6 = r26.isInapp();
        if (r6 == 0) goto L_0x0279;
    L_0x0241:
        r0 = r24;
        r1 = r28;
        r2 = r20;
        r3 = r21;
        r4 = r26;
        r6 = r0.a(r1, r2, r3, r4);
        if (r6 != 0) goto L_0x00dc;
    L_0x0251:
        r8 = new com.baidu.mobads.command.c.a;
        r0 = r20;
        r1 = r26;
        r2 = r21;
        r8.<init>(r0, r1, r2, r10);
        if (r28 == 0) goto L_0x0274;
    L_0x025e:
        r6 = "lpShoubaiStyle";
        r0 = r28;
        r6 = r0.containsKey(r6);
        if (r6 == 0) goto L_0x0274;
    L_0x0268:
        r6 = "lpShoubaiStyle";
        r0 = r28;
        r6 = r0.get(r6);
        r6 = (java.lang.String) r6;
        r8.f = r6;
    L_0x0274:
        r8.a();
        goto L_0x00dc;
    L_0x0279:
        r6 = r25.getAdContainerContext();
        r6 = r6.getApplicationContext();
        r0 = r19;
        r0.browserOutside(r6, r10);
        goto L_0x00dc;
    L_0x0288:
        r6 = r15.getActTypeMakeCall();
        if (r11 == r6) goto L_0x029a;
    L_0x028e:
        r6 = r15.getActTypeSendSMS();
        if (r11 == r6) goto L_0x029a;
    L_0x0294:
        r6 = r15.getActTypeSendMail();
        if (r11 != r6) goto L_0x0318;
    L_0x029a:
        r6 = 1;
        r9 = java.lang.Boolean.valueOf(r6);
        r6 = r27.booleanValue();
        if (r6 == 0) goto L_0x02b3;
    L_0x02a5:
        r6 = new com.baidu.mobads.command.b.a;
        r0 = r20;
        r1 = r26;
        r2 = r21;
        r6.<init>(r0, r1, r2, r10);
        r6.a();
    L_0x02b3:
        r6 = r15.getActTypeMakeCall();
        if (r11 != r6) goto L_0x0327;
    L_0x02b9:
        r6 = r20.getApplicationContext();
        r6 = r6.getPackageManager();
        r7 = new android.content.Intent;
        r8 = "android.intent.action.VIEW";
        r7.<init>(r8);
        r8 = android.net.Uri.parse(r10);
        r7.setData(r8);
        r8 = 64;
        r11 = r6.queryIntentActivities(r7, r8);
        if (r11 == 0) goto L_0x0307;
    L_0x02d7:
        r6 = r11.size();
        if (r6 <= 0) goto L_0x0307;
    L_0x02dd:
        r7 = 0;
        r10 = 1;
        r6 = 0;
        r8 = r7;
        r7 = r6;
    L_0x02e2:
        r6 = r11.size();
        if (r7 >= r6) goto L_0x032d;
    L_0x02e8:
        r6 = 1;
        if (r7 < r6) goto L_0x030a;
    L_0x02eb:
        r6 = r11.get(r7);
        r6 = (android.content.pm.ResolveInfo) r6;
        r6 = r6.activityInfo;
        r6 = r6.processName;
        r6 = r8.equals(r6);
        if (r6 != 0) goto L_0x030a;
    L_0x02fb:
        r6 = 0;
    L_0x02fc:
        if (r6 == 0) goto L_0x0307;
    L_0x02fe:
        r6 = r20.getApplicationContext();
        r0 = r24;
        r0.a(r6, r8);
    L_0x0307:
        r7 = r9;
        goto L_0x00dc;
    L_0x030a:
        r6 = r11.get(r7);
        r6 = (android.content.pm.ResolveInfo) r6;
        r6 = r6.activityInfo;
        r8 = r6.processName;
        r6 = r7 + 1;
        r7 = r6;
        goto L_0x02e2;
    L_0x0318:
        r6 = r15.getActTypeNothing2Do();
        if (r11 != r6) goto L_0x0321;
    L_0x031e:
        r7 = r9;
        goto L_0x00dc;
    L_0x0321:
        r6 = r15.getActTypeRichMedia();
        if (r11 != r6) goto L_0x0327;
    L_0x0327:
        r7 = r9;
        goto L_0x00dc;
    L_0x032a:
        r6 = move-exception;
        goto L_0x00fe;
    L_0x032d:
        r6 = r10;
        goto L_0x02fc;
    L_0x032f:
        r7 = r9;
        goto L_0x0235;
    L_0x0332:
        r7 = r18;
        goto L_0x00dc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.production.p.onAdClicked(com.baidu.mobads.interfaces.IXAdContainer, com.baidu.mobads.interfaces.IXAdInstanceInfo, java.lang.Boolean, java.util.HashMap):void");
    }

    public boolean a(HashMap<String, Object> hashMap, IXNonLinearAdSlot iXNonLinearAdSlot, IXAdResource iXAdResource, IXAdInstanceInfo iXAdInstanceInfo) {
        if (hashMap == null || !hashMap.containsKey("lpShoubaiStyle") || !hashMap.get("lpShoubaiStyle").equals("video_and_web")) {
            return false;
        }
        a aVar = new a(iXNonLinearAdSlot, iXAdInstanceInfo, iXAdResource, iXAdInstanceInfo.getWebUrl());
        aVar.f = (String) hashMap.get("lpShoubaiStyle");
        aVar.a();
        return true;
    }

    public void onAdLoaded(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b.a(iXAdContainer, (HashMap) hashMap);
        } else {
            new Handler(this.a.getMainLooper()).post(new s(this, iXAdContainer, hashMap));
        }
    }

    public void onAdStarted(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.b.b(iXAdContainer, (HashMap) hashMap);
        } else {
            new Handler(this.a.getMainLooper()).post(new t(this, iXAdContainer, hashMap));
        }
    }

    public void onAdImpression(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        a(iXAdInstanceInfo.getImpressionUrls());
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_IMPRESSION));
    }

    public void onAdStoped(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, Boolean bool2, HashMap<String, Object> hashMap) {
        if (iXAdInstanceInfo != null) {
            Set hashSet = new HashSet();
            hashSet.addAll(iXAdInstanceInfo.getCloseTrackers());
            a(hashSet);
        }
        if (bool2.booleanValue()) {
            IXAdContainerContext adContainerContext = iXAdContainer.getAdContainerContext();
            this.b.a(adContainerContext.getAdResponseInfo(), adContainerContext.getAdInstanceInfo());
            return;
        }
        this.b.e(iXAdContainer, hashMap);
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STOPPED));
    }

    public void onAdError(IXAdContainer iXAdContainer, IXAdInstanceInfo iXAdInstanceInfo, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
            if (hashMap != null) {
                IXAdConstants adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
                com.baidu.mobads.c.a.a().a(hashMap.get(adConstants.getInfoKeyErrorCode()) + "," + hashMap.get(adConstants.getInfoKeyErrorMessage()) + "," + hashMap.get(adConstants.getInfoKeyErrorModule()));
            }
            this.e.set(true);
            this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        }
    }

    public void onAdPlaying(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_PLAYING));
    }

    public void onAdPaused(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_PAUSED));
    }

    public void onAdLinearChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdExpandedChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdUserClosed(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        this.b.dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }

    public void onAdDurationChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdRemainingTimeChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVolumeChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdSizeChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdSkippableStateChange(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdSkipped(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdInteraction(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdUserAcceptInvitation(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdUserMinimize(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoStart(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoFirstQuartile(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoMidpoint(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoThirdQuartile(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdVideoComplete(IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    public void onAdCustomEvent(String str, IXAdContainer iXAdContainer, Boolean bool, HashMap<String, Object> hashMap) {
        if (!this.e.get()) {
        }
    }

    private void a(Set<String> set) {
        com.baidu.mobads.openad.d.a aVar = new com.baidu.mobads.openad.d.a();
        for (String cVar : set) {
            c cVar2 = new c(cVar, "");
            cVar2.e = 1;
            aVar.a(cVar2, Boolean.valueOf(true));
        }
    }
}
