package com.alibaba.baichuan.android.trade.config.a;

import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class b {
    private static final String c = b.class.getSimpleName();
    private Map a = new HashMap();
    private Set b = new HashSet();

    public synchronized void a(a aVar) {
        Map a = aVar.a();
        AlibcLogger.d(c, "config更新，config更新的参数值为:" + (a != null ? a : null) + "   configMem当前值=" + (this.a != null ? this.a : null) + "   filter名单=" + (this.b != null ? this.b.toString() : null));
        if (a != null) {
            for (String str : a.keySet()) {
                for (Entry entry : ((Map) a.get(str)).entrySet()) {
                    if (!this.b.contains(entry.getKey()) || !str.equals("albbTradeConfig")) {
                        a(str, (String) entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        AlibcLogger.d(c, "更新后configMemdo=" + (this.a != null ? this.a.toString() : null));
    }

    public synchronized void a(String str) {
        this.b.add(str);
    }

    public synchronized void a(String str, String str2, Object obj) {
        Map map = (Map) this.a.get(str);
        if (map == null) {
            map = new HashMap();
            this.a.put(str, map);
        }
        map.put(str2, obj);
    }

    public synchronized Object b(String str, String str2, Object obj) {
        Map map = (Map) this.a.get(str);
        if (!(map == null || map.get(str2) == null)) {
            obj = map.get(str2);
        }
        return obj;
    }
}
