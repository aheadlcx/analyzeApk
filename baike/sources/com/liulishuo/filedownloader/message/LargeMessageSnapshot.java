package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot.IWarnMessageSnapshot;

public abstract class LargeMessageSnapshot extends MessageSnapshot {

    public static class CompletedSnapshot extends LargeMessageSnapshot {
        private final boolean b;
        private final long c;

        CompletedSnapshot(int i, boolean z, long j) {
            super(i);
            this.b = z;
            this.c = j;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.c);
        }

        CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readLong();
        }

        public byte getStatus() {
            return (byte) -3;
        }

        public long getLargeTotalBytes() {
            return this.c;
        }

        public boolean isReusedDownloadedFile() {
            return this.b;
        }
    }

    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        CompletedFlowDirectlySnapshot(int i, boolean z, long j) {
            super(i, z, j);
        }
    }

    public static class ConnectedMessageSnapshot extends LargeMessageSnapshot {
        private final boolean b;
        private final long c;
        private final String d;
        private final String e;

        ConnectedMessageSnapshot(int i, boolean z, long j, String str, String str2) {
            super(i);
            this.b = z;
            this.c = j;
            this.d = str;
            this.e = str2;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeLong(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readLong();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }

        public String getFileName() {
            return this.e;
        }

        public byte getStatus() {
            return (byte) 2;
        }

        public boolean isResuming() {
            return this.b;
        }

        public long getLargeTotalBytes() {
            return this.c;
        }

        public String getEtag() {
            return this.d;
        }
    }

    public static class ErrorMessageSnapshot extends LargeMessageSnapshot {
        private final long b;
        private final Throwable c;

        ErrorMessageSnapshot(int i, long j, Throwable th) {
            super(i);
            this.b = j;
            this.c = th;
        }

        public long getLargeSofarBytes() {
            return this.b;
        }

        public byte getStatus() {
            return (byte) -1;
        }

        public Throwable getThrowable() {
            return this.c;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
            parcel.writeSerializable(this.c);
        }

        ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = (Throwable) parcel.readSerializable();
        }
    }

    public static class PendingMessageSnapshot extends LargeMessageSnapshot {
        private final long b;
        private final long c;

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.getId(), pendingMessageSnapshot.getLargeSofarBytes(), pendingMessageSnapshot.getLargeTotalBytes());
        }

        PendingMessageSnapshot(int i, long j, long j2) {
            super(i);
            this.b = j;
            this.c = j2;
        }

        public byte getStatus() {
            return (byte) 1;
        }

        public long getLargeSofarBytes() {
            return this.b;
        }

        public long getLargeTotalBytes() {
            return this.c;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
            parcel.writeLong(this.c);
        }

        PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = parcel.readLong();
        }
    }

    public static class PausedSnapshot extends PendingMessageSnapshot {
        PausedSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }

        public byte getStatus() {
            return (byte) -2;
        }
    }

    public static class ProgressMessageSnapshot extends LargeMessageSnapshot {
        private final long b;

        ProgressMessageSnapshot(int i, long j) {
            super(i);
            this.b = j;
        }

        public byte getStatus() {
            return (byte) 3;
        }

        public long getLargeSofarBytes() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }

        ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
        }
    }

    public static class RetryMessageSnapshot extends ErrorMessageSnapshot {
        private final int b;

        RetryMessageSnapshot(int i, long j, Throwable th, int i2) {
            super(i, j, th);
            this.b = i2;
        }

        public int getRetryingTimes() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        RetryMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }

        public byte getStatus() {
            return (byte) 5;
        }
    }

    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements IWarnMessageSnapshot {
        WarnMessageSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }

        WarnMessageSnapshot(Parcel parcel) {
            super(parcel);
        }

        public MessageSnapshot turnToPending() {
            return new PendingMessageSnapshot((PendingMessageSnapshot) this);
        }

        public byte getStatus() {
            return (byte) -4;
        }
    }

    public static class WarnFlowDirectlySnapshot extends WarnMessageSnapshot implements IFlowDirectly {
        WarnFlowDirectlySnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }
    }

    LargeMessageSnapshot(int i) {
        super(i);
        this.a = true;
    }

    LargeMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    public int getSmallSofarBytes() {
        if (getLargeSofarBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) getLargeSofarBytes();
    }

    public int getSmallTotalBytes() {
        if (getLargeTotalBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) getLargeTotalBytes();
    }
}
