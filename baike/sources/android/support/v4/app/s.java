package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class s implements Creator<FragmentState> {
    s() {
    }

    public FragmentState createFromParcel(Parcel parcel) {
        return new FragmentState(parcel);
    }

    public FragmentState[] newArray(int i) {
        return new FragmentState[i];
    }
}
