package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<UserInfoBean> {
    c() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new UserInfoBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new UserInfoBean[i];
    }
}
