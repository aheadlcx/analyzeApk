package com.liulishuo.filedownloader.d;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.NotificationCompat;
import com.iflytek.aiui.AIUIConstant;
import com.liulishuo.filedownloader.g.f;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

public class c implements Parcelable {
    public static final Creator<c> CREATOR = new Creator<c>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public c a(Parcel parcel) {
            return new c(parcel);
        }

        public c[] a(int i) {
            return new c[i];
        }
    };
    private int a;
    private String b;
    private String c;
    private boolean d;
    private String e;
    private final AtomicInteger f;
    private final AtomicLong g;
    private long h;
    private String i;
    private String j;
    private int k;
    private boolean l;

    public void a(int i) {
        this.a = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(String str, boolean z) {
        this.c = str;
        this.d = z;
    }

    public void a(byte b) {
        this.f.set(b);
    }

    public void a(long j) {
        this.g.set(j);
    }

    public void b(long j) {
        this.g.addAndGet(j);
    }

    public void c(long j) {
        this.l = j > 2147483647L;
        this.h = j;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return f.a(c(), l(), m());
    }

    public String e() {
        if (d() == null) {
            return null;
        }
        return f.d(d());
    }

    public byte f() {
        return (byte) this.f.get();
    }

    public long g() {
        return this.g.get();
    }

    public long h() {
        return this.h;
    }

    public boolean i() {
        return this.h == -1;
    }

    public String j() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public String k() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public boolean l() {
        return this.d;
    }

    public String m() {
        return this.e;
    }

    public void b(int i) {
        this.k = i;
    }

    public int n() {
        return this.k;
    }

    public void o() {
        this.k = 1;
    }

    public ContentValues p() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(a()));
        contentValues.put("url", b());
        contentValues.put(AIUIConstant.RES_TYPE_PATH, c());
        contentValues.put(NotificationCompat.CATEGORY_STATUS, Byte.valueOf(f()));
        contentValues.put("sofar", Long.valueOf(g()));
        contentValues.put("total", Long.valueOf(h()));
        contentValues.put("errMsg", k());
        contentValues.put("etag", j());
        contentValues.put("connectionCount", Integer.valueOf(n()));
        contentValues.put("pathAsDirectory", Boolean.valueOf(l()));
        if (l() && m() != null) {
            contentValues.put(FFmpegMediaMetadataRetriever.METADATA_KEY_FILENAME, m());
        }
        return contentValues;
    }

    public boolean q() {
        return this.l;
    }

    public String toString() {
        return f.a("id[%d], url[%s], path[%s], status[%d], sofar[%s], total[%d], etag[%s], %s", Integer.valueOf(this.a), this.b, this.c, Integer.valueOf(this.f.get()), this.g, Long.valueOf(this.h), this.j, super.toString());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.e);
        parcel.writeByte((byte) this.f.get());
        parcel.writeLong(this.g.get());
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        if (!this.l) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
    }

    public c() {
        this.g = new AtomicLong();
        this.f = new AtomicInteger();
    }

    protected c(Parcel parcel) {
        boolean z = true;
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readByte() != (byte) 0;
        this.e = parcel.readString();
        this.f = new AtomicInteger(parcel.readByte());
        this.g = new AtomicLong(parcel.readLong());
        this.h = parcel.readLong();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.l = z;
    }
}
