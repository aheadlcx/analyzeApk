package android.support.v4.media;

import android.os.Parcel;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import java.util.ArrayList;
import java.util.List;

class ac extends Result<List<MediaItem>> {
    final /* synthetic */ c a;
    final /* synthetic */ c b;

    ac(c cVar, Object obj, c cVar2) {
        this.b = cVar;
        this.a = cVar2;
        super(obj);
    }

    void a(List<MediaItem> list) {
        Object obj = null;
        if (list != null) {
            List arrayList = new ArrayList();
            for (MediaItem mediaItem : list) {
                Parcel obtain = Parcel.obtain();
                mediaItem.writeToParcel(obtain, 0);
                arrayList.add(obtain);
            }
            obj = arrayList;
        }
        this.a.sendResult(obj);
    }

    public void detach() {
        this.a.detach();
    }
}
