package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public abstract class MessageSnapshot implements Parcelable, a {
    public static final Creator<MessageSnapshot> CREATOR = new b();
    protected boolean a;
    private final int b;

    public interface IWarnMessageSnapshot {
        MessageSnapshot turnToPending();
    }

    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(FileDownloadUtils.formatString("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()), messageSnapshot.getClass().getName()));
        }
    }

    public static class StartedMessageSnapshot extends MessageSnapshot {
        StartedMessageSnapshot(int i) {
            super(i);
        }

        StartedMessageSnapshot(Parcel parcel) {
            super(parcel);
        }

        public byte getStatus() {
            return (byte) 6;
        }
    }

    MessageSnapshot(int i) {
        this.b = i;
    }

    public int getId() {
        return this.b;
    }

    public Throwable getThrowable() {
        throw new NoFieldException("getThrowable", this);
    }

    public int getRetryingTimes() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    public boolean isResuming() {
        throw new NoFieldException("isResuming", this);
    }

    public String getEtag() {
        throw new NoFieldException("getEtag", this);
    }

    public long getLargeSofarBytes() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    public long getLargeTotalBytes() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    public int getSmallSofarBytes() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    public int getSmallTotalBytes() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    public boolean isReusedDownloadedFile() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    public String getFileName() {
        throw new NoFieldException("getFileName", this);
    }

    public boolean isLargeFile() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.a ? 1 : 0));
        parcel.writeByte(getStatus());
        parcel.writeInt(this.b);
    }

    MessageSnapshot(Parcel parcel) {
        this.b = parcel.readInt();
    }
}
