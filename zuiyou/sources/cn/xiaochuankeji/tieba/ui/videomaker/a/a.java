package cn.xiaochuankeji.tieba.ui.videomaker.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class a implements Parcelable {
    public static final Creator<a> CREATOR = new Creator<a>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public a a(Parcel parcel) {
            return new a(parcel);
        }

        public a[] a(int i) {
            return new a[i];
        }
    };
    public final long a;
    public final long b;
    public final String c;
    public final long d;

    public a(long j, long j2, String str, long j3) {
        this.a = j;
        this.b = j2;
        this.c = str;
        this.d = j3;
    }

    protected a(Parcel parcel) {
        this.a = parcel.readLong();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
    }
}
