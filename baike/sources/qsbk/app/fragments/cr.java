package qsbk.app.fragments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class cr implements Creator<SavedState> {
    cr() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
