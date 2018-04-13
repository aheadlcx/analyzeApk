package android.support.v4.media;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class aq implements Creator<MediaDescriptionCompat> {
    aq() {
    }

    public MediaDescriptionCompat createFromParcel(Parcel parcel) {
        if (VERSION.SDK_INT < 21) {
            return new MediaDescriptionCompat(parcel);
        }
        return MediaDescriptionCompat.fromMediaDescription(ar.fromParcel(parcel));
    }

    public MediaDescriptionCompat[] newArray(int i) {
        return new MediaDescriptionCompat[i];
    }
}
