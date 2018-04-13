package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class au implements Creator<RatingCompat> {
    au() {
    }

    public RatingCompat createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat());
    }

    public RatingCompat[] newArray(int i) {
        return new RatingCompat[i];
    }
}
