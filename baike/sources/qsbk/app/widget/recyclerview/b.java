package qsbk.app.widget.recyclerview;

import android.view.View;
import android.view.View.OnLongClickListener;

class b implements OnLongClickListener {
    final /* synthetic */ ItemClickSupport a;

    b(ItemClickSupport itemClickSupport) {
        this.a = itemClickSupport;
    }

    public boolean onLongClick(View view) {
        if (this.a.c == null) {
            return false;
        }
        return this.a.c.onItemLongClicked(this.a.a, this.a.a.getChildViewHolder(view).getAdapterPosition(), view);
    }
}
