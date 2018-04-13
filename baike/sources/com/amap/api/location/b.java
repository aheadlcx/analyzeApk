package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<AMapLocationClientOption> {
    b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new AMapLocationClientOption(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new AMapLocationClientOption[i];
    }
}
