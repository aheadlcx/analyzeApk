package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<DistrictItem> {
    a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new DistrictItem(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DistrictItem[i];
    }
}
