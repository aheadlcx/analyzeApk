package com.tencent.connect.share;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

class e implements OnErrorListener {
    final /* synthetic */ IUiListener a;
    final /* synthetic */ QzonePublish b;

    e(QzonePublish qzonePublish, IUiListener iUiListener) {
        this.b = qzonePublish;
        this.a = iUiListener;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        f.e("openSDK_LOG.QzonePublish", "publishToQzone() mediaplayer onError()");
        this.a.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
        return false;
    }
}
