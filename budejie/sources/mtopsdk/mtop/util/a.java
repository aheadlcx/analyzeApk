package mtopsdk.mtop.util;

import cn.v6.sixrooms.ui.phone.BundMobileDialogActivity;
import com.ali.auth.third.mtop.rpc.ResultActionType;
import java.util.HashMap;
import mtopsdk.common.util.l;

public class a {
    static HashMap a = new HashMap();
    static HashMap b = new HashMap();
    static HashMap c;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put("SYSTEM_ERROR", Integer.valueOf(-2000));
        c.put("FAIL_SYS_HSF_ASYNC_POOL_FOOL", Integer.valueOf(-2001));
        c.put("FAIL_SYS_PARAM_MISSING", Integer.valueOf(-2008));
        c.put("FAIL_SYS_PARAM_FORMAT_ERROR", Integer.valueOf(-2009));
        c.put("FAIL_SYS_SYSTEM_BUSY_ERROR", Integer.valueOf(-2002));
        c.put("FAIL_SYS_API_STOP_SERVICE", Integer.valueOf(-2003));
        c.put("FAIL_BIZ_MTOP_RT_IS_NULL", Integer.valueOf(-2004));
        c.put("FAIL_SYS_API_NOT_FOUNDED", Integer.valueOf(-2018));
        c.put("FAIL_SYS_SERVICE_NOT_EXIST", Integer.valueOf(-2019));
        c.put("FAIL_SYS_SERVICE_TIMEOUT", Integer.valueOf(-2020));
        c.put("FAIL_SYS_SERVICE_FAULT", Integer.valueOf(-2021));
        c.put("FAIL_SYS_SERVICE_INNER_FAULT", Integer.valueOf(-2022));
        c.put("FAIL_SYS_PROTOVER_MISSED", Integer.valueOf(-2026));
        c.put("FAIL_SYS_PROTOPARAM_MISSED", Integer.valueOf(-2025));
        c.put("FAIL_SYS_INTERNAL_FAULT", Integer.valueOf(-2012));
        c.put("FAIL_SYS_INVALID_HTTP_METHOD", Integer.valueOf(-2013));
        c.put("FAIL_SYS_UNKNOWN_APP", Integer.valueOf(-2014));
        c.put("FAIL_SYS_RETMISSED_ERROR", Integer.valueOf(-2015));
        c.put("FAIL_SYS_API_UNAUTHORIZED", Integer.valueOf(-2007));
        c.put("FAIL_SYS_BIZPARAM_MISSED", Integer.valueOf(-2008));
        c.put("FAIL_SYS_BIZPARAM_TYPE_ERROR", Integer.valueOf(-2009));
        c.put("FAIL_SYS_ASYNC4J_RESPONSE_FETCH_FAIL", Integer.valueOf(-2024));
        c.put("FAIL_SYS_ASYNC4J_RESPONSE_QUERY_FAIL", Integer.valueOf(-2023));
        c.put("FAIL_SYS_BADARGUMENT_T", Integer.valueOf(-2010));
        c.put("FAIL_SYS_APPKEY_NOT_EXIST", Integer.valueOf(-2011));
        c.put("FAIL_SYS_TOPAUTHPARAM_MISSED", Integer.valueOf(-2032));
        c.put("FAIL_SYS_TOPAUTH_FAILED", Integer.valueOf(-2027));
        c.put("FAIL_SYS_TOPAUTH_ACCESSTOKENEXPIRED_ERROR", Integer.valueOf(-2028));
        c.put("FAIL_SYS_TOPAUTH_TRAFFICLIMIT_ERROR", Integer.valueOf(-2029));
        c.put("FAIL_SYS_TOPUNAUTHAPI_ERROR", Integer.valueOf(-2030));
        c.put("FAIL_SYS_TOPAUTH_FAULT", Integer.valueOf(-2031));
        c.put("FAIL_SYS_ILLEGAL_ARGUMENT_TTID", Integer.valueOf(-2033));
        c.put("FAIL_SYS_ACCESS_TOKEN_EXPIRED", Integer.valueOf(-2034));
        c.put("FAIL_SYS_ILLEGAL_ACCESS_TOKEN", Integer.valueOf(-2035));
        c.put("FAIL_SYS_LOGIN_CANCEL", Integer.valueOf(-2036));
        c.put("FAIL_SYS_LOGIN_FAIL", Integer.valueOf(-2037));
        b.put("ANDROID_SYS_NETWORK_ERROR", Integer.valueOf(-2501));
        b.put("ANDROID_SYS_NO_NETWORK", Integer.valueOf(-2500));
        b.put("ANDROID_SYS_JSONDATA_BLANK", Integer.valueOf(-2502));
        b.put("ANDROID_SYS_JSONDATA_PARSE_ERROR", Integer.valueOf(-2503));
        b.put("ANDROID_SYS_MTOPREQUEST_INVALID_ERROR", Integer.valueOf(-2504));
        b.put("ANDROID_SYS_MTOPPROXYBASE_INIT_ERROR", Integer.valueOf(-2505));
        b.put("ANDROID_SYS_GENERATE_MTOP_SIGN_ERROR", Integer.valueOf(-2506));
        b.put("ANDROID_SYS_API_FLOW_LIMIT_LOCKED", Integer.valueOf(-2507));
        b.put("ANDROID_SYS_API_41X_ANTI_ATTACK", Integer.valueOf(-2508));
        b.put("ANDROID_SYS_TRADE_API_ASYNC_RESULT", Integer.valueOf(-2509));
        b.put("ANDROID_SYS_MTOP_APICALL_ASYNC_TIMEOUT", Integer.valueOf(-2510));
        b.put("ANDROID_SYS_ASYNC4J_INTERNAL_ERROR", Integer.valueOf(-2511));
        b.put("ANDROID_SYS_MISS_API_RESPONSE_SIGN", Integer.valueOf(-2512));
        b.put("ANDROID_SYS_VALIDATE_API_RESPONSE_SIGN_ERROR", Integer.valueOf(-2513));
        a.put("FAIL_SYS_REQUEST_EXPIRED", Integer.valueOf(-2016));
        a.put("FAIL_SYS_SESSION_EXPIRED", Integer.valueOf(-2005));
        a.put("FAIL_SYS_ILEGEL_SIGN", Integer.valueOf(-2006));
        a.put("FAIL_SYS_TRAFFIC_LIMIT", Integer.valueOf(-2017));
        a.putAll(c);
        a.putAll(b);
        a.put(ResultActionType.SUCCESS, Integer.valueOf(BundMobileDialogActivity.IS_SUCCEED));
    }

    public static Integer a(String str) {
        Integer num = (Integer) a.get(str);
        return num == null ? Integer.valueOf(-1000) : num;
    }

    public static Integer b(String str) {
        Integer num = (Integer) b.get(str);
        return num == null ? Integer.valueOf(-1000) : num;
    }

    public static boolean c(String str) {
        return c.containsKey(str);
    }

    public static boolean d(String str) {
        return l.b(str) || b.containsKey(str);
    }

    public static boolean e(String str) {
        int intValue = b(str).intValue();
        return -2501 == intValue || -2500 == intValue;
    }

    public static boolean f(String str) {
        return -2500 == b(str).intValue();
    }

    public static boolean g(String str) {
        return -2005 == a(str).intValue();
    }

    public static boolean h(String str) {
        return -2006 == a(str).intValue();
    }

    public static boolean i(String str) {
        return BundMobileDialogActivity.IS_SUCCEED == a(str).intValue();
    }

    public static boolean j(String str) {
        return -2508 == b(str).intValue();
    }

    public static boolean k(String str) {
        return -2507 == b(str).intValue();
    }

    public static boolean l(String str) {
        return -2016 == a(str).intValue();
    }

    public static boolean m(String str) {
        return str != null && str.startsWith("FAIL_SYS_");
    }
}
