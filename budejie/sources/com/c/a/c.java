package com.c.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class c extends ViewHolder {
    private SparseArray<View> a = new SparseArray();
    private View b;
    private Context c;

    public c(Context context, View view) {
        super(view);
        this.c = context;
        this.b = view;
    }

    public static c a(Context context, View view) {
        return new c(context, view);
    }

    public static c a(Context context, ViewGroup viewGroup, int i) {
        return new c(context, LayoutInflater.from(context).inflate(i, viewGroup, false));
    }

    public <T extends View> T a(int i) {
        View view = (View) this.a.get(i);
        if (view != null) {
            return view;
        }
        T findViewById = this.b.findViewById(i);
        this.a.put(i, findViewById);
        return findViewById;
    }

    public View a() {
        return this.b;
    }

    public c a(int i, String str) {
        ((TextView) a(i)).setText(str);
        return this;
    }
}
