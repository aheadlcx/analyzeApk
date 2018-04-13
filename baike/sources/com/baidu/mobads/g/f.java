package com.baidu.mobads.g;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class f implements Creator<e> {
    f() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public e a(Parcel parcel) {
        return new e(parcel);
    }

    public e[] a(int i) {
        return new e[i];
    }
}
