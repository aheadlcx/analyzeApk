package com.baidu.mobads.vo.a;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.d;
import com.xiaomi.mipush.sdk.Constants;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public abstract class a {
    public long a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    protected Context l;
    protected d m;
    protected IXAdSystemUtils n;
    private a o;

    public static class a {
        String a = "";
        String b = "";
        String c = "";
        String d = "";
        String e = "";

        public a(IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo) {
            if (iXAdInstanceInfo != null) {
                this.a = iXAdInstanceInfo.getAdId();
                this.b = iXAdInstanceInfo.getQueryKey();
                this.d = iXAdInstanceInfo.getCreativeType().getValue();
            }
            if (iXAdProdInfo != null) {
                this.e = iXAdProdInfo.getAdPlacementId();
                this.c = iXAdProdInfo.getProdType();
            }
        }
    }

    protected abstract HashMap<String, String> b();

    public a(a aVar) {
        this(aVar.a, aVar.b, aVar.c);
        this.o = aVar;
    }

    @Deprecated
    public a(String str, String str2, String str3) {
        this.b = "-1";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.j = "";
        this.k = "";
        this.o = null;
        this.m = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        this.n = XAdSDKFoundationFacade.getInstance().getSystemUtils();
        this.l = XAdSDKFoundationFacade.getInstance().getApplicationContext();
        this.a = System.currentTimeMillis();
        this.b = str;
        this.c = str2;
        this.e = this.m.getAppSec(this.l);
        if (this.l != null) {
            this.d = this.l.getPackageName();
        }
        this.f = this.m.getAppId(this.l);
        this.h = this.n.getEncodedSN(this.l);
        this.i = "android";
        this.g = "android_" + com.baidu.mobads.a.a.c + "_" + "4.1.30";
        this.j = str3;
        this.k = XAdSDKFoundationFacade.getInstance().getSystemUtils().getCUID(this.l);
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("adid", this.b);
        hashMap.put("appsec", this.e);
        hashMap.put("appsid", this.f);
        hashMap.put("pack", this.d);
        hashMap.put("qk", this.c);
        hashMap.put(IXAdRequestInfo.SN, this.h);
        hashMap.put("ts", "" + this.a);
        hashMap.put("v", this.g);
        hashMap.put("os", this.i);
        hashMap.put("prod", this.j);
        hashMap.put("cuid", this.k);
        hashMap.put(IXAdRequestInfo.P_VER, "8.7004");
        if (this.o != null) {
            hashMap.put("adt", this.o.d);
            hashMap.put("apid", this.o.e);
        }
        return hashMap;
    }

    public HashMap<String, String> c() {
        HashMap<String, String> a = a();
        Map b = b();
        if (b != null) {
            a.putAll(b);
        }
        return a;
    }

    public String toString() {
        return a(c());
    }

    protected String a(HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            StringBuilder stringBuilder2 = new StringBuilder();
            for (String str : hashMap.keySet()) {
                String str2;
                String str3 = (String) hashMap.get(str2);
                if (!(str2 == null || str3 == null)) {
                    str2 = a(str2);
                    str3 = a(str3);
                    stringBuilder.append(str2 + "=" + str3 + com.alipay.sdk.sys.a.b);
                    stringBuilder2.append(str3 + Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
            }
            stringBuilder2.append("mobads,");
            stringBuilder.append("vd=" + commonUtils.getMD5(stringBuilder2.toString()) + com.alipay.sdk.sys.a.b);
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    protected String a(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
        } catch (Exception e) {
        }
        return str;
    }
}
