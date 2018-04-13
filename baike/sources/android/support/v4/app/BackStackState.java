package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR = new d();
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList<String> j;
    final ArrayList<String> k;
    final boolean l;

    public BackStackState(c cVar) {
        int size = cVar.b.size();
        this.a = new int[(size * 6)];
        if (cVar.i) {
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                a aVar = (a) cVar.b.get(i2);
                int i3 = i + 1;
                this.a[i] = aVar.a;
                int i4 = i3 + 1;
                this.a[i3] = aVar.b != null ? aVar.b.mIndex : -1;
                int i5 = i4 + 1;
                this.a[i4] = aVar.c;
                i3 = i5 + 1;
                this.a[i5] = aVar.d;
                i5 = i3 + 1;
                this.a[i3] = aVar.e;
                i = i5 + 1;
                this.a[i5] = aVar.f;
            }
            this.b = cVar.g;
            this.c = cVar.h;
            this.d = cVar.k;
            this.e = cVar.m;
            this.f = cVar.n;
            this.g = cVar.o;
            this.h = cVar.p;
            this.i = cVar.q;
            this.j = cVar.r;
            this.k = cVar.s;
            this.l = cVar.t;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public BackStackState(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.createStringArrayList();
        this.k = parcel.createStringArrayList();
        this.l = parcel.readInt() != 0;
    }

    public c instantiate(k kVar) {
        int i = 0;
        c cVar = new c(kVar);
        int i2 = 0;
        while (i < this.a.length) {
            a aVar = new a();
            int i3 = i + 1;
            aVar.a = this.a[i];
            if (k.a) {
                Log.v("FragmentManager", "Instantiate " + cVar + " op #" + i2 + " base fragment #" + this.a[i3]);
            }
            int i4 = i3 + 1;
            i = this.a[i3];
            if (i >= 0) {
                aVar.b = (Fragment) kVar.f.get(i);
            } else {
                aVar.b = null;
            }
            i3 = i4 + 1;
            aVar.c = this.a[i4];
            i4 = i3 + 1;
            aVar.d = this.a[i3];
            i3 = i4 + 1;
            aVar.e = this.a[i4];
            i4 = i3 + 1;
            aVar.f = this.a[i3];
            cVar.c = aVar.c;
            cVar.d = aVar.d;
            cVar.e = aVar.e;
            cVar.f = aVar.f;
            cVar.a(aVar);
            i2++;
            i = i4;
        }
        cVar.g = this.b;
        cVar.h = this.c;
        cVar.k = this.d;
        cVar.m = this.e;
        cVar.i = true;
        cVar.n = this.f;
        cVar.o = this.g;
        cVar.p = this.h;
        cVar.q = this.i;
        cVar.r = this.j;
        cVar.s = this.k;
        cVar.t = this.l;
        cVar.a(1);
        return cVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeIntArray(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList(this.j);
        parcel.writeStringList(this.k);
        if (this.l) {
            i2 = 1;
        }
        parcel.writeInt(i2);
    }
}
