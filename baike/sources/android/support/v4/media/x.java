package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import android.support.v4.os.ResultReceiver;
import java.util.List;

class x extends Result<List<MediaItem>> {
    final /* synthetic */ ResultReceiver a;
    final /* synthetic */ MediaBrowserServiceCompat b;

    x(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
        this.b = mediaBrowserServiceCompat;
        this.a = resultReceiver;
        super(obj);
    }

    void a(List<MediaItem> list) {
        if ((b() & 4) != 0 || list == null) {
            this.a.send(-1, null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS, (Parcelable[]) list.toArray(new MediaItem[0]));
        this.a.send(0, bundle);
    }
}
