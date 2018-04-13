package android.support.design.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<SavedState> {
    b() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
