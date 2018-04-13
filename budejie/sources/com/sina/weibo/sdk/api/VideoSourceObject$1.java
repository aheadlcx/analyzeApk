package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class VideoSourceObject$1 implements Creator<VideoSourceObject> {
    VideoSourceObject$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public VideoSourceObject a(Parcel parcel) {
        return new VideoSourceObject(parcel);
    }

    public VideoSourceObject[] a(int i) {
        return new VideoSourceObject[i];
    }
}
