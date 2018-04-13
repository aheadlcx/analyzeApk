package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

final class m implements ClassLoaderCreator<SavedState> {
    m() {
    }

    public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SavedState(parcel, null);
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel, null);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
