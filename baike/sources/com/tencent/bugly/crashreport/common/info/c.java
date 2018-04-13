package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<PlugInBean> {
    c() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PlugInBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PlugInBean[i];
    }
}
