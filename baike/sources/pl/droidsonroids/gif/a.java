package pl.droidsonroids.gif;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<GifAnimationMetaData> {
    a() {
    }

    public GifAnimationMetaData createFromParcel(Parcel parcel) {
        return new GifAnimationMetaData(parcel);
    }

    public GifAnimationMetaData[] newArray(int i) {
        return new GifAnimationMetaData[i];
    }
}
