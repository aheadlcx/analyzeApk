package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.ItemAnimator.ItemHolderInfo;
import android.support.v7.widget.RecyclerView.ViewHolder;

class dg {
    @VisibleForTesting
    final ArrayMap<ViewHolder, a> a = new ArrayMap();
    @VisibleForTesting
    final LongSparseArray<ViewHolder> b = new LongSparseArray();

    interface b {
        void processAppeared(ViewHolder viewHolder, @Nullable ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        void processDisappeared(ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2);

        void processPersistent(ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2);

        void unused(ViewHolder viewHolder);
    }

    static class a {
        static Pool<a> d = new SimplePool(20);
        int a;
        @Nullable
        ItemHolderInfo b;
        @Nullable
        ItemHolderInfo c;

        private a() {
        }

        static a a() {
            a aVar = (a) d.acquire();
            return aVar == null ? new a() : aVar;
        }

        static void a(a aVar) {
            aVar.a = 0;
            aVar.b = null;
            aVar.c = null;
            d.release(aVar);
        }

        static void b() {
            do {
            } while (d.acquire() != null);
        }
    }

    dg() {
    }

    void a() {
        this.a.clear();
        this.b.clear();
    }

    void a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        a aVar = (a) this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(viewHolder, aVar);
        }
        aVar.b = itemHolderInfo;
        aVar.a |= 4;
    }

    boolean a(ViewHolder viewHolder) {
        a aVar = (a) this.a.get(viewHolder);
        return (aVar == null || (aVar.a & 1) == 0) ? false : true;
    }

    @Nullable
    ItemHolderInfo b(ViewHolder viewHolder) {
        return a(viewHolder, 4);
    }

    @Nullable
    ItemHolderInfo c(ViewHolder viewHolder) {
        return a(viewHolder, 8);
    }

    private ItemHolderInfo a(ViewHolder viewHolder, int i) {
        ItemHolderInfo itemHolderInfo = null;
        int indexOfKey = this.a.indexOfKey(viewHolder);
        if (indexOfKey >= 0) {
            a aVar = (a) this.a.valueAt(indexOfKey);
            if (!(aVar == null || (aVar.a & i) == 0)) {
                aVar.a &= i ^ -1;
                if (i == 4) {
                    itemHolderInfo = aVar.b;
                } else if (i == 8) {
                    itemHolderInfo = aVar.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((aVar.a & 12) == 0) {
                    this.a.removeAt(indexOfKey);
                    a.a(aVar);
                }
            }
        }
        return itemHolderInfo;
    }

    void a(long j, ViewHolder viewHolder) {
        this.b.put(j, viewHolder);
    }

    void b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        a aVar = (a) this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(viewHolder, aVar);
        }
        aVar.a |= 2;
        aVar.b = itemHolderInfo;
    }

    boolean d(ViewHolder viewHolder) {
        a aVar = (a) this.a.get(viewHolder);
        return (aVar == null || (aVar.a & 4) == 0) ? false : true;
    }

    ViewHolder a(long j) {
        return (ViewHolder) this.b.get(j);
    }

    void c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        a aVar = (a) this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(viewHolder, aVar);
        }
        aVar.c = itemHolderInfo;
        aVar.a |= 8;
    }

    void e(ViewHolder viewHolder) {
        a aVar = (a) this.a.get(viewHolder);
        if (aVar == null) {
            aVar = a.a();
            this.a.put(viewHolder, aVar);
        }
        r0.a |= 1;
    }

    void f(ViewHolder viewHolder) {
        a aVar = (a) this.a.get(viewHolder);
        if (aVar != null) {
            aVar.a &= -2;
        }
    }

    void a(b bVar) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.a.keyAt(size);
            a aVar = (a) this.a.removeAt(size);
            if ((aVar.a & 3) == 3) {
                bVar.unused(viewHolder);
            } else if ((aVar.a & 1) != 0) {
                if (aVar.b == null) {
                    bVar.unused(viewHolder);
                } else {
                    bVar.processDisappeared(viewHolder, aVar.b, aVar.c);
                }
            } else if ((aVar.a & 14) == 14) {
                bVar.processAppeared(viewHolder, aVar.b, aVar.c);
            } else if ((aVar.a & 12) == 12) {
                bVar.processPersistent(viewHolder, aVar.b, aVar.c);
            } else if ((aVar.a & 4) != 0) {
                bVar.processDisappeared(viewHolder, aVar.b, null);
            } else if ((aVar.a & 8) != 0) {
                bVar.processAppeared(viewHolder, aVar.b, aVar.c);
            } else if ((aVar.a & 2) != 0) {
            }
            a.a(aVar);
        }
    }

    void g(ViewHolder viewHolder) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (viewHolder == this.b.valueAt(size)) {
                this.b.removeAt(size);
                break;
            }
        }
        a aVar = (a) this.a.remove(viewHolder);
        if (aVar != null) {
            a.a(aVar);
        }
    }

    void b() {
        a.b();
    }

    public void onViewDetached(ViewHolder viewHolder) {
        f(viewHolder);
    }
}
