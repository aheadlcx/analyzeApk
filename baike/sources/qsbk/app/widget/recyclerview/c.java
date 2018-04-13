package qsbk.app.widget.recyclerview;

import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.view.View;

class c implements OnChildAttachStateChangeListener {
    final /* synthetic */ ItemClickSupport a;

    c(ItemClickSupport itemClickSupport) {
        this.a = itemClickSupport;
    }

    public void onChildViewAttachedToWindow(View view) {
        if (this.a.b != null) {
            view.setOnClickListener(this.a.d);
        }
        if (this.a.c != null) {
            view.setOnLongClickListener(this.a.e);
        }
    }

    public void onChildViewDetachedFromWindow(View view) {
    }
}
