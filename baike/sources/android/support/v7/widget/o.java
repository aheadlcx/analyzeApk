package android.support.v7.widget;

import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

class o implements a {
    final ArrayList<b> a;
    final ArrayList<b> b;
    final a c;
    Runnable d;
    final boolean e;
    final bf f;
    private Pool<b> g;
    private int h;

    interface a {
        ViewHolder findViewHolder(int i);

        void markViewHoldersUpdated(int i, int i2, Object obj);

        void offsetPositionsForAdd(int i, int i2);

        void offsetPositionsForMove(int i, int i2);

        void offsetPositionsForRemovingInvisible(int i, int i2);

        void offsetPositionsForRemovingLaidOutOrNewView(int i, int i2);

        void onDispatchFirstPass(b bVar);

        void onDispatchSecondPass(b bVar);
    }

    static class b {
        int a;
        int b;
        Object c;
        int d;

        b(int i, int i2, int i3, Object obj) {
            this.a = i;
            this.b = i2;
            this.d = i3;
            this.c = obj;
        }

        String a() {
            switch (this.a) {
                case 1:
                    return "add";
                case 2:
                    return "rm";
                case 4:
                    return "up";
                case 8:
                    return "mv";
                default:
                    return "??";
            }
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.b + "c:" + this.d + ",p:" + this.c + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a != bVar.a) {
                return false;
            }
            if (this.a == 8 && Math.abs(this.d - this.b) == 1 && this.d == bVar.b && this.b == bVar.d) {
                return true;
            }
            if (this.d != bVar.d) {
                return false;
            }
            if (this.b != bVar.b) {
                return false;
            }
            if (this.c != null) {
                if (this.c.equals(bVar.c)) {
                    return true;
                }
                return false;
            } else if (bVar.c != null) {
                return false;
            } else {
                return true;
            }
        }

