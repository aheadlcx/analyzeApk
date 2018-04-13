package com.alipay.android.phone.mrpc.core;

import android.content.Context;

class f implements Config {
    final /* synthetic */ RpcParams a;
    final /* synthetic */ DefaultRpcClient b;

    f(DefaultRpcClient defaultRpcClient, RpcParams rpcParams) {
        this.b = defaultRpcClient;
        this.a = rpcParams;
    }

    public Context getApplicationContext() {
        return this.b.a.getApplicationContext();
    }

    public RpcParams getRpcParams() {
        return this.a;
    }

    public Transport getTransport() {
        return HttpManager.getInstance(getApplicationContext());
    }

    public String getUrl() {
        return this.a.getGwUrl();
    }

    public boolean isGzip() {
        return this.a.isGzip();
    }
}
