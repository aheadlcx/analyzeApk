package com.taobao.tao.remotebusiness.listener;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.wireless.security.jaq.SecurityVerification;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.securesignature.SecureSignatureDefine;
import com.alibaba.wireless.security.open.staticdatastore.IStaticDataStoreComponent;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.tao.remotebusiness.IRemoteCacheListener;
import com.taobao.tao.remotebusiness.IRemoteProcessListener;
import com.taobao.tao.remotebusiness.MtopBusiness;
import java.io.Closeable;
import java.lang.reflect.Proxy;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.b.a;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.common.d;
import mtopsdk.mtop.common.e;
import mtopsdk.mtop.common.g;
import mtopsdk.mtop.common.k;

public class c {
    private SecurityGuardManager a = null;
    private f b = null;
    private SecurityVerification c;

    public static String a(String str, MtopBusiness mtopBusiness, boolean z, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" [");
        if (mtopBusiness != null) {
            stringBuilder.append("apiName=").append(mtopBusiness.request.getApiName()).append(";version=").append(mtopBusiness.request.getVersion()).append(";requestType=").append(mtopBusiness.getRequestType());
            if (z) {
                stringBuilder.append(";clazz=").append(mtopBusiness.clazz);
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String a(Map map, String str) {
        List list;
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            list = null;
        } else {
            for (Entry entry : map.entrySet()) {
                if (str.equalsIgnoreCase((String) entry.getKey())) {
                    list = (List) entry.getValue();
                    break;
                }
            }
            list = null;
        }
        return (list == null || list.isEmpty()) ? null : (String) list.get(0);
    }

    public static k a(MtopBusiness mtopBusiness, k kVar) {
        List arrayList = new ArrayList();
        arrayList.add(e.class);
        if (kVar instanceof IRemoteProcessListener) {
            arrayList.add(g.class);
            arrayList.add(mtopsdk.mtop.common.f.class);
        }
        if ((kVar instanceof IRemoteCacheListener) || mtopBusiness.mtopProp.useCache) {
            arrayList.add(d.class);
        }
        return (k) Proxy.newProxyInstance(k.class.getClassLoader(), (Class[]) arrayList.toArray(new Class[arrayList.size()]), new a(mtopBusiness, kVar));
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static boolean a(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH");
    }

    public static boolean a(Map map) {
        try {
            if ("gzip".equalsIgnoreCase(a(map, "Content-Encoding"))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static String b(String str) {
        if (l.b(str)) {
            return str;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("utf-8"));
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                while (toHexString.length() < 2) {
                    toHexString = "0" + toHexString;
                }
                stringBuffer.append(toHexString);
            }
            return stringBuffer.toString();
        } catch (Throwable e) {
            m.b("mtopsdk.SecurityUtils", "[getMd5] compute md5 value failed for source str=" + str, e);
            return null;
        }
    }

    private static String c(String str) {
        return str == null ? "" : str;
    }

    public String a() {
        String str = null;
        try {
            if (this.c != null) {
                str = this.c.doJAQVerfificationSync(null, 20);
            }
        } catch (Throwable th) {
            m.b("mtopsdk.SecuritySignImpl", "[getSecBodyDataEx] SecurityVerification doJAQVerfificationSync error", th);
        }
        return str;
    }

    public String a(HashMap hashMap, String str) {
        if (hashMap == null || str == null) {
            m.d("mtopsdk.SecuritySignImpl", "[getMtopApiWBSign] appkey or params is null.appkey=" + str);
            return null;
        } else if (this.a == null) {
            m.d("mtopsdk.SecuritySignImpl", "[getMtopApiWBSign]SecurityGuardManager is null,please call ISign init()");
            return null;
        } else {
            try {
                Map map;
                String str2;
                SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                securityGuardParamContext.appKey = str;
                securityGuardParamContext.requestType = 7;
                if (hashMap == null || hashMap.size() <= 0) {
                    map = null;
                } else {
                    str2 = (String) hashMap.get("api");
                    String str3 = (String) hashMap.get(IXAdRequestInfo.V);
                    String str4 = (String) hashMap.get("data");
                    String str5 = (String) hashMap.get("accessToken");
                    String str6 = (String) hashMap.get("t");
                    String str7 = (String) hashMap.get("utdid");
                    String str8 = (String) hashMap.get("pv");
                    String str9 = (String) hashMap.get("x-features");
                    String str10 = (String) hashMap.get(AlibcConstants.TTID);
                    String str11 = (String) hashMap.get("sid");
                    String str12 = (String) hashMap.get("wua");
                    StringBuilder stringBuilder = new StringBuilder(64);
                    stringBuilder.append(str2).append("&");
                    stringBuilder.append(str3).append("&");
                    stringBuilder.append(b(str4)).append("&");
                    stringBuilder.append(str).append("&");
                    stringBuilder.append(c(str5)).append("&");
                    stringBuilder.append(str6).append("&");
                    stringBuilder.append(c(str7)).append("&");
                    stringBuilder.append(c(str8)).append("&");
                    stringBuilder.append(c(str9)).append("&");
                    stringBuilder.append(c(str10)).append("&");
                    stringBuilder.append(c(str11)).append("&");
                    stringBuilder.append(c(str12));
                    map = new HashMap(2);
                    map.put(SecureSignatureDefine.OPEN_KEY_SIGN_INPUT, stringBuilder.toString());
                }
                map.put(SecureSignatureDefine.OPEN_KEY_SIGN_ATLAS, "a");
                securityGuardParamContext.paramMap = map;
                ISecureSignatureComponent secureSignatureComp = this.a.getSecureSignatureComp();
                str2 = this.b.h();
                if (str2 == null) {
                    str2 = "";
                }
                return secureSignatureComp.signRequest(securityGuardParamContext, str2);
            } catch (Throwable th) {
                m.b("mtopsdk.SecuritySignImpl", "[getMtopApiWBSign] ISecureSignatureComponent signRequest error", th);
                return null;
            }
        }
    }

    public String a(a aVar) {
        if (aVar == null) {
            return null;
        }
        if (aVar.b != null) {
            return aVar.b;
        }
        IStaticDataStoreComponent staticDataStoreComp = this.a.getStaticDataStoreComp();
        try {
            String h = this.b.h();
            int i = aVar.a;
            if (h == null) {
                h = "";
            }
            return staticDataStoreComp.getAppKeyByIndex(i, h);
        } catch (Throwable e) {
            m.b("mtopsdk.SecuritySignImpl", "[getAppkey]getAppKeyByIndex error.", e);
            return null;
        }
    }

    public void a(Context context, int i) {
        if (context == null) {
            m.d("mtopsdk.SecuritySignImpl", "[init]SecuritySignImpl init.context is null");
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.b = f.a();
            this.a = SecurityGuardManager.getInstance(context);
            IStaticDataStoreComponent staticDataStoreComp = this.a.getStaticDataStoreComp();
            String h = this.b.h();
            if (h == null) {
                h = "";
            }
            h = staticDataStoreComp.getAppKeyByIndex(i, h);
            if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                m.a("mtopsdk.SecuritySignImpl", "[init]SecuritySignImpl ISign init.set GlobalAppKey=" + h);
            }
            this.c = new SecurityVerification(context);
            if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                m.a("mtopsdk.SecuritySignImpl", "[init]SecuritySignImpl ISign init succeed.init time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
        } catch (Throwable th) {
            m.d("mtopsdk.SecuritySignImpl", "[init]SecuritySignImpl init securityguard error.---" + th.toString());
        }
    }
}
