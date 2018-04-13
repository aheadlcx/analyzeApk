package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.thirdparty.bi;
import com.iflytek.cloud.thirdparty.bk;
import com.iflytek.cloud.thirdparty.cb;

public class DataUploader extends bi {
    public DataUploader(Context context) {
        super(context);
    }

    protected boolean b_() {
        return true;
    }

    public int uploadData(SpeechListener speechListener, String str, byte[] bArr) {
        try {
            this.e = new bk(this.a, this.c, a("upload"));
            ((bk) this.e).a(new a(this, speechListener), str, bArr);
            return 0;
        } catch (Throwable e) {
            Throwable th = e;
            int errorCode = th.getErrorCode();
            cb.a(th);
            return errorCode;
        } catch (Throwable e2) {
            cb.a(e2);
            return 20999;
        }
    }
}
