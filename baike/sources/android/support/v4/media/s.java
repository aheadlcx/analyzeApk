package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.ConnectionCallback;
import android.media.browse.MediaBrowser.MediaItem;
import android.media.browse.MediaBrowser.SubscriptionCallback;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(21)
class s {

    interface a {
        void onConnected();

        void onConnectionFailed();

        void onConnectionSuspended();
    }

    interface d {
        void onChildrenLoaded(@NonNull String str, List<?> list);

        void onError(@NonNull String str);
    }

    static class b<T extends a> extends ConnectionCallback {
        protected final T a;

        public b(T t) {
            this.a = t;
        }

        public void onConnected() {
            this.a.onConnected();
        }

        public void onConnectionSuspended() {
            this.a.onConnectionSuspended();
        }

        public void onConnectionFailed() {
            this.a.onConnectionFailed();
        }
    }

    static class c {
        public static int getFlags(Object obj) {
            return ((MediaItem) obj).getFlags();
        }

        public static Object getDescription(Object obj) {
            return ((MediaItem) obj).getDescription();
        }
    }

    static class e<T extends d> extends SubscriptionCallback {
        protected final T a;

        public e(T t) {
            this.a = t;
        }

        public void onChildrenLoaded(@NonNull String str, List<MediaItem> list) {
            this.a.onChildrenLoaded(str, list);
        }

        public void onError(@NonNull String str) {
            this.a.onError(str);
        }
    }

    public static Object createConnectionCallback(a aVar) {
        return new b(aVar);
    }

    public static Object createBrowser(Context context, ComponentName componentName, Object obj, Bundle bundle) {
        return new MediaBrowser(context, componentName, (ConnectionCallback) obj, bundle);
    }

    public static void connect(Object obj) {
        ((MediaBrowser) obj).connect();
    }

    public static void disconnect(Object obj) {
        ((MediaBrowser) obj).disconnect();
    }

    public static boolean isConnected(Object obj) {
        return ((MediaBrowser) obj).isConnected();
    }

    public static ComponentName getServiceComponent(Object obj) {
        return ((MediaBrowser) obj).getServiceComponent();
    }

    public static String getRoot(Object obj) {
        return ((MediaBrowser) obj).getRoot();
    }

    public static Bundle getExtras(Object obj) {
        return ((MediaBrowser) obj).getExtras();
    }

    public static Object getSessionToken(Object obj) {
        return ((MediaBrowser) obj).getSessionToken();
    }

    public static Object createSubscriptionCallback(d dVar) {
        return new e(dVar);
    }

    public static void subscribe(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).subscribe(str, (SubscriptionCallback) obj2);
    }

    public static void unsubscribe(Object obj, String str) {
        ((MediaBrowser) obj).unsubscribe(str);
    }
}
