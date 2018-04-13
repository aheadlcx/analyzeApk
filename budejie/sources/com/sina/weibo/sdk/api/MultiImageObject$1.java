package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class MultiImageObject$1 implements Creator<MultiImageObject> {
    MultiImageObject$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public MultiImageObject a(Parcel parcel) {
        return new MultiImageObject(parcel);
    }

    public MultiImageObject[] a(int i) {
        return new MultiImageObject[i];
    }
}
