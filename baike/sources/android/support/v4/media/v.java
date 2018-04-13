package android.support.v4.media;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import android.util.Log;
import java.util.List;

class v extends Result<List<MediaItem>> {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ MediaBrowserServiceCompat d;

    v(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, a aVar, String str, Bundle bundle) {
        this.d = mediaBrowserServiceCompat;
        this.a = aVar;
        this.b = str;
        this.c = bundle;
        super(obj);
    }

    void a(List<MediaItem> list) {
        if (this.d.b.get(this.a.c.asBinder()) == this.a) {
            if ((b() & 1) != 0) {
                list = this.d.a((List) list, this.c);
            }
            try {
                this.a.c.onLoadChildren(this.b, list, this.c);
            } catch (RemoteException e) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.b + " package=" + this.a.a);
            }
        } else if (MediaBrowserServiceCompat.a) {
            Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.a.a + " id=" + this.b);
        }
    }
}
