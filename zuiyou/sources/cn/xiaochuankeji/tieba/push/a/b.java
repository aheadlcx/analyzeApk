package cn.xiaochuankeji.tieba.push.a;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil.Callback;
import cn.xiaochuankeji.tieba.push.data.c;
import java.util.List;

public class b extends Callback {
    private List<c> a;
    private List<c> b;

    public b(@NonNull List<c> list, @NonNull List<c> list2) {
        this.a = list;
        this.b = list2;
    }

    public int getOldListSize() {
        return this.a.size();
    }

    public int getNewListSize() {
        return this.b.size();
    }

    public boolean areItemsTheSame(int i, int i2) {
        c cVar = (c) this.a.get(i);
        c cVar2 = (c) this.b.get(i2);
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return cVar.a == cVar2.a && cVar.j == cVar2.j;
    }

    public boolean areContentsTheSame(int i, int i2) {
        c cVar = (c) this.a.get(i);
        c cVar2 = (c) this.b.get(i2);
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return cVar.m == cVar2.m && cVar.c == cVar2.c && cVar.d == cVar2.d && cVar.e == cVar2.e && cVar.f == cVar2.f && cVar.g == cVar2.g && cVar.h == cVar2.h && cVar.b == cVar2.b && cVar.A == cVar2.A && cVar.i == cVar2.i && cVar.l == cVar2.l && cVar.s == cVar2.s && cVar.t == cVar2.t && cVar.u == cVar2.u && cVar.v == cVar2.v && cVar.w == cVar2.w && cVar.x == cVar2.x && String.valueOf(cVar.q).equals(cVar2.q) && String.valueOf(cVar.r).equals(cVar2.r) && String.valueOf(cVar.z).equals(cVar2.z);
    }
}
