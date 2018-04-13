package cn.xiaochuan.jsbridge;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

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
    public long a;
    public String b;
    public String c;
    public long d;
    public Bundle e;

    public static b a(String str, String str2) {
        return new b(str, str2);
    }

    public boolean a() {
        return !TextUtils.isEmpty(this.c);
    }

    public b(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public String toString() {
        return "WebRequest{id=" + this.a + ", title='" + this.b + '\'' + ", target='" + this.c + '\'' + ", cover='" + this.d + '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeBundle(this.e);
    }

    protected b(Parcel parcel) {
        this.a = parcel.readLong();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readLong();
        this.e = parcel.readBundle();
    }
}
