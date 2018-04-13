package com.alibaba.mtl.appmonitor.model;

import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.c.b;
import java.util.List;
import java.util.UUID;

public class Metric implements b {
    private DimensionSet b;
    /* renamed from: b */
    private MeasureSet f11b;
    private boolean g;
    private String o;
    private String p;
    private String r;
    private String s;
    private String z = null;

    public Metric(String str, String str2, MeasureSet measureSet, DimensionSet dimensionSet, boolean z) {
        this.o = str;
        this.p = str2;
        this.b = dimensionSet;
        this.f11b = measureSet;
        this.s = null;
        this.g = z;
    }

    public synchronized String getTransactionId() {
        if (this.r == null) {
            this.r = UUID.randomUUID().toString() + "$" + this.o + "$" + this.p;
        }
        return this.r;
    }

    public void resetTransactionId() {
        this.r = null;
    }

    public boolean valid(DimensionValueSet dimensionValueSet, MeasureValueSet measureValueSet) {
        boolean valid;
        boolean z = true;
        if (this.b != null) {
            valid = this.b.valid(dimensionValueSet);
        } else {
            valid = true;
        }
        Metric metric = MetricRepo.getRepo().getMetric("config_prefix" + this.o, "config_prefix" + this.p);
        if (metric == null || metric.getMeasureSet() == null || measureValueSet == null || measureValueSet.getMap() == null || this.f11b == null) {
            if (this.f11b == null) {
                z = valid;
            } else if (!(valid && this.f11b.valid(measureValueSet))) {
                z = false;
            }
            return z;
        }
        List measures = metric.getMeasureSet().getMeasures();
        for (String str : measureValueSet.getMap().keySet()) {
            Measure a = a(str, measures);
            if (a == null) {
                a = a(str, this.f11b.getMeasures());
            }
            if (a == null) {
                return false;
            }
            if (!a.valid(measureValueSet.getValue(str))) {
                return false;
            }
        }
        return valid;
    }

    private Measure a(String str, List<Measure> list) {
        if (list != null) {
            for (Measure measure : list) {
                if (TextUtils.equals(str, measure.name)) {
                    return measure;
                }
            }
        }
        return null;
    }

    public String getModule() {
        return this.o;
    }

    public String getMonitorPoint() {
        return this.p;
    }

    public DimensionSet getDimensionSet() {
        return this.b;
    }

    public MeasureSet getMeasureSet() {
        return this.f11b;
    }

    public synchronized boolean isCommitDetail() {
        boolean z;
        if ("1".equalsIgnoreCase(this.z)) {
            z = true;
        } else if ("0".equalsIgnoreCase(this.z)) {
            z = false;
        } else {
            z = this.g;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.o == null ? 0 : this.o.hashCode()) + (((this.s == null ? 0 : this.s.hashCode()) + 31) * 31)) * 31;
        if (this.p != null) {
            i = this.p.hashCode();
        }
        return hashCode + i;
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
        Metric metric = (Metric) obj;
        if (this.s == null) {
            if (metric.s != null) {
                return false;
            }
        } else if (!this.s.equals(metric.s)) {
            return false;
        }
        if (this.o == null) {
            if (metric.o != null) {
                return false;
            }
        } else if (!this.o.equals(metric.o)) {
            return false;
        }
        if (this.p == null) {
            if (metric.p != null) {
                return false;
            }
            return true;
        } else if (this.p.equals(metric.p)) {
            return true;
        } else {
            return false;
        }
    }

    public void clean() {
        this.o = null;
        this.p = null;
        this.s = null;
        this.g = false;
        this.b = null;
        this.f11b = null;
        this.r = null;
    }

    public void fill(Object... objArr) {
        this.o = (String) objArr[0];
        this.p = (String) objArr[1];
        if (objArr.length > 2) {
            this.s = (String) objArr[2];
        }
    }

    public synchronized void setCommitDetailFromConfig(String str) {
        this.z = str;
    }
}
