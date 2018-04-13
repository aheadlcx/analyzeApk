package qsbk.app.nearby.ui;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import qsbk.app.nearby.ui.HometownDialogFragment.Hometown;

final class g implements Creator<Hometown> {
    g() {
    }

    public Hometown createFromParcel(Parcel parcel) {
        return new Hometown(parcel);
    }

    public Hometown[] newArray(int i) {
        return new Hometown[i];
    }
}
