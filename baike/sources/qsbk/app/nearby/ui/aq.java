package qsbk.app.nearby.ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import qsbk.app.nearby.ui.Shake2FanSomeone.FanModel;

final class aq implements Creator<FanModel> {
    aq() {
    }

    public FanModel createFromParcel(Parcel parcel) {
        return new FanModel(parcel);
    }

    public FanModel[] newArray(int i) {
        return new FanModel[i];
    }
}
