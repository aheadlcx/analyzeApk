package com.sina.weibo.sdk.api;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class MultiImageObject extends BaseMediaObject {
    public static final Creator<MultiImageObject> CREATOR = new MultiImageObject$1();
    public ArrayList<Uri> g;

    public void a(ArrayList<Uri> arrayList) {
        this.g = arrayList;
    }

    public ArrayList<Uri> c() {
        return this.g;
    }

    protected BaseMediaObject a(String str) {
        return null;
    }

    protected String b() {
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.g);
    }

    protected MultiImageObject(Parcel parcel) {
        super(parcel);
        this.g = parcel.createTypedArrayList(Uri.CREATOR);
    }
}
