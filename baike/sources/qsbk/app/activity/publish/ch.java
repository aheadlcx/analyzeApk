package qsbk.app.activity.publish;

import android.net.Uri;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;
import qsbk.app.video.VideoUploader.TokenResp;
import qsbk.app.video.VideoUploader.UploadVideoListener;

class ch implements UploadVideoListener {
    final /* synthetic */ QiushiPublishTask a;

    ch(QiushiPublishTask qiushiPublishTask) {
        this.a = qiushiPublishTask;
    }

    public void onStart(Uri uri, Object obj) {
        LogUtil.d("start upload video");
        PublishActivity.isPublishingArticle.put(this.a.a.uuid, Boolean.valueOf(true));
    }

    public void onSuccess(Uri uri, String str, Object obj) {
        this.a.a(str, null);
        if (PublishActivity.mIsFromLocal) {
            StatService.onEvent(QsbkApp.getInstance(), "local_video_upload_sucess", "sucess");
            PublishActivity.mIsFromLocal = false;
        } else if (this.a.a.mIsVideoFacingFront) {
            StatService.onEvent(QsbkApp.getInstance(), "record_front_video_upload_sucess", "sucess");
        } else {
            StatService.onEvent(QsbkApp.getInstance(), "record_back_video_upload_sucess", "sucess");
        }
    }

    public void onFaiure(Uri uri, String str, Object obj) {
        LogUtil.d("upload video onFaiure:" + str + " extra:" + obj.toString());
        StatService.onEvent(QsbkApp.getInstance(), "video_upload_failed", str);
        this.a.a(-1);
    }

    public void onProgress(Uri uri, long j, long j2, Object obj) {
    }

    public void onTokenResp(TokenResp tokenResp) {
        this.a.e = tokenResp;
    }
}
