package com.ali.auth.third.core.cookies;

import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alipay.sdk.util.h;
import org.json.JSONObject;

public class LoginCookieUtils {
    private static final char COMMA = ',';
    private static final String DOMAIN = "domain";
    private static final char EQUAL = '=';
    private static final String EXPIRES = "expires";
    private static final String HTTPS = "https";
    private static final String HTTP_ONLY = "httponly";
    private static final int HTTP_ONLY_LENGTH = HTTP_ONLY.length();
    private static final String MAX_AGE = "max-age";
    private static final int MAX_COOKIE_LENGTH = 4096;
    private static final String PATH = "path";
    private static final char PATH_DELIM = '/';
    private static final char PERIOD = '.';
    private static final char QUESTION_MARK = '?';
    private static final char QUOTATION = '\"';
    private static final String SECURE = "secure";
    private static final int SECURE_LENGTH = SECURE.length();
    private static final char SEMICOLON = ';';
    private static final String TAG = "login.LoginCookieUtils";
    private static final char WHITE_SPACE = ' ';

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ali.auth.third.core.cookies.LoginCookie parseCookie(java.lang.String r15) {
        /*
        r14 = 61;
        r13 = 59;
        r12 = 34;
        r11 = 1;
        r10 = -1;
        r2 = 0;
        r1 = r15.length();
    L_0x000d:
        if (r2 < 0) goto L_0x0011;
    L_0x000f:
        if (r2 < r1) goto L_0x0013;
    L_0x0011:
        r0 = 0;
    L_0x0012:
        return r0;
    L_0x0013:
        r0 = r15.charAt(r2);
        r3 = 32;
        if (r0 != r3) goto L_0x001e;
    L_0x001b:
        r2 = r2 + 1;
        goto L_0x000d;
    L_0x001e:
        r0 = r15.indexOf(r13, r2);
        r4 = r15.indexOf(r14, r2);
        r3 = new com.ali.auth.third.core.cookies.LoginCookie;
        r3.<init>();
        if (r0 == r10) goto L_0x002f;
    L_0x002d:
        if (r0 < r4) goto L_0x0031;
    L_0x002f:
        if (r4 != r10) goto L_0x0043;
    L_0x0031:
        if (r0 != r10) goto L_0x0034;
    L_0x0033:
        r0 = r1;
    L_0x0034:
        r2 = r15.substring(r2, r0);
        r3.name = r2;
        r2 = 0;
        r3.value = r2;
    L_0x003d:
        if (r0 < 0) goto L_0x0041;
    L_0x003f:
        if (r0 < r1) goto L_0x008b;
    L_0x0041:
        r0 = r3;
        goto L_0x0012;
    L_0x0043:
        r0 = r15.substring(r2, r4);
        r3.name = r0;
        r0 = r1 + -1;
        if (r4 >= r0) goto L_0x020b;
    L_0x004d:
        r0 = r4 + 1;
        r0 = r15.charAt(r0);
        if (r0 != r12) goto L_0x020b;
    L_0x0055:
        r0 = r4 + 2;
        r0 = r15.indexOf(r12, r0);
        if (r0 == r10) goto L_0x0011;
    L_0x005d:
        r0 = r15.indexOf(r13, r0);
        if (r0 != r10) goto L_0x0064;
    L_0x0063:
        r0 = r1;
    L_0x0064:
        r2 = r0 - r4;
        r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        if (r2 <= r5) goto L_0x0077;
    L_0x006a:
        r2 = r4 + 1;
        r4 = r4 + 1;
        r4 = r4 + 4096;
        r2 = r15.substring(r2, r4);
        r3.value = r2;
        goto L_0x003d;
    L_0x0077:
        r2 = r4 + 1;
        if (r2 == r0) goto L_0x007d;
    L_0x007b:
        if (r0 >= r4) goto L_0x0082;
    L_0x007d:
        r2 = "";
        r3.value = r2;
        goto L_0x003d;
    L_0x0082:
        r2 = r4 + 1;
        r2 = r15.substring(r2, r0);
        r3.value = r2;
        goto L_0x003d;
    L_0x008b:
        r2 = r15.charAt(r0);
        r4 = 32;
        if (r2 == r4) goto L_0x0099;
    L_0x0093:
        r2 = r15.charAt(r0);
        if (r2 != r13) goto L_0x009c;
    L_0x0099:
        r0 = r0 + 1;
        goto L_0x003d;
    L_0x009c:
        r2 = r15.charAt(r0);
        r4 = 44;
        if (r2 != r4) goto L_0x00a7;
    L_0x00a4:
        r0 = r0 + 1;
        goto L_0x0041;
    L_0x00a7:
        r2 = r1 - r0;
        r4 = SECURE_LENGTH;
        if (r2 < r4) goto L_0x00cd;
    L_0x00ad:
        r2 = SECURE_LENGTH;
        r2 = r2 + r0;
        r2 = r15.substring(r0, r2);
        r4 = "secure";
        r2 = r2.equalsIgnoreCase(r4);
        if (r2 == 0) goto L_0x00cd;
    L_0x00bc:
        r2 = SECURE_LENGTH;
        r0 = r0 + r2;
        r3.secure = r11;
        if (r0 == r1) goto L_0x0041;
    L_0x00c3:
        r2 = r15.charAt(r0);
        if (r2 != r14) goto L_0x003d;
    L_0x00c9:
        r0 = r0 + 1;
        goto L_0x003d;
    L_0x00cd:
        r2 = r1 - r0;
        r4 = HTTP_ONLY_LENGTH;
        if (r2 < r4) goto L_0x00f3;
    L_0x00d3:
        r2 = HTTP_ONLY_LENGTH;
        r2 = r2 + r0;
        r2 = r15.substring(r0, r2);
        r4 = "httponly";
        r2 = r2.equalsIgnoreCase(r4);
        if (r2 == 0) goto L_0x00f3;
    L_0x00e2:
        r2 = HTTP_ONLY_LENGTH;
        r0 = r0 + r2;
        r3.httpOnly = r11;
        if (r0 == r1) goto L_0x0041;
    L_0x00e9:
        r2 = r15.charAt(r0);
        if (r2 != r14) goto L_0x003d;
    L_0x00ef:
        r0 = r0 + 1;
        goto L_0x003d;
    L_0x00f3:
        r4 = r15.indexOf(r14, r0);
        if (r4 <= 0) goto L_0x0208;
    L_0x00f9:
        r2 = r15.substring(r0, r4);
        r5 = r2.toLowerCase();
        r2 = "expires";
        r2 = r5.equals(r2);
        if (r2 == 0) goto L_0x0119;
    L_0x0109:
        r2 = 44;
        r2 = r15.indexOf(r2, r4);
        if (r2 == r10) goto L_0x0119;
    L_0x0111:
        r6 = r2 - r4;
        r7 = 10;
        if (r6 > r7) goto L_0x0119;
    L_0x0117:
        r0 = r2 + 1;
    L_0x0119:
        r2 = r15.indexOf(r13, r0);
        r6 = 44;
        r0 = r15.indexOf(r6, r0);
        if (r2 != r10) goto L_0x0171;
    L_0x0125:
        if (r0 != r10) goto L_0x0171;
    L_0x0127:
        r0 = r1;
    L_0x0128:
        r2 = r4 + 1;
        r2 = r15.substring(r2, r0);
        r4 = r2.length();
        r6 = 2;
        if (r4 <= r6) goto L_0x0146;
    L_0x0135:
        r4 = 0;
        r4 = r2.charAt(r4);
        if (r4 != r12) goto L_0x0146;
    L_0x013c:
        r4 = r2.indexOf(r12, r11);
        if (r4 <= 0) goto L_0x0146;
    L_0x0142:
        r2 = r2.substring(r11, r4);
    L_0x0146:
        r4 = "expires";
        r4 = r5.equals(r4);
        if (r4 == 0) goto L_0x017c;
    L_0x014e:
        r4 = com.ali.auth.third.core.cookies.HttpDateTime.parse(r2);	 Catch:{ IllegalArgumentException -> 0x0156 }
        r3.expires = r4;	 Catch:{ IllegalArgumentException -> 0x0156 }
        goto L_0x003d;
    L_0x0156:
        r4 = move-exception;
        r5 = "login.LoginCookieUtils";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "illegal format for expires: ";
        r6 = r6.append(r7);
        r2 = r6.append(r2);
        r2 = r2.toString();
        com.ali.auth.third.core.trace.SDKLogger.e(r5, r2, r4);
        goto L_0x003d;
    L_0x0171:
        if (r2 == r10) goto L_0x0128;
    L_0x0173:
        if (r0 != r10) goto L_0x0177;
    L_0x0175:
        r0 = r2;
        goto L_0x0128;
    L_0x0177:
        r0 = java.lang.Math.min(r2, r0);
        goto L_0x0128;
    L_0x017c:
        r4 = "max-age";
        r4 = r5.equals(r4);
        if (r4 == 0) goto L_0x01af;
    L_0x0184:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ NumberFormatException -> 0x0194 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r8 = java.lang.Long.parseLong(r2);	 Catch:{ NumberFormatException -> 0x0194 }
        r6 = r6 * r8;
        r4 = r4 + r6;
        r3.expires = r4;	 Catch:{ NumberFormatException -> 0x0194 }
        goto L_0x003d;
    L_0x0194:
        r4 = move-exception;
        r5 = "login.LoginCookieUtils";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "illegal format for max-age: ";
        r6 = r6.append(r7);
        r2 = r6.append(r2);
        r2 = r2.toString();
        com.ali.auth.third.core.trace.SDKLogger.e(r5, r2, r4);
        goto L_0x003d;
    L_0x01af:
        r4 = "path";
        r4 = r5.equals(r4);
        if (r4 == 0) goto L_0x01c1;
    L_0x01b7:
        r4 = r2.length();
        if (r4 <= 0) goto L_0x003d;
    L_0x01bd:
        r3.path = r2;
        goto L_0x003d;
    L_0x01c1:
        r4 = "domain";
        r4 = r5.equals(r4);
        if (r4 == 0) goto L_0x003d;
    L_0x01c9:
        r4 = 46;
        r4 = r2.lastIndexOf(r4);
        if (r4 != 0) goto L_0x01d6;
    L_0x01d1:
        r2 = 0;
        r3.domain = r2;
        goto L_0x003d;
    L_0x01d6:
        r5 = r4 + 1;
        r5 = r2.substring(r5);	 Catch:{ NumberFormatException -> 0x01e1 }
        java.lang.Integer.parseInt(r5);	 Catch:{ NumberFormatException -> 0x01e1 }
        goto L_0x003d;
    L_0x01e1:
        r5 = move-exception;
        r2 = r2.toLowerCase();
        r5 = 0;
        r5 = r2.charAt(r5);
        r6 = 46;
        if (r5 == r6) goto L_0x0204;
    L_0x01ef:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = 46;
        r5 = r5.append(r6);
        r2 = r5.append(r2);
        r2 = r2.toString();
        r4 = r4 + 1;
    L_0x0204:
        r3.domain = r2;
        goto L_0x003d;
    L_0x0208:
        r0 = r1;
        goto L_0x003d;
    L_0x020b:
        r0 = r2;
        goto L_0x005d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ali.auth.third.core.cookies.LoginCookieUtils.parseCookie(java.lang.String):com.ali.auth.third.core.cookies.LoginCookie");
    }

    public static void expiresCookies(LoginCookie loginCookie) {
        loginCookie.expires = Long.valueOf(1000).longValue();
    }

    public static String getHttpDomin(LoginCookie loginCookie) {
        String str = loginCookie.domain;
        if (!TextUtils.isEmpty(str) && str.startsWith(".")) {
            str = str.substring(1);
        }
        return "http://" + str;
    }

    public static String getValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object cookie = CookieManagerService.getWebViewProxy().getCookie(".taobao.com");
            if (TextUtils.isEmpty(cookie)) {
                return null;
            }
            int indexOf;
            for (String str2 : cookie.split(h.b)) {
                String[] split = str2.split(LoginConstants.EQUAL);
                if (split.length >= 2 && str.equals(split[0].trim())) {
                    indexOf = str2.indexOf(LoginConstants.EQUAL);
                    if (split.length == 2) {
                        return split[1].trim();
                    }
                    return str2.substring(indexOf + 1);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getKeyValues(String str) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            try {
                Object cookie = CookieManagerService.getWebViewProxy().getCookie(".taobao.com");
                if (!TextUtils.isEmpty(cookie)) {
                    for (String str2 : cookie.split(h.b)) {
                        String[] split = str2.split(LoginConstants.EQUAL);
                        if (split.length >= 2 && split[0].contains(str)) {
                            if (split.length == 2) {
                                jSONObject.put(split[0].trim(), split[1].trim());
                            } else {
                                jSONObject.put(split[0].trim(), str2.substring(str2.indexOf(LoginConstants.EQUAL) + 1));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }
}
