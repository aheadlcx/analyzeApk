package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.liulishuo.filedownloader.message.h.c;
import com.liulishuo.filedownloader.message.h.d;
import com.liulishuo.filedownloader.message.h.e;
import com.liulishuo.filedownloader.message.h.f;
import com.liulishuo.filedownloader.message.h.g;
import com.liulishuo.filedownloader.message.h.i;

public abstract class MessageSnapshot implements Parcelable, c {
    public static final Creator<MessageSnapshot> CREATOR = new Creator<MessageSnapshot>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public MessageSnapshot a(Parcel parcel) {
            MessageSnapshot iVar;
            boolean z = true;
            if (parcel.readByte() != (byte) 1) {
                z = false;
            }
            byte readByte = parcel.readByte();
            switch (readByte) {
                case (byte) -4:
                    if (!z) {
                        iVar = new i(parcel);
                        break;
                    }
                    iVar = new d.i(parcel);
                    break;
                case (byte) -3:
                    if (!z) {
                        iVar = new com.liulishuo.filedownloader.message.h.b(parcel);
                        break;
                    }
                    iVar = new com.liulishuo.filedownloader.message.d.b(parcel);
                    break;
                case (byte) -1:
                    if (!z) {
                        iVar = new d(parcel);
                        break;
                    }
                    iVar = new d.d(parcel);
                    break;
                case (byte) 1:
                    if (!z) {
                        iVar = new e(parcel);
                        break;
                    }
                    iVar = new d.e(parcel);
                    break;
                case (byte) 2:
                    if (!z) {
                        iVar = new c(parcel);
                        break;
                    }
                    iVar = new d.c(parcel);
                    break;
                case (byte) 3:
                    if (!z) {
                        iVar = new f(parcel);
                        break;
                    }
                    iVar = new d.f(parcel);
                    break;
                case (byte) 5:
                    if (!z) {
                        iVar = new g(parcel);
                        break;
                    }
                    iVar = new d.g(parcel);
                    break;
                case (byte) 6:
                    iVar = new b(parcel);
                    break;
                default:
                    iVar = null;
                    break;
            }
            if (iVar != null) {
                iVar.a = z;
                return iVar;
            }
            throw new IllegalStateException("Can't restore the snapshot because unknown status: " + readByte);
        }

        public MessageSnapshot[] a(int i) {
            return new MessageSnapshot[i];
        }
    };
    protected boolean a;
    private final int b;

    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(com.liulishuo.filedownloader.g.f.a("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.m()), Byte.valueOf(messageSnapshot.b()), messageSnapshot.getClass().getName()));
        }
    }

    public interface a {
        MessageSnapshot l();
    }

    public static class b extends MessageSnapshot {
        b(int i) {
            super(i);
        }

        b(Parcel parcel) {
            super(parcel);
        }

        public byte b() {
            return (byte) 6;
        }
    }

    MessageSnapshot(int i) {
        this.b = i;
    }

    public int m() {
        return this.b;
    }

    public Throwable j() {
        throw new NoFieldException("getThrowable", this);
    }

    public int k() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    public boolean g() {
        throw new NoFieldException("isResuming", this);
    }

    public String h() {
        throw new NoFieldException("getEtag", this);
    }

    public long i() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    public long d() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    public int a() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    public int c() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    public boolean e() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    public String f() {
        throw new NoFieldException("getFileName", this);
    }

    public boolean n() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.a ? 1 : 0));
        parcel.writeByte(b());
        parcel.writeInt(this.b);
    }

    MessageSnapshot(Parcel parcel) {
        this.b = parcel.readInt();
    }
}
