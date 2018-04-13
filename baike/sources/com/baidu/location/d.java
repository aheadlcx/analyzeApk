package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d implements Creator<Poi> {
    d() {
    }

    public Poi createFromParcel(Parcel parcel) {
        return new Poi(parcel.readString(), parcel.readString(), parcel.readDouble());
    }

    public Poi[] newArray(int i) {
        return new Poi[i];
    }
}
