package qsbk.app.live.adapter;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class w implements Creator<MutableForegroundColorSpan> {
    final /* synthetic */ MutableForegroundColorSpan a;

    w(MutableForegroundColorSpan mutableForegroundColorSpan) {
        this.a = mutableForegroundColorSpan;
    }

    public MutableForegroundColorSpan createFromParcel(Parcel parcel) {
        return new MutableForegroundColorSpan(this.a.a, parcel);
    }

    public MutableForegroundColorSpan[] newArray(int i) {
        return new MutableForegroundColorSpan[i];
    }
}
