package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot.IWarnMessageSnapshot;

public abstract class SmallMessageSnapshot extends MessageSnapshot {

    public static class CompletedSnapshot extends SmallMessageSnapshot {
        private final boolean b;
        private final int c;

        CompletedSnapshot(int i, boolean z, int i2) {
            super(i);
            this.b = z;
            this.c = i2;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.c);
        }

        CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readInt();
        }

        public byte getStatus() {
            return (byte) -3;
        }

        public int getSmallTotalBytes() {
            return this.c;
        }

        public boolean isReusedDownloadedFile() {
            return this.b;
        }
    }

    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        CompletedFlowDirectlySnapshot(int i, boolean z, int i2) {
            super(i, z, i2);
        }
    }

    public static class ConnectedMessageSnapshot extends SmallMessageSnapshot {
        private final boolean b;
        private final int c;
        private final String d;
        private final String e;

        ConnectedMessageSnapshot(int i, boolean z, int i2, String str, String str2) {
            super(i);
            this.b = z;
            this.c = i2;
            this.d = str;
            this.e = str2;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readInt();
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

        public int getSmallTotalBytes() {
            return this.c;
        }

        public String getEtag() {
            return this.d;
        }
    }

    public static class ErrorMessageSnapshot extends SmallMessageSnapshot {
        private final int b;
        private final Throwable c;

        ErrorMessageSnapshot(int i, int i2, Throwable th) {
            super(i);
            this.b = i2;
            this.c = th;
        }

        public int getSmallSofarBytes() {
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
            parcel.writeInt(this.b);
            parcel.writeSerializable(this.c);
        }

        ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = (Throwable) parcel.readSerializable();
        }
    }

    public static class PendingMessageSnapshot extends SmallMessageSnapshot {
        private final int b;
        private final int c;

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.getId(), pendingMessageSnapshot.getSmallSofarBytes(), pendingMessageSnapshot.getSmallTotalBytes());
        }

        PendingMessageSnapshot(int i, int i2, int i3) {
            super(i);
            this.b = i2;
            this.c = i3;
        }

        PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
        }

        public byte getStatus() {
            return (byte) 1;
        }

        public int getSmallSofarBytes() {
            return this.b;
        }

        public int getSmallTotalBytes() {
            return this.c;
        }
    }

    public static class PausedSnapshot extends PendingMessageSnapshot {
        PausedSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        public byte getStatus() {
            return (byte) -2;
        }
    }

    public static class ProgressMessageSnapshot extends SmallMessageSnapshot {
        private final int b;

        ProgressMessageSnapshot(int i, int i2) {
            super(i);
            this.b = i2;
        }

        public byte getStatus() {
            return (byte) 3;
        }

        public int getSmallSofarBytes() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }
    }

    public static class RetryMessageSnapshot extends ErrorMessageSnapshot {
        private final int b;

        RetryMessageSnapshot(int i, int i2, Throwable th, int i3) {
            super(i, i2, th);
            this.b = i3;
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
        WarnMessageSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
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
        WarnFlowDirectlySnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }
    }

    SmallMessageSnapshot(int i) {
        super(i);
        this.a = false;
    }

    SmallMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    public long getLargeTotalBytes() {
        return (long) getSmallTotalBytes();
    }

    public long getLargeSofarBytes() {
        return (long) getSmallSofarBytes();
    }
}
