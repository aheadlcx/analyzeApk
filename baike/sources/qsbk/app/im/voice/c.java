package qsbk.app.im.voice;

import android.util.Base64;
import com.baidu.mobstat.Config;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import java.io.File;
import org.json.JSONObject;
import qsbk.app.im.image.ImageUploader;
import qsbk.app.im.voice.VoiceManager.VoiceCallback;
import qsbk.app.utils.LogUtil;

class c extends CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ VoiceCallback c;
    final /* synthetic */ Object d;
    final /* synthetic */ VoiceManager e;

    c(VoiceManager voiceManager, String str, String str2, VoiceCallback voiceCallback, Object obj) {
        this.e = voiceManager;
        this.a = str;
        this.b = str2;
        this.c = voiceCallback;
        this.d = obj;
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
        this.c.onSuccess(this.a, ImageUploader.img_prefix + uploadCallRet.getKey(), this.d);
        a();
    }

    public void onProcess(long j, long j2) {
        this.c.onProgress(this.a, j, j2, this.d);
    }

    public void onFailure(CallRet callRet) {
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
        this.c.onFaiure(this.a, callRet.getResponse(), this.d);
        a();
    }
}
