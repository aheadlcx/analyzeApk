package com.qiniu.android.common;

import com.qiniu.android.common.Zone.QueryHandler;

public final class FixedZone extends Zone {
    private final ServiceAddress a;
    private final ServiceAddress b;

    public FixedZone(ServiceAddress serviceAddress, ServiceAddress serviceAddress2) {
        this.a = serviceAddress;
        this.b = serviceAddress2;
    }

    public ServiceAddress upHost(String str) {
        return this.a;
    }

    public ServiceAddress upHostBackup(String str) {
        return this.b;
    }

    public void preQuery(String str, QueryHandler queryHandler) {
        queryHandler.onSuccess();
    }
}
