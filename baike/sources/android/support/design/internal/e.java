package android.support.design.internal;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

final class e implements ClassLoaderCreator<ParcelableSparseArray> {
    e() {
    }

    public ParcelableSparseArray createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new ParcelableSparseArray(parcel, classLoader);
    }

    public ParcelableSparseArray createFromParcel(Parcel parcel) {
        return new ParcelableSparseArray(parcel, null);
    }

    public ParcelableSparseArray[] newArray(int i) {
        return new ParcelableSparseArray[i];
    }
}
