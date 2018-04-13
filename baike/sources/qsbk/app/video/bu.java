package qsbk.app.video;

import android.net.Uri;
import qsbk.app.core.AsyncTask;
import qsbk.app.video.VideoUploader.TokenResp;
import qsbk.app.video.VideoUploader.UploadVideoListener;
import qsbk.app.video.VideoUploader.VideoUploadTask;

class bu extends AsyncTask<Void, Void, TokenResp> {
    final /* synthetic */ UploadVideoListener a;
    final /* synthetic */ Uri b;
    final /* synthetic */ Object c;
    final /* synthetic */ TokenResp d;
    final /* synthetic */ VideoUploadTask e;
    final /* synthetic */ VideoUploader f;

    bu(VideoUploader videoUploader, UploadVideoListener uploadVideoListener, Uri uri, Object obj, TokenResp tokenResp, VideoUploadTask videoUploadTask) {
        this.f = videoUploader;
        this.a = uploadVideoListener;
        this.b = uri;
        this.c = obj;
        this.d = tokenResp;
        this.e = videoUploadTask;
    }

    protected void a() {
        this.a.onStart(this.b, this.c);
    }

    protected TokenResp a(Void... voidArr) {
        if (this.d != null) {
            return this.d;
        }
        TokenResp a = this.f.a();
        this.a.onTokenResp(a);
        return a;
    }

    protected void a(TokenResp tokenResp) {
        super.a(tokenResp);
        if (tokenResp == null) {
            this.a.onFaiure(this.b, "上传图片或视频失败，请重新投稿", this.c);
            return;
        }
        this.e.setUploadTaskExecutor(this.f.a(tokenResp, this.b, this.a, this.c));
    }
}
