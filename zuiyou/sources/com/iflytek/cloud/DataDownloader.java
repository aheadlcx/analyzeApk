package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.thirdparty.bi;
import com.iflytek.cloud.thirdparty.bk;
import com.iflytek.cloud.thirdparty.cb;

public class DataDownloader extends bi {
    public DataDownloader(Context context) {
        super(context);
    }

    protected boolean b_() {
        return true;
    }

    public int downloadData(SpeechListener speechListener) {
        try {
            this.e = new bk(this.a, this.c, a("download"));
            ((bk) this.e).a(new a(this, speechListener));
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
