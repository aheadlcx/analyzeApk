package com.sina.weibo.sdk.net;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Set;

public class WeiboParameters {
    private LinkedHashMap<String, Object> a = new LinkedHashMap();
    private String b;
    private String c;

    public WeiboParameters(String str) {
        this.c = str;
    }

    public String getAppKey() {
        return this.c;
    }

    public LinkedHashMap<String, Object> getParams() {
        return this.a;
    }

    public void setParams(LinkedHashMap<String, Object> linkedHashMap) {
        this.a = linkedHashMap;
    }

    @Deprecated
    public void add(String str, String str2) {
        this.a.put(str, str2);
    }

    @Deprecated
    public void add(String str, int i) {
        this.a.put(str, String.valueOf(i));
    }

    @Deprecated
    public void add(String str, long j) {
        this.a.put(str, String.valueOf(j));
    }

    @Deprecated
    public void add(String str, Object obj) {
        this.a.put(str, obj.toString());
    }

    public void put(String str, String str2) {
        this.a.put(str, str2);
    }

    public void put(String str, int i) {
        this.a.put(str, String.valueOf(i));
    }

    public void put(String str, long j) {
        this.a.put(str, String.valueOf(j));
    }

    public void put(String str, Bitmap bitmap) {
        this.a.put(str, bitmap);
    }

    public void put(String str, Object obj) {
        this.a.put(str, obj.toString());
    }

    public Object get(String str) {
        return this.a.get(str);
    }

    public void remove(String str) {
        if (this.a.containsKey(str)) {
            this.a.remove(str);
            this.a.remove(this.a.get(str));
        }
    }

    public Set<String> keySet() {
        return this.a.keySet();
    }

    public boolean containsKey(String str) {
        return this.a.containsKey(str);
    }

    public boolean containsValue(String str) {
        return this.a.containsValue(str);
    }

    public int size() {
        return this.a.size();
    }

    public String encodeUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : this.a.keySet()) {
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append(a.b);
                obj2 = obj;
            }
            obj = this.a.get(str);
            if (obj instanceof String) {
                String str2 = (String) obj;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        stringBuilder.append(URLEncoder.encode(str, "UTF-8") + "=" + URLEncoder.encode(str2, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                LogUtil.i("encodeUrl", stringBuilder.toString());
            }
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public boolean hasBinaryData() {
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

    public void setAnonymousCookie(String str) {
        this.b = str;
    }

    public String getAnonymousCookie() {
        return this.b;
    }
}
