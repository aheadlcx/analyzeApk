package qsbk.app.activity;

import android.net.Uri;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.api.BigCoverHelper.UploadListener;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;

class lk extends CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ Uri b;
    final /* synthetic */ UploadListener c;
    final /* synthetic */ FinishGroupActivity d;

    lk(FinishGroupActivity finishGroupActivity, String str, Uri uri, UploadListener uploadListener) {
        this.d = finishGroupActivity;
        this.a = str;
        this.b = uri;
        this.c = uploadListener;
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        String str;
        try {
            str = new JSONObject(new String(Base64.decode(this.a.split(Config.TRACE_TODAY_VISIT_SPLIT)[2], 10), "utf-8")).optString("scope").split(Config.TRACE_TODAY_VISIT_SPLIT)[0];
        } catch (Exception e) {
            String str2 = "<bucketName>";
            e.printStackTrace();
        }
        str = uploadCallRet.getKey();
        LogUtil.d(" return key =====" + str);
        this.d.n = str;
        this.d.k = Constants.URL_UPLOAD_IMAGE_OF_GROUP_INFO + str;
        this.d.a(this.b);
    }

    public void onProcess(long j, long j2) {
        if (this.c != null) {
            this.c.onUploading(j, j2);
        }
        DebugUtil.debug(this.d.a, "onProcess  current:" + j + "  total:" + j2);
    }

    public void onFailure(CallRet callRet) {
        DebugUtil.debug(this.d.a, "onFailure  " + callRet.getResponse());
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
        if (this.c != null) {
            this.c.onFail(callRet.getStatusCode(), callRet.getResponse());
        }
    }
}
