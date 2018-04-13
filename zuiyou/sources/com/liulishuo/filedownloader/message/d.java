package com.liulishuo.filedownloader.message;

import android.os.Parcel;

public abstract class d extends MessageSnapshot {

    public static class b extends d {
        private final boolean b;
        private final long c;

        b(int i, boolean z, long j) {
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

        b(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readLong();
        }

        public byte b() {
            return (byte) -3;
        }

        public long d() {
            return this.c;
        }

        public boolean e() {
            return this.b;
        }
    }

    public static class a extends b implements b {
        a(int i, boolean z, long j) {
            super(i, z, j);
        }
    }

    public static class c extends d {
        private final boolean b;
        private final long c;
        private final String d;
        private final String e;

        c(int i, boolean z, long j, String str, String str2) {
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

        c(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readLong();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }

        public String f() {
            return this.e;
        }

        public byte b() {
            return (byte) 2;
        }

        public boolean g() {
            return this.b;
        }

        public long d() {
            return this.c;
        }

        public String h() {
            return this.d;
        }
    }

    public static class d extends d {
        private final long b;
        private final Throwable c;

        d(int i, long j, Throwable th) {
            super(i);
            this.b = j;
            this.c = th;
        }

        public long i() {
            return this.b;
        }

        public byte b() {
            return (byte) -1;
        }

        public Throwable j() {
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

        d(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = (Throwable) parcel.readSerializable();
        }
    }

    public static class e extends d {
        private final long b;
        private final long c;

        e(e eVar) {
            this(eVar.m(), eVar.i(), eVar.d());
        }

        e(int i, long j, long j2) {
            super(i);
            this.b = j;
            this.c = j2;
        }

        public byte b() {
            return (byte) 1;
        }

        public long i() {
            return this.b;
        }

        public long d() {
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

        e(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = parcel.readLong();
        }
    }

    public static class f extends d {
        private final long b;

        f(int i, long j) {
            super(i);
            this.b = j;
        }

        public byte b() {
            return (byte) 3;
        }

        public long i() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }

        f(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
        }
    }

    public static class g extends d {
        private final int b;

        g(int i, long j, Throwable th, int i2) {
            super(i, j, th);
            this.b = i2;
        }

        public int k() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        g(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }

        public byte b() {
            return (byte) 5;
        }
    }

    public static class i extends e implements com.liulishuo.filedownloader.message.MessageSnapshot.a {
        i(int i, long j, long j2) {
            super(i, j, j2);
        }

        i(Parcel parcel) {
            super(parcel);
        }

        public MessageSnapshot l() {
            return new e((e) this);
        }

        public byte b() {
            return (byte) -4;
        }
    }

    public static class h extends i implements b {
        h(int i, long j, long j2) {
            super(i, j, j2);
        }
    }

    d(int i) {
        super(i);
        this.a = true;
    }

    d(Parcel parcel) {
        super(parcel);
    }

    public int a() {
        if (i() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) i();
    }

    public int c() {
        if (d() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) d();
    }
}
