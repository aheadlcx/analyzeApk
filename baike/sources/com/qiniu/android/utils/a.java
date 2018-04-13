package com.qiniu.android.utils;

import com.qiniu.android.utils.StringMap.Consumer;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class a implements Consumer {
    final /* synthetic */ StringBuilder a;
    final /* synthetic */ StringMap b;
    private boolean c = false;

    a(StringMap stringMap, StringBuilder stringBuilder) {
        this.b = stringMap;
        this.a = stringBuilder;
    }

    public void accept(String str, Object obj) {
        if (this.c) {
            this.a.append(com.alipay.sdk.sys.a.b);
        }
        try {
            this.a.append(URLEncoder.encode(str, "UTF-8")).append('=').append(URLEncoder.encode(obj.toString(), "UTF-8"));
            this.c = true;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
