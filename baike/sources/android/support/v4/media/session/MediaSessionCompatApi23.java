package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaSessionCompatApi23 {

    public interface Callback extends a {
        void onPlayFromUri(Uri uri, Bundle bundle);
    }

    static class a<T extends Callback> extends b<T> {
        public a(T t) {
            super(t);
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
            ((Callback) this.a).onPlayFromUri(uri, bundle);
        }
    }

    public static Object createCallback(Callback callback) {
        return new a(callback);
    }
}
