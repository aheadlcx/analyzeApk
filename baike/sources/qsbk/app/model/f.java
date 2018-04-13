package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class f implements Creator<CircleComment> {
    f() {
    }

    public CircleComment createFromParcel(Parcel parcel) {
        return new CircleComment(parcel);
    }

    public CircleComment[] newArray(int i) {
        return new CircleComment[i];
    }
}
