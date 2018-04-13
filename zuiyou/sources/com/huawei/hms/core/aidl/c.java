package com.huawei.hms.core.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<b> {
    c() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public b a(Parcel parcel) {
        return new b(parcel);
    }

    public b[] a(int i) {
        return new b[i];
    }
}
