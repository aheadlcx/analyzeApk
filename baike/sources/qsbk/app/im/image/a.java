package qsbk.app.im.image;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a implements Creator<IMImageSize> {
    a() {
    }

    public IMImageSize createFromParcel(Parcel parcel) {
        return new IMImageSize(parcel);
    }

    public IMImageSize[] newArray(int i) {
        return new IMImageSize[i];
    }
}
