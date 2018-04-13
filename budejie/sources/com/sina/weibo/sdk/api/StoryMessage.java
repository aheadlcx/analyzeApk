package com.sina.weibo.sdk.api;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StoryMessage implements Parcelable {
    public static final Creator<StoryMessage> CREATOR = new StoryMessage$1();
    private Uri a;
    private Uri b;

    public Uri a() {
        return this.a;
    }

    public Uri b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
    }

    protected StoryMessage(Parcel parcel) {
        this.a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.b = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public boolean c() {
        if (this.a != null && this.b != null) {
            return false;
        }
        if (this.a == null && this.b == null) {
            return false;
        }
        return true;
    }
}
