package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;

final class f implements Creator<QueueItem> {
    f() {
    }

    public QueueItem createFromParcel(Parcel parcel) {
        return new QueueItem(parcel);
    }

    public QueueItem[] newArray(int i) {
        return new QueueItem[i];
    }
}
