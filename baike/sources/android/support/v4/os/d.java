package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d implements Creator<ResultReceiver> {
    d() {
    }

    public ResultReceiver createFromParcel(Parcel parcel) {
        return new ResultReceiver(parcel);
    }

    public ResultReceiver[] newArray(int i) {
        return new ResultReceiver[i];
    }
}
