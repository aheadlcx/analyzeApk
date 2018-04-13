package com.prolificinteractive.materialcalendarview;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.SavedState;

final class m implements Creator<SavedState> {
    m() {
    }

    public SavedState createFromParcel(Parcel parcel) {
        return new SavedState(parcel, null);
    }

    public SavedState[] newArray(int i) {
        return new SavedState[i];
    }
}
