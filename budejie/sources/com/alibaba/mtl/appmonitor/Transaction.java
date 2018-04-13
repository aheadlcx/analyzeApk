package com.alibaba.mtl.appmonitor;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import java.util.UUID;

public class Transaction implements Parcelable {
    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return b(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Transaction b(Parcel parcel) {
            return Transaction.a(parcel);
        }

        public Transaction[] a(int i) {
            return new Transaction[i];
        }
    };
    protected Integer a;
    protected DimensionValueSet b;
    private Object lock;
    protected String o;
    protected String p;
    protected String r;

    Transaction(Integer num, String str, String str2, DimensionValueSet dimensionValueSet) {
        this.a = num;
        this.o = str;
        this.p = str2;
        this.r = UUID.randomUUID().toString();
        this.b = dimensionValueSet;
        this.lock = new Object();
    }

    public void begin(String str) {
        if (AppMonitor.f4a != null) {
            try {
                AppMonitor.f4a.transaction_begin(this, str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void end(String str) {
        if (AppMonitor.f4a != null) {
            try {
                AppMonitor.f4a.transaction_end(this, str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void addDimensionValues(DimensionValueSet dimensionValueSet) {
        synchronized (this.lock) {
            if (this.b == null) {
                this.b = dimensionValueSet;
            } else {
                this.b.addValues(dimensionValueSet);
            }
        }
    }

    public void addDimensionValues(String str, String str2) {
        synchronized (this.lock) {
            if (this.b == null) {
                this.b = (DimensionValueSet) a.a().a(DimensionValueSet.class, new Object[0]);
            }
            this.b.setValue(str, str2);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.a.intValue());
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.r);
    }

    static Transaction a(Parcel parcel) {
        Transaction transaction = new Transaction();
        try {
            transaction.b = (DimensionValueSet) parcel.readParcelable(Transaction.class.getClassLoader());
            transaction.a = Integer.valueOf(parcel.readInt());
            transaction.o = parcel.readString();
            transaction.p = parcel.readString();
            transaction.r = parcel.readString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return transaction;
    }
}
