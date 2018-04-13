package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<StrategyBean> {
    b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new StrategyBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new StrategyBean[i];
    }
}
