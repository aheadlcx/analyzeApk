package com.sina.weibo.sdk.net;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.sina.weibo.sdk.a.d;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Set;

public class e {
    private LinkedHashMap<String, Object> a = new LinkedHashMap();
    private String b;

    public e(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public void a(String str, String str2) {
        this.a.put(str, str2);
    }

    public Object a(String str) {
        return this.a.get(str);
    }

    public Set<String> b() {
        return this.a.keySet();
    }

    public String c() {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : this.a.keySet()) {
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append("&");
                obj2 = obj;
            }
            obj = this.a.get(str);
            if (obj instanceof String) {
                String str2 = (String) obj;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        stringBuilder.append(URLEncoder.encode(str, "UTF-8") + LoginConstants.EQUAL + URLEncoder.encode(str2, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                d.b("encodeUrl", stringBuilder.toString());
            }
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public boolean d() {
        for (String str : this.a.keySet()) {
            Object obj = this.a.get(str);
            if (!(obj instanceof ByteArrayOutputStream)) {
                if (obj instanceof Bitmap) {
                }
            }
            return true;
        }
        return false;
    }
}
