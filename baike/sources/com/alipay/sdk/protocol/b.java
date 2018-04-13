package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class b {
    public a a;
    public String[] b;
    private String c;

    private b(String str, a aVar) {
        this.c = str;
        this.a = aVar;
    }

    public static List<b> a(JSONObject jSONObject) {
        List<b> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        Object optString = jSONObject.optString("name", "");
        String[] strArr = null;
        if (!TextUtils.isEmpty(optString)) {
            strArr = optString.split(h.b);
        }
        for (int i = 0; i < strArr.length; i++) {
            a a = a.a(strArr[i]);
            if (a != a.a) {
                b bVar = new b(strArr[i], a);
                bVar.b = a(strArr[i]);
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private static String[] a(String str) {
        List arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        String[] split = str.substring(indexOf + 1, lastIndexOf).split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        if (split != null) {
            for (indexOf = 0; indexOf < split.length; indexOf++) {
                if (!TextUtils.isEmpty(split[indexOf])) {
                    arrayList.add(split[indexOf].trim().replaceAll("'", "").replaceAll("\"", ""));
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }
}
