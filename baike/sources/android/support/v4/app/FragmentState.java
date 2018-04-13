package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR = new s();
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    final boolean j;
    Bundle k;
    Fragment l;

    FragmentState(Fragment fragment) {
        this.a = fragment.getClass().getName();
        this.b = fragment.mIndex;
        this.c = fragment.mFromLayout;
        this.d = fragment.mFragmentId;
        this.e = fragment.mContainerId;
        this.f = fragment.mTag;
        this.g = fragment.mRetainInstance;
        this.h = fragment.mDetached;
        this.i = fragment.mArguments;
        this.j = fragment.mHidden;
    }

    FragmentState(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readInt() != 0;
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.g = z;
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.h = z;
        this.i = parcel.readBundle();
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        this.j = z2;
        this.k = parcel.readBundle();
    }

    public Fragment instantiate(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment, FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (this.l == null) {
            Context b = fragmentHostCallback.b();
            if (this.i != null) {
                this.i.setClassLoader(b.getClassLoader());
            }
            if (fragmentContainer != null) {
                this.l = fragmentContainer.instantiate(b, this.a, this.i);
            } else {
                this.l = Fragment.instantiate(b, this.a, this.i);
            }
            if (this.k != null) {
                this.k.setClassLoader(b.getClassLoader());
                this.l.mSavedFragmentState = this.k;
            }
            this.l.setIndex(this.b, fragment);
            this.l.mFromLayout = this.c;
            this.l.mRestored = true;
            this.l.mFragmentId = this.d;
            this.l.mContainerId = this.e;
            this.l.mTag = this.f;
            this.l.mRetainInstance = this.g;
            this.l.mDetached = this.h;
            this.l.mHidden = this.j;
            this.l.mFragmentManager = fragmentHostCallback.d;
            if (k.a) {
                Log.v("FragmentManager", "Instantiated fragment " + this.l);
            }
        }
        this.l.mChildNonConfig = fragmentManagerNonConfig;
        return this.l;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c ? 1 : 0);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        if (this.g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeBundle(this.i);
        if (!this.j) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeBundle(this.k);
    }
}
