package com.tonicartos.widget.stickygridheaders;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class j implements Creator<SavedState> {
    j() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }
}
