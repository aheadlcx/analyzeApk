package com.amap.api.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<AMapLocation> {
    a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = true;
        AMapLocation aMapLocation = new AMapLocation((Location) Location.CREATOR.createFromParcel(parcel));
        aMapLocation.g = parcel.readString();
        aMapLocation.h = parcel.readString();
        aMapLocation.v = parcel.readString();
        aMapLocation.a = parcel.readString();
        aMapLocation.d = parcel.readString();
        aMapLocation.f = parcel.readString();
        aMapLocation.j = parcel.readString();
        aMapLocation.e = parcel.readString();
        aMapLocation.o = parcel.readInt();
        aMapLocation.p = parcel.readString();
        aMapLocation.b = parcel.readString();
        aMapLocation.z = parcel.readInt() != 0;
        aMapLocation.n = parcel.readInt() != 0;
        aMapLocation.s = parcel.readDouble();
        aMapLocation.q = parcel.readString();
        aMapLocation.r = parcel.readInt();
        aMapLocation.t = parcel.readDouble();
        if (parcel.readInt() == 0) {
            z = false;
        }
        aMapLocation.x = z;
        aMapLocation.m = parcel.readString();
        aMapLocation.i = parcel.readString();
        aMapLocation.c = parcel.readString();
        aMapLocation.k = parcel.readString();
        aMapLocation.u = parcel.readInt();
        aMapLocation.w = parcel.readInt();
        aMapLocation.l = parcel.readString();
        aMapLocation.y = parcel.readString();
        return aMapLocation;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new AMapLocation[i];
    }
}
