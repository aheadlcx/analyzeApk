package com.tencent.connect.share;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import com.tencent.open.a.f;
import com.tencent.tauth.IUiListener;
import java.io.File;

class d implements OnPreparedListener {
    final /* synthetic */ String a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ Activity c;
    final /* synthetic */ IUiListener d;
    final /* synthetic */ QzonePublish e;

    d(QzonePublish qzonePublish, String str, Bundle bundle, Activity activity, IUiListener iUiListener) {
        this.e = qzonePublish;
        this.a = str;
        this.b = bundle;
        this.c = activity;
        this.d = iUiListener;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        long length = new File(this.a).length();
        int duration = mediaPlayer.getDuration();
        this.b.putString(QzonePublish.PUBLISH_TO_QZONE_VIDEO_PATH, this.a);
        this.b.putInt(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, duration);
        this.b.putLong(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE, length);
        this.e.b(this.c, this.b, this.d);
        f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
    }
}
