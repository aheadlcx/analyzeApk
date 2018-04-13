package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class i implements Creator<SavedState> {
    i() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
