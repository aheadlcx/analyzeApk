package org.ahocorasick.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntervalNode {
    private IntervalNode a = null;
    private IntervalNode b = null;
    private int c;
    private List<c> d = new ArrayList();

    private enum Direction {
        LEFT,
        RIGHT
    }

    public IntervalNode(List<c> list) {
        this.c = a((List) list);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (c cVar : list) {
            if (cVar.b() < this.c) {
                arrayList.add(cVar);
            } else if (cVar.a() > this.c) {
                arrayList2.add(cVar);
            } else {
                this.d.add(cVar);
            }
        }
        if (arrayList.size() > 0) {
            this.a = new IntervalNode(arrayList);
        }
        if (arrayList2.size() > 0) {
            this.b = new IntervalNode(arrayList2);
        }
    }

    public int a(List<c> list) {
        int i = -1;
        int i2 = -1;
        for (c cVar : list) {
            int a = cVar.a();
            int b = cVar.b();
            if (i2 == -1 || a < i2) {
                i2 = a;
            }
            if (i != -1 && b <= i) {
                b = i;
            }
            i = b;
        }
        return (i2 + i) / 2;
    }

    public List<c> a(c cVar) {
        List<c> arrayList = new ArrayList();
        if (this.c < cVar.a()) {
            a(cVar, arrayList, a(this.b, cVar));
            a(cVar, arrayList, c(cVar));
        } else if (this.c > cVar.b()) {
            a(cVar, arrayList, a(this.a, cVar));
            a(cVar, arrayList, b(cVar));
        } else {
            a(cVar, arrayList, this.d);
            a(cVar, arrayList, a(this.a, cVar));
            a(cVar, arrayList, a(this.b, cVar));
        }
        return arrayList;
    }

    protected void a(c cVar, List<c> list, List<c> list2) {
        for (c cVar2 : list2) {
            if (!cVar2.equals(cVar)) {
                list.add(cVar2);
            }
        }
    }

    protected List<c> b(c cVar) {
        return a(cVar, Direction.LEFT);
    }

    protected List<c> c(c cVar) {
        return a(cVar, Direction.RIGHT);
    }

    protected List<c> a(c cVar, Direction direction) {
        List<c> arrayList = new ArrayList();
        for (c cVar2 : this.d) {
            switch (direction) {
                case LEFT:
                    if (cVar2.a() > cVar.b()) {
                        break;
                    }
                    arrayList.add(cVar2);
                    break;
                case RIGHT:
                    if (cVar2.b() < cVar.a()) {
                        break;
                    }
                    arrayList.add(cVar2);
                    break;
                default:
                    break;
            }
        }
        return arrayList;
    }

    protected List<c> a(IntervalNode intervalNode, c cVar) {
        if (intervalNode != null) {
            return intervalNode.a(cVar);
        }
        return Collections.emptyList();
    }
}
