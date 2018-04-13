package qsbk.app.video;

import java.io.File;
import qsbk.app.utils.UpdateHelper.DownloadListener;

class bg implements DownloadListener {
    final /* synthetic */ File a;
    final /* synthetic */ VideoPlayerView b;

    bg(VideoPlayerView videoPlayerView, File file) {
        this.b = videoPlayerView;
        this.a = file;
    }

    public void onStart(String str) {
    }

    public void onProgress(String str, long j, long j2) {
    }

    public void onFinished(String str) {
        this.b.post(new bh(this));
    }

    public void onFailure(String str, Throwable th) {
        this.b.post(new bi(this));
    }
}
