package cn.tatagou.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Coupon implements Parcelable {
    public static final Creator<Coupon> CREATOR = new Coupon$1();
    private String couponUrl;
    private String denomination;

    protected Coupon(Parcel parcel) {
        this.denomination = parcel.readString();
        this.couponUrl = parcel.readString();
    }

    public String getDenomination() {
        return this.denomination;
    }

    public void setDenomination(String str) {
        this.denomination = str;
    }

    public String getCouponUrl() {
        return this.couponUrl;
    }

    public void setCouponUrl(String str) {
        this.couponUrl = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.denomination);
        parcel.writeString(this.couponUrl);
    }
}
