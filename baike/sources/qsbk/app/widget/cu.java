package qsbk.app.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import qsbk.app.widget.PagerSlidingTabStrip.SavedState;

final class cu implements Creator<SavedState> {
    cu() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel, null);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
