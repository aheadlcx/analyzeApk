package com.spriteapp.booklibrary.recyclerView.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;

public abstract class BaseViewHolder<T> extends ViewHolder {
    private View mItemView;
    private SparseArray<View> views = new SparseArray();

    public abstract void bindViewData(T t);

    protected BaseViewHolder(View view) {
        super(view);
        this.mItemView = view;
    }

    public View getViews(int i) {
        View view = (View) this.views.get(i);
        if (view != null) {
            return view;
        }
        view = this.mItemView;
        this.views.put(i, this.mItemView);
        return view;
    }
}
