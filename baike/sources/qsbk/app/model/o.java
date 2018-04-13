package qsbk.app.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class o implements Creator<Image> {
    o() {
    }

    public Image createFromParcel(Parcel parcel) {
        return new Image(parcel);
    }

    public Image[] newArray(int i) {
        return new Image[i];
    }
}
