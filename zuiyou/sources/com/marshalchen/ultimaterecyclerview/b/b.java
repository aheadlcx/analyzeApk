package com.marshalchen.ultimaterecyclerview.b;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;

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
    public static final b a = new b() {
    };
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public SparseIntArray g;
    public Parcelable h;

    public b() {
        this.c = -1;
        this.h = null;
    }

    public b(Parcelable parcelable) {
        this.c = -1;
        if (parcelable == a) {
            parcelable = null;
        }
        this.h = parcelable;
    }

    public b(Parcel parcel) {
        this.c = -1;
        Parcelable readParcelable = parcel.readParcelable(RecyclerView.class.getClassLoader());
        if (readParcelable == null) {
            readParcelable = a;
        }
        this.h = readParcelable;
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = new SparseIntArray();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            for (int i = 0; i < readInt; i++) {
                this.g.put(parcel.readInt(), parcel.readInt());
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeParcelable(this.h, i);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        int size = this.g == null ? 0 : this.g.size();
        parcel.writeInt(size);
        if (size > 0) {
            while (i2 < size) {
                parcel.writeInt(this.g.keyAt(i2));
                parcel.writeInt(this.g.valueAt(i2));
                i2++;
            }
        }
    }

    public Parcelable a() {
        return this.h;
    }
}
