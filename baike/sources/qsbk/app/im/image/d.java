package qsbk.app.im.image;

import android.net.Uri;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import java.io.File;
import org.json.JSONObject;
import qsbk.app.im.image.ImageUploader.UploadImageCallback;
import qsbk.app.utils.LogUtil;

class d extends CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ UploadImageCallback c;
    final /* synthetic */ Uri d;
    final /* synthetic */ Object e;
    final /* synthetic */ ImageUploader f;

    d(ImageUploader imageUploader, String str, String str2, UploadImageCallback uploadImageCallback, Uri uri, Object obj) {
        this.f = imageUploader;
        this.a = str;
        this.b = str2;
        this.c = uploadImageCallback;
        this.d = uri;
        this.e = obj;
    }

    private void a() {
        File file = new File(this.a);
        try {
            file.delete();
            LogUtil.d("delete file success:" + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        try {
            String str = new JSONObject(new String(Base64.decode(this.b.split(Config.TRACE_TODAY_VISIT_SPLIT)[2], 10), "utf-8")).optString("scope").split(Config.TRACE_TODAY_VISIT_SPLIT)[0];
        } catch (Exception e) {
            String str2 = "<bucketName>";
            e.printStackTrace();
        }
        this.c.onSuccess(this.d, ImageUploader.img_prefix + uploadCallRet.getKey(), this.e);
        a();
    }

    public void onProcess(long j, long j2) {
        this.c.onProgress(this.d, j, j2, this.e);
    }

    public void onFailure(CallRet callRet) {
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
        this.c.onFaiure(this.d, callRet.getResponse(), this.e);
        a();
    }
}
