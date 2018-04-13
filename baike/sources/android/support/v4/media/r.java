package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.media.MediaBrowserCompat.MediaItem;

final class r implements Creator<MediaItem> {
    r() {
    }

    public MediaItem createFromParcel(Parcel parcel) {
        return new MediaItem(parcel);
    }

    public MediaItem[] newArray(int i) {
        return new MediaItem[i];
    }
}
