package com.slidingmenu.lib;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class e implements Creator<SlidingMenu$SavedState> {
    e() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final SlidingMenu$SavedState a(Parcel parcel) {
        return new SlidingMenu$SavedState(parcel);
    }

    public final SlidingMenu$SavedState[] a(int i) {
        return new SlidingMenu$SavedState[i];
    }
}
