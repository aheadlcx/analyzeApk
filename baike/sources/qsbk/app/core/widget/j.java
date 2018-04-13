package qsbk.app.core.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class j implements Creator<FragmentTabHost$SavedState> {
    j() {
    }

    public FragmentTabHost$SavedState createFromParcel(Parcel parcel) {
        return new FragmentTabHost$SavedState(parcel);
    }

    public FragmentTabHost$SavedState[] newArray(int i) {
        return new FragmentTabHost$SavedState[i];
    }
}
