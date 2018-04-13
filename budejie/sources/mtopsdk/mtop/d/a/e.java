package mtopsdk.mtop.d.a;

import com.ali.auth.third.login.LoginConstants;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;

public class e {
    public static String a(Map map, String str) {
        if (map == null) {
            return null;
        }
        if (l.b(str)) {
            str = "utf-8";
        }
        StringBuilder stringBuilder = new StringBuilder(64);
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            try {
                String encode = URLEncoder.encode((String) entry.getKey(), str);
                stringBuilder.append(encode).append(LoginConstants.EQUAL).append(URLEncoder.encode((String) entry.getValue(), str));
                if (it.hasNext()) {
                    stringBuilder.append("&");
                }
            } catch (Throwable th) {
                m.d("mtopsdk.NetworkConverterUtils", "[createParamQueryStr]getQueryStr error ---" + th.toString());
            }
        }
        return stringBuilder.toString();
    }

    public static URL a(String str, Map map) {
        if (l.b(str)) {
            m.d("mtopsdk.NetworkConverterUtils", "[initUrl]  baseUrl is blank, initUrl error");
            return null;
        }
        URL url;
        try {
            StringBuilder stringBuilder = new StringBuilder(str);
            if (map != null) {
                String a = a(map, "utf-8");
                if (l.a(a) && str.indexOf("?") == -1) {
                    stringBuilder.append("?").append(a);
                }
            }
            url = new URL(stringBuilder.toString());
        } catch (Throwable e) {
            m.b("mtopsdk.NetworkConverterUtils", "[initUrl]initUrl new URL error", e);
            url = null;
        }
        return url;
    }
}
