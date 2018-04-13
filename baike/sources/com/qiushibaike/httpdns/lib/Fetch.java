package com.qiushibaike.httpdns.lib;

import java.io.IOException;

public abstract class Fetch {
    private FetchResultListener a;

    public abstract String getIpByDomain(String str) throws IOException;

    public FetchResultListener getResultListener() {
        return this.a;
    }

    public void setResultListener(FetchResultListener fetchResultListener) {
        this.a = fetchResultListener;
    }
}
