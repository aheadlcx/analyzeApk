package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.a.d;

public class TextObject extends BaseMediaObject {
    public static final Creator<TextObject> CREATOR = new Creator<TextObject>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public TextObject a(Parcel parcel) {
            return new TextObject(parcel);
        }

        public TextObject[] a(int i) {
            return new TextObject[i];
        }
    };
    public String g;

    public TextObject(Parcel parcel) {
        this.g = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.g);
    }

    public boolean a() {
        if (this.g != null && this.g.length() != 0 && this.g.length() <= 1024) {
            return true;
        }
        d.c("Weibo.TextObject", "checkArgs fail, text is invalid");
        return false;
    }

    protected BaseMediaObject a(String str) {
        return this;
    }

    protected String b() {
        return "";
    }
}
