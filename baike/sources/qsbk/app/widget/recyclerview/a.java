package qsbk.app.widget.recyclerview;

import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ ItemClickSupport a;

    a(ItemClickSupport itemClickSupport) {
        this.a = itemClickSupport;
    }

    public void onClick(View view) {
        if (this.a.b != null) {
            this.a.b.onItemClicked(this.a.a, this.a.a.getChildViewHolder(view).getAdapterPosition(), view);
        }
    }
}
