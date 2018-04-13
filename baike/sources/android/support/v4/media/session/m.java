package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.media.session.PlaybackStateCompat.CustomAction;

final class m implements Creator<CustomAction> {
    m() {
    }

    public CustomAction createFromParcel(Parcel parcel) {
        return new CustomAction(parcel);
    }

    public CustomAction[] newArray(int i) {
        return new CustomAction[i];
    }
}
