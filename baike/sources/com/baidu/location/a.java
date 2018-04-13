package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<BDLocation> {
    a() {
    }

    public BDLocation createFromParcel(Parcel parcel) {
        return new BDLocation(parcel);
    }

    public BDLocation[] newArray(int i) {
        return new BDLocation[i];
    }
}
