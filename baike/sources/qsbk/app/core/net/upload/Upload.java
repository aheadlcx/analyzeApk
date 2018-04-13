package qsbk.app.core.net.upload;

import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.utils.LogUtils;

public abstract class Upload {
    private static final String a = Upload.class.getSimpleName();
    private UploadManager b = a();
    private IUploadListener c;

    protected abstract UploadManager a();

    public void addUploadListener(IUploadListener iUploadListener) {
        this.c = iUploadListener;
    }

    public void removeUploadListener() {
        this.c = null;
    }

    public void uploadFile(String str, String str2, String str3, Map<String, String> map) {
        Map hashMap;
        if (map != null) {
            hashMap = new HashMap();
            for (String str4 : map.keySet()) {
                hashMap.put("x:" + str4, map.get(str4));
            }
        } else {
            hashMap = null;
        }
        LogUtils.d(a, "filePath:" + str + "  params:" + (map == null ? "null" : map.toString()));
        this.b.put(str, str2, str3, (UpCompletionHandler) new a(this, str), new UploadOptions(hashMap, null, false, new b(this, str), new c(this)));
    }
}
