package qsbk.app.im.image;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IMImageSize implements Parcelable {
    public static final Creator<IMImageSize> CREATOR = new a();
    private int a;
    private int b;
    private int c;
    private int d;

    public IMImageSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private IMImageSize(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
    }

    public int getOriginWidth() {
        return this.a;
    }

    public void setOriginWidth(int i) {
        this.a = i;
    }

    public int getOriginHeight() {
        return this.b;
    }

    public void setOriginHeight(int i) {
        this.b = i;
    }

    public int getDstWidth() {
        return this.c;
    }

    public void setDstWidth(int i) {
        this.c = i;
    }

    public int getDstHeight() {
        return this.d;
    }

    public void setDstHeight(int i) {
        this.d = i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }

    public String toString() {
        return "IMImageSize [originWidth=" + this.a + ", originHeight=" + this.b + ", dstWidth=" + this.c + ", dstHeight=" + this.d + "]";
    }
}
