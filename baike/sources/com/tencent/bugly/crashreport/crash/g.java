package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class g implements Creator<CrashDetailBean> {
    g() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new CrashDetailBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new CrashDetailBean[i];
    }
}
