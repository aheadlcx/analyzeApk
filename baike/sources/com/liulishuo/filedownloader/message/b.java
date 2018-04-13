package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.liulishuo.filedownloader.message.MessageSnapshot.StartedMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.CompletedSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.ConnectedMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.ErrorMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.PendingMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.ProgressMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.RetryMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.WarnMessageSnapshot;

final class b implements Creator<MessageSnapshot> {
    b() {
    }

    public MessageSnapshot createFromParcel(Parcel parcel) {
        MessageSnapshot warnMessageSnapshot;
        boolean z = true;
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        byte readByte = parcel.readByte();
        switch (readByte) {
            case (byte) -4:
                if (!z) {
                    warnMessageSnapshot = new WarnMessageSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.WarnMessageSnapshot(parcel);
                break;
            case (byte) -3:
                if (!z) {
                    warnMessageSnapshot = new CompletedSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.CompletedSnapshot(parcel);
                break;
            case (byte) -1:
                if (!z) {
                    warnMessageSnapshot = new ErrorMessageSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(parcel);
                break;
            case (byte) 1:
                if (!z) {
                    warnMessageSnapshot = new PendingMessageSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.PendingMessageSnapshot(parcel);
                break;
            case (byte) 2:
                if (!z) {
                    warnMessageSnapshot = new ConnectedMessageSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.ConnectedMessageSnapshot(parcel);
                break;
            case (byte) 3:
                if (!z) {
                    warnMessageSnapshot = new ProgressMessageSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.ProgressMessageSnapshot(parcel);
                break;
            case (byte) 5:
                if (!z) {
                    warnMessageSnapshot = new RetryMessageSnapshot(parcel);
                    break;
                }
                warnMessageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(parcel);
                break;
            case (byte) 6:
                warnMessageSnapshot = new StartedMessageSnapshot(parcel);
                break;
            default:
                warnMessageSnapshot = null;
                break;
        }
        if (warnMessageSnapshot != null) {
            warnMessageSnapshot.a = z;
            return warnMessageSnapshot;
        }
        throw new IllegalStateException("Can't restore the snapshot because unknow status: " + readByte);
    }

    public MessageSnapshot[] newArray(int i) {
        return new MessageSnapshot[i];
    }
}
