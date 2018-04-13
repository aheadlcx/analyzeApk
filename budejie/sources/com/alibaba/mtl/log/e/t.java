package com.alibaba.mtl.log.e;

import android.content.Context;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.mtl.appmonitor.SdkMeta;
import com.alibaba.mtl.log.a.a;
import com.alibaba.mtl.log.model.LogField;
import com.alibaba.mtl.log.sign.IRequestAuth;
import com.alibaba.mtl.log.sign.SecurityRequestAuth;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class t {
    private static final String TAG = t.class.getSimpleName();

    public static String a(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2 = "";
        if (map2 != null && map2.size() > 0) {
            Set keySet = map2.keySet();
            String[] strArr = new String[keySet.size()];
            keySet.toArray(strArr);
            String str3 = str2;
            for (String str4 : g.a().a(strArr, true)) {
                str3 = str3 + str4 + j.b((byte[]) map2.get(str4));
            }
            str2 = str3;
        }
        try {
            str2 = a(str, null, null, str2);
        } catch (Throwable th) {
            str2 = a(a.M, null, null, str2);
        }
        Object obj = a.N;
        if (TextUtils.isEmpty(obj)) {
            return str2;
        }
        return str2 + "&dk=" + URLEncoder.encode(obj, "UTF-8");
    }

    public static String b(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2;
        if (map == null) {
            HashMap hashMap = new HashMap();
        }
        Context context = com.alibaba.mtl.log.a.getContext();
        String appkey = b.getAppkey();
        String l = b.l();
        if (l == null) {
            str2 = "";
        } else {
            str2 = l;
        }
        l = (String) d.a(context).get(LogField.APPVERSION.toString());
        String str3 = (String) d.a(context).get(LogField.OS.toString());
        String str4 = SdkMeta.SDK_VERSION;
        String str5 = (String) d.a(context).get(LogField.UTDID.toString());
        String str6 = "3.0";
        String valueOf = String.valueOf(System.currentTimeMillis());
        IRequestAuth a = com.alibaba.mtl.log.a.a();
        str6 = "0";
        if (a instanceof SecurityRequestAuth) {
            str6 = "1";
        }
        str3 = a.getSign(j.b((appkey + l + str2 + str3 + str5 + str4 + valueOf + str6 + map.get("_b01n15") + map.get("_b01na")).getBytes()));
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.append("?");
        stringBuilder.append("ak").append(LoginConstants.EQUAL).append(appkey);
        stringBuilder.append("&").append(com.alipay.sdk.sys.a.k).append(LoginConstants.EQUAL).append(l);
        stringBuilder.append("&").append("c").append(LoginConstants.EQUAL).append(URLEncoder.encode(str2));
        stringBuilder.append("&").append("d").append(LoginConstants.EQUAL).append(str5);
        stringBuilder.append("&").append(com.alipay.sdk.sys.a.h).append(LoginConstants.EQUAL).append(str4);
        stringBuilder.append("&").append("t").append(LoginConstants.EQUAL).append(valueOf);
        stringBuilder.append("&").append("is").append(LoginConstants.EQUAL).append(str6);
        stringBuilder.append("&").append("_b01n15").append(LoginConstants.EQUAL).append(map.get("_b01n15"));
        stringBuilder.append("&").append("_b01na").append(LoginConstants.EQUAL).append(map.get("_b01na"));
        stringBuilder.append("&").append("s").append(LoginConstants.EQUAL).append(str3);
        return stringBuilder.toString();
    }

    private static String a(String str, String str2, String str3, String str4) throws Exception {
        String str5;
        Context context = com.alibaba.mtl.log.a.getContext();
        String appkey = b.getAppkey();
        String l = b.l();
        if (l == null) {
            str5 = "";
        } else {
            str5 = l;
        }
        l = (String) d.a(context).get(LogField.APPVERSION.toString());
        String str6 = (String) d.a(context).get(LogField.OS.toString());
        String str7 = SdkMeta.SDK_VERSION;
        String str8 = (String) d.a(context).get(LogField.UTDID.toString());
        String str9 = "3.0";
        String valueOf = String.valueOf(System.currentTimeMillis());
        IRequestAuth a = com.alibaba.mtl.log.a.a();
        String str10 = "1";
        String str11 = "0";
        if (a instanceof SecurityRequestAuth) {
            str11 = "1";
            str10 = "0";
        }
        StringBuilder append = new StringBuilder().append(appkey).append(str5).append(l).append(str6).append(str7).append(str8).append(valueOf).append(str9).append(str11);
        if (str3 == null) {
            str3 = "";
        }
        append = append.append(str3);
        if (str4 == null) {
            str4 = "";
        }
        String sign = a.getSign(j.b(append.append(str4).toString().getBytes()));
        String str12 = "";
        if (!TextUtils.isEmpty(str2)) {
            str12 = str2 + "&";
        }
        return String.format("%s?%sak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s&is=%s&k=%s", new Object[]{str, str12, e(appkey), e(l), e(str5), e(str9), e(sign), e(str8), str7, str6, valueOf, "", str11, str10});
    }

    private static String e(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
