package android.support.v7.util;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiffUtil {
    private static final Comparator<c> a = new c();

    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int i, int i2);

        public abstract boolean areItemsTheSame(int i, int i2);

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        @Nullable
        public Object getChangePayload(int i, int i2) {
            return null;
        }
    }

    public static class DiffResult {
        private final List<c> a;
        private final int[] b;
        private final int[] c;
        private final Callback d;
        private final int e;
        private final int f;
        private final boolean g;

        DiffResult(Callback callback, List<c> list, int[] iArr, int[] iArr2, boolean z) {
            this.a = list;
            this.b = iArr;
            this.c = iArr2;
            Arrays.fill(this.b, 0);
            Arrays.fill(this.c, 0);
            this.d = callback;
            this.e = callback.getOldListSize();
            this.f = callback.getNewListSize();
            this.g = z;
            a();
            b();
        }

        private void a() {
            c cVar = this.a.isEmpty() ? null : (c) this.a.get(0);
            if (cVar == null || cVar.a != 0 || cVar.b != 0) {
                cVar = new c();
                cVar.a = 0;
                cVar.b = 0;
                cVar.d = false;
                cVar.c = 0;
                cVar.e = false;
                this.a.add(0, cVar);
            }
        }

        private void b() {
            int i = this.e;
            int i2 = this.f;
            for (int size = this.a.size() - 1; size >= 0; size--) {
                c cVar = (c) this.a.get(size);
                int i3 = cVar.a + cVar.c;
                int i4 = cVar.b + cVar.c;
                if (this.g) {
                    while (i > i3) {
                        a(i, i2, size);
                        i--;
                    }
                    while (i2 > i4) {
                        b(i, i2, size);
                        i2--;
                    }
                }
                for (i2 = 0; i2 < cVar.c; i2++) {
                    i3 = cVar.a + i2;
                    i4 = cVar.b + i2;
                    i = this.d.areContentsTheSame(i3, i4) ? 1 : 2;
                    this.b[i3] = (i4 << 5) | i;
                    this.c[i4] = i | (i3 << 5);
                }
                i = cVar.a;
                i2 = cVar.b;
            }
        }

        private void a(int i, int i2, int i3) {
            if (this.b[i - 1] == 0) {
                a(i, i2, i3, false);
            }
        }

        private void b(int i, int i2, int i3) {
            if (this.c[i2 - 1] == 0) {
                a(i, i2, i3, true);
            }
        }

        private boolean a(int i, int i2, int i3, boolean z) {
            int i4;
            int i5;
            int i6 = 8;
            if (z) {
                i4 = i2 - 1;
                i2--;
                i5 = i4;
                i4 = i;
            } else {
                i5 = i - 1;
                i4 = i - 1;
            }
            int i7 = i4;
            while (i3 >= 0) {
                c cVar = (c) this.a.get(i3);
                int i8 = cVar.a + cVar.c;
                int i9 = cVar.b + cVar.c;
                if (z) {
                    for (i7--; i7 >= i8; i7--) {
                        if (this.d.areItemsTheSame(i7, i5)) {
                            i4 = this.d.areContentsTheSame(i7, i5) ? 8 : 4;
                            this.c[i5] = (i7 << 5) | 16;
                            this.b[i7] = i4 | (i5 << 5);
                            return true;
                        }
                    }
                    continue;
                } else {
                    for (i7 = i2 - 1; i7 >= i9; i7--) {
                        if (this.d.areItemsTheSame(i5, i7)) {
                            if (!this.d.areContentsTheSame(i5, i7)) {
                                i6 = 4;
                            }
                            this.b[i - 1] = (i7 << 5) | 16;
                            this.c[i7] = ((i - 1) << 5) | i6;
                            return true;
                        }
                    }
                    continue;
                }
                i7 = cVar.a;
                i2 = cVar.b;
                i3--;
            }
            return false;
        }

        public void dispatchUpdatesTo(Adapter adapter) {
            dispatchUpdatesTo(new d(this, adapter));
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            List arrayList = new ArrayList();
            int i = this.e;
            int size = this.a.size() - 1;
            int i2 = this.f;
            while (size >= 0) {
                c cVar = (c) this.a.get(size);
                int i3 = cVar.c;
                int i4 = cVar.a + i3;
                int i5 = cVar.b + i3;
                if (i4 < i) {
                    b(arrayList, batchingListUpdateCallback, i4, i - i4, i4);
                }
                if (i5 < i2) {
                    a(arrayList, batchingListUpdateCallback, i4, i2 - i5, i5);
                }
                for (int i6 = i3 - 1; i6 >= 0; i6--) {
                    if ((this.b[cVar.a + i6] & 31) == 2) {
                        batchingListUpdateCallback.onChanged(cVar.a + i6, 1, this.d.getChangePayload(cVar.a + i6, cVar.b + i6));
                    }
                }
                i = cVar.a;
                size--;
                i2 = cVar.b;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        private static a a(List<a> list, int i, boolean z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                a aVar = (a) list.get(size);
                if (aVar.a == i && aVar.c == z) {
                    list.remove(size);
                    for (int i2 = size; i2 < list.size(); i2++) {
                        a aVar2 = (a) list.get(i2);
                        aVar2.b = (z ? 1 : -1) + aVar2.b;
                    }
                    return aVar;
                }
            }
            return null;
        }

        private void a(List<a> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (this.g) {
                for (int i4 = i2 - 1; i4 >= 0; i4--) {
                    int i5 = this.c[i3 + i4] & 31;
                    switch (i5) {
                        case 0:
                            listUpdateCallback.onInserted(i, 1);
                            for (a aVar : list) {
                                aVar.b++;
                            }
                            break;
                        case 4:
                        case 8:
                            int i6 = this.c[i3 + i4] >> 5;
                            listUpdateCallback.onMoved(a((List) list, i6, true).b, i);
                            if (i5 != 4) {
                                break;
                            }
                            listUpdateCallback.onChanged(i, 1, this.d.getChangePayload(i6, i3 + i4));
                            break;
                        case 16:
                            list.add(new a(i3 + i4, i, false));
                            break;
                        default:
                            throw new IllegalStateException("unknown flag for pos " + (i4 + i3) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Long.toBinaryString((long) i5));
                    }
                }
                return;
            }
            listUpdateCallback.onInserted(i, i2);
        }

        private void b(List<a> list, ListUpdateCallback listUpdateCallback, int i, int i2, int i3) {
            if (this.g) {
                for (int i4 = i2 - 1; i4 >= 0; i4--) {
                    int i5 = this.b[i3 + i4] & 31;
                    switch (i5) {
                        case 0:
                            listUpdateCallback.onRemoved(i + i4, 1);
                            for (a aVar : list) {
                                aVar.b--;
                            }
                            break;
                        case 4:
                        case 8:
                            int i6 = this.b[i3 + i4] >> 5;
                            a a = a((List) list, i6, false);
                            listUpdateCallback.onMoved(i + i4, a.b - 1);
                            if (i5 != 4) {
                                break;
                            }
                            listUpdateCallback.onChanged(a.b - 1, 1, this.d.getChangePayload(i3 + i4, i6));
                            break;
                        case 16:
                            list.add(new a(i3 + i4, i + i4, true));
                            break;
                        default:
                            throw new IllegalStateException("unknown flag for pos " + (i4 + i3) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Long.toBinaryString((long) i5));
                    }
                }
                return;
            }
            listUpdateCallback.onRemoved(i, i2);
        }
    }

    private static class a {
        int a;
        int b;
        boolean c;

        public a(int i, int i2, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = z;
        }
    }

    static class b {
        int a;
        int b;
        int c;
        int d;

        public b(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    static class c {
        int a;
        int b;
        int c;
        boolean d;
        boolean e;

        c() {
        }
    }

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback callback) {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList2.add(new b(0, oldListSize, 0, newListSize));
        int abs = (oldListSize + newListSize) + Math.abs(oldListSize - newListSize);
        int[] iArr = new int[(abs * 2)];
        int[] iArr2 = new int[(abs * 2)];
        List arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            b bVar = (b) arrayList2.remove(arrayList2.size() - 1);
            c a = a(callback, bVar.a, bVar.b, bVar.c, bVar.d, iArr, iArr2, abs);
            if (a != null) {
                if (a.c > 0) {
                    arrayList.add(a);
                }
                a.a += bVar.a;
                a.b += bVar.c;
                b bVar2 = arrayList3.isEmpty() ? new b() : (b) arrayList3.remove(arrayList3.size() - 1);
                bVar2.a = bVar.a;
                bVar2.c = bVar.c;
                if (a.e) {
                    bVar2.b = a.a;
                    bVar2.d = a.b;
                } else if (a.d) {
                    bVar2.b = a.a - 1;
                    bVar2.d = a.b;
                } else {
                    bVar2.b = a.a;
                    bVar2.d = a.b - 1;
                }
                arrayList2.add(bVar2);
                if (!a.e) {
                    bVar.a = a.a + a.c;
                    bVar.c = a.b + a.c;
                } else if (a.d) {
                    bVar.a = (a.a + a.c) + 1;
                    bVar.c = a.b + a.c;
                } else {
                    bVar.a = a.a + a.c;
                    bVar.c = (a.b + a.c) + 1;
                }
                arrayList2.add(bVar);
            } else {
                arrayList3.add(bVar);
            }
        }
        Collections.sort(arrayList, a);
        return new DiffResult(callback, arrayList, iArr, iArr2, z);
    }

    private static c a(Callback callback, int i, int i2, int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i2 - i < 1 || i4 - i3 < 1) {
            return null;
        }
        int i8 = i6 - i7;
        int i9 = ((i6 + i7) + 1) / 2;
        Arrays.fill(iArr, (i5 - i9) - 1, (i5 + i9) + 1, 0);
        Arrays.fill(iArr2, ((i5 - i9) - 1) + i8, ((i5 + i9) + 1) + i8, i6);
        Object obj = i8 % 2 != 0 ? 1 : null;
        int i10 = 0;
        while (i10 <= i9) {
            int i11;
            int i12 = -i10;
            while (i12 <= i10) {
                boolean z;
                if (i12 == (-i10) || (i12 != i10 && iArr[(i5 + i12) - 1] < iArr[(i5 + i12) + 1])) {
                    i11 = iArr[(i5 + i12) + 1];
                    z = false;
                } else {
                    i11 = iArr[(i5 + i12) - 1] + 1;
                    z = true;
                }
                int i13 = i11;
                i11 -= i12;
                while (i13 < i6 && i11 < i7 && callback.areItemsTheSame(i + i13, i3 + i11)) {
                    i13++;
                    i11++;
                }
                iArr[i5 + i12] = i13;
                if (obj == null || i12 < (i8 - i10) + 1 || i12 > (i8 + i10) - 1 || iArr[i5 + i12] < iArr2[i5 + i12]) {
                    i12 += 2;
                } else {
                    c cVar = new c();
                    cVar.a = iArr2[i5 + i12];
                    cVar.b = cVar.a - i12;
                    cVar.c = iArr[i5 + i12] - iArr2[i5 + i12];
                    cVar.d = z;
                    cVar.e = false;
                    return cVar;
                }
            }
            i12 = -i10;
            while (i12 <= i10) {
                int i14 = i12 + i8;
                if (i14 == i10 + i8 || (i14 != (-i10) + i8 && iArr2[(i5 + i14) - 1] < iArr2[(i5 + i14) + 1])) {
                    i11 = iArr2[(i5 + i14) - 1];
                    z = false;
                } else {
                    i11 = iArr2[(i5 + i14) + 1] - 1;
                    z = true;
                }
                i13 = i11;
                i11 -= i14;
                while (i13 > 0 && i11 > 0 && callback.areItemsTheSame((i + i13) - 1, (i3 + i11) - 1)) {
                    i13--;
                    i11--;
                }
                iArr2[i5 + i14] = i13;
                if (obj != null || i12 + i8 < (-i10) || i12 + i8 > i10 || iArr[i5 + i14] < iArr2[i5 + i14]) {
                    i12 += 2;
                } else {
                    cVar = new c();
                    cVar.a = iArr2[i5 + i14];
                    cVar.b = cVar.a - i14;
                    cVar.c = iArr[i5 + i14] - iArr2[i5 + i14];
                    cVar.d = z;
                    cVar.e = true;
                    return cVar;
                }
            }
            i10++;
        }
        throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }
}
