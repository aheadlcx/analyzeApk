package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.os.IResultReceiver.Stub;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ResultReceiver implements Parcelable {
    public static final Creator<ResultReceiver> CREATOR = new d();
    final boolean a;
    final Handler b;
    IResultReceiver c;

    class a extends Stub {
        final /* synthetic */ ResultReceiver a;

        a(ResultReceiver resultReceiver) {
            this.a = resultReceiver;
        }

        public void send(int i, Bundle bundle) {
            if (this.a.b != null) {
                this.a.b.post(new b(this.a, i, bundle));
            } else {
                this.a.a(i, bundle);
            }
        }
    }

    class b implements Runnable {
        final int a;
        final Bundle b;
        final /* synthetic */ ResultReceiver c;

        b(ResultReceiver resultReceiver, int i, Bundle bundle) {
            this.c = resultReceiver;
            this.a = i;
            this.b = bundle;
        }

        public void run() {
            this.c.a(this.a, this.b);
        }
    }

    public ResultReceiver(Handler handler) {
        this.a = true;
        this.b = handler;
    }

    public void send(int i, Bundle bundle) {
        if (this.a) {
            if (this.b != null) {
                this.b.post(new b(this, i, bundle));
            } else {
                a(i, bundle);
            }
        } else if (this.c != null) {
            try {
                this.c.send(i, bundle);
            } catch (RemoteException e) {
            }
        }
    }

    protected void a(int i, Bundle bundle) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.c == null) {
                this.c = new a(this);
            }
            parcel.writeStrongBinder(this.c.asBinder());
        }
    }

    ResultReceiver(Parcel parcel) {
        this.a = false;
        this.b = null;
        this.c = Stub.asInterface(parcel.readStrongBinder());
    }
}
