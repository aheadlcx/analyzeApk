package com.lnyp.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

public class a extends Adapter<ViewHolder> {
    private Adapter<ViewHolder> a;
    private ArrayList<View> b = new ArrayList();
    private ArrayList<View> c = new ArrayList();
    private AdapterDataObserver d = new AdapterDataObserver(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onChanged() {
            super.onChanged();
            this.a.notifyDataSetChanged();
        }

        public void onItemRangeChanged(int i, int i2) {
            super.onItemRangeChanged(i, i2);
            this.a.notifyItemRangeChanged(this.a.c() + i, i2);
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            this.a.notifyItemRangeInserted(this.a.c() + i, i2);
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            this.a.notifyItemRangeRemoved(this.a.c() + i, i2);
        }

        public void onItemRangeMoved(int i, int i2, int i3) {
            super.onItemRangeMoved(i, i2, i3);
            int c = this.a.c();
            this.a.notifyItemRangeChanged(i + c, (c + i2) + i3);
        }
    };

    public static class a extends ViewHolder {
        public a(View view) {
            super(view);
        }
    }

    public a(Adapter adapter) {
        a(adapter);
    }

    public void a(Adapter<ViewHolder> adapter) {
        if (this.a != null) {
            notifyItemRangeRemoved(c(), this.a.getItemCount());
            this.a.unregisterAdapterDataObserver(this.d);
        }
        this.a = adapter;
        this.a.registerAdapterDataObserver(this.d);
        notifyItemRangeInserted(c(), this.a.getItemCount());
    }

    public Adapter a() {
        return this.a;
    }

    public void a(View view) {
        if (view == null) {
            throw new RuntimeException("header is null");
        }
        this.b.add(view);
        notifyDataSetChanged();
    }

    public void b(View view) {
        if (view == null) {
            throw new RuntimeException("footer is null");
        }
        this.c.add(view);
        notifyDataSetChanged();
    }

    public View b() {
        return d() > 0 ? (View) this.c.get(0) : null;
    }

    public void c(View view) {
        this.b.remove(view);
        notifyDataSetChanged();
    }

    public int c() {
        return this.b.size();
    }

    public int d() {
        return this.c.size();
    }

    public boolean a(int i) {
        return c() > 0 && i == 0;
    }

    public boolean b(int i) {
        return d() > 0 && i == getItemCount() - 1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i < c() - Integer.MIN_VALUE) {
            return new a((View) this.b.get(i - Integer.MIN_VALUE));
        }
        if (i < -2147483647 || i >= 1073741823) {
            return this.a.onCreateViewHolder(viewGroup, i - 1073741823);
        }
        return new a((View) this.c.get(i - -2147483647));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int c = c();
        if (i < c || i >= this.a.getItemCount() + c) {
            LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                return;
            }
            return;
        }
        this.a.onBindViewHolder(viewHolder, i - c);
    }

    public int getItemCount() {
        return (c() + d()) + this.a.getItemCount();
    }

    public int getItemViewType(int i) {
        int itemCount = this.a.getItemCount();
        int c = c();
        if (i < c) {
            return Integer.MIN_VALUE + i;
        }
        if (c > i || i >= c + itemCount) {
            return ((-2147483647 + i) - c) - itemCount;
        }
        itemCount = this.a.getItemViewType(i - c);
        if (itemCount < 1073741823) {
            return itemCount + 1073741823;
        }
        throw new IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        int adapterPosition = viewHolder.getAdapterPosition();
        if (!a(adapterPosition) && !b(adapterPosition)) {
            this.a.onViewAttachedToWindow(viewHolder);
        }
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        int adapterPosition = viewHolder.getAdapterPosition();
        if (!a(adapterPosition) && !b(adapterPosition)) {
            this.a.onViewDetachedFromWindow(viewHolder);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.a.onAttachedToRecyclerView(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.a.onDetachedFromRecyclerView(recyclerView);
    }
}
