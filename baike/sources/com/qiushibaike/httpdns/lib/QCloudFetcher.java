package com.qiushibaike.httpdns.lib;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.io.IOException;
import java.util.Random;

public class QCloudFetcher extends Fetch {
    public String getIpByDomain(String str) throws IOException {
        Object a = Http.a(String.format("http://119.29.29.29/d?dn=%s", new Object[]{str}));
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        String[] split = a.split(h.b);
        return split[new Random(System.currentTimeMillis()).nextInt(split.length)];
    }
}
