package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l implements Creator<SavedState> {
    l() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
