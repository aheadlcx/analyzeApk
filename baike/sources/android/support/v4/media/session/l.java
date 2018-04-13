package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l implements Creator<PlaybackStateCompat> {
    l() {
    }

    public PlaybackStateCompat createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat(parcel);
    }

    public PlaybackStateCompat[] newArray(int i) {
        return new PlaybackStateCompat[i];
    }
}
