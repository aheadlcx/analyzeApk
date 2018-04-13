package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class t implements Creator<SavedState> {
    t() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
