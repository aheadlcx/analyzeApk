package com.sina.weibo.sdk.web;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class BaseWebViewRequestData$1 implements Creator<BaseWebViewRequestData> {
    BaseWebViewRequestData$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public BaseWebViewRequestData a(Parcel parcel) {
        return new BaseWebViewRequestData(parcel);
    }

    public BaseWebViewRequestData[] a(int i) {
        return new BaseWebViewRequestData[i];
    }
}
