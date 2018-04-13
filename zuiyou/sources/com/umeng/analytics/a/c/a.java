package com.umeng.analytics.a.c;

import com.umeng.a.g;
import com.umeng.analytics.a.a.d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class a implements Serializable {
    private static final long a = 1;
    private Map<List<String>, b> b = new HashMap();
    private long c = 0;

    public Map<List<String>, b> a() {
        return this.b;
    }

    public void a(Map<List<String>, b> map) {
        if (this.b.size() <= 0) {
            this.b = map;
        } else {
            b(map);
        }
    }

    private void b(Map<List<String>, b> map) {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        Iterator it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            List list = (List) entry.getKey();
            Iterator it2 = this.b.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry2 = (Entry) it2.next();
                List list2 = (List) entry.getKey();
                if (list.equals(list2)) {
                    b bVar = (b) entry2.getValue();
                    a((b) entry.getValue(), bVar);
                    this.b.remove(list);
                    this.b.put(list, bVar);
                } else {
                    this.b.put(list2, entry2.getValue());
                }
            }
        }
    }

    private void a(b bVar, b bVar2) {
        bVar2.c(bVar2.g() + bVar.g());
        bVar2.b(bVar2.f() + bVar.f());
        bVar2.a(bVar2.e() + bVar.e());
        for (int i = 0; i < bVar.d().size(); i++) {
            bVar2.a((String) bVar.d().get(i));
        }
    }

    public long b() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(final com.umeng.analytics.a.b.a aVar, e eVar) {
        try {
            if (a(eVar.a())) {
                b bVar = (b) this.b.get(eVar.a());
                if (bVar != null) {
                    bVar.a(new com.umeng.analytics.a.b.a(this) {
                        final /* synthetic */ a b;

                        public void a(Object obj, boolean z) {
                            b bVar = (b) obj;
                            this.b.b.remove(bVar.a());
                            this.b.b.put(bVar.b(), bVar);
                            aVar.a(this, false);
                        }
                    }, eVar);
                    return;
                } else {
                    a(aVar, eVar.a(), eVar);
                    return;
                }
            }
            a(aVar, eVar.a(), eVar);
        } catch (Exception e) {
            g.d("aggregated faild!");
        }
    }

    public void a(com.umeng.analytics.a.b.a aVar, List<String> list, e eVar) {
        b bVar = new b();
        bVar.a(eVar);
        this.b.put(list, bVar);
        aVar.a(this, false);
    }

    public boolean a(List<?> list) {
        if (this.b == null || !this.b.containsKey(list)) {
            return false;
        }
        return true;
    }

    public void a(com.umeng.analytics.a.b.a aVar) {
        for (List list : this.b.keySet()) {
            if (!aVar.a()) {
                aVar.a(this.b.get(list), false);
            } else {
                return;
            }
        }
    }

    public int c() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public void d() {
        this.b.clear();
    }

    public boolean a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return false;
        }
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            arrayList.add(d.b((String) list.get(i)));
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        return arrayList.contains(list2);
    }

    public void a(com.umeng.analytics.a.b.a aVar, e eVar, List<String> list, List<String> list2) {
        while (list.size() >= 1) {
            try {
                if (list.size() == 1) {
                    if (a((List) list2, (List) list)) {
                        a(aVar, eVar, (List) list);
                        return;
                    } else {
                        aVar.a(Boolean.valueOf(false), false);
                        return;
                    }
                } else if (a((List) list2, (List) list)) {
                    a(aVar, eVar, (List) list);
                    return;
                } else {
                    list.remove(list.size() - 1);
                }
            } catch (Exception e) {
                g.d("overFlowAggregated faild");
                return;
            }
        }
    }

    private void a(com.umeng.analytics.a.b.a aVar, e eVar, List<String> list) {
        if (a((List) list)) {
            a(aVar, eVar);
        } else {
            a(aVar, (List) list, eVar);
        }
    }
}
