package qsbk.app.ye.videotools.recorder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public class SegmentInfo implements Parcelable {
    public static final Creator<SegmentInfo> CREATOR = new b();
    int a;
    int[] b;
    int[] c;
    double[] d;

    SegmentInfo() {
    }

    protected SegmentInfo(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = new int[this.a];
        parcel.readIntArray(this.b);
        this.c = new int[this.a];
        parcel.readIntArray(this.c);
        this.d = new double[this.a];
        parcel.readDoubleArray(this.d);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeIntArray(this.b);
        parcel.writeIntArray(this.c);
        parcel.writeDoubleArray(this.d);
    }

    public String toString() {
        return "SegmentInfo{mSegmentIndex=" + this.a + ", mSegmentPosition=" + Arrays.toString(this.b) + ", mSegmentSyncOutPTS=" + Arrays.toString(this.c) + ", mSegmentPreSyncOutIPTS=" + Arrays.toString(this.d) + '}';
    }
}
