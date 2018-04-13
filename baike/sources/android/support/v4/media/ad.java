package android.support.v4.media;

import android.os.Parcel;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.Result;

class ad extends Result<MediaItem> {
    final /* synthetic */ c a;
    final /* synthetic */ d b;

    ad(d dVar, Object obj, c cVar) {
        this.b = dVar;
        this.a = cVar;
        super(obj);
    }

    void a(MediaItem mediaItem) {
        if (mediaItem == null) {
            this.a.sendResult(null);
            return;
        }
        Parcel obtain = Parcel.obtain();
        mediaItem.writeToParcel(obtain, 0);
        this.a.sendResult(obtain);
    }

    public void detach() {
        this.a.detach();
    }
}
