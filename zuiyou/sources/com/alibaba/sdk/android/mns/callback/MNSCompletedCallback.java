package com.alibaba.sdk.android.mns.callback;

import com.alibaba.sdk.android.common.ClientException;
import com.alibaba.sdk.android.common.ServiceException;
import com.alibaba.sdk.android.mns.model.MNSRequest;
import com.alibaba.sdk.android.mns.model.MNSResult;

public interface MNSCompletedCallback<T1 extends MNSRequest, T2 extends MNSResult> {
    void onFailure(T1 t1, ClientException clientException, ServiceException serviceException);

    void onSuccess(T1 t1, T2 t2);
}
