package cn.xiaochuankeji.tieba.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class c<T> extends Adapter {
    protected Context a;
    protected LayoutInflater b;
    protected List<T> c = new ArrayList();
    private List<View> d;
    private Map<Integer, View> e;
    private Map<View, Integer> f;
    private int g;
    private int h;
    private int i;

    public class a extends ViewHolder {
        final /* synthetic */ c a;

        public a(c cVar, View view) {
            this.a = cVar;
            super(view);
        }
    }

    public abstract int a(int i);

    public abstract a a(ViewGroup viewGroup, int i);

    public abstract void a(ViewHolder viewHolder, int i);

    public c(Context context) {
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        this.d = new ArrayList();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = Integer.MIN_VALUE;
        this.h = this.g + 100;
        this.i = this.g;
    }

    public final void a(View view) {
        if (view == null) {
            throw new RuntimeException("headerView cannot be null");
        } else if (!this.d.contains(view)) {
            this.d.add(view);
            this.i++;
            this.e.put(Integer.valueOf(this.i), view);
            this.f.put(view, Integer.valueOf(this.i));
            notifyDataSetChanged();
        }
    }

    public int a() {
        return this.d.size();
    }

    private boolean b(int i) {
        return i >= this.g && i <= this.h;
    }

    public void a(List<T> list) {
        if (!(list == null || list.isEmpty())) {
            this.c.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void b(List<T> list) {
        this.c.clear();
        if (!(list == null || list.isEmpty())) {
            this.c.addAll(list);
        }
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (b(i)) {
            return b(viewGroup, i);
        }
        return a(viewGroup, i);
    }

    public final void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (getItemViewType(i) >= 0) {
            a(viewHolder, i - a());
        }
    }

    private ViewHolder b(ViewGroup viewGroup, int i) {
        return new a(this, (View) this.e.get(Integer.valueOf(i)));
    }

    public final int getItemViewType(int i) {
        if (i < a()) {
            return ((Integer) this.f.get(this.d.get(i))).intValue();
        }
        return a(i - a());
    }

    public int getItemCount() {
        return a() + this.c.size();
    }
}
