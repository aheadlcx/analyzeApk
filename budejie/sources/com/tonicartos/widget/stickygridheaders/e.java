package com.tonicartos.widget.stickygridheaders;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class e extends BaseAdapter implements a {
    private d a;
    private b[] b;

    private final class a extends DataSetObserver {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public final void onChanged() {
            this.a.b = this.a.a(this.a.a);
            this.a.notifyDataSetChanged();
        }

        public final void onInvalidated() {
            this.a.b = this.a.a(this.a.a);
            this.a.notifyDataSetInvalidated();
        }
    }

    private class b {
        final /* synthetic */ e a;
        private int b = 0;
        private int c;

        public b(e eVar, int i) {
            this.a = eVar;
            this.c = i;
        }

        public final int a() {
            return this.b;
        }

        public final int b() {
            return this.c;
        }

        public final void c() {
            this.b++;
        }
    }

    public e(d dVar) {
        this.a = dVar;
        dVar.registerDataSetObserver(new a());
        this.b = a(dVar);
    }

    public int getCount() {
        return this.a.getCount();
    }

    public int a(int i) {
        return this.b[i].a();
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        return this.a.a(this.b[i].b(), view, viewGroup);
    }

    public Object getItem(int i) {
        return this.a.getItem(i);
    }

    public long getItemId(int i) {
        return this.a.getItemId(i);
    }

    public int getItemViewType(int i) {
        return this.a.getItemViewType(i);
    }

    public int a() {
        return this.b.length;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.a.getView(i, view, viewGroup);
    }

    public int getViewTypeCount() {
        return this.a.getViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.a.hasStableIds();
    }

    protected b[] a(d dVar) {
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (int i = 0; i < dVar.getCount(); i++) {
            long a = dVar.a(i);
            b bVar = (b) hashMap.get(Long.valueOf(a));
            if (bVar == null) {
                bVar = new b(this, i);
                arrayList.add(bVar);
            }
            bVar.c();
            hashMap.put(Long.valueOf(a), bVar);
        }
        return (b[]) arrayList.toArray(new b[arrayList.size()]);
    }
}
