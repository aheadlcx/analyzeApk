package com.qiushibaike.httpdns.lib;

public interface FetchResultListener {
    void onFailure(String str, Exception exception);

    void onSuccess(DomainRecord domainRecord);
}
