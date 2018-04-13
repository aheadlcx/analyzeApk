package android.arch.lifecycle;

import java.util.HashMap;

public class r {
    private final HashMap<String, o> a = new HashMap();

    final void a(String str, o oVar) {
        o oVar2 = (o) this.a.get(str);
        if (oVar2 != null) {
            oVar2.a();
        }
        this.a.put(str, oVar);
    }

    final o a(String str) {
        return (o) this.a.get(str);
    }

    public final void a() {
        for (o a : this.a.values()) {
            a.a();
        }
        this.a.clear();
    }
}
