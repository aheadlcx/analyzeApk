package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Measure implements Parcelable {
    public static final Creator<Measure> CREATOR = new Creator<Measure>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return b(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Measure b(Parcel parcel) {
            return Measure.a(parcel);
        }

        public Measure[] a(int i) {
            return new Measure[i];
        }
    };
    protected Double a;
    protected Double b;
    protected Double c;
    protected String name;

    public Measure(String str) {
        this(str, Double.valueOf(0.0d));
    }

    public Measure(String str, Double d) {
        this(str, d, Double.valueOf(0.0d), null);
    }

    public Measure(String str, Double d, Double d2, Double d3) {
        double d4 = 0.0d;
        this.a = Double.valueOf(0.0d);
        this.b = Double.valueOf(0.0d);
        this.c = Double.valueOf(0.0d);
        this.a = d2;
        this.b = d3;
        this.name = str;
        if (d != null) {
            d4 = d.doubleValue();
        }
        this.c = Double.valueOf(d4);
    }

    public void setRange(Double d, Double d2) {
        this.a = d;
        this.b = d2;
    }

    public Double getMin() {
        return this.a;
    }

    public void setMin(Double d) {
        this.a = d;
    }

    public Double getMax() {
        return this.b;
    }

    public void setMax(Double d) {
        this.b = d;
    }

    public String getName() {
        return this.name;
    }

    public Double getConstantValue() {
        return this.c;
    }

    public void setConstantValue(Double d) {
        this.c = d;
    }

    public boolean valid(MeasureValue measureValue) {
        Double valueOf = Double.valueOf(measureValue.getValue());
        return valueOf != null && ((this.a == null || valueOf.doubleValue() >= this.a.doubleValue()) && (this.b == null || valueOf.doubleValue() <= this.b.doubleValue()));
    }

    public int hashCode() {
        return (this.name == null ? 0 : this.name.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Measure measure = (Measure) obj;
        if (this.name == null) {
            if (measure.name != null) {
                return false;
            }
            return true;
        } else if (this.name.equals(measure.name)) {
            return true;
        } else {
            return false;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        try {
            int i3;
            parcel.writeInt(this.b == null ? 0 : 1);
            if (this.b != null) {
                parcel.writeDouble(this.b.doubleValue());
            }
            if (this.a == null) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            parcel.writeInt(i3);
            if (this.a != null) {
                parcel.writeDouble(this.a.doubleValue());
            }
            parcel.writeString(this.name);
            if (this.c != null) {
                i2 = 1;
            }
            parcel.writeInt(i2);
            if (this.c != null) {
                parcel.writeDouble(this.c.doubleValue());
            }
        } catch (Throwable th) {
        }
    }

    static Measure a(Parcel parcel) {
        Object obj = 1;
        try {
            Double valueOf;
            Object obj2;
            Double valueOf2;
            Double valueOf3;
            if ((parcel.readInt() == 0 ? 1 : null) == null) {
                valueOf = Double.valueOf(parcel.readDouble());
            } else {
                valueOf = null;
            }
            if (parcel.readInt() == 0) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 == null) {
                valueOf2 = Double.valueOf(parcel.readDouble());
            } else {
                valueOf2 = null;
            }
            String readString = parcel.readString();
            if (parcel.readInt() != 0) {
                obj = null;
            }
            if (obj == null) {
                valueOf3 = Double.valueOf(parcel.readDouble());
            } else {
                valueOf3 = null;
            }
            return new Measure(readString, valueOf3, valueOf2, valueOf);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
