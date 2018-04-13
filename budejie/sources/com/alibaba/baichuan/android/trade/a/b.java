package com.alibaba.baichuan.android.trade.a;

import com.ali.auth.third.mtop.rpc.ResultActionType;
import com.alibaba.baichuan.android.trade.adapter.mtop.NetworkResponse;

public class b extends c {
    public b() {
        this.a = "mtop.alibaba.baichuan.nbsdk.sclick.create";
    }

    public static String a(NetworkResponse networkResponse) {
        return (networkResponse.data != null && networkResponse.isSuccess && networkResponse.errorCode.equals(ResultActionType.SUCCESS)) ? networkResponse.data.get("data").toString() : null;
    }
}
