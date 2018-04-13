package com.baidu.mobads.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<XAdInstanceInfo> {
    a() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public XAdInstanceInfo a(Parcel parcel) {
        return new XAdInstanceInfo(parcel);
    }

    public XAdInstanceInfo[] a(int i) {
        return new XAdInstanceInfo[i];
    }
}
