package com.c;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.c.a.c;

final class d implements OnLongClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ a b;

    d(a aVar, c cVar) {
        this.b = aVar;
        this.a = cVar;
    }

    public final boolean onLongClick(View view) {
        if (this.b.mOnItemClickListener == null) {
            return false;
        }
        return this.b.mOnItemClickListener.b(view, this.a, this.a.getAdapterPosition());
    }
}
