package android.support.v4.media;

import android.os.Parcel;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import java.util.ArrayList;
import java.util.List;

class ae extends Result<List<MediaItem>> {
    final /* synthetic */ b a;
    final /* synthetic */ e b;

    ae(e eVar, Object obj, b bVar) {
        this.b = eVar;
        this.a = bVar;
        super(obj);
    }

    void a(List<MediaItem> list) {
        List list2 = null;
        if (list != null) {
            List arrayList = new ArrayList();
            for (MediaItem mediaItem : list) {
                Parcel obtain = Parcel.obtain();
                mediaItem.writeToParcel(obtain, 0);
                arrayList.add(obtain);
            }
            list2 = arrayList;
        }
        this.a.sendResult(list2, b());
    }

    public void detach() {
        this.a.detach();
    }
}
