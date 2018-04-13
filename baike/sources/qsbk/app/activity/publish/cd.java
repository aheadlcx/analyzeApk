package qsbk.app.activity.publish;

import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;

class cd extends CallBack {
    final /* synthetic */ cc a;

    cd(cc ccVar) {
        this.a = ccVar;
    }

    public void onProcess(long j, long j2) {
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        if (this.a.b.a != null) {
            this.a.b.a.onUploadSuccess(this.a.b.c, uploadCallRet.getKey());
        }
    }

    public void onFailure(CallRet callRet) {
        if (this.a.b.a != null) {
            this.a.b.a.onUploadFail(this.a.b.c);
        }
    }
}
