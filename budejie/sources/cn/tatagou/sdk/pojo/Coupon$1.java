package cn.tatagou.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Coupon$1 implements Creator<Coupon> {
    Coupon$1() {
    }

    public Coupon createFromParcel(Parcel parcel) {
        return new Coupon(parcel);
    }

    public Coupon[] newArray(int i) {
        return new Coupon[i];
    }
}
