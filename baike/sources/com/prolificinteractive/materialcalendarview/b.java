package com.prolificinteractive.materialcalendarview;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<CalendarDay> {
    b() {
    }

    public CalendarDay createFromParcel(Parcel parcel) {
        return new CalendarDay(parcel);
    }

    public CalendarDay[] newArray(int i) {
        return new CalendarDay[i];
    }
}
