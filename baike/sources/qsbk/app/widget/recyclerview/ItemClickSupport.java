package qsbk.app.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import qsbk.app.R;

public class ItemClickSupport {
    private final RecyclerView a;
    private OnItemClickListener b;
    private OnItemLongClickListener c;
    private OnClickListener d = new a(this);
    private OnLongClickListener e = new b(this);
    private OnChildAttachStateChangeListener f = new c(this);

    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int i, View view);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int i, View view);
    }

    private ItemClickSupport(RecyclerView recyclerView) {
        this.a = recyclerView;
        this.a.setTag(R.id.item_click_support, this);
        this.a.addOnChildAttachStateChangeListener(this.f);
    }

    public static ItemClickSupport addTo(RecyclerView recyclerView) {
        ItemClickSupport itemClickSupport = (ItemClickSupport) recyclerView.getTag(R.id.item_click_support);
        if (itemClickSupport == null) {
            return new ItemClickSupport(recyclerView);
        }
        return itemClickSupport;
    }

    public static ItemClickSupport removeFrom(RecyclerView recyclerView) {
        ItemClickSupport itemClickSupport = (ItemClickSupport) recyclerView.getTag(R.id.item_click_support);
        if (itemClickSupport != null) {
            itemClickSupport.a(recyclerView);
        }
        return itemClickSupport;
    }

    public ItemClickSupport setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
        return this;
    }

    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.c = onItemLongClickListener;
        return this;
    }

    private void a(RecyclerView recyclerView) {
        recyclerView.removeOnChildAttachStateChangeListener(this.f);
        recyclerView.setTag(R.id.item_click_support, null);
    }
}
