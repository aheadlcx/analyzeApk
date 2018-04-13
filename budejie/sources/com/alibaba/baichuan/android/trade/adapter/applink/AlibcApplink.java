package com.alibaba.baichuan.android.trade.adapter.applink;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.cache.MemoryCacheUtils;
import com.alibaba.baichuan.android.trade.utils.json.JSONUtils;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.taobao.applink.TBAppLinkParam;
import com.taobao.applink.TBAppLinkSDK;
import com.taobao.applink.param.TBDetailParam;
import com.taobao.applink.param.TBShopParam;
import com.taobao.applink.param.TBURIParam;
import com.taobao.applink.util.TBAppLinkUtil;
import java.util.HashMap;
import java.util.Map;

public class AlibcApplink implements IAlibcApplink {
    private static final String a = AlibcApplink.class.getSimpleName();
    private static volatile AlibcApplink d = null;
    private TBAppLinkSDK b = TBAppLinkSDK.getInstance();
    private boolean c;

    private AlibcApplink() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.taobao.applink.param.TBBaseParam a(java.util.Map r5, java.lang.String r6) {
        /*
        r4 = this;
        r0 = 0;
        if (r5 == 0) goto L_0x0009;
    L_0x0003:
        r1 = r5.size();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r1 = -1;
        r2 = r6.hashCode();
        switch(r2) {
            case 2544374: goto L_0x00ca;
            case 79626270: goto L_0x00e0;
            case 2013072465: goto L_0x00d5;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r1) {
            case 0: goto L_0x0016;
            case 1: goto L_0x00eb;
            case 2: goto L_0x00fe;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0009;
    L_0x0016:
        r1 = "shopId";
        r1 = r4.getStringValue(r5, r1);
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x011f;
    L_0x0022:
        r2 = new com.taobao.applink.param.TBShopParam;
        r2.<init>(r1);
    L_0x0027:
        if (r2 == 0) goto L_0x0009;
    L_0x0029:
        r0 = "backURL";
        r0 = r4.getStringValue(r5, r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0111;
    L_0x0035:
        r2.setBackUrl(r0);
    L_0x0038:
        r0 = "e";
        r0 = r4.getStringValue(r5, r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0047;
    L_0x0044:
        r2.setE(r0);
    L_0x0047:
        r0 = "sign";
        r0 = r4.getStringValue(r5, r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0056;
    L_0x0053:
        r2.setSign(r0);
    L_0x0056:
        r0 = "type";
        r0 = r4.getStringValue(r5, r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x0065;
    L_0x0062:
        r2.setType(r0);
    L_0x0065:
        r0 = "addParams";
        r1 = r5.get(r0);
        if (r1 == 0) goto L_0x0118;
    L_0x006d:
        r0 = r1 instanceof java.util.HashMap;
        if (r0 == 0) goto L_0x0118;
    L_0x0071:
        r0 = r1;
        r0 = (java.util.HashMap) r0;
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0118;
    L_0x007a:
        r1 = (java.util.HashMap) r1;
    L_0x007c:
        r0 = "ybhpss";
        r0 = r4.getStringValue(r5, r0);
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x0093;
    L_0x0088:
        r3 = r1.containsKey(r0);
        if (r3 != 0) goto L_0x0093;
    L_0x008e:
        r3 = "ybhpss";
        r1.put(r3, r0);
    L_0x0093:
        r2.addExtraParams(r1);
        r0 = "TBAuth";
        r0 = r6.equals(r0);
        if (r0 != 0) goto L_0x00b8;
    L_0x009e:
        r0 = "jsonParams";
        r1 = r5.get(r0);
        if (r1 == 0) goto L_0x00b8;
    L_0x00a6:
        r0 = r1 instanceof org.json.JSONObject;
        if (r0 == 0) goto L_0x00b8;
    L_0x00aa:
        r0 = r1;
        r0 = (org.json.JSONObject) r0;
        r0 = r0.length();
        if (r0 <= 0) goto L_0x00b8;
    L_0x00b3:
        r1 = (org.json.JSONObject) r1;
        r2.setParams(r1);
    L_0x00b8:
        r0 = "appType";
        r0 = r4.getStringValue(r5, r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x00c7;
    L_0x00c4:
        r2.setAppType(r0);
    L_0x00c7:
        r0 = r2;
        goto L_0x0009;
    L_0x00ca:
        r2 = "SHOP";
        r2 = r6.equals(r2);
        if (r2 == 0) goto L_0x0012;
    L_0x00d2:
        r1 = 0;
        goto L_0x0012;
    L_0x00d5:
        r2 = "DETAIL";
        r2 = r6.equals(r2);
        if (r2 == 0) goto L_0x0012;
    L_0x00dd:
        r1 = 1;
        goto L_0x0012;
    L_0x00e0:
        r2 = "TBURI";
        r2 = r6.equals(r2);
        if (r2 == 0) goto L_0x0012;
    L_0x00e8:
        r1 = 2;
        goto L_0x0012;
    L_0x00eb:
        r1 = "itemId";
        r1 = r4.getStringValue(r5, r1);
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x011f;
    L_0x00f7:
        r2 = new com.taobao.applink.param.TBDetailParam;
        r2.<init>(r1);
        goto L_0x0027;
    L_0x00fe:
        r1 = "url";
        r1 = r4.getStringValue(r5, r1);
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x011f;
    L_0x010a:
        r2 = new com.taobao.applink.param.TBURIParam;
        r2.<init>(r1);
        goto L_0x0027;
    L_0x0111:
        r0 = "alisdk://";
        r2.setBackUrl(r0);
        goto L_0x0038;
    L_0x0118:
        r1 = new java.util.HashMap;
        r1.<init>();
        goto L_0x007c;
    L_0x011f:
        r2 = r0;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink.a(java.util.Map, java.lang.String):com.taobao.applink.param.TBBaseParam");
    }

    private void a(Object obj, boolean z) {
        Map hashMap = new HashMap();
        if (!TextUtils.isEmpty(AlibcContext.getAppKey())) {
            hashMap.put("appkey", AlibcContext.getAppKey());
        }
        hashMap.put("ybhpss", (String) MemoryCacheUtils.getGroupProperity(AlibcConstants.TRADE_GROUP, "ybhpss"));
        hashMap.put("param", JSONUtils.objectToJson("param", obj));
        hashMap.put(UserTrackerConstants.FROM, UserTrackerConstants.FROM_VALUE);
        hashMap.put(UserTrackerConstants.IS_SUCCESS, z ? "1" : "0");
        AlibcUserTracker.getInstance().sendCustomHit(UserTrackerConstants.E_SHOW_APPLINK, "", hashMap);
    }

    public static AlibcApplink getInstance() {
        if (d == null) {
            synchronized (AlibcApplink.class) {
                if (d == null) {
                    d = new AlibcApplink();
                }
            }
        }
        return d;
    }

    public static boolean isApplinkSupported(String str) {
        if (TBAppLinkUtil.isSupportAppLinkSDK(AlibcContext.context, str)) {
            return true;
        }
        AlibcLogger.d("AliTradeApplinkServiceImp", "对不起，请使用最新版的手机淘宝");
        return false;
    }

    public String getStringValue(Map map, String str) {
        Object obj = map.get(str);
        return (obj == null || !(obj instanceof String)) ? null : (String) obj;
    }

    public void initApplink() {
        if (!this.c) {
            TBAppLinkParam tBAppLinkParam = new TBAppLinkParam(AlibcContext.getAppKey(), null, "backurl", "");
            tBAppLinkParam.setUtdid(AlibcContext.getUtdid());
            tBAppLinkParam.setTTID(AlibcConfig.getInstance().getWebTTID());
            tBAppLinkParam.setSource(AlibcConfig.getInstance().taobaoNativeSource);
            this.b.init(AlibcContext.context, tBAppLinkParam);
            this.c = true;
        }
    }

    public boolean jumpDetail(Context context, Map map) {
        boolean z;
        boolean z2 = false;
        AlibcLogger.d(a, "调用applink jumpdetail方法,传入参数为:params=" + map);
        initApplink();
        TBDetailParam tBDetailParam = (TBDetailParam) a(map, AppLinkConstants.DETAIL);
        if (tBDetailParam != null) {
            try {
                this.b.jumpDetail(context, tBDetailParam);
                z = true;
            } catch (Exception e) {
                AlibcLogger.e("AliTradeApplinkServiceImp", "Applink調用jumpDetail失败：" + e.getMessage());
            }
        } else {
            z = false;
        }
        z2 = z;
        a((Object) map, z2);
        AlibcLogger.d("AliTradeApplinkServiceImp", "Applink調用jumpDetail" + (z2 ? "成功" : "失败"));
        return z2;
    }

    public boolean jumpShop(Context context, Map map) {
        boolean z;
        boolean z2 = false;
        AlibcLogger.d(a, "调用applink jumpshop方法,传入参数为:params=" + map);
        initApplink();
        TBShopParam tBShopParam = (TBShopParam) a(map, AppLinkConstants.SHOP);
        if (tBShopParam != null) {
            try {
                this.b.jumpShop(context, tBShopParam);
                z = true;
            } catch (Exception e) {
                AlibcLogger.e("AliTradeApplinkServiceImp", "Applink調用jumpShop失败：" + e.getMessage());
            }
        } else {
            z = false;
        }
        z2 = z;
        a((Object) map, z2);
        AlibcLogger.d("AliTradeApplinkServiceImp", "Applink調用jumpShop" + (z2 ? "成功" : "失败"));
        return z2;
    }

    public boolean jumpTBURI(Context context, Map map) {
        boolean z;
        AlibcLogger.d(a, "调用applink jumpuri方法,传入参数为:params=" + map);
        boolean z2 = false;
        initApplink();
        TBURIParam tBURIParam = (TBURIParam) a(map, AppLinkConstants.TBURI);
        if (tBURIParam != null) {
            try {
                this.b.jumpTBURI(context, tBURIParam);
                z = true;
            } catch (Exception e) {
                AlibcLogger.e("AliTradeApplinkServiceImp", "Applink調用jumpTBURI失败：" + e.getMessage());
            }
        } else {
            z = false;
        }
        z2 = z;
        a((Object) map, z2);
        AlibcLogger.d("AliTradeApplinkServiceImp", "Applink調用jumpTBURI" + (z2 ? "成功" : "失败"));
        return z2;
    }

    public void setOpenParam(Map map) {
        initApplink();
        if (this.b != null && map != null) {
            TBAppLinkParam tBAppLinkParam = this.b.sOpenParam;
            if (tBAppLinkParam != null) {
                String str = (String) map.get(AppLinkConstants.BACKURL);
                if (!TextUtils.isEmpty(str)) {
                    tBAppLinkParam.mBackUrl = str;
                }
                str = (String) map.get("pid");
                if (!TextUtils.isEmpty(str)) {
                    tBAppLinkParam.mPid = str;
                }
                str = (String) map.get(AppLinkConstants.TAG);
                if (!TextUtils.isEmpty(str)) {
                    tBAppLinkParam.mTag = str;
                }
                str = (String) map.get(AppLinkConstants.TTID);
                if (!TextUtils.isEmpty(str)) {
                    tBAppLinkParam.mTtid = str;
                }
                str = (String) map.get("source");
                if (!TextUtils.isEmpty(str)) {
                    tBAppLinkParam.mSource = str;
                }
                str = (String) map.get("utdid");
                if (!TextUtils.isEmpty(str)) {
                    tBAppLinkParam.mUtdid = str;
                }
            }
        }
    }
}
