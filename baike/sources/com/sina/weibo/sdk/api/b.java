package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<ImageObject> {
    b() {
    }

    public ImageObject createFromParcel(Parcel parcel) {
        return new ImageObject(parcel);
    }

    public ImageObject[] newArray(int i) {
        return new ImageObject[i];
    }
}
