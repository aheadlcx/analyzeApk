package com.alibaba.mtl.appmonitor.model;

import com.alibaba.mtl.appmonitor.a.d;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.c.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetricValueSet implements b {
    private Map<Metric, d> l = Collections.synchronizedMap(new HashMap());

    public List<d> getEvents() {
        return new ArrayList(this.l.values());
    }

    public d getEvent(Integer num, String str, String str2, String str3, Class<? extends d> cls) {
        int i = 1;
        if (num.intValue() == f.STAT.a()) {
            i = 0;
            b metric = MetricRepo.getRepo().getMetric(str, str2);
        } else {
            Object obj = (Metric) a.a().a(Metric.class, str, str2, str3);
        }
        d dVar = null;
        if (metric != null) {
            if (this.l.containsKey(metric)) {
                dVar = (d) this.l.get(metric);
            } else {
                synchronized (MetricValueSet.class) {
                    dVar = (d) a.a().a(cls, num, str, str2, str3);
                    this.l.put(metric, dVar);
                }
                i = 0;
            }
            if (i != 0) {
                a.a().a(metric);
            }
        }
        return dVar;
    }

    public void clean() {
        for (b a : this.l.values()) {
            a.a().a(a);
        }
        this.l.clear();
    }

    public void fill(Object... objArr) {
        if (this.l == null) {
            this.l = Collections.synchronizedMap(new HashMap());
        }
    }
}
