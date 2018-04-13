package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

final class a implements ClassLoaderCreator<AbsSavedState> {
    a() {
    }

    public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return AbsSavedState.EMPTY_STATE;
        }
        throw new IllegalStateException("superState must be null");
    }

    public AbsSavedState createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, null);
    }

    public AbsSavedState[] newArray(int i) {
        return new AbsSavedState[i];
    }
}
