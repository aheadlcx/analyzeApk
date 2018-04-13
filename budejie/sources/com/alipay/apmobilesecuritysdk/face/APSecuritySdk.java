package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.f.d;
import com.alipay.apmobilesecuritysdk.f.g;
import com.alipay.apmobilesecuritysdk.f.h;
import com.alipay.apmobilesecuritysdk.f.i;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;

public class APSecuritySdk {
    private static APSecuritySdk a;
    private static Object c = new Object();
    private Context b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        final /* synthetic */ APSecuritySdk a;
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult(APSecuritySdk aPSecuritySdk) {
            this.a = aPSecuritySdk;
        }
    }

    private APSecuritySdk(Context context) {
        this.b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (a == null) {
            synchronized (c) {
                if (a == null) {
                    a = new APSecuritySdk(context);
                }
            }
        }
        return a;
    }

    public static String getUtdid(Context context) {
        return "";
    }

    public String getApdidToken() {
        return a.a(this.b, "");
    }

    public String getSdkName() {
        return "security-sdk-token";
    }

    public String getSdkVersion() {
        return "3.2.0-20160621";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult(this);
        try {
            tokenResult.apdidToken = a.a(this.b, "");
            tokenResult.clientKey = h.c(this.b);
            tokenResult.apdid = a.a(this.b);
            Context context = this.b;
            tokenResult.umidToken = com.alipay.apmobilesecuritysdk.e.a.a();
        } catch (Throwable th) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        com.alipay.apmobilesecuritysdk.b.a.a().a(i);
        String a = h.a(this.b);
        String c = com.alipay.apmobilesecuritysdk.b.a.a().c();
        if (com.alipay.b.a.a.a.a.b(a) && !com.alipay.b.a.a.a.a.a(a, c)) {
            com.alipay.apmobilesecuritysdk.f.a.a(this.b);
            d.a(this.b);
            g.a(this.b);
            i.h();
        }
        if (!com.alipay.b.a.a.a.a.a(a, c)) {
            h.a(this.b, c);
        }
        Object a2 = com.alipay.b.a.a.a.a.a(map, "utdid", "");
        c = com.alipay.b.a.a.a.a.a(map, b.c, "");
        String a3 = com.alipay.b.a.a.a.a.a(map, UserTrackerConstants.USERID, "");
        if (com.alipay.b.a.a.a.a.a((String) a2)) {
            Context context = this.b;
            a2 = "";
        }
        final Map hashMap = new HashMap();
        hashMap.put("utdid", a2);
        hashMap.put(b.c, c);
        hashMap.put(UserTrackerConstants.USERID, a3);
        hashMap.put("appName", "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "3");
        com.alipay.apmobilesecuritysdk.g.b.a().a(new Runnable(this) {
            final /* synthetic */ APSecuritySdk c;

            public void run() {
                new a(this.c.b).a(hashMap);
                if (initResultListener != null) {
                    initResultListener.onResult(this.c.getTokenResult());
                }
            }
        });
    }
}
