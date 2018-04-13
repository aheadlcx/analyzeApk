package com.liulishuo.filedownloader.d;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashMap;
import java.util.List;

public class b implements Parcelable {
    public static final Creator<b> CREATOR = new Creator<b>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public b a(Parcel parcel) {
            return new b(parcel);
        }

        public b[] a(int i) {
            return new b[i];
        }
    };
    private HashMap<String, List<String>> a;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.a);
    }

    public HashMap<String, List<String>> a() {
        return this.a;
    }

    protected b(Parcel parcel) {
        this.a = parcel.readHashMap(String.class.getClassLoader());
    }

    public String toString() {
        return this.a.toString();
    }
}
