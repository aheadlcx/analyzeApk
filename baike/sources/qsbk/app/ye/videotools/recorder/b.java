package qsbk.app.ye.videotools.recorder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<SegmentInfo> {
    b() {
    }

    public SegmentInfo createFromParcel(Parcel parcel) {
        return new SegmentInfo(parcel);
    }

    public SegmentInfo[] newArray(int i) {
        return new SegmentInfo[i];
    }
}
