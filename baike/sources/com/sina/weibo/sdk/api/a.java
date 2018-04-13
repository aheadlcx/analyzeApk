package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<CmdObject> {
    a() {
    }

    public CmdObject createFromParcel(Parcel parcel) {
        return new CmdObject(parcel);
    }

    public CmdObject[] newArray(int i) {
        return new CmdObject[i];
    }
}
