package qsbk.app.nearby.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class s implements Creator<RandomLocationMgr$Location> {
    s() {
    }

    public RandomLocationMgr$Location createFromParcel(Parcel parcel) {
        return new RandomLocationMgr$Location(parcel);
    }

    public RandomLocationMgr$Location[] newArray(int i) {
        return new RandomLocationMgr$Location[i];
    }
}
