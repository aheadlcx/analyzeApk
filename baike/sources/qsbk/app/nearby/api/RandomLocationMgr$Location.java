package qsbk.app.nearby.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RandomLocationMgr$Location implements Parcelable {
    public static final Creator<RandomLocationMgr$Location> CREATOR = new s();
    private String a;
    private double b;
    private double c;

    public RandomLocationMgr$Location(String str, double d, double d2) {
        this.a = str;
        this.c = d2;
        this.b = d;
    }

    private RandomLocationMgr$Location(String str, RandomLocationMgr$a randomLocationMgr$a) {
        this.a = str;
        this.c = randomLocationMgr$a.b;
        this.b = randomLocationMgr$a.a;
    }

    private RandomLocationMgr$Location(Parcel parcel) {
        this.b = parcel.readDouble();
        this.c = parcel.readDouble();
        this.a = parcel.readString();
    }

    public String getName() {
        return this.a;
    }

    public double getLongitude() {
        return this.b;
    }

    public double getLatitude() {
        return this.c;
    }

    public String toString() {
        return "Location [name=" + this.a + ", longitude=" + this.b + ", latitude=" + this.c + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.b);
        parcel.writeDouble(this.c);
        parcel.writeString(this.a);
    }
}
