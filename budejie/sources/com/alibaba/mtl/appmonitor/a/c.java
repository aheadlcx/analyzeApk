package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.c.b;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureValue;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.log.e.i;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c extends d {
    private static final Long a = Long.valueOf(300000);
    /* renamed from: a */
    private Metric f24a;
    private DimensionValueSet b;
    /* renamed from: b */
    private MeasureValueSet f25b;
    /* renamed from: b */
    private Long f26b;
    private Map<String, MeasureValue> f;

    public boolean e() {
        long currentTimeMillis = System.currentTimeMillis();
        List measures = this.f24a.getMeasureSet().getMeasures();
        if (measures != null) {
            int size = measures.size();
            for (int i = 0; i < size; i++) {
                Measure measure = (Measure) measures.get(i);
                if (measure != null) {
                    double doubleValue = measure.getMax() != null ? measure.getMax().doubleValue() : (double) a.longValue();
                    MeasureValue measureValue = (MeasureValue) this.f.get(measure.getName());
                    if (!(measureValue == null || measureValue.isFinish() || ((double) currentTimeMillis) - measureValue.getValue() <= doubleValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f.isEmpty()) {
            this.f26b = Long.valueOf(currentTimeMillis);
        }
        this.f.put(str, (MeasureValue) a.a().a(MeasureValue.class, Double.valueOf((double) currentTimeMillis), Double.valueOf((double) (currentTimeMillis - this.f26b.longValue()))));
    }

    /* renamed from: a */
    public boolean m18a(String str) {
        MeasureValue measureValue = (MeasureValue) this.f.get(str);
        if (measureValue != null) {
            i.a("DurationEvent", "statEvent consumeTime. module:", this.o, " monitorPoint:", this.p, " measureName:", str, " time:", Double.valueOf(((double) System.currentTimeMillis()) - measureValue.getValue()));
            measureValue.setValue(((double) r4) - measureValue.getValue());
            measureValue.setFinish(true);
            this.f25b.setValue(str, measureValue);
            if (this.f24a.getMeasureSet().valid(this.f25b)) {
                return true;
            }
        }
        return false;
    }

    public void a(DimensionValueSet dimensionValueSet) {
        if (this.b == null) {
            this.b = dimensionValueSet;
        } else {
            this.b.addValues(dimensionValueSet);
        }
    }

    /* renamed from: a */
    public MeasureValueSet m17a() {
        return this.f25b;
    }

    public DimensionValueSet a() {
        return this.b;
    }

    public void clean() {
        super.clean();
        this.f24a = null;
        this.f26b = null;
        for (b a : this.f.values()) {
            a.a().a(a);
        }
        this.f.clear();
        if (this.f25b != null) {
            a.a().a(this.f25b);
            this.f25b = null;
        }
        if (this.b != null) {
            a.a().a(this.b);
            this.b = null;
        }
    }

    public void fill(Object... objArr) {
        super.fill(objArr);
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f24a = MetricRepo.getRepo().getMetric(this.o, this.p);
        if (this.f24a.getDimensionSet() != null) {
            this.b = (DimensionValueSet) a.a().a(DimensionValueSet.class, new Object[0]);
            this.f24a.getDimensionSet().setConstantValue(this.b);
        }
        this.f25b = (MeasureValueSet) a.a().a(MeasureValueSet.class, new Object[0]);
    }
}
