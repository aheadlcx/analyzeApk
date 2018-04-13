package com.alibaba.sdk.android.mns.model.result;

import com.alibaba.sdk.android.mns.model.MNSResult;
import com.alibaba.sdk.android.mns.model.PagingListResult;

public class ListQueueResult extends MNSResult {
    private PagingListResult<String> queueLists;

    public void setQueueLists(PagingListResult<String> pagingListResult) {
        this.queueLists = pagingListResult;
    }

    public PagingListResult<String> getQueueLists() {
        return this.queueLists;
    }
}
