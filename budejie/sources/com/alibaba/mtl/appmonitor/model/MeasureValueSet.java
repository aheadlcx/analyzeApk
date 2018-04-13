package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.c.b;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MeasureValueSet implements Parcelable, b {
    public static final Creator<MeasureValueSet> CREATOR = new MeasureValueSet$1();
    private Map<String, MeasureValue> map = new LinkedHashMap();

    public static MeasureValueSet create() {
        return (MeasureValueSet) a.a().a(MeasureValueSet.class, new Object[0]);
    }

    @Deprecated
    public static MeasureValueSet create(int i) {
        return (MeasureValueSet) a.a().a(MeasureValueSet.class, new Object[0]);
    }

    public static MeasureValueSet create(Map<String, Double> map) {
        MeasureValueSet measureValueSet = (MeasureValueSet) a.a().a(MeasureValueSet.class, new Object[0]);
        if (map != null) {
            for (String str : map.keySet()) {
                if (((Double) map.get(str)) != null) {
                    measureValueSet.map.put(str, a.a().a(MeasureValue.class, new Object[]{r2}));
                }
            }
        }
        return measureValueSet;
    }

    public static MeasureValueSet fromStringMap(Map<String, String> map) {
        MeasureValueSet measureValueSet = (MeasureValueSet) a.a().a(MeasureValueSet.class, new Object[0]);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (a((String) entry.getValue()) != null) {
                    measureValueSet.map.put(entry.getKey(), a.a().a(MeasureValue.class, new Object[]{r2}));
                }
            }
        }
        return measureValueSet;
    }

    private static Double a(String str) {
        try {
            return Double.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MeasureValueSet setValue(String str, double d) {
        this.map.put(str, a.a().a(MeasureValue.class, new Object[]{Double.valueOf(d)}));
        return this;
    }

    public void setValue(String str, MeasureValue measureValue) {
        this.map.put(str, measureValue);
    }

    public MeasureValue getValue(String str) {
        return (MeasureValue) this.map.get(str);
    }

    public Map<String, MeasureValue> getMap() {
        return this.map;
    }

    public void setMap(Map<String, MeasureValue> map) {
        this.map = map;
    }

    public boolean containValue(String str) {
        return this.map.containsKey(str);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public void merge(MeasureValueSet measureValueSet) {
        for (String str : this.map.keySet()) {
            ((MeasureValue) this.map.get(str)).merge(measureValueSet.getValue(str));
        }
    }

    public void clean() {
        for (MeasureValue a : this.map.values()) {
            a.a().a(a);
        }
        this.map.clear();
    }

    public void fill(Object... objArr) {
        if (this.map == null) {
            this.map = new LinkedHashMap();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.map);
    }

    static MeasureValueSet a(Parcel parcel) {
        MeasureValueSet measureValueSet = null;
        try {
            measureValueSet = create();
            measureValueSet.map = parcel.readHashMap(DimensionValueSet.class.getClassLoader());
            return measureValueSet;
        } catch (Throwable th) {
            return measureValueSet;
        }
    }
}
