package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public class DefaultRpcClient extends RpcClient {
    private Context a;

    public DefaultRpcClient(Context context) {
        this.a = context;
    }

    private Config a(RpcParams rpcParams) {
        return new f(this, rpcParams);
    }

    public <T> T getRpcProxy(Class<T> cls, RpcParams rpcParams) {
        return new RpcFactory(a(rpcParams)).getRpcProxy(cls);
    }
}
