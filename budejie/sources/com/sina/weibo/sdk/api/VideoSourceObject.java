package com.sina.weibo.sdk.api;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class VideoSourceObject extends BaseMediaObject {
    public static final Creator<VideoSourceObject> CREATOR = new VideoSourceObject$1();
    public Uri g;
    public Uri h;
    public long i;

    protected String b() {
        return null;
    }

    protected BaseMediaObject a(String str) {
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.g, i);
        parcel.writeParcelable(this.h, i);
        parcel.writeLong(this.i);
    }

    protected VideoSourceObject(Parcel parcel) {
        super(parcel);
        this.g = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.h = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.i = parcel.readLong();
    }
}
