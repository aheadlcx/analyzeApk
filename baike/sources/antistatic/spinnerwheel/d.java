package antistatic.spinnerwheel;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d implements Creator<SavedState> {
    d() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
