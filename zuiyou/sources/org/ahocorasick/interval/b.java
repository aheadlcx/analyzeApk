package org.ahocorasick.interval;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class b {
    private IntervalNode a = null;

    public b(List<c> list) {
        this.a = new IntervalNode(list);
    }

    public List<c> a(List<c> list) {
        Collections.sort(list, new e());
        Set<c> treeSet = new TreeSet();
        for (c cVar : list) {
            if (!treeSet.contains(cVar)) {
                treeSet.addAll(a(cVar));
            }
        }
        for (c cVar2 : treeSet) {
            list.remove(cVar2);
        }
        Collections.sort(list, new d());
        return list;
    }

    public List<c> a(c cVar) {
        return this.a.a(cVar);
    }
}
