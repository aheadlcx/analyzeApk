package cn.xiaochuankeji.tieba.background.c;

import cn.xiaochuankeji.tieba.background.topic.Topic;
import java.util.ArrayList;
import java.util.HashMap;

public class e {
    private static e b;
    private HashMap<Long, a> a = new HashMap();

    public class a {
        public ArrayList<Topic> a = new ArrayList();
        public boolean b;
        public long c;
        final /* synthetic */ e d;

        public a(e eVar) {
            this.d = eVar;
        }
    }

    private e() {
    }

    public static e a() {
        if (b == null) {
            b = new e();
        }
        return b;
    }

    public void a(long j, ArrayList<Topic> arrayList, boolean z, long j2) {
        if (((a) this.a.get(Long.valueOf(j))) != null) {
            this.a.remove(Long.valueOf(j));
        }
        a aVar = new a(this);
        aVar.a.addAll(arrayList);
        aVar.b = z;
        aVar.c = j2;
        this.a.put(Long.valueOf(j), aVar);
    }

    public a a(long j) {
        return (a) this.a.get(Long.valueOf(j));
    }
}
