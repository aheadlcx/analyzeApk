package qsbk.app.api;

import android.net.Uri;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.api.BigCoverHelper.UploadListener;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.DebugUtil;

class a extends CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ UploadListener b;
    final /* synthetic */ Uri c;
    final /* synthetic */ BigCoverHelper d;

    a(BigCoverHelper bigCoverHelper, String str, UploadListener uploadListener, Uri uri) {
        this.d = bigCoverHelper;
        this.a = str;
        this.b = uploadListener;
        this.c = uri;
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        String str;
        try {
            str = new JSONObject(new String(Base64.decode(this.a.split(Config.TRACE_TODAY_VISIT_SPLIT)[2], 10), "utf-8")).optString("scope").split(Config.TRACE_TODAY_VISIT_SPLIT)[0];
        } catch (Exception e) {
            Exception exception = e;
            str = "<bucketName>";
            exception.printStackTrace();
        }
        String key = uploadCallRet.getKey();
        str = "http://" + str + ".qiniudn.com/" + key;
        DebugUtil.debug(BigCoverHelper.a, "onSuccess  " + uploadCallRet.getKey() + " : " + str);
        Map hashMap = new HashMap();
        hashMap.put("name", key);
        new b(this, hashMap, str).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[0]);
    }

    public void onProcess(long j, long j2) {
        if (this.b != null) {
            this.b.onUploading(j, j2);
        }
        DebugUtil.debug(BigCoverHelper.a, "onProcess  current:" + j + "  total:" + j2);
    }

    public void onFailure(CallRet callRet) {
        DebugUtil.debug(BigCoverHelper.a, "onFailure  " + callRet.getResponse());
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
        if (this.b != null) {
            this.b.onFail(callRet.getStatusCode(), callRet.getResponse());
        }
    }
}
