package com.alibaba.baichuan.android.auth.a;

import com.alibaba.baichuan.android.trade.a.c;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkClient.NetworkRequestListener;
import com.alibaba.baichuan.android.trade.utils.json.JSONUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class a extends c {
    public a() {
        this.a = "mtop.alibaba.baichuan.auth.token.get";
        this.c = true;
        this.g = 90000;
    }

    private HashMap a(Set set, String str) {
        Object obj;
        HashMap hashMap = new HashMap();
        hashMap.put("hintArray", set == null ? "[]" : JSONUtils.toJsonString(new ArrayList(set)));
        String str2 = "oldToken";
        if (str == null) {
            obj = "";
        }
        hashMap.put(str2, obj);
        return hashMap;
    }

    public boolean a(Set set, String str, NetworkRequestListener networkRequestListener) {
        return a(a(set, str), networkRequestListener);
    }
}
