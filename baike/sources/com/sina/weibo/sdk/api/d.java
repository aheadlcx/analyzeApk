package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d implements Creator<WebpageObject> {
    d() {
    }

    public WebpageObject createFromParcel(Parcel parcel) {
        return new WebpageObject(parcel);
    }

    public WebpageObject[] newArray(int i) {
        return new WebpageObject[i];
    }
}
