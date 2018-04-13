package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c implements Creator<TextObject> {
    c() {
    }

    public TextObject createFromParcel(Parcel parcel) {
        return new TextObject(parcel);
    }

    public TextObject[] newArray(int i) {
        return new TextObject[i];
    }
}
