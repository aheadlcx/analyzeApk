package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class h implements Serializable {
    private static final long a = 1;
    private Map<List<String>, i> b = new HashMap();
    private long c = 0;

    public Map<List<String>, i> a() {
        return this.b;
    }

    public void a(Map<List<String>, i> map) {
        if (this.b.size() <= 0) {
            this.b = map;
        } else {
            b(map);
        }
    }

    private void b(Map<List<String>, i> map) {
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
                    i iVar = (i) entry2.getValue();
                    a((i) entry.getValue(), iVar);
                    this.b.remove(list);
                    this.b.put(list, iVar);
                } else {
                    this.b.put(list2, entry2.getValue());
                }
            }
        }
    }

    private void a(i iVar, i iVar2) {
        iVar2.c(iVar2.g() + iVar.g());
        iVar2.b(iVar2.f() + iVar.f());
        iVar2.a(iVar2.e() + iVar.e());
        for (int i = 0; i < iVar.d().size(); i++) {
            iVar2.a((String) iVar.d().get(i));
        }
    }

    public long b() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(final f fVar, l lVar) {
        try {
            if (a(lVar.a())) {
                i iVar = (i) this.b.get(lVar.a());
                if (iVar != null) {
                    iVar.a(new f(this) {
                        final /* synthetic */ h b;

                        public void a(Object obj, boolean z) {
                            i iVar = (i) obj;
                            this.b.b.remove(iVar.a());
                            this.b.b.put(iVar.b(), iVar);
                            fVar.a(this, false);
                        }
                    }, lVar);
                    return;
                } else {
                    a(fVar, lVar.a(), lVar);
                    return;
                }
            }
            a(fVar, lVar.a(), lVar);
        } catch (Exception e) {
            by.e("aggregated faild!");
        }
    }

    public void a(f fVar, List<String> list, l lVar) {
        i iVar = new i();
        iVar.a(lVar);
        this.b.put(list, iVar);
        fVar.a(this, false);
    }

    public boolean a(List<?> list) {
        if (this.b == null || !this.b.containsKey(list)) {
            return false;
        }
        return true;
    }

    public void a(f fVar) {
        for (List list : this.b.keySet()) {
            if (!fVar.a()) {
                fVar.a(this.b.get(list), false);
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

    public void a(f fVar, l lVar, List<String> list, List<String> list2) {
        while (list.size() >= 1) {
            try {
                if (list.size() == 1) {
                    if (a((List) list2, (List) list)) {
                        a(fVar, lVar, (List) list);
                        return;
                    } else {
                        fVar.a(Boolean.valueOf(false), false);
                        return;
                    }
                } else if (a((List) list2, (List) list)) {
                    a(fVar, lVar, (List) list);
                    return;
                } else {
                    list.remove(list.size() - 1);
                }
            } catch (Exception e) {
                by.e("overFlowAggregated faild");
                return;
            }
        }
    }

    private void a(f fVar, l lVar, List<String> list) {
        if (a((List) list)) {
            a(fVar, lVar);
        } else {
            a(fVar, (List) list, lVar);
        }
    }
}
