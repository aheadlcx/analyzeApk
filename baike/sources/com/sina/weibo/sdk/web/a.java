package com.sina.weibo.sdk.web;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<BaseWebViewRequestData> {
    a() {
    }

    public BaseWebViewRequestData createFromParcel(Parcel parcel) {
        return new BaseWebViewRequestData(parcel);
    }

    public BaseWebViewRequestData[] newArray(int i) {
        return new BaseWebViewRequestData[i];
    }
}
