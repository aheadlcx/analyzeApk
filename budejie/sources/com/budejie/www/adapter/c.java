package com.budejie.www.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class c<T> extends BaseAdapter {
    protected final List<d> d = new ArrayList();

    protected abstract d a(T t, int i);

    protected c() {
    }

    public void a(List<T> list) {
        this.d.clear();
        for (int i = 0; i < list.size(); i++) {
            d a = a(list.get(i), i);
            if (a != null) {
                this.d.add(a);
            }
        }
        notifyDataSetChanged();
    }

    public void b() {
        this.d.clear();
        notifyDataSetChanged();
    }

    public void b(List<T> list) {
        int size = this.d.size();
        for (int i = 0; i < list.size(); i++) {
            d a = a(list.get(i), size + i);
            if (a != null) {
                this.d.add(a);
            }
        }
        notifyDataSetChanged();
    }

    public int getViewTypeCount() {
        return RowType.values().length;
    }

    public int getItemViewType(int i) {
        return ((d) this.d.get(i)).c();
    }

    public int getCount() {
        return this.d.size();
    }

    public T getItem(int i) {
        if (i < this.d.size()) {
            return ((d) this.d.get(i)).d();
        }
        return ((d) this.d.get(this.d.size() - 1)).d();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return ((d) this.d.get(i)).a(view);
    }
}
