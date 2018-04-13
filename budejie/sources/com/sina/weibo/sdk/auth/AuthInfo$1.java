package com.sina.weibo.sdk.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AuthInfo$1 implements Creator<AuthInfo> {
    AuthInfo$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public AuthInfo a(Parcel parcel) {
        return new AuthInfo(parcel);
    }

    public AuthInfo[] a(int i) {
        return new AuthInfo[i];
    }
}
