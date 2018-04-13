package com.huawei.hms.support.api.a;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.a;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.ConnectResp;

final class b extends a<ResolveResult<ConnectResp>, ConnectResp> {
    b(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        super(apiClient, str, iMessageEntity);
    }

    public /* synthetic */ Result onComplete(IMessageEntity iMessageEntity) {
        return a((ConnectResp) iMessageEntity);
    }

    public ResolveResult<ConnectResp> a(ConnectResp connectResp) {
        ResolveResult<ConnectResp> resolveResult = new ResolveResult(connectResp);
        resolveResult.setStatus(Status.SUCCESS);
        return resolveResult;
    }

    protected boolean checkApiClient(ApiClient apiClient) {
        return apiClient != null;
    }
}
