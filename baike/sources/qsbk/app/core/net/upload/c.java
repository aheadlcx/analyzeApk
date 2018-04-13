package qsbk.app.core.net.upload;

import com.qiniu.android.storage.UpCancellationSignal;
import qsbk.app.core.utils.LogUtils;

class c implements UpCancellationSignal {
    final /* synthetic */ Upload a;

    c(Upload upload) {
        this.a = upload;
    }

    public boolean isCancelled() {
        LogUtils.d(Upload.a, "isCancelled()");
        return this.a.c == null;
    }
}
