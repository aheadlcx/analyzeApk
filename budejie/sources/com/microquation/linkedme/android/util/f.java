package com.microquation.linkedme.android.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONObject;

public class f {
    private static ThreadLocal<MessageDigest> a = new ThreadLocal<MessageDigest>() {
        protected MessageDigest a() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                return null;
            }
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }
    };

    @NonNull
    public static String a(String str) {
        return b(str);
    }

    @NonNull
    public static String a(JSONObject jSONObject, String str) {
        Iterable arrayList = new ArrayList();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            arrayList.add(jSONObject.opt((String) keys.next()).toString());
        }
        Collections.sort(arrayList);
        return b(TextUtils.join("&", arrayList) + str);
    }

    @NonNull
    private static String a(byte[] bArr) {
        if (bArr.length == 0) {
            return "";
        }
        MessageDigest messageDigest = (MessageDigest) a.get();
        messageDigest.reset();
        messageDigest.update(bArr);
        return b(messageDigest.digest());
    }

    @NonNull
    private static String b(String str) {
        return a(str.getBytes());
    }

    @NonNull
    private static String b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length + bArr.length);
        for (byte b : bArr) {
            if ((b & 255) < 16) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Long.toString((long) (b & 255), 16));
        }
        return stringBuilder.toString();
    }
}
