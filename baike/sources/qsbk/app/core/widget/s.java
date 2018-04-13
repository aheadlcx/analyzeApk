package qsbk.app.core.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class s implements Creator<SavedState> {
    s() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
