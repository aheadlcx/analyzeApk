package com.alibaba.baichuan.android.trade.component;

public class b {
    private static final String a = b.class.getSimpleName();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r5, com.alibaba.baichuan.android.trade.model.ApplinkOpenType r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.util.Map r12) {
        /*
        if (r12 != 0) goto L_0x0007;
    L_0x0002:
        r12 = new java.util.HashMap;
        r12.<init>();
    L_0x0007:
        r1 = 0;
        r0 = r12.entrySet();	 Catch:{ JSONException -> 0x0109 }
        r3 = r0.iterator();	 Catch:{ JSONException -> 0x0109 }
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0109 }
        r2.<init>();	 Catch:{ JSONException -> 0x0109 }
    L_0x0015:
        r0 = r3.hasNext();	 Catch:{ JSONException -> 0x002f }
        if (r0 == 0) goto L_0x00b9;
    L_0x001b:
        r0 = r3.next();	 Catch:{ JSONException -> 0x002f }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ JSONException -> 0x002f }
        r1 = r0.getKey();	 Catch:{ JSONException -> 0x002f }
        r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x002f }
        r0 = r0.getValue();	 Catch:{ JSONException -> 0x002f }
        r2.put(r1, r0);	 Catch:{ JSONException -> 0x002f }
        goto L_0x0015;
    L_0x002f:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0032:
        r1.printStackTrace();
    L_0x0035:
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = "backURL";
        r1.put(r2, r11);
        r2 = "pid";
        r1.put(r2, r10);
        r2 = "tag";
        r1.put(r2, r9);
        r2 = "TTID";
        r3 = com.alibaba.baichuan.android.trade.config.AlibcConfig.getInstance();
        r3 = r3.getWebTTID();
        r1.put(r2, r3);
        r2 = "source";
        r3 = "bc";
        r1.put(r2, r3);
        r2 = "utdid";
        r3 = com.alibaba.baichuan.android.trade.AlibcContext.getUtdid();
        r1.put(r2, r3);
        r2 = com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink.getInstance();
        r2.setOpenParam(r1);
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = "backURL";
        r1.put(r2, r11);
        r2 = "addParams";
        r1.put(r2, r12);
        r2 = "jsonParams";
        r1.put(r2, r0);
        r2 = a;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "appType is ";
        r3 = r0.append(r3);
        r0 = "appType";
        r0 = r12.get(r0);
        r0 = (java.lang.String) r0;
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.alibaba.baichuan.android.trade.utils.log.AlibcLogger.e(r2, r0);
        r0 = "appType";
        r2 = "appType";
        r2 = r12.get(r2);
        r1.put(r0, r2);
        r0 = com.alibaba.baichuan.android.trade.component.b$1.a;
        r2 = r6.ordinal();
        r0 = r0[r2];
        switch(r0) {
            case 1: goto L_0x00ca;
            case 2: goto L_0x00da;
            case 3: goto L_0x00f1;
            default: goto L_0x00b7;
        };
    L_0x00b7:
        r0 = 0;
    L_0x00b8:
        return r0;
    L_0x00b9:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x002f }
        r0.<init>();	 Catch:{ JSONException -> 0x002f }
        r1 = "params";
        r3 = r2.toString();	 Catch:{ JSONException -> 0x002f }
        r0.put(r1, r3);	 Catch:{ JSONException -> 0x002f }
        r0 = r2;
        goto L_0x0035;
    L_0x00ca:
        if (r8 == 0) goto L_0x00da;
    L_0x00cc:
        r0 = "itemId";
        r1.put(r0, r8);
        r0 = com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink.getInstance();
        r0 = r0.jumpDetail(r5, r1);
        goto L_0x00b8;
    L_0x00da:
        if (r8 == 0) goto L_0x00ea;
    L_0x00dc:
        r0 = "shopId";
        r1.put(r0, r8);
        r0 = com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink.getInstance();
        r0 = r0.jumpShop(r5, r1);
        goto L_0x00b8;
    L_0x00ea:
        r0 = a;
        r2 = "startNativeTaobao: shopid is null";
        com.alibaba.baichuan.android.trade.utils.log.AlibcLogger.e(r0, r2);
    L_0x00f1:
        if (r7 == 0) goto L_0x0101;
    L_0x00f3:
        r0 = "url";
        r1.put(r0, r7);
        r0 = com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink.getInstance();
        r0 = r0.jumpTBURI(r5, r1);
        goto L_0x00b8;
    L_0x0101:
        r0 = a;
        r1 = "startNativeTaobao: shwourl is null";
        com.alibaba.baichuan.android.trade.utils.log.AlibcLogger.e(r0, r1);
        goto L_0x00b7;
    L_0x0109:
        r0 = move-exception;
        r4 = r0;
        r0 = r1;
        r1 = r4;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.baichuan.android.trade.component.b.a(android.content.Context, com.alibaba.baichuan.android.trade.model.ApplinkOpenType, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map):boolean");
    }
}
