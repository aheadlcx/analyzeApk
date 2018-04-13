package cn.tatagou.sdk.pojo;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import cn.tatagou.sdk.activity.TaobaoH5Activity;
import cn.tatagou.sdk.util.a;

public class H5Params implements Parcelable {
    public static final Creator<H5Params> CREATOR = new H5Params$1();
    private String back;
    private Coupon coupon;
    private String couponType;
    private String detailType;
    private String finalPrices;
    private String notify;
    private String title;
    private int type;
    private String typeParams;

    protected H5Params(Parcel parcel) {
        this.title = parcel.readString();
        this.typeParams = parcel.readString();
        this.type = parcel.readInt();
        this.couponType = parcel.readString();
        this.finalPrices = parcel.readString();
        this.detailType = parcel.readString();
        this.back = parcel.readString();
        this.notify = parcel.readString();
        this.coupon = (Coupon) parcel.readParcelable(Coupon.class.getClassLoader());
    }

    public String getFinalPrices() {
        return this.finalPrices;
    }

    public H5Params setFinalPrices(String str) {
        this.finalPrices = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public H5Params setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getTypeParams() {
        return this.typeParams;
    }

    public H5Params setTypeParams(String str) {
        this.typeParams = str;
        return this;
    }

    public int getType() {
        return this.type;
    }

    public H5Params setType(int i) {
        this.type = i;
        return this;
    }

    public String getCouponType() {
        return this.couponType;
    }

    public H5Params setCouponType(String str) {
        this.couponType = str;
        return this;
    }

    public Coupon getCoupon() {
        return this.coupon;
    }

    public H5Params setCoupon(Coupon coupon) {
        this.coupon = coupon;
        return this;
    }

    public void openH5(Context context) {
        if (a.isBcSucc(context)) {
            Intent intent = new Intent(context, TaobaoH5Activity.class);
            intent.putExtra("params", this);
            intent.setFlags(276824064);
            context.startActivity(intent);
        }
    }

    public String getDetailType() {
        return this.detailType;
    }

    public H5Params setDetailType(String str) {
        this.detailType = str;
        return this;
    }

    public String getBack() {
        return this.back;
    }

    public H5Params setBack(String str) {
        this.back = str;
        return this;
    }

    public String getNotify() {
        return this.notify;
    }

    public H5Params setNotify(String str) {
        this.notify = str;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.typeParams);
        parcel.writeInt(this.type);
        parcel.writeString(this.couponType);
        parcel.writeString(this.finalPrices);
        parcel.writeString(this.detailType);
        parcel.writeString(this.back);
        parcel.writeString(this.notify);
        parcel.writeParcelable(this.coupon, i);
    }
}
