package com.qiniu.rs;

import com.qiniu.utils.UploadTask;

public class UploadTaskExecutor {
    private volatile UploadTask a;

    public UploadTaskExecutor(UploadTask uploadTask) {
        this.a = uploadTask;
    }

    public void setTask(UploadTask uploadTask) {
        this.a = uploadTask;
    }

    public CallRet get() {
        if (this.a != null) {
            try {
                return (CallRet) this.a.get();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public boolean isUpCancelled() {
        return this.a != null && this.a.isUpCancelled();
    }

    public void cancel() {
        if (this.a != null) {
            try {
                this.a.cancel();
            } catch (Exception e) {
            }
        }
    }
}
