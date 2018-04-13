package com.c;

import android.view.View;
import android.view.View.OnClickListener;

final class c implements OnClickListener {
    final /* synthetic */ com.c.a.c a;
    final /* synthetic */ a b;

    c(a aVar, com.c.a.c cVar) {
        this.b = aVar;
        this.a = cVar;
    }

    public final void onClick(View view) {
        if (this.b.mOnItemClickListener != null) {
            this.b.mOnItemClickListener.a(view, this.a, this.a.getAdapterPosition());
        }
    }
}
