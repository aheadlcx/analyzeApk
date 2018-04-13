package com.sina.weibo.sdk.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<AuthInfo> {
    b() {
    }

    public AuthInfo createFromParcel(Parcel parcel) {
        return new AuthInfo(parcel);
    }

    public AuthInfo[] newArray(int i) {
        return new AuthInfo[i];
    }
}
