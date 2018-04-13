package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.c.b;
import com.alibaba.mtl.appmonitor.c.d;
import com.alibaba.mtl.appmonitor.c.e;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureValue;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.log.e.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class g extends d {
    private Metric a;
    private Map<DimensionValueSet, a> j;

    public class a {
        final /* synthetic */ g a;
        private List<MeasureValueSet> b = new ArrayList();
        private int count = 0;
        private int l = 0;

        public a(g gVar) {
            this.a = gVar;
        }

        /* renamed from: a */
        public void m20a(MeasureValueSet measureValueSet) {
            if (measureValueSet == null) {
                return;
            }
            if (this.a.a != null && this.a.a.isCommitDetail()) {
                this.b.add(a(measureValueSet));
            } else if (this.b.isEmpty()) {
                this.b.add(a(measureValueSet));
            } else {
                ((MeasureValueSet) this.b.get(0)).merge(measureValueSet);
            }
        }

        private MeasureValueSet a(MeasureValueSet measureValueSet) {
            MeasureValueSet measureValueSet2 = (MeasureValueSet) com.alibaba.mtl.appmonitor.c.a.a().a(MeasureValueSet.class, new Object[0]);
            if (!(this.a.a == null || this.a.a.getMeasureSet() == null)) {
                List measures = this.a.a.getMeasureSet().getMeasures();
                if (measures != null) {
                    int size = measures.size();
                    for (int i = 0; i < size; i++) {
                        Measure measure = (Measure) measures.get(i);
                        if (measure != null) {
                            MeasureValue measureValue = (MeasureValue) com.alibaba.mtl.appmonitor.c.a.a().a(MeasureValue.class, new Object[0]);
                            MeasureValue value = measureValueSet.getValue(measure.getName());
                            if (value.getOffset() != null) {
                                measureValue.setOffset(value.getOffset().doubleValue());
                            }
                            measureValue.setValue(value.getValue());
                            measureValueSet2.setValue(measure.getName(), measureValue);
                        }
                    }
                }
            }
            return measureValueSet2;
        }

        public List<Map<String, Map<String, Double>>> a() {
            if (this.b == null || this.b.isEmpty()) {
                return null;
            }
            List<Map<String, Map<String, Double>>> arrayList = new ArrayList();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                MeasureValueSet measureValueSet = (MeasureValueSet) this.b.get(i);
                if (measureValueSet != null) {
                    Map map = measureValueSet.getMap();
                    if (!(map == null || map.isEmpty())) {
                        Map hashMap = new HashMap();
                        for (Entry entry : map.entrySet()) {
                            Map hashMap2 = new HashMap();
                            String str = (String) entry.getKey();
                            MeasureValue measureValue = (MeasureValue) entry.getValue();
                            hashMap2.put("value", Double.valueOf(measureValue.getValue()));
                            if (measureValue.getOffset() != null) {
                                hashMap2.put("offset", measureValue.getOffset());
                            }
                            hashMap.put(str, hashMap2);
                        }
                        arrayList.add(hashMap);
                    }
                }
            }
            return arrayList;
        }

        public void i() {
            this.count++;
        }

        public void j() {
            this.l++;
        }
    }

    public synchronized void a(DimensionValueSet dimensionValueSet, MeasureValueSet measureValueSet) {
        a aVar;
        boolean valid;
        if (dimensionValueSet == null) {
            DimensionValueSet dimensionValueSet2 = (DimensionValueSet) com.alibaba.mtl.appmonitor.c.a.a().a(DimensionValueSet.class, new Object[0]);
            dimensionValueSet2.addValues(dimensionValueSet);
            dimensionValueSet = dimensionValueSet2;
        }
        if (this.j.containsKey(dimensionValueSet)) {
            aVar = (a) this.j.get(dimensionValueSet);
        } else {
            dimensionValueSet2 = (DimensionValueSet) com.alibaba.mtl.appmonitor.c.a.a().a(DimensionValueSet.class, new Object[0]);
            dimensionValueSet2.addValues(dimensionValueSet);
            aVar = new a(this);
            this.j.put(dimensionValueSet2, aVar);
        }
        if (this.a != null) {
            valid = this.a.valid(dimensionValueSet, measureValueSet);
        } else {
            valid = false;
        }
        if (valid) {
            aVar.i();
            aVar.a(measureValueSet);
        } else {
            aVar.j();
            if (this.a.isCommitDetail()) {
                aVar.a(measureValueSet);
            }
        }
        i.a("StatEvent", "entity  count:", Integer.valueOf(aVar.count), " noise:", Integer.valueOf(aVar.l));
    }

    public synchronized JSONObject a() {
        JSONObject a;
        a = super.a();
        if (this.a != null) {
            a.put("isCommitDetail", String.valueOf(this.a.isCommitDetail()));
        }
        JSONArray jSONArray = (JSONArray) com.alibaba.mtl.appmonitor.c.a.a().a(d.class, new Object[0]);
        if (this.j != null) {
            for (Entry entry : this.j.entrySet()) {
                Object jSONObject;
                JSONObject jSONObject2 = (JSONObject) com.alibaba.mtl.appmonitor.c.a.a().a(e.class, new Object[0]);
                DimensionValueSet dimensionValueSet = (DimensionValueSet) entry.getKey();
                a aVar = (a) entry.getValue();
                Integer valueOf = Integer.valueOf(aVar.count);
                Integer valueOf2 = Integer.valueOf(aVar.l);
                jSONObject2.put("count", valueOf);
                jSONObject2.put("noise", valueOf2);
                String str = "dimensions";
                if (dimensionValueSet != null) {
                    jSONObject = new JSONObject(dimensionValueSet.getMap());
                } else {
                    try {
                        jSONObject = "";
                    } catch (Exception e) {
                    }
                }
                jSONObject2.put(str, jSONObject);
                List a2 = aVar.a();
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < a2.size(); i++) {
                    JSONObject jSONObject3 = new JSONObject();
                    Map map = (Map) a2.get(i);
                    if (map != null) {
                        Set<String> keySet = map.keySet();
                        if (keySet != null) {
                            for (String str2 : keySet) {
                                if (map.get(str2) != null) {
                                    jSONObject3.put(str2, new JSONObject((Map) map.get(str2)));
                                } else {
                                    jSONObject3.put(str2, "");
                                }
                            }
                        }
                    }
                    jSONArray2.put(jSONObject3);
                }
                jSONObject2.put("measures", jSONArray2);
                jSONArray.put(jSONObject2);
            }
        }
        a.put("values", jSONArray);
        return a;
    }

    public synchronized void clean() {
        super.clean();
        this.a = null;
        for (b a : this.j.keySet()) {
            com.alibaba.mtl.appmonitor.c.a.a().a(a);
        }
        this.j.clear();
    }

    public void fill(Object... objArr) {
        super.fill(objArr);
        if (this.j == null) {
            this.j = new HashMap();
        }
        this.a = MetricRepo.getRepo().getMetric(this.o, this.p);
    }
}
