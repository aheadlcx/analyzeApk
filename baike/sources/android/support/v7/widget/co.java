package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class co implements Creator<FullSpanItem> {
    co() {
    }

    public FullSpanItem createFromParcel(Parcel parcel) {
        return new FullSpanItem(parcel);
    }

    public FullSpanItem[] newArray(int i) {
        return new FullSpanItem[i];
    }
}