        public int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.d;
        }
    }

    o(a aVar) {
        this(aVar, false);
    }

    o(a aVar, boolean z) {
        this.g = new SimplePool(30);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.h = 0;
        this.c = aVar;
        this.e = z;
        this.f = new bf(this);
    }

    void a() {
        a(this.a);
        a(this.b);
        this.h = 0;
    }

    void b() {
        this.f.a(this.a);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.a.get(i);
            switch (bVar.a) {
                case 1:
                    e(bVar);
                    break;
                case 2:
                    b(bVar);
                    break;
                case 4:
                    c(bVar);
                    break;
                case 8:
                    a(bVar);
                    break;
            }
            if (this.d != null) {
                this.d.run();
            }
        }
        this.a.clear();
    }

    void c() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.c.onDispatchSecondPass((b) this.b.get(i));
        }
        a(this.b);
        this.h = 0;
    }

    private void a(b bVar) {
        f(bVar);
    }

    private void b(b bVar) {
        int i = bVar.b;
        int i2 = bVar.b + bVar.d;
        Object obj = -1;
        int i3 = bVar.b;
        int i4 = 0;
        while (i3 < i2) {
            Object obj2;
            int i5;
            if (this.c.findViewHolder(i3) != null || c(i3)) {
                if (obj == null) {
                    d(obtainUpdateOp(2, i, i4, null));
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = 1;
            } else {
                if (obj == 1) {
                    f(obtainUpdateOp(2, i, i4, null));
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                obj = null;
            }
            if (obj2 != null) {
                i5 = i3 - i4;
                i3 = i2 - i4;
                i2 = 1;
            } else {
                int i6 = i3;
                i3 = i2;
                i2 = i4 + 1;
                i5 = i6;
            }
            i4 = i2;
            i2 = i3;
            i3 = i5 + 1;
        }
        if (i4 != bVar.d) {
            recycleUpdateOp(bVar);
            bVar = obtainUpdateOp(2, i, i4, null);
        }
        if (obj == null) {
            d(bVar);
        } else {
            f(bVar);
        }
    }

    private void c(b bVar) {
        int i = bVar.b;
        int i2 = bVar.b + bVar.d;
        int i3 = bVar.b;
        Object obj = -1;
        int i4 = 0;
        while (i3 < i2) {
            int i5;
            Object obj2;
            if (this.c.findViewHolder(i3) != null || c(i3)) {
                if (obj == null) {
                    d(obtainUpdateOp(4, i, i4, bVar.c));
                    i4 = 0;
                    i = i3;
                }
                i5 = i;
                i = i4;
                obj2 = 1;
            } else {
                if (obj == 1) {
                    f(obtainUpdateOp(4, i, i4, bVar.c));
                    i4 = 0;
                    i = i3;
                }
                i5 = i;
                i = i4;
                obj2 = null;
            }
            i3++;
            Object obj3 = obj2;
            i4 = i + 1;
            i = i5;
            obj = obj3;
        }
        if (i4 != bVar.d) {
            Object obj4 = bVar.c;
            recycleUpdateOp(bVar);
            bVar = obtainUpdateOp(4, i, i4, obj4);
        }
        if (obj == null) {
            d(bVar);
        } else {
            f(bVar);
        }
    }

    private void d(b bVar) {
        if (bVar.a == 1 || bVar.a == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int i;
        int d = d(bVar.b, bVar.a);
        int i2 = bVar.b;
        switch (bVar.a) {
            case 2:
                i = 0;
                break;
            case 4:
                i = 1;
                break;
            default:
                throw new IllegalArgumentException("op should be remove or update." + bVar);
        }
        int i3 = 1;
        int i4 = d;
        d = i2;
        for (i2 = 1; i2 < bVar.d; i2++) {
            Object obj;
            int d2 = d(bVar.b + (i * i2), bVar.a);
            int i5;
            switch (bVar.a) {
                case 2:
                    if (d2 != i4) {
                        obj = null;
                        break;
                    } else {
                        i5 = 1;
                        break;
                    }
                case 4:
                    if (d2 != i4 + 1) {
                        obj = null;
                        break;
                    } else {
                        i5 = 1;
                        break;
                    }
                default:
                    obj = null;
                    break;
            }
            if (obj != null) {
                i3++;
            } else {
                b obtainUpdateOp = obtainUpdateOp(bVar.a, i4, i3, bVar.c);
                a(obtainUpdateOp, d);
                recycleUpdateOp(obtainUpdateOp);
                if (bVar.a == 4) {
                    d += i3;
                }
                i3 = 1;
                i4 = d2;
            }
        }
        Object obj2 = bVar.c;
        recycleUpdateOp(bVar);
        if (i3 > 0) {
            b obtainUpdateOp2 = obtainUpdateOp(bVar.a, i4, i3, obj2);
            a(obtainUpdateOp2, d);
            recycleUpdateOp(obtainUpdateOp2);
        }
    }

    void a(b bVar, int i) {
        this.c.onDispatchFirstPass(bVar);
        switch (bVar.a) {
            case 2:
                this.c.offsetPositionsForRemovingInvisible(i, bVar.d);
                return;
            case 4:
                this.c.markViewHoldersUpdated(i, bVar.d, bVar.c);
                return;
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int d(int i, int i2) {
        int i3;
        int i4 = i;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            b bVar = (b) this.b.get(size);
            if (bVar.a == 8) {
                int i5;
                int i6;
                if (bVar.b < bVar.d) {
                    i5 = bVar.b;
                    i3 = bVar.d;
                } else {
                    i5 = bVar.d;
                    i3 = bVar.b;
                }
                if (i4 < i5 || i4 > r2) {
                    if (i4 < bVar.b) {
                        if (i2 == 1) {
                            bVar.b++;
                            bVar.d++;
                            i6 = i4;
                        } else if (i2 == 2) {
                            bVar.b--;
                            bVar.d--;
                        }
                    }
                    i6 = i4;
                } else if (i5 == bVar.b) {
                    if (i2 == 1) {
                        bVar.d++;
                    } else if (i2 == 2) {
                        bVar.d--;
                    }
                    i6 = i4 + 1;
                } else {
                    if (i2 == 1) {
                        bVar.b++;
                    } else if (i2 == 2) {
                        bVar.b--;
                    }
                    i6 = i4 - 1;
                }
                i4 = i6;
            } else if (bVar.b <= i4) {
                if (bVar.a == 1) {
                    i4 -= bVar.d;
                } else if (bVar.a == 2) {
                    i4 += bVar.d;
                }
            } else if (i2 == 1) {
                bVar.b++;
            } else if (i2 == 2) {
                bVar.b--;
            }
        }
        for (i3 = this.b.size() - 1; i3 >= 0; i3--) {
            bVar = (b) this.b.get(i3);
            if (bVar.a == 8) {
                if (bVar.d == bVar.b || bVar.d < 0) {
                    this.b.remove(i3);
                    recycleUpdateOp(bVar);
                }
            } else if (bVar.d <= 0) {
                this.b.remove(i3);
                recycleUpdateOp(bVar);
            }
        }
        return i4;
    }

    private boolean c(int i) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = (b) this.b.get(i2);
            if (bVar.a == 8) {
                if (a(bVar.d, i2 + 1) == i) {
                    return true;
                }
            } else if (bVar.a == 1) {
                int i3 = bVar.b + bVar.d;
                for (int i4 = bVar.b; i4 < i3; i4++) {
                    if (a(i4, i2 + 1) == i) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void e(b bVar) {
        f(bVar);
    }

    private void f(b bVar) {
        this.b.add(bVar);
        switch (bVar.a) {
            case 1:
                this.c.offsetPositionsForAdd(bVar.b, bVar.d);
                return;
            case 2:
                this.c.offsetPositionsForRemovingLaidOutOrNewView(bVar.b, bVar.d);
                return;
            case 4:
                this.c.markViewHoldersUpdated(bVar.b, bVar.d, bVar.c);
                return;
            case 8:
                this.c.offsetPositionsForMove(bVar.b, bVar.d);
                return;
            default:
                throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    boolean d() {
        return this.a.size() > 0;
    }

    boolean a(int i) {
        return (this.h & i) != 0;
    }

    int b(int i) {
        return a(i, 0);
    }

    int a(int i, int i2) {
        int size = this.b.size();
        int i3 = i;
        while (i2 < size) {
            b bVar = (b) this.b.get(i2);
            if (bVar.a == 8) {
                if (bVar.b == i3) {
                    i3 = bVar.d;
                } else {
                    if (bVar.b < i3) {
                        i3--;
                    }
                    if (bVar.d <= i3) {
                        i3++;
                    }
                }
            } else if (bVar.b > i3) {
                continue;
            } else if (bVar.a == 2) {
                if (i3 < bVar.b + bVar.d) {
                    return -1;
                }
                i3 -= bVar.d;
            } else if (bVar.a == 1) {
                i3 += bVar.d;
            }
            i2++;
        }
        return i3;
    }

    boolean a(int i, int i2, Object obj) {
        boolean z = true;
        if (i2 < 1) {
            return false;
        }
        this.a.add(obtainUpdateOp(4, i, i2, obj));
        this.h |= 4;
        if (this.a.size() != 1) {
            z = false;
        }
        return z;
    }

    boolean b(int i, int i2) {
        boolean z = true;
        if (i2 < 1) {
            return false;
        }
        this.a.add(obtainUpdateOp(1, i, i2, null));
        this.h |= 1;
        if (this.a.size() != 1) {
            z = false;
        }
        return z;
    }

    boolean c(int i, int i2) {
        boolean z = true;
        if (i2 < 1) {
            return false;
        }
        this.a.add(obtainUpdateOp(2, i, i2, null));
        this.h |= 2;
        if (this.a.size() != 1) {
            z = false;
        }
        return z;
    }

    boolean a(int i, int i2, int i3) {
        boolean z = true;
        if (i == i2) {
            return false;
        }
        if (i3 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.a.add(obtainUpdateOp(8, i, i2, null));
        this.h |= 8;
        if (this.a.size() != 1) {
            z = false;
        }
        return z;
    }

    void e() {
        c();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.a.get(i);
            switch (bVar.a) {
                case 1:
                    this.c.onDispatchSecondPass(bVar);
                    this.c.offsetPositionsForAdd(bVar.b, bVar.d);
                    break;
                case 2:
                    this.c.onDispatchSecondPass(bVar);
                    this.c.offsetPositionsForRemovingInvisible(bVar.b, bVar.d);
                    break;
                case 4:
                    this.c.onDispatchSecondPass(bVar);
                    this.c.markViewHoldersUpdated(bVar.b, bVar.d, bVar.c);
                    break;
                case 8:
                    this.c.onDispatchSecondPass(bVar);
                    this.c.offsetPositionsForMove(bVar.b, bVar.d);
                    break;
            }
            if (this.d != null) {
                this.d.run();
            }
        }
        a(this.a);
        this.h = 0;
    }

    public int applyPendingUpdatesToPosition(int i) {
        int size = this.a.size();
        int i2 = i;
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = (b) this.a.get(i3);
            switch (bVar.a) {
                case 1:
                    if (bVar.b > i2) {
                        break;
                    }
                    i2 += bVar.d;
                    break;
                case 2:
                    if (bVar.b <= i2) {
                        if (bVar.b + bVar.d <= i2) {
                            i2 -= bVar.d;
                            break;
                        }
                        return -1;
                    }
                    continue;
                case 8:
                    if (bVar.b != i2) {
                        if (bVar.b < i2) {
                            i2--;
                        }
                        if (bVar.d > i2) {
                            break;
                        }
                        i2++;
                        break;
                    }
                    i2 = bVar.d;
                    break;
                default:
                    break;
            }
        }
        return i2;
    }

    boolean f() {
        return (this.b.isEmpty() || this.a.isEmpty()) ? false : true;
    }

    public b obtainUpdateOp(int i, int i2, int i3, Object obj) {
        b bVar = (b) this.g.acquire();
        if (bVar == null) {
            return new b(i, i2, i3, obj);
        }
        bVar.a = i;
        bVar.b = i2;
        bVar.d = i3;
        bVar.c = obj;
        return bVar;
    }

    public void recycleUpdateOp(b bVar) {
        if (!this.e) {
            bVar.c = null;
            this.g.release(bVar);
        }
    }

    void a(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            recycleUpdateOp((b) list.get(i));
        }
        list.clear();
    }
}
