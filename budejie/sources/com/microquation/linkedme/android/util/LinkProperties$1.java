package com.microquation.linkedme.android.util;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class LinkProperties$1 implements Creator {
    LinkProperties$1() {
    }

    public LinkProperties a(Parcel parcel) {
        return new LinkProperties(parcel, null);
    }

    public LinkProperties[] a(int i) {
        return new LinkProperties[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
