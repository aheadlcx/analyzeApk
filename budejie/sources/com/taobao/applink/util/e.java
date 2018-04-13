package com.taobao.applink.util;

import android.util.Log;
import com.ali.auth.third.login.LoginConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Pattern;

public class e {
    public static String a(Map map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : map.keySet()) {
            String str2;
            String encode;
            String str3 = (String) map.get(str2);
            if (str3 != null) {
                try {
                    encode = URLEncoder.encode(str3, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    UnsupportedEncodingException unsupportedEncodingException = e;
                    encode = str3;
                    UnsupportedEncodingException unsupportedEncodingException2 = unsupportedEncodingException;
                    Log.e(TBAppLinkUtil.TAG, unsupportedEncodingException2.toString());
                    if (encode == null) {
                        stringBuffer.append("&").append(str2).append(LoginConstants.EQUAL).append(encode);
                    }
                }
            } else {
                encode = str3;
            }
            try {
                str2 = URLEncoder.encode(str2, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                unsupportedEncodingException2 = e2;
                Log.e(TBAppLinkUtil.TAG, unsupportedEncodingException2.toString());
                if (encode == null) {
                    stringBuffer.append("&").append(str2).append(LoginConstants.EQUAL).append(encode);
                }
            }
            if (encode == null) {
                stringBuffer.append("&").append(str2).append(LoginConstants.EQUAL).append(encode);
            }
        }
        return stringBuffer.toString().substring(1);
    }

    public static boolean a(String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    public static boolean b(String str) {
        return a(str) ? false : Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static boolean c(String str) {
        return a(str) ? false : Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$").matcher(str).matches();
    }
}
