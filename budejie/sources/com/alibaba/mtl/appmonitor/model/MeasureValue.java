package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.c.b;

public class MeasureValue implements Parcelable, b {
    public static final Creator<MeasureValue> CREATOR = new MeasureValue$1();
    private Double d;
    private double e;
    private boolean n;

    @Deprecated
    public MeasureValue(double d) {
        this.e = d;
    }

    @Deprecated
    public MeasureValue(double d, double d2) {
        this.d = Double.valueOf(d2);
        this.e = d;
        this.n = false;
    }

    public static MeasureValue create() {
        return (MeasureValue) a.a().a(MeasureValue.class, new Object[0]);
    }

    public static MeasureValue create(double d) {
        return (MeasureValue) a.a().a(MeasureValue.class, new Object[]{Double.valueOf(d)});
    }

    public static MeasureValue create(double d, double d2) {
        return (MeasureValue) a.a().a(MeasureValue.class, new Object[]{Double.valueOf(d), Double.valueOf(d2)});
    }

    public Double getOffset() {
        return this.d;
    }

    public boolean isFinish() {
        return this.n;
    }

    public void setFinish(boolean z) {
        this.n = z;
    }

    public void setOffset(double d) {
        this.d = Double.valueOf(d);
    }

    public double getValue() {
        return this.e;
    }

    public void setValue(double d) {
        this.e = d;
    }

    public synchronized void merge(MeasureValue measureValue) {
        if (measureValue != null) {
            try {
                this.e += measureValue.getValue();
                if (measureValue.getOffset() != null) {
                    if (this.d == null) {
                        this.d = Double.valueOf(0.0d);
                    }
                    this.d = Double.valueOf(this.d.doubleValue() + measureValue.getOffset().doubleValue());
                }
            } catch (Throwable th) {
            }
        }
    }

    public synchronized void clean() {
        this.e = 0.0d;
        this.d = null;
        this.n = false;
    }

    public synchronized void fill(Object... objArr) {
        if (objArr != null) {
            if (objArr.length > 0) {
                this.e = ((Double) objArr[0]).doubleValue();
            }
            if (objArr.length > 1) {
                this.d = (Double) objArr[1];
                this.n = false;
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeInt(this.n ? 1 : 0);
            parcel.writeDouble(this.d == null ? 0.0d : this.d.doubleValue());
            parcel.writeDouble(this.e);
        } catch (Throwable th) {
        }
    }

    static MeasureValue a(Parcel parcel) {
        MeasureValue create;
        Throwable th;
        try {
            boolean z = parcel.readInt() != 0;
            Double valueOf = Double.valueOf(parcel.readDouble());
            double readDouble = parcel.readDouble();
            create = create();
            try {
                create.n = z;
                create.d = valueOf;
                create.e = readDouble;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return create;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            create = null;
            th = th4;
            th.printStackTrace();
            return create;
        }
        return create;
    }
}
