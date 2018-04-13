package com.slidingmenu.lib;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

public class SlidingMenu$SavedState extends BaseSavedState {
    public static final Creator<SlidingMenu$SavedState> CREATOR = new e();
    private final int a;

    public SlidingMenu$SavedState(Parcelable parcelable, int i) {
        super(parcelable);
        this.a = i;
    }

    private SlidingMenu$SavedState(Parcel parcel) {
        super(parcel);
        this.a = parcel.readInt();
    }

    public int a() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.a);
    }
}
