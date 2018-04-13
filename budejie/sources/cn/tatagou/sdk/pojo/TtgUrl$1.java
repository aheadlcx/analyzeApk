package cn.tatagou.sdk.pojo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class TtgUrl$1 implements Creator<TtgUrl> {
    TtgUrl$1() {
    }

    public TtgUrl createFromParcel(Parcel parcel) {
        return new TtgUrl(parcel);
    }

    public TtgUrl[] newArray(int i) {
        return new TtgUrl[i];
    }
}
