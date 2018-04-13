package com.umeng.commonsdk.framework;

import org.json.JSONObject;

public interface UMLogDataProtocol {

    public enum UMBusinessType {
        U_APP,
        U_DPLUS,
        U_INTERNAL
    }

    void removeCacheData(Object obj);

    JSONObject setupReportData(long j);

    void workEvent(Object obj, int i);
}
