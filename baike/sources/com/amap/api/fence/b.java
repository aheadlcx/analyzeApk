package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<GeoFence> {
    b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new GeoFence(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GeoFence[i];
    }
}
