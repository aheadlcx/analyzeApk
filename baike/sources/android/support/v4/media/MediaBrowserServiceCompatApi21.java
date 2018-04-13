package android.support.v4.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.BrowserRoot;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
class MediaBrowserServiceCompatApi21 {

    public interface ServiceCompatProxy {
        a onGetRoot(String str, int i, Bundle bundle);

        void onLoadChildren(String str, c<List<Parcel>> cVar);
    }

    static class a {
        final String a;
        final Bundle b;

        a(String str, Bundle bundle) {
            this.a = str;
            this.b = bundle;
        }
    }

    static class b extends MediaBrowserService {
        final ServiceCompatProxy a;

        b(Context context, ServiceCompatProxy serviceCompatProxy) {
            attachBaseContext(context);
            this.a = serviceCompatProxy;
        }

        public BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
            a onGetRoot = this.a.onGetRoot(str, i, bundle == null ? null : new Bundle(bundle));
            if (onGetRoot == null) {
                return null;
            }
            return new BrowserRoot(onGetRoot.a, onGetRoot.b);
        }

        public void onLoadChildren(String str, Result<List<MediaItem>> result) {
            this.a.onLoadChildren(str, new c(result));
        }
    }

    static class c<T> {
        Result a;

        c(Result result) {
            this.a = result;
        }

        public void sendResult(T t) {
            if (t instanceof List) {
                this.a.sendResult(a((List) t));
            } else if (t instanceof Parcel) {
                Parcel parcel = (Parcel) t;
                parcel.setDataPosition(0);
                this.a.sendResult(MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            } else {
                this.a.sendResult(null);
            }
        }

        public void detach() {
            this.a.detach();
        }

        List<MediaItem> a(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            List<MediaItem> arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }
    }

    public static Object createService(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new b(context, serviceCompatProxy);
    }

    public static void onCreate(Object obj) {
        ((MediaBrowserService) obj).onCreate();
    }

    public static IBinder onBind(Object obj, Intent intent) {
        return ((MediaBrowserService) obj).onBind(intent);
    }

    public static void setSessionToken(Object obj, Object obj2) {
        ((MediaBrowserService) obj).setSessionToken((Token) obj2);
    }

    public static void notifyChildrenChanged(Object obj, String str) {
        ((MediaBrowserService) obj).notifyChildrenChanged(str);
    }
}
