package com.baidu.mobads.c;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.vo.a.b;
import com.baidu.mobads.vo.a.d;
import com.tencent.connect.common.Constants;
import com.umeng.commonsdk.proguard.g;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class a {
    public static volatile String b = "";
    public static volatile String c = "";
    private static a d = new a();
    private static boolean f = false;
    protected final IXAdLogger a = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private Context e;

    public static a a() {
        return d;
    }

    private a() {
        new Handler(Looper.getMainLooper()).postDelayed(new b(this), 2000);
    }

    public void a(Context context) {
        if (this.e == null) {
            this.e = context;
        }
    }

    public void a(Context context, com.baidu.mobads.command.a aVar) {
        a(context, "9", aVar);
    }

    public void a(com.baidu.mobads.command.a aVar) {
    }

    public void a(Context context, IXAppInfo iXAppInfo) {
        a(context, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, iXAppInfo);
    }

    public void b(Context context, IXAppInfo iXAppInfo) {
        a(context, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, iXAppInfo);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("temp_for_feed_response_html")) {
            a(str, "400", null);
        } else if (!f) {
            a("temp_for_feed_response_html", "405", b + "___" + c);
            f = true;
        }
    }

    public void a(String str, String str2, String str3) {
        try {
            Builder appendQueryParameter = new Builder().appendQueryParameter("stacktrace", str2);
            appendQueryParameter.appendQueryParameter(g.an, str3);
            for (Entry entry : new b().c().entrySet()) {
                appendQueryParameter.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            a(str, "404", appendQueryParameter);
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().e(e);
        }
    }

    private void a(String str, String str2, Builder builder) {
        IXAdConstants adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
        IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        if (builder == null) {
            builder = new Builder();
        }
        try {
            builder.appendQueryParameter("type", str2).appendQueryParameter(IXAdRequestInfo.P_VER, "8.7004").appendQueryParameter("appsid", adConstants.getAppSid()).appendQueryParameter("v", "android_" + com.baidu.mobads.a.a.c + "_" + "4.1.30").appendQueryParameter("reason", str).appendQueryParameter(IXAdRequestInfo.OSV, VERSION.RELEASE).appendQueryParameter(IXAdRequestInfo.BDR, "" + VERSION.SDK_INT).appendQueryParameter(IXAdRequestInfo.BRAND, "" + commonUtils.getTextEncoder(Build.BRAND)).appendQueryParameter("pack", adConstants.getAppPackageNameOfPublisher());
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().e(e);
        }
        c cVar = new c("https://mobads-logs.baidu.com/brwhis.log", "");
        cVar.a(builder);
        cVar.a(0);
        new com.baidu.mobads.openad.d.a().a(cVar);
    }

    private void a(Context context, String str, com.baidu.mobads.command.a aVar) {
        IXAppInfo a = com.baidu.mobads.command.a.a.a(aVar);
        if (a != null) {
            a(context, str, a);
        }
    }

    private void a(Context context, String str, IXAppInfo iXAppInfo) {
        com.baidu.mobads.vo.a.c cVar = new com.baidu.mobads.vo.a.c(context, iXAppInfo);
        cVar.b = iXAppInfo.getAdId();
        b(a(context, str, cVar.c()));
    }

    public void a(Context context, String str, IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo, Object... objArr) {
        a(0.1d, context, str, iXAdInstanceInfo, iXAdProdInfo, objArr);
    }

    public void a(double d, Context context, String str, IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo, Object... objArr) {
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < objArr.length) {
            try {
                hashMap.put("custom_" + i, String.valueOf(objArr[i]));
                i++;
            } catch (Throwable e) {
                l.a().e(e);
                return;
            }
        }
        a(d, context, str, iXAdInstanceInfo, iXAdProdInfo, hashMap);
    }

    public void a(Context context, String str, IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo, HashMap<String, String> hashMap) {
        a(1.0d, context, str, iXAdInstanceInfo, iXAdProdInfo, (HashMap) hashMap);
    }

    public void a(double d, Context context, String str, IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo, HashMap<String, String> hashMap) {
        try {
            if (d > new Random().nextDouble()) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put("probability", String.valueOf(d));
                b(new d(str, iXAdInstanceInfo, iXAdProdInfo, hashMap).a(context));
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
    }

    private void b(String str) {
        a(1, str);
    }

    private void a(int i, String str) {
        com.baidu.mobads.openad.d.a aVar = new com.baidu.mobads.openad.d.a();
        c cVar = new c(str, "");
        cVar.e = i;
        aVar.a(cVar, Boolean.valueOf(true));
    }

    private String a(Context context, String str, Map<String, String> map) {
        try {
            String encodeURIComponent;
            StringBuilder stringBuilder = new StringBuilder("type=" + str + com.alipay.sdk.sys.a.b);
            StringBuilder stringBuilder2 = new StringBuilder();
            map.put("ts", System.currentTimeMillis() + "");
            IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            for (String encodeURIComponent2 : map.keySet()) {
                String str2 = (String) map.get(encodeURIComponent2);
                if (!(encodeURIComponent2 == null || str2 == null)) {
                    encodeURIComponent2 = commonUtils.encodeURIComponent(encodeURIComponent2);
                    str2 = commonUtils.encodeURIComponent(str2);
                    stringBuilder.append(encodeURIComponent2);
                    stringBuilder.append("=");
                    stringBuilder.append(str2);
                    stringBuilder.append(com.alipay.sdk.sys.a.b);
                    stringBuilder2.append(str2);
                    stringBuilder2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
            }
            stringBuilder2.append("mobads,");
            encodeURIComponent2 = commonUtils.getMD5(stringBuilder2.toString());
            this.a.d("ExtraQuery.allValue:" + stringBuilder2);
            stringBuilder.append("vd=" + encodeURIComponent2 + com.alipay.sdk.sys.a.b);
            this.a.d("ExtraQuery.params:" + stringBuilder);
            return "https://mobads-logs.baidu.com/dz.zb" + "?" + stringBuilder.toString();
        } catch (Throwable e) {
            this.a.d(e);
            return "";
        }
    }
}
