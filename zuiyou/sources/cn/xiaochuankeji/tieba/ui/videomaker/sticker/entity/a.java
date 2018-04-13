package cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import cn.xiaochuankeji.tieba.json.UgcEmoji;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;

public class a implements Parcelable {
    public static final Creator<a> CREATOR = new Creator<a>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public a a(Parcel parcel) {
            return new a(parcel);
        }

        public a[] a(int i) {
            return new a[i];
        }
    };
    public static final a a = new a();
    public static final a b = new a();
    public int c = -1;
    public String d;
    public ServerImgJson e;
    public String f;
    public String g;
    public int h;
    public int i;
    public int j;
    public int k;
    public String l;
    @DrawableRes
    public int m = -1;
    @DrawableRes
    public int n = -1;
    public String o;
    public String p;
    public String q;
    public long r;
    @FloatRange(from = -100.0d, to = 100.0d)
    public float s = -1.0f;
    public UgcEmoji t;

    static {
        a.p = "S_ADD_NAME";
        b.p = "S_DELETE_NAME";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeSerializable(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeString(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeLong(this.r);
        parcel.writeFloat(this.s);
    }

    protected a(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = (ServerImgJson) parcel.readSerializable();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readString();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readLong();
        this.s = parcel.readFloat();
    }

    public String toString() {
        return "Sticker{status=" + this.c + ", path='" + this.d + '\'' + ", remote=" + this.e + ", source='" + this.f + '\'' + ", preview='" + this.g + '\'' + ", width=" + this.h + ", height=" + this.i + ", rawX=" + this.j + ", rawY=" + this.k + ", mime_type='" + this.l + '\'' + ", res=" + this.m + ", type='" + this.o + '\'' + ", name='" + this.p + '\'' + ", cr='" + this.q + '\'' + ", size=" + this.r + ", percent=" + this.s + '}';
    }
}
