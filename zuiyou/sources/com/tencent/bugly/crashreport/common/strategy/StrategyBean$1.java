package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class StrategyBean$1 implements Creator<StrategyBean> {
    StrategyBean$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public StrategyBean a(Parcel parcel) {
        return new StrategyBean(parcel);
    }

    public StrategyBean[] a(int i) {
        return new StrategyBean[i];
    }
}
