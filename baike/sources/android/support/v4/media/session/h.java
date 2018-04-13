package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.media.session.MediaSessionCompat.Token;

final class h implements Creator<Token> {
    h() {
    }

    public Token createFromParcel(Parcel parcel) {
        Object readParcelable;
        if (VERSION.SDK_INT >= 21) {
            readParcelable = parcel.readParcelable(null);
        } else {
            readParcelable = parcel.readStrongBinder();
        }
        return new Token(readParcelable);
    }

    public Token[] newArray(int i) {
        return new Token[i];
    }
}
