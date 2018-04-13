package qsbk.app.core.net.upload;

import com.qiniu.android.storage.UploadManager;

public class NormalUpload extends Upload {
    protected UploadManager a() {
        return new UploadManager();
    }
}
