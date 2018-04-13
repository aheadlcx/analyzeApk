package android.support.v7.widget;

import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class ao implements Runnable {
    static final ThreadLocal<ao> a = new ThreadLocal();
    static Comparator<b> e = new ap();
    ArrayList<RecyclerView> b = new ArrayList();
    long c;
    long d;
    private ArrayList<b> f = new ArrayList();

    static class a implements LayoutPrefetchRegistry {
        int a;
        int b;
        int[] c;
        int d;

        a() {
        }

        void a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        void a(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            if (this.c != null) {
                Arrays.fill(this.c, -1);
            }
            LayoutManager layoutManager = recyclerView.m;
            if (recyclerView.l != null && layoutManager != null && layoutManager.isItemPrefetchEnabled()) {
                if (z) {
                    if (!recyclerView.e.d()) {
                        layoutManager.collectInitialPrefetchPositions(recyclerView.l.getItemCount(), this);
                    }
                } else if (!recyclerView.hasPendingAdapterUpdates()) {
                    layoutManager.collectAdjacentPrefetchPositions(this.a, this.b, recyclerView.B, this);
                }
                if (this.d > layoutManager.x) {
                    layoutManager.x = this.d;
                    layoutManager.y = z;
                    recyclerView.d.a();
                }
            }
        }

        public void addPosition(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            } else if (i2 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            } else {
                int i3 = this.d * 2;
                if (this.c == null) {
                    this.c = new int[4];
                    Arrays.fill(this.c, -1);
                } else if (i3 >= this.c.length) {
                    Object obj = this.c;
                    this.c = new int[(i3 * 2)];
                    System.arraycopy(obj, 0, this.c, 0, obj.length);
                }
                this.c[i3] = i;
                this.c[i3 + 1] = i2;
                this.d++;
            }
        }

        boolean a(int i) {
            if (this.c == null) {
                return false;
            }
            int i2 = this.d * 2;
            for (int i3 = 0; i3 < i2; i3 += 2) {
                if (this.c[i3] == i) {
                    return true;
                }
            }
            return false;
        }

        void a() {
            if (this.c != null) {
                Arrays.fill(this.c, -1);
            }
            this.d = 0;
        }
    }

    static class b {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        b() {
        }

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    ao() {
    }

    public void add(RecyclerView recyclerView) {
        this.b.add(recyclerView);
    }

    public void remove(RecyclerView recyclerView) {
        this.b.remove(recyclerView);
    }

    void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.c == 0) {
            this.c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.A.a(i, i2);
    }

    private void a() {
        int size = this.b.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            RecyclerView recyclerView = (RecyclerView) this.b.get(i);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.A.a(recyclerView, false);
                i3 = recyclerView.A.d + i2;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        this.f.ensureCapacity(i2);
        boolean z = false;
        for (int i4 = 0; i4 < size; i4++) {
            recyclerView = (RecyclerView) this.b.get(i4);
            if (recyclerView.getWindowVisibility() == 0) {
                a aVar = recyclerView.A;
                int abs = Math.abs(aVar.a) + Math.abs(aVar.b);
                boolean z2 = z;
                for (i = 0; i < aVar.d * 2; i += 2) {
                    b bVar;
                    boolean z3;
                    if (z2 >= this.f.size()) {
                        bVar = new b();
                        this.f.add(bVar);
                    } else {
                        bVar = (b) this.f.get(z2);
                    }
                    int i5 = aVar.c[i + 1];
                    if (i5 <= abs) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    bVar.immediate = z3;
                    bVar.viewVelocity = abs;
                    bVar.distanceToItem = i5;
                    bVar.view = recyclerView;
                    bVar.position = aVar.c[i];
                    z2++;
                }
                z = z2;
            }
        }
        Collections.sort(this.f, e);
    }

    static boolean a(RecyclerView recyclerView, int i) {
        int c = recyclerView.f.c();
        for (int i2 = 0; i2 < c; i2++) {
            ViewHolder b = RecyclerView.b(recyclerView.f.d(i2));
            if (b.b == i && !b.j()) {
                return true;
            }
        }
        return false;
    }

    private ViewHolder a(RecyclerView recyclerView, int i, long j) {
        if (a(recyclerView, i)) {
            return null;
        }
        Recycler recycler = recyclerView.d;
        try {
            recyclerView.k();
            ViewHolder a = recycler.a(i, false, j);
            if (a != null) {
                if (!a.l() || a.j()) {
                    recycler.a(a, false);
                } else {
                    recycler.recycleView(a.itemView);
                }
            }
            recyclerView.b(false);
            return a;
        } catch (Throwable th) {
            recyclerView.b(false);
        }
    }

    private void a(@Nullable RecyclerView recyclerView, long j) {
        if (recyclerView != null) {
            if (recyclerView.w && recyclerView.f.c() != 0) {
                recyclerView.c();
            }
            a aVar = recyclerView.A;
            aVar.a(recyclerView, true);
            if (aVar.d != 0) {
                try {
                    TraceCompat.beginSection("RV Nested Prefetch");
                    recyclerView.B.a(recyclerView.l);
                    for (int i = 0; i < aVar.d * 2; i += 2) {
                        a(recyclerView, aVar.c[i], j);
                    }
                } finally {
                    TraceCompat.endSection();
                }
            }
        }
    }

    private void a(b bVar, long j) {
        ViewHolder a = a(bVar.view, bVar.position, bVar.immediate ? Long.MAX_VALUE : j);
        if (a != null && a.a != null && a.l() && !a.j()) {
            a((RecyclerView) a.a.get(), j);
        }
    }

    private void b(long j) {
        int i = 0;
        while (i < this.f.size()) {
            b bVar = (b) this.f.get(i);
            if (bVar.view != null) {
                a(bVar, j);
                bVar.clear();
                i++;
            } else {
                return;
            }
        }
    }

    void a(long j) {
        a();
        b(j);
    }

    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.b.isEmpty()) {
                int size = this.b.size();
                int i = 0;
                long j = 0;
                while (i < size) {
                    long max;
                    RecyclerView recyclerView = (RecyclerView) this.b.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        max = Math.max(recyclerView.getDrawingTime(), j);
                    } else {
                        max = j;
                    }
                    i++;
                    j = max;
                }
                if (j == 0) {
                    this.c = 0;
                    TraceCompat.endSection();
                    return;
                }
                a(TimeUnit.MILLISECONDS.toNanos(j) + this.d);
                this.c = 0;
                TraceCompat.endSection();
            }
        } finally {
            this.c = 0;
            TraceCompat.endSection();
        }
    }
}
