package com.liulishuo.filedownloader.message;

import android.os.Parcel;

public abstract class h extends MessageSnapshot {

    public static class b extends h {
        private final boolean b;
        private final int c;

        b(int i, boolean z, int i2) {
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

        b(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readInt();
        }

        public byte b() {
            return (byte) -3;
        }

        public int c() {
            return this.c;
        }

        public boolean e() {
            return this.b;
        }
    }

    public static class a extends b implements b {
        a(int i, boolean z, int i2) {
            super(i, z, i2);
        }
    }

    public static class c extends h {
        private final boolean b;
        private final int c;
        private final String d;
        private final String e;

        c(int i, boolean z, int i2, String str, String str2) {
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

        c(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != (byte) 0;
            this.c = parcel.readInt();
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

        public int c() {
            return this.c;
        }

        public String h() {
            return this.d;
        }
    }

    public static class d extends h {
        private final int b;
        private final Throwable c;

        d(int i, int i2, Throwable th) {
            super(i);
            this.b = i2;
            this.c = th;
        }

        public int a() {
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
            parcel.writeInt(this.b);
            parcel.writeSerializable(this.c);
        }

        d(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = (Throwable) parcel.readSerializable();
        }
    }

    public static class e extends h {
        private final int b;
        private final int c;

        e(e eVar) {
            this(eVar.m(), eVar.a(), eVar.c());
        }

        e(int i, int i2, int i3) {
            super(i);
            this.b = i2;
            this.c = i3;
        }

        e(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
        }

        public byte b() {
            return (byte) 1;
        }

        public int a() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }

    public static class f extends h {
        private final int b;

        f(int i, int i2) {
            super(i);
            this.b = i2;
        }

        public byte b() {
            return (byte) 3;
        }

        public int a() {
            return this.b;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        f(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }
    }

    public static class g extends d {
        private final int b;

        g(int i, int i2, Throwable th, int i3) {
            super(i, i2, th);
            this.b = i3;
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
        i(int i, int i2, int i3) {
            super(i, i2, i3);
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
        h(int i, int i2, int i3) {
            super(i, i2, i3);
        }
    }

    h(int i) {
        super(i);
        this.a = false;
    }

    h(Parcel parcel) {
        super(parcel);
    }

    public long d() {
        return (long) c();
    }

    public long i() {
        return (long) a();
    }
}
