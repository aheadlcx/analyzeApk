package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class g implements Creator<ResultReceiverWrapper> {
    g() {
    }

    public ResultReceiverWrapper createFromParcel(Parcel parcel) {
        return new ResultReceiverWrapper(parcel);
    }

    public ResultReceiverWrapper[] newArray(int i) {
        return new ResultReceiverWrapper[i];
    }
}
