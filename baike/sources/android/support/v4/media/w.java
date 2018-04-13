package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import android.support.v4.os.ResultReceiver;

class w extends Result<MediaItem> {
    final /* synthetic */ ResultReceiver a;
    final /* synthetic */ MediaBrowserServiceCompat b;

    w(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
        this.b = mediaBrowserServiceCompat;
        this.a = resultReceiver;
        super(obj);
    }

    void a(MediaItem mediaItem) {
        if ((b() & 2) != 0) {
            this.a.send(-1, null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM, mediaItem);
        this.a.send(0, bundle);
    }
}
