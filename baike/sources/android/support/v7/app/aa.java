package android.support.v7.app;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

final class aa implements ClassLoaderCreator<SavedState> {
    aa() {
    }

    public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return SavedState.a(parcel, classLoader);
    }

    public SavedState createFromParcel(Parcel parcel) {
        return SavedState.a(parcel, null);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
