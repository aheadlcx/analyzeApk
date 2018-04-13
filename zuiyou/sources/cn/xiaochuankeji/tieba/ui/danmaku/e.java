package cn.xiaochuankeji.tieba.ui.danmaku;

import android.util.Pair;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.background.danmaku.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class e implements cn.xiaochuankeji.tieba.background.danmaku.b.a {
    private final b a;
    private final ArrayList<Integer> b = new ArrayList();
    private int c;
    private int d;
    private int e;
    private boolean f = true;
    private boolean g;
    private int h;
    private a i;

    public interface a {
        void a(ArrayList<DanmakuItem> arrayList);

        void b(ArrayList<DanmakuItem> arrayList);
    }

    public e(long j, long j2, boolean z) {
        this.a = new b(j, j2, z);
        this.a.a((cn.xiaochuankeji.tieba.background.danmaku.b.a) this);
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a() {
        this.a.b();
    }

    public void a(int i) {
        if (this.f) {
            this.c = i;
            if (!b() && !this.a.a() && this.h < 3) {
                if (!b(i) && !c(i)) {
                    d(i);
                } else if (i + 5000 > this.e) {
                    d(this.e);
                }
            }
        }
    }

    private boolean b() {
        if (!this.g && this.b.size() == 2 && ((Integer) this.b.get(0)).intValue() == 0 && ((Integer) this.b.get(1)).intValue() == Integer.MAX_VALUE) {
            this.g = true;
        }
        return this.g;
    }

    private boolean b(int i) {
        return i >= this.d && i < this.e;
    }

    private boolean c(int i) {
        int i2 = 0;
        while (i2 < this.b.size()) {
            int intValue = ((Integer) this.b.get(i2)).intValue();
            int intValue2 = ((Integer) this.b.get(i2 + 1)).intValue();
            if (i < intValue || i >= intValue2) {
                i2 += 2;
            } else {
                this.d = intValue;
                this.e = intValue2;
                return true;
            }
        }
        return false;
    }

    private void d(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.b.size(); i3 += 2) {
            int intValue = ((Integer) this.b.get(i3)).intValue();
            if (i < intValue) {
                i2 = intValue;
                break;
            }
        }
        this.a.a(i, i2);
    }

    public void a(Pair<Integer, Integer> pair, ArrayList<DanmakuItem> arrayList, ArrayList<DanmakuItem> arrayList2) {
        int i = 0;
        this.b.add(pair.first);
        this.b.add(pair.second);
        Collections.sort(this.b);
        ArrayList arrayList3 = new ArrayList();
        int i2 = -1;
        for (int i3 = 0; i3 < this.b.size(); i3 += 2) {
            if (((Integer) this.b.get(i3)).intValue() == i2) {
                arrayList3.add(Integer.valueOf(i3 - 1));
                arrayList3.add(Integer.valueOf(i3));
            }
            i2 = ((Integer) this.b.get(i3 + 1)).intValue();
        }
        if (!arrayList3.isEmpty()) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                it.next();
                if (arrayList3.contains(Integer.valueOf(i))) {
                    it.remove();
                }
                i++;
            }
        }
        c(this.c);
        if (this.i != null) {
            this.i.a(arrayList);
            this.i.b(arrayList2);
        }
    }

    public void a(int i, int i2, String str) {
        this.h++;
    }
}
