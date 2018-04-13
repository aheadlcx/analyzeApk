package com.iflytek.cloud.thirdparty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class dd implements Parcelable {
    public static final Creator<dd> CREATOR = new Creator<dd>() {
        public dd a(Parcel parcel) {
            dd ddVar = new dd();
            ddVar.a = parcel.readString();
            ddVar.b = parcel.readString();
            ddVar.c = parcel.readString();
            ddVar.d = parcel.readString();
            ddVar.e = parcel.readString();
            ddVar.f = parcel.readString();
            ddVar.g = parcel.readString();
            return ddVar;
        }

        public dd[] a(int i) {
            return new dd[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String a = null;
    private String b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;

    public dd(String str, String str2, String str3, String str4, String str5, String str6) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.g = str6;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
