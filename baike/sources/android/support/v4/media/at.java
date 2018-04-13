package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class at implements Creator<MediaMetadataCompat> {
    at() {
    }

    public MediaMetadataCompat createFromParcel(Parcel parcel) {
        return new MediaMetadataCompat(parcel);
    }

    public MediaMetadataCompat[] newArray(int i) {
        return new MediaMetadataCompat[i];
    }
}
