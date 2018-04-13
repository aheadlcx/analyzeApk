package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Dimension implements Parcelable {
    public static final Creator<Dimension> CREATOR = new Creator<Dimension>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return b(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Dimension b(Parcel parcel) {
            return Dimension.a(parcel);
        }

        public Dimension[] a(int i) {
            return new Dimension[i];
        }
    };
    protected String name;
    protected String y;

    public Dimension() {
        this.y = "null";
    }

    public Dimension(String str) {
        this(str, null);
    }

    public Dimension(String str, String str2) {
        this.y = "null";
        this.name = str;
        if (str2 == null) {
            str2 = "null";
        }
        this.y = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getConstantValue() {
        return this.y;
    }

    public void setConstantValue(String str) {
        this.y = str;
    }

    public int hashCode() {
        return (this.name == null ? 0 : this.name.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Dimension dimension = (Dimension) obj;
        if (this.name == null) {
            if (dimension.name != null) {
                return false;
            }
            return true;
        } else if (this.name.equals(dimension.name)) {
            return true;
        } else {
            return false;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.y);
        parcel.writeString(this.name);
    }

    static Dimension a(Parcel parcel) {
        try {
            return new Dimension(parcel.readString(), parcel.readString());
        } catch (Throwable th) {
            return null;
        }
    }
}
