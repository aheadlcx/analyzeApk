package com.yalantis.ucrop.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AspectRatio$1 implements Creator<AspectRatio> {
    AspectRatio$1() {
    }

    public AspectRatio createFromParcel(Parcel parcel) {
        return new AspectRatio(parcel);
    }

    public AspectRatio[] newArray(int i) {
        return new AspectRatio[i];
    }
}
