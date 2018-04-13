package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.ItemCallback;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class t {

    interface a {
        void onError(@NonNull String str);

        void onItemLoaded(Parcel parcel);
    }

    static class b<T extends a> extends ItemCallback {
        protected final T a;

        public b(T t) {
            this.a = t;
        }

        public void onItemLoaded(MediaItem mediaItem) {
            if (mediaItem == null) {
                this.a.onItemLoaded(null);
                return;
            }
            Parcel obtain = Parcel.obtain();
            mediaItem.writeToParcel(obtain, 0);
            this.a.onItemLoaded(obtain);
        }

        public void onError(@NonNull String str) {
            this.a.onError(str);
        }
    }

    public static Object createItemCallback(a aVar) {
        return new b(aVar);
    }

    public static void getItem(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).getItem(str, (ItemCallback) obj2);
    }
}
