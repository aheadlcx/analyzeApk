package qsbk.app.core.net.upload;

import com.qiniu.android.storage.UpProgressHandler;
import qsbk.app.core.utils.LogUtils;

class b implements UpProgressHandler {
    final /* synthetic */ String a;
    final /* synthetic */ Upload b;

    b(Upload upload, String str) {
        this.b = upload;
        this.a = str;
    }

    public void progress(String str, double d) {
        LogUtils.d(Upload.a, "percent:" + d);
        if (this.b.c != null) {
            this.b.c.uploadProgress(this.a, d);
        }
    }
}
