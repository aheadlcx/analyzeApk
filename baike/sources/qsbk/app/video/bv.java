package qsbk.app.video;

import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.UploadCallRet;
import com.umeng.analytics.pro.b;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.LogUtil;
import qsbk.app.video.VideoUploader.TokenResp;
import qsbk.app.video.VideoUploader.UploadVideoListener;

class bv extends CallBack {
    final /* synthetic */ TokenResp a;
    final /* synthetic */ UploadVideoListener b;
    final /* synthetic */ Uri c;
    final /* synthetic */ Object d;
    final /* synthetic */ VideoUploader e;

    bv(VideoUploader videoUploader, TokenResp tokenResp, UploadVideoListener uploadVideoListener, Uri uri, Object obj) {
        this.e = videoUploader;
        this.a = tokenResp;
        this.b = uploadVideoListener;
        this.c = uri;
        this.d = obj;
    }

    public void onSuccess(UploadCallRet uploadCallRet) {
        try {
            LogUtil.d("bucketName:" + new JSONObject(new String(Base64.decode(this.a.token.split(Config.TRACE_TODAY_VISIT_SPLIT)[2], 10), "utf-8")).optString("scope").split(Config.TRACE_TODAY_VISIT_SPLIT)[0]);
        } catch (Exception e) {
            String str = "<bucketName>";
            e.printStackTrace();
        }
        String str2 = "http://qiubai-video.qiushibaike.com/" + uploadCallRet.getKey();
        LogUtil.d("video upload resp:" + uploadCallRet.getResponse());
        try {
            if (new JSONObject(uploadCallRet.getResponse()).getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                this.b.onSuccess(this.c, str2, this.d);
                return;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            str2 = new JSONObject(uploadCallRet.getResponse()).optString(b.J);
            if (TextUtils.isEmpty(str2)) {
                str2 = "上传图片或视频失败，请重试";
            }
            this.b.onFaiure(this.c, str2, this.d);
        } catch (JSONException e22) {
            this.b.onFaiure(this.c, "上传图片或视频失败，请重试", this.d);
            e22.printStackTrace();
        }
    }

    public void onProcess(long j, long j2) {
        this.b.onProgress(this.c, j, j2, this.d);
    }

    public void onFailure(CallRet callRet) {
        if (callRet.getException() != null) {
            callRet.getException().printStackTrace();
        }
        try {
            String optString = new JSONObject(callRet.getResponse()).optString(b.J);
            if (TextUtils.isEmpty(optString)) {
                optString = "上传图片或视频失败，请重试";
            }
            this.b.onFaiure(this.c, optString, this.d);
        } catch (JSONException e) {
            this.b.onFaiure(this.c, "上传图片或视频失败，请重试", this.d);
            e.printStackTrace();
        }
    }
}
