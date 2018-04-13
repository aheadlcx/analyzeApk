package com.qiushibaike.httpdns.lib;

import android.text.TextUtils;
import java.io.IOException;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public class AliFetch extends Fetch {
    private static final String[] a = new String[]{"http://203.107.1.34/119712/d?host=%s", "http://203.107.1.1/119712/d?host=%s"};

    public String getIpByDomain(String str) throws IOException {
        String str2 = null;
        Object a = Http.a(String.format(a[new Random().nextInt(a.length)], new Object[]{str}));
        if (!TextUtils.isEmpty(a)) {
            try {
                JSONArray optJSONArray = new JSONObject(a).optJSONArray("ips");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    str2 = optJSONArray.getString(0);
                }
            } catch (Throwable e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        return str2;
    }
}
