package com.ut.mini;

import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.mtl.log.e.i;
import com.ut.mini.base.UTMIVariables;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UTHybridHelper {
    private static UTHybridHelper a = new UTHybridHelper();

    public static UTHybridHelper getInstance() {
        return a;
    }

    public void setH5Url(String str) {
        if (str != null) {
            UTMIVariables.getInstance().setH5Url(str);
        }
    }

    public void h5UT(Map<String, String> map, Object obj) {
        if (map == null || map.size() == 0) {
            i.a("h5UT", (Object) "dataMap is empty");
            return;
        }
        String str = (String) map.get("functype");
        if (str == null) {
            i.a("h5UT", (Object) "funcType is null");
            return;
        }
        String str2 = (String) map.get("utjstype");
        if (str2 == null || str2.equals("0") || str2.equals("1")) {
            map.remove("functype");
            Date date = new Date();
            if (str.equals("2001")) {
                a(date, map, obj);
                return;
            } else if (str.equals("2101")) {
                a(date, map);
                return;
            } else {
                return;
            }
        }
        i.a("h5UT", (Object) "utjstype should be 1 or 0 or null");
    }

    private void a(Date date, Map<String, String> map, Object obj) {
        if (map != null && map.size() != 0) {
            String b = b((String) map.get("urlpagename"), (String) map.get("url"));
            if (b == null || TextUtils.isEmpty(b)) {
                i.a("h5Page", (Object) "pageName is null,return");
                return;
            }
            Map c;
            String refPage = UTMIVariables.getInstance().getRefPage();
            String str = (String) map.get("utjstype");
            map.remove("utjstype");
            if (str == null || str.equals("0")) {
                c = c(map);
            } else if (str.equals("1")) {
                c = d(map);
            } else {
                c = null;
            }
            int i = 2006;
            if (UTPageHitHelper.getInstance().a(obj)) {
                i = 2001;
            }
            UTOriginalCustomHitBuilder uTOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder(b, i, refPage, null, null, c);
            if (2001 == i) {
                UTMIVariables.getInstance().setRefPage(b);
            }
            Map c2 = UTPageHitHelper.getInstance().c();
            if (c2 != null && c2.size() > 0) {
                uTOriginalCustomHitBuilder.setProperties(c2);
            }
            UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send(uTOriginalCustomHitBuilder.build());
            } else {
                i.a("h5Page event error", (Object) "Fatal Error,must call setRequestAuthentication method first.");
            }
            UTPageHitHelper.getInstance().a(obj);
        }
    }

    private void a(Date date, Map<String, String> map) {
        if (map != null && map.size() != 0) {
            Object b = b((String) map.get("urlpagename"), (String) map.get("url"));
            if (b == null || TextUtils.isEmpty(b)) {
                i.a("h5Ctrl", "pageName is null,return");
                return;
            }
            String str = (String) map.get("logkey");
            if (str == null || TextUtils.isEmpty(str)) {
                i.a("h5Ctrl", "logkey is null,return");
                return;
            }
            Map e;
            String str2 = (String) map.get("utjstype");
            map.remove("utjstype");
            if (str2 == null || str2.equals("0")) {
                e = e(map);
            } else if (str2.equals("1")) {
                e = f(map);
            } else {
                e = null;
            }
            UTOriginalCustomHitBuilder uTOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder(b, 2101, str, null, null, e);
            UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send(uTOriginalCustomHitBuilder.build());
            } else {
                i.a("h5Ctrl event error", (Object) "Fatal Error,must call setRequestAuthentication method first.");
            }
        }
    }

    private Map<String, String> c(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Object obj;
        String str;
        HashMap hashMap = new HashMap();
        String str2 = (String) map.get("url");
        String str3 = "_h5url";
        if (str2 == null) {
            obj = "";
        } else {
            str = str2;
        }
        hashMap.put(str3, obj);
        if (str2 != null) {
            Uri parse = Uri.parse(str2);
            CharSequence queryParameter = parse.getQueryParameter("spm");
            if (queryParameter == null || TextUtils.isEmpty(queryParameter)) {
                hashMap.put("spm", "0.0.0.0");
            } else {
                hashMap.put("spm", queryParameter);
            }
            CharSequence queryParameter2 = parse.getQueryParameter(AlibcConstants.SCM);
            if (!(queryParameter2 == null || TextUtils.isEmpty(queryParameter2))) {
                hashMap.put(AlibcConstants.SCM, queryParameter2);
            }
        } else {
            hashMap.put("spm", "0.0.0.0");
        }
        Object obj2 = (String) map.get("spmcnt");
        str = "_spmcnt";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("spmpre");
        str = "_spmpre";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("lzsid");
        str = "_lzsid";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("extendargs");
        str = "_h5ea";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("cna");
        str = "_cna";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private Map<String, String> d(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Object obj = (String) map.get("url");
        String str = "_h5url";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        obj = (String) map.get("extendargs");
        str = "_h5ea";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private Map<String, String> e(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        Object obj = (String) map.get("logkeyargs");
        String str = "_lka";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        obj = (String) map.get("cna");
        str = "_cna";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        obj = (String) map.get("extendargs");
        str = "_h5ea";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private Map<String, String> f(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        Object obj = (String) map.get("extendargs");
        String str = "_h5ea";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private String b(String str, String str2) {
        String str3 = "";
        if (str != null && !TextUtils.isEmpty(str)) {
            return str;
        }
        if (TextUtils.isEmpty(str2)) {
            return str3;
        }
        int indexOf = str2.indexOf("?");
        if (indexOf == -1) {
            return str2;
        }
        return str2.substring(0, indexOf);
    }
}
