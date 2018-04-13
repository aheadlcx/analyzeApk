package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class r implements Creator<FragmentManagerState> {
    r() {
    }

    public FragmentManagerState createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    public FragmentManagerState[] newArray(int i) {
        return new FragmentManagerState[i];
    }
}
