package com.tencent.wcdb;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CursorWindow$1 implements Creator<CursorWindow> {
    CursorWindow$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public CursorWindow a(Parcel parcel) {
        return new CursorWindow(parcel, null);
    }

    public CursorWindow[] a(int i) {
        return new CursorWindow[i];
    }
}
