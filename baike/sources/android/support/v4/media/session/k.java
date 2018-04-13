package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class k implements Creator<ParcelableVolumeInfo> {
    k() {
    }

    public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
        return new ParcelableVolumeInfo(parcel);
    }

    public ParcelableVolumeInfo[] newArray(int i) {
        return new ParcelableVolumeInfo[i];
    }
}
