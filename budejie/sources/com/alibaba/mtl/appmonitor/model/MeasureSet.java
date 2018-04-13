package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MeasureSet implements Parcelable {
    public static final Creator<MeasureSet> CREATOR = new Creator<MeasureSet>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return b(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public MeasureSet b(Parcel parcel) {
            return MeasureSet.a(parcel);
        }

        public MeasureSet[] a(int i) {
            return new MeasureSet[i];
        }
    };
    private List<Measure> d = new ArrayList(3);

    public static MeasureSet create() {
        return new MeasureSet();
    }

    public static MeasureSet create(Collection<String> collection) {
        MeasureSet measureSet = new MeasureSet();
        if (collection != null) {
            for (String addMeasure : collection) {
                measureSet.addMeasure(addMeasure);
            }
        }
        return measureSet;
    }

    public static MeasureSet create(String[] strArr) {
        MeasureSet measureSet = new MeasureSet();
        if (strArr != null) {
            for (String addMeasure : strArr) {
                measureSet.addMeasure(addMeasure);
            }
        }
        return measureSet;
    }

    private MeasureSet() {
    }

    public boolean valid(MeasureValueSet measureValueSet) {
        if (this.d != null) {
            if (measureValueSet == null) {
                return false;
            }
            for (int i = 0; i < this.d.size(); i++) {
                Measure measure = (Measure) this.d.get(i);
                if (measure != null) {
                    String name = measure.getName();
                    if (!measureValueSet.containValue(name) || !measure.valid(measureValueSet.getValue(name))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public MeasureSet addMeasure(Measure measure) {
        if (!this.d.contains(measure)) {
            this.d.add(measure);
        }
        return this;
    }

    public MeasureSet addMeasure(String str) {
        return addMeasure(new Measure(str));
    }

    public Measure getMeasure(String str) {
        for (Measure measure : this.d) {
            if (measure.getName().equals(str)) {
                return measure;
            }
        }
        return null;
    }

    public List<Measure> getMeasures() {
        return this.d;
    }

    public void setConstantValue(MeasureValueSet measureValueSet) {
        if (this.d != null && measureValueSet != null) {
            for (Measure measure : this.d) {
                if (measure.getConstantValue() != null && measureValueSet.getValue(measure.getName()) == null) {
                    measureValueSet.setValue(measure.getName(), measure.getConstantValue().doubleValue());
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.d != null) {
            try {
                Object[] toArray = this.d.toArray();
                Parcelable[] parcelableArr = null;
                if (toArray != null) {
                    Measure[] measureArr = new Measure[toArray.length];
                    for (int i2 = 0; i2 < toArray.length; i2++) {
                        measureArr[i2] = (Measure) toArray[i2];
                    }
                    parcelableArr = measureArr;
                }
                parcel.writeParcelableArray(parcelableArr, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static MeasureSet a(Parcel parcel) {
        MeasureSet create = create();
        try {
            Parcelable[] readParcelableArray = parcel.readParcelableArray(MeasureSet.class.getClassLoader());
            if (readParcelableArray != null) {
                List arrayList = new ArrayList(readParcelableArray.length);
                for (Parcelable parcelable : readParcelableArray) {
                    arrayList.add((Measure) parcelable);
                }
                create.d = arrayList;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return create;
    }

    public void upateMeasures(List<Measure> list) {
        int size = this.d.size();
        int size2 = list.size();
        for (int i = 0; i < size; i++) {
            for (int i2 = 0; i2 < size2; i2++) {
                if (TextUtils.equals(((Measure) this.d.get(i)).name, ((Measure) list.get(i2)).name)) {
                    ((Measure) this.d.get(i)).setMax(((Measure) list.get(i2)).getMax());
                    ((Measure) this.d.get(i)).setMin(((Measure) list.get(i2)).getMin());
                }
            }
        }
    }

    public void upateMeasure(Measure measure) {
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(((Measure) this.d.get(i)).name, measure.name)) {
                ((Measure) this.d.get(i)).setMax(measure.getMax());
                ((Measure) this.d.get(i)).setMin(measure.getMin());
                ((Measure) this.d.get(i)).setConstantValue(measure.getConstantValue());
            }
        }
    }
}
