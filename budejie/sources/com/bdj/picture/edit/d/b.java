package com.bdj.picture.edit.d;

import com.bdj.picture.edit.e.c;
import com.bdj.picture.edit.widget.HSuperImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class b {
    protected Map<Object, List<c>> a = new HashMap();

    public void a(Object obj, c cVar) {
        if (!this.a.containsKey(obj)) {
            this.a.put(obj, new ArrayList());
        }
        List list = (List) this.a.get(obj);
        if (list != null) {
            list.add(cVar);
        }
    }

    public void b(Object obj, c cVar) {
        if (this.a.containsKey(obj)) {
            List list = (List) this.a.get(obj);
            if (list != null && !list.isEmpty()) {
                list.remove(cVar);
            }
        }
    }

    public boolean a(Object obj, boolean z) {
        List<c> list = (List) this.a.get(obj);
        if (list == null || list.isEmpty()) {
            return true;
        }
        boolean z2 = true;
        for (c cVar : list) {
            boolean z3;
            if (((HSuperImageView) cVar).J) {
                z3 = false;
            } else {
                z3 = z2;
            }
            cVar.a(z);
            z2 = z3;
        }
        return z2;
    }
}
