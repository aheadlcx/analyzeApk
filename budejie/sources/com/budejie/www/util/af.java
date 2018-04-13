package com.budejie.www.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class af implements CookieStore {
    private final ConcurrentHashMap<String, Cookie> a = new ConcurrentHashMap();
    private final SharedPreferences b;

    public af(Context context) {
        int i = 0;
        this.b = context.getSharedPreferences("CookiePrefsFile", 0);
        String string = this.b.getString("names", null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                String string2 = this.b.getString("cookie_" + str, null);
                if (string2 != null) {
                    Cookie a = a(string2);
                    if (a != null) {
                        this.a.put(str, a);
                    }
                }
                i++;
            }
            clearExpired(new Date());
        }
    }

    public void addCookie(Cookie cookie) {
        String str = cookie.getName() + cookie.getDomain();
        if (cookie.isExpired(new Date())) {
            this.a.remove(str);
        } else {
            this.a.put(str, cookie);
        }
        Editor edit = this.b.edit();
        edit.putString("names", TextUtils.join(",", this.a.keySet()));
        edit.putString("cookie_" + str, a(new SerializableCookie(cookie)));
        edit.commit();
    }

    public void clear() {
        Editor edit = this.b.edit();
        for (String str : this.a.keySet()) {
            edit.remove("cookie_" + str);
        }
        edit.remove("names");
        edit.commit();
        this.a.clear();
    }

    public boolean clearExpired(Date date) {
        Editor edit = this.b.edit();
        boolean z = false;
        for (Entry entry : this.a.entrySet()) {
            boolean z2;
            String str = (String) entry.getKey();
            if (((Cookie) entry.getValue()).isExpired(date)) {
                this.a.remove(str);
                edit.remove("cookie_" + str);
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (z) {
            edit.putString("names", TextUtils.join(",", this.a.keySet()));
        }
        edit.commit();
        return z;
    }

    public List<Cookie> getCookies() {
        return new ArrayList(this.a.values());
    }

    protected String a(SerializableCookie serializableCookie) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableCookie);
            return a(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    protected Cookie a(String str) {
        try {
            return ((SerializableCookie) new ObjectInputStream(new ByteArrayInputStream(b(str))).readObject()).getCookie();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString().toUpperCase();
    }

    protected byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
