package com.alibaba.sdk.android.httpdns;

import java.util.ArrayList;

public interface HttpDnsService {
    String getIpByHostAsync(String str);

    String[] getIpsByHostAsync(String str);

    void setDegradationFilter(DegradationFilter degradationFilter);

    void setExpiredIPEnabled(boolean z);

    void setHTTPSRequestEnabled(boolean z);

    void setLogEnabled(boolean z);

    void setPreResolveAfterNetworkChanged(boolean z);

    void setPreResolveHosts(ArrayList arrayList);

    void setTimeoutInterval(int i);
}
