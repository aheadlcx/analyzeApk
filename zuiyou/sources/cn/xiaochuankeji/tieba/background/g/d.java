package cn.xiaochuankeji.tieba.background.g;

import java.util.LinkedList;
import java.util.List;

public class d {
    private List<e> a;

    private static class a {
        private static d a = new d();
    }

    private d() {
        this.a = new LinkedList();
    }

    public static d a() {
        return a.a;
    }

    public void a(e eVar) {
        this.a.add(eVar);
    }

    public void b(e eVar) {
        this.a.remove(eVar);
    }

    public void a(a aVar) {
        for (e a : this.a) {
            a.a(aVar);
        }
    }
}
