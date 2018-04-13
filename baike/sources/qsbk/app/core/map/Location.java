package qsbk.app.core.map;

import android.text.TextUtils;

public class Location {
    public static int NON_LOCATION = -10000;
    public String city;
    public int code;
    public String district;
    public double latitude;
    public double longitude;
    public String province;

    public Location(int i, double d, double d2, String str, String str2, String str3) {
        this.code = i;
        this.latitude = d;
        this.longitude = d2;
        this.province = str;
        this.district = str2;
        this.city = str3;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.city) || this.latitude == 0.0d || this.longitude == 0.0d) ? false : true;
    }

    public String toString() {
        return "Location [code=" + this.code + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", province=" + this.province + ", district=" + this.district + ", city=" + this.city + "]";
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof Location) && obj.hashCode() == hashCode()) {
            return true;
        }
        return false;
    }
}
