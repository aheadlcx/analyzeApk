package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public enum a {
    a(IXAdSystemUtils.NT_NONE),
    WapPay("js://wappay"),
    Update("js://update");
    
    private String d;

    private a(String str) {
        this.d = str;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return a;
        }
        a aVar = a;
        for (a aVar2 : values()) {
            if (str.startsWith(aVar2.d)) {
                return aVar2;
            }
        }
        return aVar;
    }
}
