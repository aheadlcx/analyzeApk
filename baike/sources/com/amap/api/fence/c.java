package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<PoiItem> {
    c() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PoiItem(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PoiItem[i];
    }
}
