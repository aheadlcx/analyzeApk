package qsbk.app.activity.publish;

import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;

class cg extends CallBack {
    final /* synthetic */ QiniuVideoUploader a;

    cg(QiniuVideoUploader qiniuVideoUploader) {
        this.a = qiniuVideoUploader;
    }

    public void onProcess(long j, long j2) {
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        if (this.a.a != null) {
            this.a.a.onUploadSuccess(this.a.c, uploadCallRet.getKey());
        }
    }

    public void onFailure(CallRet callRet) {
        if (this.a.a != null) {
            this.a.a.onUploadFail(this.a.c);
        }
    }
}
