package cn.xiaochuankeji.tieba.abmgr.ui.a;

import cn.xiaochuankeji.tieba.abmgr.a.b;
import cn.xiaochuankeji.tieba.abmgr.data.RequireStyle;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;
import java.util.LinkedList;
import java.util.List;

public class c {
    public static List<a> a() {
        List<a> linkedList = new LinkedList();
        for (Requirement requirement : Requirement.values()) {
            linkedList.add(new d(requirement));
            if (requirement.requireStyleArray != null) {
                for (RequireStyle requireStyle : requirement.requireStyleArray) {
                    b bVar = new b(requireStyle, requirement);
                    bVar.c = b.a().a(requirement).equals(requireStyle);
                    linkedList.add(bVar);
                }
            }
        }
        return linkedList;
    }
}
