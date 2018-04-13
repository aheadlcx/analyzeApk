package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StoryObject implements Parcelable {
    public static final Creator<StoryObject> CREATOR = new Creator<StoryObject>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public StoryObject a(Parcel parcel) {
            return new StoryObject(parcel);
        }

        public StoryObject[] a(int i) {
            return new StoryObject[i];
        }
    };
    public String a;
    public int b;
    public String c;
    public String d;
    public String e = "com.sina.weibo.sdk.action.ACTION_SDK_REQ_STORY";

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    protected StoryObject(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
