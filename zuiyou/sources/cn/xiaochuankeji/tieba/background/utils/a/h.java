package cn.xiaochuankeji.tieba.background.utils.a;

import cn.xiaochuankeji.tieba.background.utils.monitor.a.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class h {
    private ArrayList<String> a = new ArrayList();

    public void a(String str) {
        this.a.add(str);
    }

    public void a(String str, long j, String str2, int i) {
        Map hashMap = new HashMap();
        hashMap.put("querys", new ArrayList(this.a));
        hashMap.put("pos", Integer.valueOf(i));
        b.a().a(str, "search", j, 0, str2, hashMap);
    }
}
