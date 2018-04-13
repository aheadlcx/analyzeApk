package com.qiniu.rs;

import com.qiniu.resumableio.SliceUploadTask.Block;

public abstract class CallBack {
    public abstract void onFailure(CallRet callRet);

    public abstract void onProcess(long j, long j2);

    public abstract void onSuccess(UploadCallRet uploadCallRet);

    public void onBlockSuccess(Block block) {
    }
}
