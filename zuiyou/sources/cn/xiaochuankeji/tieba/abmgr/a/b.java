package cn.xiaochuankeji.tieba.abmgr.a;

import cn.xiaochuankeji.tieba.abmgr.data.RequireStyle;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class b {
    private HashMap<Requirement, RequireStyle> a;
    private HashSet<a> b;

    private static class a {
        private static b a = new b();
    }

    private b() {
        this.a = new HashMap();
        this.b = new HashSet();
        for (Requirement requirement : Requirement.values()) {
            for (RequireStyle requireStyle : requirement.requireStyleArray) {
                if (requireStyle.value == cn.xiaochuankeji.tieba.background.a.a().getInt(requirement.key, 0)) {
                    this.a.put(requirement, requireStyle);
                }
            }
        }
    }

    public static b a() {
        return a.a;
    }

    public void a(a aVar) {
        this.b.add(aVar);
    }

    public void b(a aVar) {
        this.b.remove(aVar);
    }

    public void a(Requirement requirement, RequireStyle requireStyle) {
        this.a.put(requirement, requireStyle);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(requirement, requireStyle);
        }
        cn.xiaochuankeji.tieba.background.a.a().edit().putInt("ab_short_refresh_btn", requireStyle.value).apply();
    }

    public RequireStyle a(Requirement requirement) {
        return (RequireStyle) this.a.get(requirement);
    }
}
