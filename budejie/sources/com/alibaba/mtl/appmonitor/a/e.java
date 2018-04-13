package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.c.a;
import com.alibaba.mtl.appmonitor.c.b;
import com.alibaba.mtl.appmonitor.f.c;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.appmonitor.model.MetricValueSet;
import com.alibaba.mtl.appmonitor.model.UTDimensionValueSet;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.r;
import com.alibaba.mtl.log.model.LogField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class e {
    private static e a;
    /* renamed from: a */
    private AtomicInteger f23a = new AtomicInteger(0);
    private AtomicInteger b = new AtomicInteger(0);
    private AtomicInteger c = new AtomicInteger(0);
    private Map<String, c> g = new ConcurrentHashMap();
    private Map<UTDimensionValueSet, MetricValueSet> h = new ConcurrentHashMap();

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    private e() {
    }

    private UTDimensionValueSet a(int i) {
        UTDimensionValueSet uTDimensionValueSet = (UTDimensionValueSet) a.a().a(UTDimensionValueSet.class, new Object[0]);
        uTDimensionValueSet.setValue(LogField.ACCESS.toString(), com.alibaba.mtl.log.a.b());
        uTDimensionValueSet.setValue(LogField.ACCESS_SUBTYPE.toString(), com.alibaba.mtl.log.a.c());
        uTDimensionValueSet.setValue(LogField.USERID.toString(), com.alibaba.mtl.log.a.d());
        uTDimensionValueSet.setValue(LogField.USERNICK.toString(), com.alibaba.mtl.log.a.e());
        uTDimensionValueSet.setValue(LogField.EVENTID.toString(), String.valueOf(i));
        return uTDimensionValueSet;
    }

    public void a(int i, String str, String str2, String str3) {
        UTDimensionValueSet a = a(i);
        ((a) a(a, str, str2, str3, a.class)).f();
        if (com.alibaba.mtl.log.a.a.g()) {
            a aVar = (a) a.a().a(a.class, Integer.valueOf(i), str, str2, str3);
            aVar.f();
            c.a(a, aVar);
        }
        a(f.a(i), this.f23a);
    }

    public void a(int i, String str, String str2, String str3, String str4, String str5) {
        UTDimensionValueSet a = a(i);
        a aVar = (a) a(a, str, str2, str3, a.class);
        aVar.g();
        aVar.a(str4, str5);
        if (com.alibaba.mtl.log.a.a.g()) {
            aVar = (a) a.a().a(a.class, Integer.valueOf(i), str, str2, str3);
            aVar.g();
            aVar.a(str4, str5);
            c.a(a, aVar);
        }
        a(f.a(i), this.f23a);
    }

    public void a(int i, String str, String str2, String str3, double d) {
        UTDimensionValueSet a = a(i);
        ((b) a(a, str, str2, str3, b.class)).a(d);
        if (com.alibaba.mtl.log.a.a.g()) {
            b bVar = (b) a.a().a(b.class, Integer.valueOf(i), str, str2, str3);
            bVar.a(d);
            c.a(a, bVar);
        }
        a(f.a(i), this.b);
    }

    public void a(int i, String str, String str2, MeasureValueSet measureValueSet, DimensionValueSet dimensionValueSet) {
        Metric metric = MetricRepo.getRepo().getMetric(str, str2);
        if (metric != null) {
            if (metric.getDimensionSet() != null) {
                metric.getDimensionSet().setConstantValue(dimensionValueSet);
            }
            if (metric.getMeasureSet() != null) {
                metric.getMeasureSet().setConstantValue(measureValueSet);
            }
            UTDimensionValueSet a = a(i);
            ((g) a(a, str, str2, null, g.class)).a(dimensionValueSet, measureValueSet);
            if (com.alibaba.mtl.log.a.a.g()) {
                g gVar = (g) a.a().a(g.class, Integer.valueOf(i), str, str2);
                gVar.a(dimensionValueSet, measureValueSet);
                c.a(a, gVar);
            }
            a(f.a(i), this.c);
            return;
        }
        i.a("EventRepo", (Object) "metric is null");
    }

    public void a(Integer num, String str, String str2, String str3) {
        String a = a(str, str2);
        if (a != null) {
            a(a, num, str, str2, str3);
        }
    }

    public void a(String str, Integer num, String str2, String str3, String str4) {
        Metric metric = MetricRepo.getRepo().getMetric(str2, str3);
        if (metric != null && metric.getMeasureSet() != null && metric.getMeasureSet().getMeasure(str4) != null) {
            c cVar;
            synchronized (c.class) {
                cVar = (c) this.g.get(str);
                if (cVar == null) {
                    cVar = (c) a.a().a(c.class, num, str2, str3);
                    this.g.put(str, cVar);
                }
            }
            cVar.a(str4);
        }
    }

    public void a(String str, String str2, String str3) {
        String a = a(str, str2);
        if (a != null) {
            a(a, str3, true);
        }
    }

    public void a(String str, String str2, boolean z) {
        b bVar = (c) this.g.get(str);
        if (bVar != null && bVar.a(str2)) {
            this.g.remove(str);
            if (z) {
                b(bVar.o, bVar.p);
            }
            a(bVar.e, bVar.o, bVar.p, bVar.a(), bVar.a());
            a.a().a(bVar);
        }
    }

    public void a(String str, Integer num, String str2, String str3, DimensionValueSet dimensionValueSet) {
        c cVar;
        synchronized (c.class) {
            cVar = (c) this.g.get(str);
            if (cVar == null) {
                cVar = (c) a.a().a(c.class, num, str2, str3);
                this.g.put(str, cVar);
            }
        }
        cVar.a(dimensionValueSet);
    }

    private String a(String str, String str2) {
        Metric metric = MetricRepo.getRepo().getMetric(str, str2);
        if (metric != null) {
            return metric.getTransactionId();
        }
        return null;
    }

    private void b(String str, String str2) {
        Metric metric = MetricRepo.getRepo().getMetric(str, str2);
        if (metric != null) {
            metric.resetTransactionId();
        }
    }

    private d a(UTDimensionValueSet uTDimensionValueSet, String str, String str2, String str3, Class<? extends d> cls) {
        if (com.alibaba.mtl.appmonitor.f.b.c(str) && com.alibaba.mtl.appmonitor.f.b.c(str2)) {
            Integer eventId = uTDimensionValueSet.getEventId();
            if (eventId != null) {
                MetricValueSet metricValueSet;
                synchronized (this.h) {
                    metricValueSet = (MetricValueSet) this.h.get(uTDimensionValueSet);
                    if (metricValueSet == null) {
                        metricValueSet = (MetricValueSet) a.a().a(MetricValueSet.class, new Object[0]);
                        this.h.put(uTDimensionValueSet, metricValueSet);
                    }
                }
                return metricValueSet.getEvent(eventId, str, str2, str3, cls);
            }
        }
        return null;
    }

    private void a(f fVar, AtomicInteger atomicInteger) {
        i.a("EventRepo", fVar.toString(), " EVENT size:", String.valueOf(atomicInteger.incrementAndGet()));
        if (atomicInteger.incrementAndGet() >= fVar.b()) {
            i.a("EventRepo", fVar.toString(), " event size exceed trigger count.");
            atomicInteger.set(0);
            a(fVar.a());
        }
    }

    /* renamed from: a */
    public Map<UTDimensionValueSet, List<d>> m15a(int i) {
        Map<UTDimensionValueSet, List<d>> hashMap = new HashMap();
        synchronized (this.h) {
            List arrayList = new ArrayList(this.h.keySet());
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                UTDimensionValueSet uTDimensionValueSet = (UTDimensionValueSet) arrayList.get(i2);
                if (uTDimensionValueSet != null && uTDimensionValueSet.getEventId().intValue() == i) {
                    hashMap.put(uTDimensionValueSet, ((MetricValueSet) this.h.get(uTDimensionValueSet)).getEvents());
                    this.h.remove(uTDimensionValueSet);
                }
            }
        }
        return hashMap;
    }

    public void h() {
        List arrayList = new ArrayList(this.g.keySet());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            String str = (String) arrayList.get(i);
            c cVar = (c) this.g.get(str);
            if (cVar != null && cVar.e()) {
                this.g.remove(str);
            }
        }
    }

    /* renamed from: a */
    public void m16a(int i) {
        final Map a = a(i);
        r.a().b(new Runnable(this) {
            final /* synthetic */ e b;

            public void run() {
                c.b(a);
            }
        });
    }
}
