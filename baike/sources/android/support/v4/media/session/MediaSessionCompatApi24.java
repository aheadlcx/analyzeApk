package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

@RequiresApi(24)
class MediaSessionCompatApi24 {

    public interface Callback extends android.support.v4.media.session.MediaSessionCompatApi23.Callback {
        void onPrepare();

        void onPrepareFromMediaId(String str, Bundle bundle);

        void onPrepareFromSearch(String str, Bundle bundle);

        void onPrepareFromUri(Uri uri, Bundle bundle);
    }

    static class a<T extends Callback> extends a<T> {
        public a(T t) {
            super(t);
        }

        public void onPrepare() {
            ((Callback) this.a).onPrepare();
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
            ((Callback) this.a).onPrepareFromMediaId(str, bundle);
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
            ((Callback) this.a).onPrepareFromSearch(str, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
            ((Callback) this.a).onPrepareFromUri(uri, bundle);
        }
    }

    public static Object createCallback(Callback callback) {
        return new a(callback);
    }

    public static String getCallingPackage(Object obj) {
        Throwable e;
        MediaSession mediaSession = (MediaSession) obj;
        try {
            return (String) mediaSession.getClass().getMethod("getCallingPackage", new Class[0]).invoke(mediaSession, new Object[0]);
        } catch (NoSuchMethodException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        } catch (IllegalAccessException e4) {
            e = e4;
        }
        Log.e("MediaSessionCompatApi24", "Cannot execute MediaSession.getCallingPackage()", e);
        return null;
    }
}
