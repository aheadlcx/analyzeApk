package qsbk.app.activity.publish;

import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;

class cf extends CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ ce d;

    cf(ce ceVar, String str, int i, int i2) {
        this.d = ceVar;
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public void onProcess(long j, long j2) {
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        if (this.d.d.a != null) {
            this.d.d.a.onUploadSuccess(this.d.d.c, uploadCallRet.getKey(), this.a, this.b, this.c);
        }
    }

    public void onFailure(CallRet callRet) {
        if (this.d.d.a != null) {
            this.d.d.a.onUploadFail(this.d.d.c, this.a);
        }
    }
}
