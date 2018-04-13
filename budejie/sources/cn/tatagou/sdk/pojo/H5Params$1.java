package cn.tatagou.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class H5Params$1 implements Creator<H5Params> {
    H5Params$1() {
    }

    public H5Params createFromParcel(Parcel parcel) {
        return new H5Params(parcel);
    }

    public H5Params[] newArray(int i) {
        return new H5Params[i];
    }
}
