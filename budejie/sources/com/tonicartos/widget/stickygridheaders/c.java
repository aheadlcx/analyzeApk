package com.tonicartos.widget.stickygridheaders;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class c extends BaseAdapter implements a {
    private DataSetObserver a = new k(this);
    private ListAdapter b;

    public c(ListAdapter listAdapter) {
        this.b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.a);
        }
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getCount();
    }

    public int a(int i) {
        return 0;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public Object getItem(int i) {
        if (this.b == null) {
            return null;
        }
        return this.b.getItem(i);
    }

    public long getItemId(int i) {
        return this.b.getItemId(i);
    }

    public int getItemViewType(int i) {
        return this.b.getItemViewType(i);
    }

    public int a() {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.b.getView(i, view, viewGroup);
    }

    public int getViewTypeCount() {
        return this.b.getViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.b.hasStableIds();
    }
}
